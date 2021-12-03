package tfc.tomolist.controllers.app;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tfc.tomolist.model.AmigoVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.model.pagination.Paged;
import tfc.tomolist.security.SecurityConfig;
import tfc.tomolist.services.ServiciosAmigo;
import tfc.tomolist.services.ServiciosEntrada;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
@RequestMapping("/app")
public class UsersController {

	@Autowired
	ServiciosUsuario su;

	@Autowired
	ServiciosEntrada se;

	@Autowired
	ServiciosAmigo sa;

	@Autowired
	SecurityConfig config;

	@GetMapping("/home")
	public String homeUsu(Model m,
			@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(name = "size", required = false, defaultValue = "6") int size) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		UsuarioVO usuario = su.findByUsername(auth.getName()).get();
		Paged<EntradaVO> postsPageados = se.entradasTablon(usuario.getIdusuario(), pageNumber, size).get();
		m.addAttribute("usuario", usuario);

		if (postsPageados.getPage().isEmpty()) {
			m.addAttribute("avisoTablon", "Aún no tienes <strong>TomoPosts</strong> en tu tablón de amigos");
		} else {
			m.addAttribute("posts", postsPageados);
		}
		return "app/home";
	}

	@GetMapping("/perfil")
	public String perfilUsuario(@RequestParam(required = true, name = "id") int id, Model m,
			@RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber,
			@RequestParam(name = "size", required = false, defaultValue = "6") int size) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		String nombre = auth.getName();
		UsuarioVO usuarioPerfil = su.findById(id).get();
		UsuarioVO usuarioSesion = su.findByUsername(nombre).get();
		Paged<EntradaVO> postsPageados = se.entradasPerfil(usuarioPerfil.getIdusuario(), pageNumber, size).get();

		boolean isAmigo = su.getAmigoUsuario(usuarioSesion.getIdusuario(), usuarioPerfil.getIdusuario()).get()
				.isEmpty();
		boolean isSolicitud = su.getSolicitudUsuario(usuarioSesion.getIdusuario(), usuarioPerfil.getIdusuario()).get()
				.isEmpty();

		m.addAttribute("isSolicitud", isSolicitud);
		m.addAttribute("isAmigo", isAmigo);
		m.addAttribute("usuarioSesion", usuarioSesion);
		m.addAttribute("usuarioPerfil", usuarioPerfil);
		m.addAttribute("posts", postsPageados);
		return "app/perfil";
	}

	@PostMapping("/borrarAmistad/{id1}/{id2}")
	public String borrarAmistad(@PathVariable int id1, @PathVariable int id2) {
		su.borrarAmistad(id1, id2);
		return "redirect:/app/perfil?id=" + id2;
	}

	@PostMapping("/newSolicitud/{id1}/{id2}")
	public String solicitudAmistad(@PathVariable int id1, @PathVariable int id2) {
		AmigoVO am = new AmigoVO();
		am.setAceptado(false);
		am.setAmigo1(su.findById(id1).get());
		am.setAmigo2(su.findById(id2).get());
		sa.save(am);
		return "redirect:/app/perfil?id=" + id2;
	}

	@PostMapping("/borrarSolicitud/{id1}/{id2}")
	public String borrarSolicitud(@PathVariable int id1, @PathVariable int id2) {
		su.borrarAmistad(id1, id2);
		return "redirect:/app/perfil?id=" + id2;
	}

	@GetMapping("/updateUsuario")
	public String modificarPerfil(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		m.addAttribute("usuarioNav", su.findByUsername(auth.getName()).get());
		m.addAttribute("usuario", su.findByUsername(auth.getName()).get());
		return "app/updatePerfil";
	}

	@PostMapping("/updatePerfil/submit")
	public String modificarPerfil(@Valid @ModelAttribute("usuario") UsuarioVO usuario, BindingResult br, Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		m.addAttribute("usuarioNav", su.findByUsername(auth.getName()).get());
		if (br.hasErrors()) {
			return "app/updatePerfil";
		} else {
			usuario.setPassword(config.encriptarPassword(usuario.getRawpass()));
			su.save(usuario);
			return "redirect:/app/perfil?id=" + usuario.getIdusuario();
		}

		
	}

}

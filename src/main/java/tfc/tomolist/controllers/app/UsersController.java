package tfc.tomolist.controllers.app;

import java.util.ArrayList;

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
		boolean isSolicitudReceptor = su.getSolicitudUsuario(usuarioPerfil.getIdusuario(), usuarioSesion.getIdusuario()).get()
				.isEmpty();
		int isTablonVacio = (int) postsPageados.getPage().getTotalElements();

		m.addAttribute("isVacio", isTablonVacio);
		m.addAttribute("isSolicitud", isSolicitud);
		m.addAttribute("isSolicitudReceptor", isSolicitudReceptor);
		m.addAttribute("isAmigo", isAmigo);
		m.addAttribute("usuarioSesion", usuarioSesion);
		m.addAttribute("usuarioPerfil", usuarioPerfil);
		m.addAttribute("posts", postsPageados);
		return "app/perfil";
	}

	@PostMapping("/borrarAmistad/{id1}/{id2}")
	public String borrarAmistad(@PathVariable int id1, @PathVariable int id2) {
		su.borrarAmistad(id1, id2);
		return "redirect:/app/perfil?id=" + id1;
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

	@GetMapping("/amigos")
	public String amigosView(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO u = su.findByUsername(auth.getName()).get();
		m.addAttribute("usuario", u);
		m.addAttribute("solicitudes", su.solicitudesOrdenadas(u.getIdusuario()).get());
		boolean isRest = false;
		m.addAttribute("isRest", isRest);
		m.addAttribute("amigos", su.amigosOrdenados(u.getIdusuario()).get());
		return "app/amigos";
	}

	@PostMapping("/crearAmistad/{idA}")
	public String crearAmistad(@PathVariable int idA) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO uR = su.findByUsername(auth.getName()).get();
		UsuarioVO uA = su.findById(idA).get();

		ArrayList<AmigoVO> a = su.getSolicitudUsuario(uA.getIdusuario(), uR.getIdusuario()).get();
		a.get(0).setAceptado(true);
		sa.save(a.get(0));

		return "redirect:/app/amigos";
	}

	@PostMapping("/borrarSolicitud/{idA}")
	public String borrarSolicitud(@PathVariable int idA) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO uR = su.findByUsername(auth.getName()).get();
		UsuarioVO uA = su.findById(idA).get();

		su.borrarAmistad(uA.getIdusuario(), uR.getIdusuario());

		return "redirect:/app/amigos";
	}

	@PostMapping("/borrarAmistad/{idA}")
	public String borrarAmistad(@PathVariable int idA) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO uR = su.findByUsername(auth.getName()).get();
		UsuarioVO uA = su.findById(idA).get();

		su.borrarAmistad(uA.getIdusuario(), uR.getIdusuario());

		return "redirect:/app/amigos";
	}

	@GetMapping("/friendsearch/{user}/{id}")
	public String getAmigosBuscados(@PathVariable("user") String name, @PathVariable("id") int id, Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO u = su.findByUsername(auth.getName()).get();
		m.addAttribute("usuario", u);
		m.addAttribute("solicitudes", su.solicitudesOrdenadas(u.getIdusuario()).get());
		boolean isRest = true;
		m.addAttribute("isRest", isRest);
		m.addAttribute("amigos", su.amigosRestOrdenadas(name, id).get());
		return "app/amigos";

	}
	
	

}

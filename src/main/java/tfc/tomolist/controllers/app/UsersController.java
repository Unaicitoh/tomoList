package tfc.tomolist.controllers.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.model.pagination.Paged;
import tfc.tomolist.services.ServiciosEntrada;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
@RequestMapping("/app")
public class UsersController {

	@Autowired
	ServiciosUsuario su;

	@Autowired
	ServiciosEntrada se;

	
	@GetMapping("/home")
	public String homeUsu(Model m, @RequestParam(name = "pageNumber", required = false, defaultValue = "1") int pageNumber, @RequestParam(name = "size", required = false, defaultValue = "6") int size) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		UsuarioVO usuario=su.findByUsername(auth.getName()).get();
		Paged<EntradaVO> postsPageados= se.entradasTablon(usuario.getIdusuario(), pageNumber, size).get();
		m.addAttribute("usuario",usuario);
		
		
		if(postsPageados.getPage().isEmpty()) {
			m.addAttribute("avisoTablon", "Aún no tienes <strong>TomoPosts</strong> en tu tablón de amigos");
		}else {
			m.addAttribute("posts", postsPageados);
		}
		return "app/home";
	}
	

	@GetMapping("/perfil/{id}")
	public String perfilUsuario(@PathVariable int id, Model m) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		String nombre= auth.getName();
		UsuarioVO usuarioPerfil=su.findById(id).get();
		UsuarioVO usuarioSesion=su.findByUsername(nombre).get();
		
		m.addAttribute("usuarioSesion",usuarioSesion);
		m.addAttribute("usuarioPerfil", usuarioPerfil);
		return "app/perfil";
	}
	
}

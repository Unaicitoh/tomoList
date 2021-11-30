package tfc.tomolist.controllers.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.services.ServiciosEntrada;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
@RequestMapping("/app")
public class PostsController {

	@Autowired
	ServiciosUsuario su;

	@Autowired
	ServiciosEntrada se;

	
	@GetMapping("/comentarios/{idE}")
	public String getComentariosEntrada(@PathVariable int idE, Model m) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		m.addAttribute("usuario", su.findByUsername(auth.getName()).get());
		m.addAttribute("comentarios", se.getComentarios(idE).get());
		m.addAttribute("nuevoComentario", new ComentarioVO());
		return "app/comentarios";
		
	}
	
}

package tfc.tomolist.controllers.app;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.services.ServiciosComentario;
import tfc.tomolist.services.ServiciosEntrada;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
@RequestMapping("/app")
public class PostsController {

	@Autowired
	ServiciosUsuario su;

	@Autowired
	ServiciosEntrada se;

	@Autowired
	ServiciosComentario sc;
	
	@GetMapping("/comentarios/{idE}")
	public String getComentariosEntrada(@PathVariable int idE, Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		m.addAttribute("usuario", su.findByUsername(auth.getName()).get());
		m.addAttribute("post", se.findById(idE).get());
		m.addAttribute("comentarios", se.getComentarios(idE).get());
		m.addAttribute("newComentario", new ComentarioVO());
		return "app/comentarios";
	}

	@PostMapping("newComentario/{idE}")
	public ModelAndView insertarComentario(@PathVariable int idE, @Valid @ModelAttribute("newComentario") ComentarioVO comentario,
			BindingResult br, ModelMap m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		m.addAttribute("usuario", su.findByUsername(auth.getName()).get());
		m.addAttribute("post", se.findById(idE).get());
		m.addAttribute("comentarios", se.getComentarios(idE).get());
		if (br.hasErrors()) {
			return new ModelAndView("app/comentarios", m);
		}else {
			comentario.setFecha(LocalDateTime.now());
			comentario.setUsuario(su.findByUsername(auth.getName()).get());
			comentario.setEntrada(se.findById(idE).get());
			sc.save(comentario);
			m.remove("comentarios");
			m.addAttribute("comentarios", se.getComentarios(idE).get());
			m.addAttribute("newComentario", new ComentarioVO());
			return new ModelAndView("app/comentarios", m);
		}
	}

}

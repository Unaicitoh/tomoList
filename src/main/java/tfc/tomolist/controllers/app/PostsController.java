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
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.UsuarioVO;
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

	@PostMapping("/newComentario/{idE}")
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
	
	@GetMapping("/newPost")
	public String crearEntrada(Model m) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		m.addAttribute("usuario", su.findByUsername(auth.getName()).get());
		m.addAttribute("entrada", new EntradaVO());
		
		return "app/entradaInfo";
	}
	
	@GetMapping("/updatePost/{id}")
	public String updateEntrada(Model m, @PathVariable int id) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		m.addAttribute("usuario", su.findByUsername(auth.getName()).get());
		m.addAttribute("entrada", se.findById(id).get());
		
		return "app/entradaInfo";
	}
	
	@PostMapping("/newPost/submit")
	public String crearEntradaSubmit(@Valid @ModelAttribute("entrada") EntradaVO e, BindingResult br,Model m) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO usuario= su.findByUsername(auth.getName()).get();
		m.addAttribute("usuario", usuario);
		if(br.hasErrors()) {
			return "app/entradaInfo";
		}else {
			e.setFecha(LocalDateTime.now());
			e.setAutor(usuario);
			se.save(e);
			return "redirect:/app/perfil?id="+usuario.getIdusuario();
		}
		
	}
	
	@PostMapping("/borrarPost/{id}")
	public String borrarEntrada(@PathVariable int id) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		int idU=su.findByUsername(auth.getName()).get().getIdusuario();	
		se.delete(se.findById(id).get());
		return "redirect:/app/perfil?id="+idU;
	}
	@PostMapping("/borrarComentario/{id}/{idE}")
	public String borrarComentario(@PathVariable int id, @PathVariable int idE) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		int idU=su.findByUsername(auth.getName()).get().getIdusuario();	
		ComentarioVO com=se.getComentarioUsuario(id, idU).get();
		sc.delete(com);
		return "redirect:/app/comentarios/"+idE;
	}
}

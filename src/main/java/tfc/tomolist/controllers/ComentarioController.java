package tfc.tomolist.controllers;

import java.time.LocalDateTime;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.services.ServiciosComentario;

@Controller
public class ComentarioController {

	@Autowired
	ServiciosComentario sc;

	
	@GetMapping("/admin/comentarios")
	public String allComment(Model m) {
		
		
		m.addAttribute("comentarios", sc.findAll());
		
		return "admin/comentarios/index";
	}
	
	@GetMapping("/admin/comentarios/borrado/{id}")
	public String borradoComment(@PathVariable int id) {
		sc.deleteById(id);
		return "redirect:/admin/comentarios";
	}
	
	@GetMapping("/admin/comentarios/update/{id}")
	public String infoComment(@PathVariable int id, Model m) {
		ComentarioVO com=sc.findById(id).get();
		m.addAttribute("comentario1", com);
		return "admin/comentarios/info";
	}
	
	@GetMapping("/admin/comentarios/new")
	public String infoNewComment( Model m) {

		m.addAttribute("comentario1", new ComentarioVO());
		return "admin/comentarios/infoNew";
	}
	
	@PostMapping("/admin/comentarios/submit")
	public String modificarComment(@Valid @ModelAttribute("comentario1") ComentarioVO comentario, BindingResult br) {
		
		if(br.hasErrors()) {
			return "admin/comentarios/info";
		}else {
			sc.save(comentario);
			return "redirect:/admin/comentarios";
		}
		
	}
	@PostMapping("/admin/comentarios/new/submit")
	public String insertarComment(@Valid @ModelAttribute("comentario1") ComentarioVO comentario, BindingResult br) {
		comentario.setFecha(LocalDateTime.now());
		if(br.hasErrors()) {
			return "admin/comentarios/infoNew";
		}else {
			sc.save(comentario);
			return "redirect:/admin/comentarios";
		}
		
	}
	
}

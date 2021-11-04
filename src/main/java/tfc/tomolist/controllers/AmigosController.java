package tfc.tomolist.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tfc.tomolist.model.AmigoVO;
import tfc.tomolist.services.ServiciosAmigo;

@Controller
public class AmigosController {

	@Autowired
	ServiciosAmigo sa;

	
	
	
	@GetMapping("/admin/amigos")
	public String allAmigos(Model m) {
		
		
		m.addAttribute("amigos", sa.findAll());
		
		return "admin/amigos/index";
	}
	
	@GetMapping("/admin/amigos/borrado/{id}")
	public String borradoAmigos(@PathVariable int id) {
		sa.deleteById(id);
		return "redirect:/admin/amigos";
	}
	
	@GetMapping("/admin/amigos/update/{id}")
	public String infoAmigos(@PathVariable int id, Model m) {
		AmigoVO a=sa.findById(id).get();
		m.addAttribute("amigo", a);
		return "admin/amigos/info";
	}
	
	@GetMapping("/admin/amigos/new")
	public String infoNewAmigos( Model m) {

		m.addAttribute("amigo", new AmigoVO());
		return "admin/amigos/infoNew";
	}
	
	@PostMapping("/admin/amigos/submit")
	public String modificarAmigos(@Valid @ModelAttribute("amigo") AmigoVO a, BindingResult br) {
		
		if(br.hasErrors()) {
			return "admin/amigos/info";
		}else {
			sa.save(a);
			return "redirect:/admin/amigos";
		}
		
	}
	@PostMapping("/admin/amigos/new/submit")
	public String insertarAmigos(@Valid @ModelAttribute("amigo") AmigoVO a, BindingResult br) {
	
		if(br.hasErrors()) {
			return "admin/amigos/infoNew";
		}else {
			sa.save(a);
			return "redirect:/admin/amigos";
		}
		
	}
	
}

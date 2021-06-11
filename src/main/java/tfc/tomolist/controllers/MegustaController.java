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

import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.services.ServiciosMegusta;

@Controller
public class MegustaController {

	@Autowired
	ServiciosMegusta smg;

	
	@GetMapping("/admin/likes")
	public String allLikes(Model m) {
		
		
		m.addAttribute("likes", smg.findAll());
		
		return "admin/likes/index";
	}
	
	@GetMapping("/admin/likes/borrado/{id}")
	public String borradoLikes(@PathVariable int id) {
		smg.deleteById(id);
		return "redirect:/admin/likes";
	}
	
	@GetMapping("/admin/likes/update/{id}")
	public String infoLikes(@PathVariable int id, Model m) {
		MegustaVO meg=smg.findById(id).get();
		m.addAttribute("like", meg);
		return "admin/likes/info";
	}
	
	@GetMapping("/admin/likes/new")
	public String infoNewLikes( Model m) {

		m.addAttribute("like", new MegustaVO());
		return "admin/likes/infoNew";
	}
	
	@PostMapping("/admin/likes/submit")
	public String modificarLikes(@Valid @ModelAttribute("like") MegustaVO like, BindingResult br) {
		
		if(br.hasErrors()) {
			return "admin/likes/info";
		}else {
			smg.save(like);
			return "redirect:/admin/likes";
		}
		
	}
	@PostMapping("/admin/likes/new/submit")
	public String insertarLikes(@Valid @ModelAttribute("like") MegustaVO like, BindingResult br) {
	
		if(br.hasErrors()) {
			return "admin/likes/infoNew";
		}else {
			smg.save(like);
			return "redirect:/admin/likes";
		}
		
	}
	
}

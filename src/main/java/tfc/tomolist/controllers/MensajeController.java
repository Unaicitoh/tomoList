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

import tfc.tomolist.model.MensajeVO;
import tfc.tomolist.services.ServiciosMensaje;

@Controller
public class MensajeController {

	@Autowired
	ServiciosMensaje sm;

	
	@GetMapping("/admin/mensajes")
	public String allMensajes(Model m) {
		
		
		m.addAttribute("mensajes", sm.findAll());
		
		return "admin/mensajes/index";
	}
	
	@GetMapping("/admin/mensajes/borrado/{id}")
	public String borradoMen(@PathVariable int id) {
		sm.deleteById(id);
		return "redirect:/admin/mensajes";
	}
	
	@GetMapping("/admin/mensajes/update/{id}")
	public String infoMen(@PathVariable int id, Model m) {
		MensajeVO men=sm.findById(id).get();
		m.addAttribute("mensaje", men);
		return "admin/mensajes/info";
	}
	
	@GetMapping("/admin/mensajes/new")
	public String infoNewMen( Model m) {

		m.addAttribute("mensaje", new MensajeVO());
		return "admin/mensajes/infoNew";
	}
	
	@PostMapping("/admin/mensajes/submit")
	public String modificarMen(@Valid @ModelAttribute("mensaje") MensajeVO mensaje, BindingResult br) {
		
		if(br.hasErrors()) {
			return "admin/mensajes/info";
		}else {
			sm.save(mensaje);
			return "redirect:/admin/mensajes";
		}
		
	}
	@PostMapping("/admin/mensajes/new/submit")
	public String insertarMen(@Valid @ModelAttribute("mensaje") MensajeVO mensaje, BindingResult br) {
		mensaje.setFecha(LocalDateTime.now());
		if(br.hasErrors()) {
			return "admin/mensajes/infoNew";
		}else {
			sm.save(mensaje);
			return "redirect:/admin/mensajes";
		}
		
	}
	
}

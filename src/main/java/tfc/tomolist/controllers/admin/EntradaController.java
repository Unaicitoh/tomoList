package tfc.tomolist.controllers.admin;

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

import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.services.ServiciosEntrada;

@Controller
public class EntradaController {

	@Autowired
	ServiciosEntrada se;

	@GetMapping("/admin/entradas")
	public String allEntradas(Model m) {
		
		
		m.addAttribute("entradas", se.findAll());
		
		return "admin/entradas/index";
	}
	
	@GetMapping("/admin/entradas/borrado/{id}")
	public String borradoEntrada(@PathVariable int id) {
		se.deleteById(id);
		return "redirect:/admin/entradas";
	}
	
	@GetMapping("/admin/entradas/update/{id}")
	public String infoEntrada(@PathVariable int id, Model m) {
		EntradaVO en=se.findById(id).get();
		m.addAttribute("entrada", en);
		return "admin/entradas/info";
	}
	
	@GetMapping("/admin/entradas/new")
	public String infoNewEntrada( Model m) {

		m.addAttribute("entrada", new EntradaVO());
		return "admin/entradas/infoNew";
	}
	
	
	
	@PostMapping("/admin/entradas/submit")
	public String modificarEntrada(@Valid @ModelAttribute("entrada") EntradaVO entrada, BindingResult br) {
		
		if(br.hasErrors()) {
			return "admin/entradas/info";
		}else {
			se.save(entrada);
			return "redirect:/admin/entradas";
		}
		
	}
	
	@PostMapping("/admin/entradas/new/submit")
	public String insertarEntrada(@Valid @ModelAttribute("entrada") EntradaVO entrada, BindingResult br) {
		entrada.setFecha(LocalDateTime.now());
		if(br.hasErrors()) {
			return "admin/entradas/infoNew";
		}else {
			se.save(entrada);
			return "redirect:/admin/entradas";
		}
	}
	
	
}

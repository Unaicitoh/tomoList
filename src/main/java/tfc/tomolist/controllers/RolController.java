package tfc.tomolist.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import tfc.tomolist.services.ServiciosRol;

@Controller
public class RolController {

	@Autowired
	ServiciosRol sr;
	
	@GetMapping("/admin/roles")
	public String allRoles(Model m) {
		
		
		m.addAttribute("roles", sr.findAll());
		
		return "admin/roles/index";
	}
	
	
}

package tfc.tomolist.controllers.admin;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.security.SecurityConfig;
import tfc.tomolist.services.ServiciosRol;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
public class UsuarioController {

	@Autowired
	SecurityConfig config;
	
	@Autowired
	ServiciosUsuario su;
	
	@Autowired
	ServiciosRol sr;
	
	@GetMapping("/admin/usuarios")
	public String allUsuarios(Model m) {
		
		
		m.addAttribute("usuarios", su.findAll());
		
		return "admin/usuarios/index";
	}
	
	@GetMapping("/admin/usuarios/borrado/{id}")
	public String borradoUsu(@PathVariable int id) {
		su.deleteById(id);
		return "redirect:/admin/usuarios";
	}
	
	@GetMapping("/admin/usuarios/update/{id}")
	public String infoUsu(@PathVariable int id, Model m) {
		UsuarioVO usu=su.findById(id).get();
		m.addAttribute("usu", usu);
		return "admin/usuarios/info";
	}
	
	@GetMapping("/admin/newAdmin")
	public String infoUsu(Model m) {
		m.addAttribute("usu", new UsuarioVO());
		return "admin/usuarios/newAdmin";
	}
	
	@PostMapping("/admin/newAdmin/submit")
	public String insertarAdmin(@Valid @ModelAttribute(name = "usu") UsuarioVO u, BindingResult br) {
		if(br.hasErrors()) {
			return "admin/usuarios/newAdmin";
		}else {
			u.setPassword(config.encriptarPassword(u.getRawpass()));
			u.setFecha(LocalDate.now());
			u.setRol(sr.findById(1).get());
			su.save(u);
			return "redirect:/admin/usuarios";
		}
		
	}
	
	@PostMapping("/admin/usuarios/submit")
	public String updateUsu(@Valid @ModelAttribute("usu") UsuarioVO usu, BindingResult br) {
		
		if(br.hasErrors()) {
			return "admin/usuarios/info";
		}else {
			usu.setPassword(config.encriptarPassword(usu.getRawpass()));
			if(usu.getRol().getIdrol()==1) {
				usu.setRol(sr.findById(1).get());
			}else {
				usu.setRol(sr.findById(2).get());
			}
			su.save(usu);
			return "redirect:/admin/usuarios";
		}
		
	}

	
}

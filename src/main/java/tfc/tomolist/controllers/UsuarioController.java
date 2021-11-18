package tfc.tomolist.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
public class UsuarioController {

	@Autowired
	ServiciosUsuario su;

	
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
	
	@PostMapping("/admin/usuarios/submit")
	public String updateUsu(@Valid @ModelAttribute("usu") UsuarioVO usu, BindingResult br) {
		
		if(br.hasErrors()) {
			return "admin/usuarios/info";
		}else {
			su.save(usu);
			return "redirect:/admin/usuarios";
		}
		
	}
	
	@GetMapping("/app/home")
	public String homeUsu(Model m) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		UsuarioVO usuario=su.findByUsername(auth.getName()).get();

		m.addAttribute("usuario",usuario);
		
		return "app/home";
	}

	@GetMapping("/app/perfil/{id}")
	public String perfilUsuario(@PathVariable int id, Model m) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		String nombre= auth.getName();
		UsuarioVO usuarioPerfil=su.findById(id).get();
		UsuarioVO usuarioSesion=su.findByUsername(nombre).get();
		
		m.addAttribute("usuarioSesion",usuarioSesion);
		m.addAttribute("usuarioPerfil", usuarioPerfil);
		return "app/perfil";
	}
	
}

package tfc.tomolist.controllers;

import java.time.LocalDate;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.model.pagination.Paged;
import tfc.tomolist.security.SecurityConfig;
import tfc.tomolist.services.ServiciosEntrada;
import tfc.tomolist.services.ServiciosRol;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
public class UsuarioController {

	@Autowired
	SecurityConfig config;
	
	@Autowired
	ServiciosUsuario su;

	@Autowired
	ServiciosEntrada se;
	
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
			usu.setEdad(16);
			usu.setFecha(LocalDate.now());
			usu.setPassword(config.encriptarPassword(usu.getRawpass()));
			usu.setRol(sr.findById(1).get());
			su.save(usu);
			return "redirect:/admin/usuarios";
		}
		
	}
	
	@GetMapping("/app/home")
	public String homeUsu(Model m, @RequestParam(name = "num", required = false, defaultValue = "1") int pageNumber, @RequestParam(name = "size", required = false, defaultValue = "5") int size) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		
		UsuarioVO usuario=su.findByUsername(auth.getName()).get();
		Paged<EntradaVO> postsPageados= se.entradasTablon(usuario.getIdusuario(), pageNumber, size).get();
		m.addAttribute("usuario",usuario);
		m.addAttribute("entradas", postsPageados);
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

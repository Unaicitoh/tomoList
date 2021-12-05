package tfc.tomolist.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.security.SecurityConfig;
import tfc.tomolist.services.ServiciosRol;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
public class MainController {

	@Autowired
	SecurityConfig config;

	@Autowired
	ServiciosUsuario su;

	@Autowired
	ServiciosRol sr;

	
	@GetMapping("/admin")
	public String moderador() {

		return "admin/index";
	}

	@GetMapping("/login")
	public String logIn(@RequestParam(required = false, name = "registro") String registro, Model m) {
		if (registro != null) {
			m.addAttribute("msgRegistro", "Registro de usuario completado satisfactoriamente.");
		}
		return "login";
	}

	@GetMapping("/logout")
	public String logout() {

		return "login";
	}

	@GetMapping("/")
	public String redALog() {
		return "redirect:/login";
	}

	@GetMapping("/app")
	public String home() {

		return "redirect:/app/home";
	}

	@RequestMapping("/success")
	public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {

		String role = authResult.getAuthorities().toString();

		if (role.contains("ROLE_ADMIN")) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "admin"));
		} else if (role.contains("ROLE_ESTANDAR")) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "app"));
		}
	}

	@GetMapping("/register")
	public String register(Model m) {
		m.addAttribute("usuario", new UsuarioVO());

		return "register";
	}
	
	@ModelAttribute("usuarioRegistro")
	public UsuarioVO getUsuario() {
		UsuarioVO usu=new UsuarioVO();
		usu.setNombre("User Apellido");
		usu.setEdad(16);

		return usu;
	}

	@PostMapping("/register/submit")
	public String envioRegistro(@Valid @ModelAttribute("usuarioRegistro") UsuarioVO usuario, BindingResult br, Model m) {

		usuario.setFecha(LocalDate.now());
		usuario.setPassword(config.encriptarPassword(usuario.getRawpass()));
		usuario.setRol(sr.findById(2).get());
		usuario.setFoto("URL");
		if (br.hasErrors()) {

			return "register";
		} else {
			try {
				su.save(usuario);
			} catch (DataIntegrityViolationException e) {
				
				if(su.findByUsername(usuario.getUsername()).isPresent()) 
					m.addAttribute("mensajeUsername", "Nickname ya registrado en la app");
				
				if(su.findByEmail(usuario.getEmail()).isPresent()) 
					m.addAttribute("mensajeEmail", "E-mail ya registrado en la app");
				
				
				return "register";
			}

			return "redirect:/login?registro";
		}

	}


}

package tfc.tomolist.controllers;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String logIn() {
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
    public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException, ServletException {

        String role =  authResult.getAuthorities().toString();

        if(role.contains("ROLE_ADMIN")){
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "admin"));
        }
        else if(role.contains("ROLE_ESTANDAR")) {
            response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "app"));
        }
    }
	
	@GetMapping("/register")
	public String register(Model m) {
		
		m.addAttribute("usuario",new UsuarioVO());
		
		return "register";
	}

	
	@PostMapping("/register/submit")
	public String envioRegistro(@ModelAttribute("usuario") UsuarioVO usuario) {
		
		String rndName="User_";
		
		for(int i=0; i<5; i++) {
			int rnd=(int) Math.floor(Math.random()*10);
			rndName+=rnd;
		}
		
		usuario.setEdad(16);
		usuario.setNombre(rndName);
		usuario.setFecha(LocalDate.now());
		usuario.setPassword(config.encriptarPassword(usuario.getRawpass()));
		usuario.setRol(sr.findById(2).get());
		su.save(usuario);

		
		return "redirect:/login";
		
	}
		
	
}

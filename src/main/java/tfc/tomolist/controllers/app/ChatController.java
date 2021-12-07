package tfc.tomolist.controllers.app;

import java.time.LocalDateTime;

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
import org.springframework.web.bind.annotation.RequestMapping;

import tfc.tomolist.model.MensajeVO;
import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.services.ServiciosMensaje;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
@RequestMapping("/app")
public class ChatController {
	
	@Autowired
	ServiciosUsuario su;
	
	@Autowired
	ServiciosMensaje sm;
	
	@GetMapping("/friendchatsearcher/{user}/{id}")
	public String getAmigosChatBuscados(@PathVariable("user") String name, @PathVariable("id") int id, Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO u = su.findByUsername(auth.getName()).get();
		m.addAttribute("usuario", u);
		m.addAttribute("solicitudes", su.solicitudesOrdenadas(u.getIdusuario()).get());
		boolean isRest = true;
		m.addAttribute("isRest", isRest);
		m.addAttribute("amigos", su.amigosRestOrdenadas(name, id).get());
		return "app/chats";

	}

	@GetMapping("/chats")
	public String getChat(Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO u = su.findByUsername(auth.getName()).get();
		boolean isRest = false;
		m.addAttribute("isRest", isRest);
		m.addAttribute("usuario", u);
		m.addAttribute("amigos", su.amigosOrdenados(u.getIdusuario()).get());
		return "app/chats";
	}
	
	@GetMapping("/chat/{id}")
	public String getChatAmigo(@PathVariable int id, Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO u = su.findByUsername(auth.getName()).get();
		
		m.addAttribute("usuarioReceptor", su.findById(id).get());
		m.addAttribute("usuario", u);
		m.addAttribute("mensajes", su.getConversacionEntreAmigos(u.getIdusuario(), id).get());
		m.addAttribute("input", new MensajeVO());
		return "app/chatUsuario";
	}
	
	@PostMapping("/mensaje/{idR}")
	public String envioMensaje(@PathVariable int idR,@Valid @ModelAttribute MensajeVO men, BindingResult br, Model m) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO u = su.findByUsername(auth.getName()).get();
		
		
		if(br.hasErrors()) {
			m.addAttribute("usuarioReceptor", su.findById(idR).get());
			m.addAttribute("usuario", u);
			m.addAttribute("mensajes", su.getConversacionEntreAmigos(u.getIdusuario(), idR).get());
			return "app/chatUsuario";
		}else {
			men.setFecha(LocalDateTime.now());
			men.setReceptor(su.findById(idR).get());
			men.setAutor(u);
			men.setContenido(men.getContenido().trim());
			sm.save(men);
			return "redirect:/app/chat/"+idR;
		}	
	}
}

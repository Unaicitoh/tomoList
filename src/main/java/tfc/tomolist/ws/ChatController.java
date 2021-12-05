package tfc.tomolist.ws;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.google.gson.Gson;

import tfc.tomolist.model.MensajeVO;
import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.services.ServiciosUsuario;

@Controller
public class ChatController {
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
	@Autowired
	ServiciosUsuario su;
	
	private Gson gson = new Gson();
	
	@GetMapping("/app/chats")
	public String getChat(Model m) {
		Authentication auth= SecurityContextHolder.getContext().getAuthentication();
		UsuarioVO u= su.findByUsername(auth.getName()).get();
		m.addAttribute("usuario", u);
		return "app/chat";
	}
	
	@MessageMapping("/app/chat/registro")
	@SendToUser("/mensajes/respuesta")
	public String register(@Payload MensajeVO men, SimpMessageHeaderAccessor headerAccesor) {
		
		headerAccesor.getSessionAttributes().put("username", men.getAutor().getUsername());
		
		return gson
		          .fromJson(men.getAutor().getUsername(), Map.class)
		          .get("username").toString();
		
	}
	
	@MessageMapping("/app/chat/envio")
	@SendToUser("/mensajes/send")
	public MensajeVO enviarMensaje(@Payload MensajeVO men) {
		
		return men;
		
	}
	
	@MessageExceptionHandler
    @SendToUser("/mensajes/errors")
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }
	
}

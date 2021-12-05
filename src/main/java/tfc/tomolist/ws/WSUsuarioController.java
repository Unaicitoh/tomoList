package tfc.tomolist.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.services.ServiciosUsuario;

@RestController
@RequestMapping("/api")
public class WSUsuarioController {

	@Autowired
	ServiciosUsuario su;
	
	@GetMapping("/usersearch/{user}/{id}")
	public List<UsuarioVO> getUsuariosBuscados(@PathVariable("user") String name, @PathVariable("id") int id){
		
		List<UsuarioVO> list=su.userSearcherByNickname(name, id).get();
		
		return list;
		
	}
	

	
}

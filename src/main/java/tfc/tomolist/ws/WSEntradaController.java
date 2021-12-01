package tfc.tomolist.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.services.ServiciosEntrada;
import tfc.tomolist.services.ServiciosMegusta;
import tfc.tomolist.services.ServiciosUsuario;

@RestController
@RequestMapping("/api")
public class WSEntradaController {

	@Autowired
	ServiciosEntrada se;
	
	@Autowired
	ServiciosUsuario su;
	
	@Autowired
	ServiciosMegusta smg;;
	

	@GetMapping("/postlikes/{idE}")
	public List<MegustaVO> getLikesEntrada(@PathVariable int idE){
		
		List<MegustaVO> lista=se.getMegustas(idE).get();
		
		return lista;

	}
	
	@GetMapping("/likeActivo/{idE}/{idU}")
	public boolean getLikesActivos(@PathVariable int idE, @PathVariable int idU) {
		
		return smg.getMegustaUsuarioEntrada(idE, idU).isPresent();
		
	}
	
	@GetMapping("/postcomments/{idE}")
	public List<ComentarioVO> getCommentariosEntrada(@PathVariable int idE){
		
		List<ComentarioVO> lista= se.getComentarios(idE).get();
		
		return lista;

	}

	@DeleteMapping("/borrarLike/{idE}/{idU}")
	private void borrarLike(@PathVariable("idE") int idE, @PathVariable("idU") int idU) {
		smg.delete(smg.getMegustaUsuarioEntrada(idE, idU).get());
	}

	@PostMapping("/creaLike/{idE}/{idU}")
	private MegustaVO crearLike(@PathVariable("idE") int idE, @PathVariable("idU") int idU) {
		
		MegustaVO mg= new MegustaVO();
		mg.setEntrada(se.findById(idE).get());
		mg.setUsuario(su.findById(idU).get());
		return smg.save(mg);
	}
}

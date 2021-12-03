package tfc.tomolist.servicesImp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tfc.tomolist.model.AmigoVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.model.MensajeVO;
import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.repository.UsuarioRepository;
import tfc.tomolist.services.ServiciosUsuario;

@Service
public class ServiciosUsuarioImp implements ServiciosUsuario, UserDetailsService{

	@Autowired
	UsuarioRepository ur;
	
	
	@Override
	public <S extends UsuarioVO> S save(S entity) {
		return ur.save(entity);
	}

	@Override
	public <S extends UsuarioVO> Iterable<S> saveAll(Iterable<S> entities) {
		return ur.saveAll(entities);
	}

	@Override
	public Optional<UsuarioVO> findById(Integer id) {
		return ur.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return ur.existsById(id);
	}

	@Override
	public Iterable<UsuarioVO> findAll() {
		return ur.findAll();
	}

	@Override
	public Iterable<UsuarioVO> findAllById(Iterable<Integer> ids) {
		return ur.findAllById(ids);
	}

	@Override
	public long count() {
		return ur.count();
	}

	@Override
	public void deleteById(Integer id) {
		ur.deleteById(id);
	}

	@Override
	public void delete(UsuarioVO entity) {
		ur.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends UsuarioVO> entities) {
		ur.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		ur.deleteAll();
	}

	public Optional<ArrayList<MensajeVO>> getMensajes(int id) {
		return ur.getMensajes(id);
	}

	public Optional<ArrayList<AmigoVO>> getAmigos(int id) {
		return ur.getAmigos(id);
	}

	public Optional<ArrayList<EntradaVO>> getEntradas(int id) {
		return ur.getEntradas(id);
	}
	


	public Optional<ArrayList<MensajeVO>> getConversacionEntreAmigos(int amigo1, int amigo2) {
		return ur.getConversacionEntreAmigos(amigo1, amigo2);
	}

	@Override
	public int numeroAmigos(int id) {
		return getAmigos(id).get().size();
	}

	public Optional<ArrayList<UsuarioVO>> findByUsernameContaining(String nombre) {
		return ur.findByUsernameContaining(nombre);
	}

	@Override
	public Optional<ArrayList<AmigoVO>> amigosOrdenados(int id) {
		ArrayList<AmigoVO> filterList=ur.getAmigos(id).get();
		filterList.stream().forEach(x->{
			if(x.getAmigo2().getIdusuario()==id) {
				UsuarioVO aux;
				aux=x.getAmigo1();
				x.setAmigo1(x.getAmigo2());
				x.setAmigo2(aux);
			}
		});
		return Optional.of(filterList);
	}



	@Override
	public int numeroEntradas(int id) {
		return ur.getEntradas(id).get().size();
	}

	public void borrarAmistad(int id1, int id2) {
		ur.borrarAmistad(id1, id2);
	}


	public Optional<UsuarioVO> findByUsername(String username) {
		return ur.findByUsername(username);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return (UserDetails) ur.findByUsername(username).get();
	}

	public Optional<ArrayList<UsuarioVO>> userSearcherByNickname(String s, int id) {
		return ur.userSearcherByNickname(s, id);
	}

	@Override
	public Optional<ArrayList<MegustaVO>> getMegustasUsuarios(int id) {
		return ur.getMegustasUsuarios(id);
	}

	@Override
	public Optional<UsuarioVO> findByEmail(String email) {
		return ur.findByEmail(email);
	}


	@Override
	public Optional<ArrayList<AmigoVO>> getAmigoUsuario(int id, int id2) {
		return ur.getAmigoUsuario(id, id2);
	}

	@Override
	public Optional<ArrayList<AmigoVO>> getSolicitudUsuario(int id, int id2) {
		return ur.getSolicitudUsuario(id, id2);
	}




}

package tfc.tomolist.services;

import java.util.ArrayList;
import java.util.Optional;

import tfc.tomolist.model.AmigoVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.model.MensajeVO;
import tfc.tomolist.model.UsuarioVO;

public interface ServiciosUsuario {

	<S extends UsuarioVO> S save(S entity);

	<S extends UsuarioVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<UsuarioVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<UsuarioVO> findAll();

	Iterable<UsuarioVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(UsuarioVO entity);

	void deleteAll(Iterable<? extends UsuarioVO> entities);

	void deleteAll();
	
	//Mensajes de usuario
	Optional<ArrayList<MensajeVO>> getMensajes(int id);

	//Amigos de usuario
	Optional<ArrayList<AmigoVO>> getAmigos(int id);
	
	//Amigos con usuario ordenado
	Optional<ArrayList<AmigoVO>> amigosOrdenados(int id);
	Optional<ArrayList<AmigoVO>> solicitudesOrdenadas(int id);
	Optional<ArrayList<AmigoVO>> getAmigosRest(String s, int id);
	Optional<ArrayList<AmigoVO>> amigosRestOrdenadas(String s,int id);
	//Entradas de un usuario
	Optional<ArrayList<EntradaVO>> getEntradas(int id);
	
	//Conversacion entre usuarios
	Optional<ArrayList<MensajeVO>> getConversacionEntreAmigos(int amigo1, int amigo2);
	
	//Numero de amigos de un usuario
	int numeroAmigos(int id);
	
	//Numero de entradas
	int numeroEntradas(int id);
	
	//Buscador de usuarios
	Optional<ArrayList<UsuarioVO>> findByUsernameContaining(String nombre);
	
	//Buscador de usuarios
	Optional<ArrayList<UsuarioVO>> userSearcherByNickname(String s, int id);
	
	Optional<UsuarioVO> findByEmail(String email);
	
	//Borrar amigos en ambas direcciones
	void borrarAmistad(int id1, int id2);
	
	Optional<UsuarioVO> findByUsername(String username);

	Optional<ArrayList<AmigoVO>> getSolicitudes(int id);
	
	Optional<ArrayList<AmigoVO>> getAmigoUsuario(int id,int id2);
	
	Optional<ArrayList<AmigoVO>> getSolicitudUsuario(int id, int id2);
	
	Optional<ArrayList<MegustaVO>> getMegustasUsuarios(int id);
	
}
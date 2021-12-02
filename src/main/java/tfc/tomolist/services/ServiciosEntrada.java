package tfc.tomolist.services;

import java.util.ArrayList;
import java.util.Optional;

import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.model.pagination.Paged;

public interface ServiciosEntrada {

	<S extends EntradaVO> S save(S entity);

	<S extends EntradaVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<EntradaVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<EntradaVO> findAll();

	Iterable<EntradaVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(EntradaVO entity);

	void deleteAll(Iterable<? extends EntradaVO> entities);

	void deleteAll();

	//Comentarios de una entrada
	Optional<ArrayList<ComentarioVO>> getComentarios(int id);
	
	//Likes de una entrada
	Optional<ArrayList<MegustaVO>> getMegustas(int id);
	
	//Entradas para la página principal de usuario registrados
	Optional<Paged<EntradaVO>> entradasTablon(int id, int pageNumber, int size);
	
	Optional<Paged<EntradaVO>> entradasPerfil(int id, int pageNumber, int size);
	
	//Numero de comentarios de la entrada
	int numeroComentarios(int id);
	
	//Numero de likes de la entrada
	int numeroDeLikes(int id);
}
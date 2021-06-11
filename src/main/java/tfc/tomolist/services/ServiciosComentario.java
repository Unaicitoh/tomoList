package tfc.tomolist.services;

import java.util.ArrayList;
import java.util.Optional;

import tfc.tomolist.model.ComentarioVO;

public interface ServiciosComentario {

	<S extends ComentarioVO> S save(S entity);

	<S extends ComentarioVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<ComentarioVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<ComentarioVO> findAll();

	Iterable<ComentarioVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(ComentarioVO entity);

	void deleteAll(Iterable<? extends ComentarioVO> entities);

	void deleteAll();
	
	Optional<ArrayList<ComentarioVO>> getSubComentarios(int id);

}
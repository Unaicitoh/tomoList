package tfc.tomolist.services;

import java.util.Optional;

import tfc.tomolist.model.AmigoVO;

public interface ServiciosAmigo {

	<S extends AmigoVO> S save(S entity);

	<S extends AmigoVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<AmigoVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<AmigoVO> findAll();

	Iterable<AmigoVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(AmigoVO entity);

	void deleteAll(Iterable<? extends AmigoVO> entities);

	void deleteAll();

}
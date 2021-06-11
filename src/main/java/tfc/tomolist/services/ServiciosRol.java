package tfc.tomolist.services;

import java.util.Optional;

import tfc.tomolist.model.RolVO;

public interface ServiciosRol {

	<S extends RolVO> S save(S entity);

	<S extends RolVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<RolVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<RolVO> findAll();

	Iterable<RolVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(RolVO entity);

	void deleteAll(Iterable<? extends RolVO> entities);

	void deleteAll();

}
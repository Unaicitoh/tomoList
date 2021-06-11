package tfc.tomolist.services;

import java.util.Optional;

import tfc.tomolist.model.MegustaVO;

public interface ServiciosMegusta {

	<S extends MegustaVO> S save(S entity);

	<S extends MegustaVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<MegustaVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<MegustaVO> findAll();

	Iterable<MegustaVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(MegustaVO entity);

	void deleteAll(Iterable<? extends MegustaVO> entities);

	void deleteAll();

}
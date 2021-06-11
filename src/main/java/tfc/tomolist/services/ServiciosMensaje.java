package tfc.tomolist.services;

import java.util.Optional;

import tfc.tomolist.model.MensajeVO;

public interface ServiciosMensaje {

	<S extends MensajeVO> S save(S entity);

	<S extends MensajeVO> Iterable<S> saveAll(Iterable<S> entities);

	Optional<MensajeVO> findById(Integer id);

	boolean existsById(Integer id);

	Iterable<MensajeVO> findAll();

	Iterable<MensajeVO> findAllById(Iterable<Integer> ids);

	long count();

	void deleteById(Integer id);

	void delete(MensajeVO entity);

	void deleteAll(Iterable<? extends MensajeVO> entities);

	void deleteAll();

}
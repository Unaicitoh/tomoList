package tfc.tomolist.servicesImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.repository.MegustaRepository;
import tfc.tomolist.services.ServiciosMegusta;
@Service
public class ServiciosMegustaImp implements ServiciosMegusta {

	@Autowired
	MegustaRepository mgr;

	@Override
	public <S extends MegustaVO> S save(S entity) {
		return mgr.save(entity);
	}

	@Override
	public <S extends MegustaVO> Iterable<S> saveAll(Iterable<S> entities) {
		return mgr.saveAll(entities);
	}

	@Override
	public Optional<MegustaVO> findById(Integer id) {
		return mgr.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return mgr.existsById(id);
	}

	@Override
	public Iterable<MegustaVO> findAll() {
		return mgr.findAll();
	}

	@Override
	public Iterable<MegustaVO> findAllById(Iterable<Integer> ids) {
		return mgr.findAllById(ids);
	}

	@Override
	public long count() {
		return mgr.count();
	}

	@Override
	public void deleteById(Integer id) {
		mgr.deleteById(id);
	}

	@Override
	public void delete(MegustaVO entity) {
		mgr.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends MegustaVO> entities) {
		mgr.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		mgr.deleteAll();
	}
}

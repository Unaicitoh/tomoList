package tfc.tomolist.servicesImp;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tfc.tomolist.model.AmigoVO;
import tfc.tomolist.repository.AmigoRepository;
import tfc.tomolist.services.ServiciosAmigo;

@Service
public class ServiciosAmigoImp implements ServiciosAmigo {

	
	@Autowired
	AmigoRepository ar;

	@Override
	public <S extends AmigoVO> S save(S entity) {
		return ar.save(entity);
	}

	@Override
	public <S extends AmigoVO> Iterable<S> saveAll(Iterable<S> entities) {
		return ar.saveAll(entities);
	}

	@Override
	public Optional<AmigoVO> findById(Integer id) {
		return ar.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return ar.existsById(id);
	}

	@Override
	public Iterable<AmigoVO> findAll() {
		return ar.findAll();
	}

	@Override
	public Iterable<AmigoVO> findAllById(Iterable<Integer> ids) {
		return ar.findAllById(ids);
	}

	@Override
	public long count() {
		return ar.count();
	}

	@Override
	public void deleteById(Integer id) {
		ar.deleteById(id);
	}

	@Override
	public void delete(AmigoVO entity) {
		ar.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends AmigoVO> entities) {
		ar.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		ar.deleteAll();
	}
}

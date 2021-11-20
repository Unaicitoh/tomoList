package tfc.tomolist.servicesImp;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.model.pagination.Paged;
import tfc.tomolist.model.pagination.Paging;
import tfc.tomolist.repository.EntradaRepository;
import tfc.tomolist.services.ServiciosEntrada;

@Service
public class ServiciosEntradaImp implements ServiciosEntrada {

	@Autowired
	EntradaRepository er;

	@Override
	public <S extends EntradaVO> S save(S entity) {
		return er.save(entity);
	}

	@Override
	public <S extends EntradaVO> Iterable<S> saveAll(Iterable<S> entities) {
		return er.saveAll(entities);
	}

	@Override
	public Optional<EntradaVO> findById(Integer id) {
		return er.findById(id);
	}

	@Override
	public boolean existsById(Integer id) {
		return er.existsById(id);
	}

	@Override
	public Iterable<EntradaVO> findAll() {
		return er.findAll();
	}

	@Override
	public Iterable<EntradaVO> findAllById(Iterable<Integer> ids) {
		return er.findAllById(ids);
	}

	@Override
	public long count() {
		return er.count();
	}

	@Override
	public void deleteById(Integer id) {
		er.deleteById(id);
	}

	@Override
	public void delete(EntradaVO entity) {
		er.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends EntradaVO> entities) {
		er.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		er.deleteAll();
	}

	public Optional<ArrayList<ComentarioVO>> getComentarios(int id) {
		return er.getComentarios(id);
	}

	public Optional<ArrayList<MegustaVO>> getMegustas(int id) {
		return er.getMegustas(id);
	}
	
	public Optional<Paged<EntradaVO>> entradasTablon(int id,int pageNumber, int size) {

		PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<EntradaVO> postPage = er.entradasTablon(id, request).get();
        return Optional.of(new Paged<EntradaVO>(postPage, Paging.of(postPage.getTotalPages(), pageNumber, size)));
	}

	@Override
	public int numeroComentarios(int id) {
		return er.getComentarios(id).get().size();
	}

	@Override
	public int numeroDeLikes(int id) {
		return er.getMegustas(id).get().size();
	}

	
}

package tfc.tomolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.MensajeVO;

@Repository
public interface MensajeRepository extends CrudRepository<MensajeVO, Integer>{
	
	
}

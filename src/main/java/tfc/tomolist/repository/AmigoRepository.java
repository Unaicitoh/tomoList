package tfc.tomolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.AmigoVO;

@Repository
public interface AmigoRepository extends CrudRepository<AmigoVO, Integer> {

	
}

package tfc.tomolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.MegustaVO;

@Repository
public interface MegustaRepository extends CrudRepository<MegustaVO, Integer>{

}

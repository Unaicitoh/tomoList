package tfc.tomolist.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.RolVO;

@Repository
public interface RolRepository extends CrudRepository<RolVO, Integer> {

}

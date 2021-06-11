package tfc.tomolist.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.MegustaVO;
@Repository
public interface EntradaRepository extends CrudRepository<EntradaVO, Integer>{

	@Query("SELECT c FROM ComentarioVO c WHERE c.entrada.identrada=:id")
	Optional<ArrayList<ComentarioVO>> getComentarios(@Param("id") int id);
	
	@Query("SELECT m FROM MegustaVO m WHERE m.entrada.identrada=:id")
	Optional<ArrayList<MegustaVO>> getMegustas(@Param("id") int id);
	
	
}

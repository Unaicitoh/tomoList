package tfc.tomolist.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.ComentarioVO;

@Repository
public interface ComentarioRepository extends CrudRepository<ComentarioVO, Integer> {

	@Query("SELECT c FROM ComentarioVO c WHERE c.comentario.idcomentario=:id ORDER BY c.fecha")
	Optional<ArrayList<ComentarioVO>> getSubComentarios(@Param("id") int id);
	
}

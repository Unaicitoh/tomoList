package tfc.tomolist.repository;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.MegustaVO;
@Repository
public interface EntradaRepository extends CrudRepository<EntradaVO, Integer>{

	@Query("SELECT c FROM ComentarioVO c WHERE c.entrada.identrada=:id ORDER BY c.fecha DESC")
	Optional<ArrayList<ComentarioVO>> getComentarios(@Param("id") int id);
	
	@Query("SELECT m FROM MegustaVO m WHERE m.entrada.identrada=:id")
	Optional<ArrayList<MegustaVO>> getMegustas(@Param("id") int id);
	
	@Query("SELECT e FROM EntradaVO e, AmigoVO a WHERE (e.autor.idusuario=a.amigo1.idusuario OR e.autor.idusuario=a.amigo2.idusuario) AND (a.amigo1.idusuario=:id OR a.amigo2.idusuario=:id) AND a.aceptado=1 AND e.autor.idusuario!=:id ORDER BY e.fecha DESC")
	Optional<Page<EntradaVO>> entradasTablon(@Param("id") int id, Pageable page);
	
	@Query("SELECT e FROM EntradaVO e WHERE e.autor.idusuario=:id ORDER BY e.fecha DESC")
	Optional<Page<EntradaVO>> entradasPerfil(@Param("id") int id, Pageable page);
}

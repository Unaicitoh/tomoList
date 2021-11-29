package tfc.tomolist.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.MegustaVO;

@Repository
public interface MegustaRepository extends CrudRepository<MegustaVO, Integer>{

	@Query("SELECT m FROM MegustaVO m WHERE m.entrada.identrada=:id AND m.usuario.idusuario=:idU")
	Optional<MegustaVO> getMegustaUsuario(int id, int idU);
	
}

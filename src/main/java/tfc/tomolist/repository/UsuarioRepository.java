package tfc.tomolist.repository;

import java.util.ArrayList;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tfc.tomolist.model.AmigoVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.model.MensajeVO;
import tfc.tomolist.model.UsuarioVO;

@Repository
public interface UsuarioRepository extends CrudRepository<UsuarioVO, Integer> {
	
	@Query("SELECT m FROM MensajeVO m WHERE m.autor.idusuario=:id ORDER BY m.fecha")
	Optional<ArrayList<MensajeVO>> getMensajes(@Param("id") int id);
	
	@Query("SELECT a FROM AmigoVO a WHERE (a.amigo2.idusuario=:id OR a.amigo1.idusuario=:id) AND a.aceptado=1")
	Optional<ArrayList<AmigoVO>> getAmigos(@Param("id") int id);
	
	@Query("SELECT a FROM AmigoVO a WHERE (a.amigo1.idusuario=:id AND a.amigo2.idusuario=:id2 AND a.aceptado=1) OR (a.amigo1.idusuario=:id2 AND a.amigo2.idusuario=:id AND a.aceptado=1)")
	Optional<ArrayList<AmigoVO>> getAmigoUsuario(@Param("id") int id, @Param("id2") int id2);
	
	@Query("SELECT a FROM AmigoVO a WHERE a.amigo1.idusuario=:id AND a.amigo2.idusuario=:id2 AND a.aceptado=0")
	Optional<ArrayList<AmigoVO>> getSolicitudUsuario(@Param("id") int id, @Param("id2") int id2);
	
	@Query("SELECT a FROM AmigoVO a WHERE a.amigo2.idusuario=:id AND a.aceptado=0")
	Optional<ArrayList<AmigoVO>> getSolicitudes(@Param("id") int id);
	
	@Query("SELECT e FROM EntradaVO e WHERE e.autor.idusuario=:id")
	Optional<ArrayList<EntradaVO>> getEntradas(@Param("id") int id);
	
	@Query("SELECT m FROM MegustaVO m WHERE m.entrada.autor.idusuario=:id")
	Optional<ArrayList<MegustaVO>> getMegustasUsuarios(@Param("id") int id);
	
	@Query("SELECT m FROM MensajeVO m WHERE m.autor.idusuario=:amigo1 AND m.receptor.idusuario=:amigo2 OR m.autor.idusuario=:amigo2 AND m.receptor.idusuario=:amigo1 ORDER BY m.fecha")
	Optional<ArrayList<MensajeVO>> getConversacionEntreAmigos(@Param("amigo1")int amigo1,@Param("amigo2") int amigo2);
	
	@Query("SELECT u FROM UsuarioVO u WHERE u.username LIKE %:s% AND u.idusuario!=:id AND u.rol.idrol!=1")
	Optional<ArrayList<UsuarioVO>> userSearcherByNickname(String s, int id);
	
	Optional<ArrayList<UsuarioVO>> findByUsernameContaining(String s);
	
	Optional<UsuarioVO> findByUsername(String username);
	
	Optional<UsuarioVO> findByEmail(String email);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM AmigoVO a WHERE (a.amigo1.idusuario=:id1 OR a.amigo2.idusuario=:id1) AND (a.amigo1.idusuario=:id2 OR a.amigo2.idusuario=:id2)")
	void borrarAmistad(@Param("id1") int id1,@Param("id2") int id2);
	
}

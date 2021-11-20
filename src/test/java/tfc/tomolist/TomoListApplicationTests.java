package tfc.tomolist;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tfc.tomolist.model.AmigoVO;
import tfc.tomolist.model.ComentarioVO;
import tfc.tomolist.model.EntradaVO;
import tfc.tomolist.model.MegustaVO;
import tfc.tomolist.model.MensajeVO;
import tfc.tomolist.model.RolVO;
import tfc.tomolist.model.UsuarioVO;
import tfc.tomolist.services.ServiciosAmigo;
import tfc.tomolist.services.ServiciosComentario;
import tfc.tomolist.services.ServiciosEntrada;
import tfc.tomolist.services.ServiciosMegusta;
import tfc.tomolist.services.ServiciosMensaje;
import tfc.tomolist.services.ServiciosRol;
import tfc.tomolist.services.ServiciosUsuario;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TomoListApplicationTests {


	@Autowired
	ServiciosUsuario su;
	
	@Autowired
	ServiciosAmigo sa;
	
	@Autowired
	ServiciosMensaje sm;
	
	@Autowired
	ServiciosEntrada se;
	
	@Autowired
	ServiciosMegusta smg;
	
	@Autowired
	ServiciosComentario sc;
	
 	@Autowired
	ServiciosRol sr;
	
	@Test
	@Order(1)
	public void insertarRoles() {
		sr.save(new RolVO(0,"admin"));
		sr.save(new RolVO(0,"estandar"));
		assertNotNull(sr.findById(2).get());
	}

	
	@Test
	@Order(2)
	public void insertarUsuario() {
		su.save(new UsuarioVO(0, "Unai Gonzalez", "unai@unai.com","usu1", "123","123", LocalDate.now(), 'H', "images", "Adivinas", "1234", "Calle 13",22, sr.findById(1).get()));
		su.save(new UsuarioVO(0, "Rober Rodriguez", "r@unai.com","usu2", "123","123", LocalDate.now(), 'H', "images", "Adivinas", "1234", "Calle 13",22, sr.findById(2).get()));
		su.save(new UsuarioVO(0, "Xenia Rodriguez","@unai.com","usu3", "123","123", LocalDate.now(), 'M', "images", "Adivinas", "1234", "Calle 13",23, sr.findById(2).get()));
		assertNotNull(su.findById(1).get());
	}
	
	@Test
	@Order(3)
	public void insertarAmigos() {
		sa.save(new AmigoVO(0,true,su.findById(2).get(),su.findById(1).get()));
		sa.save(new AmigoVO(0,true,su.findById(1).get(),su.findById(3).get()));
		assertNotNull(sa.findById(1).get());
	}
	
	@Test
	@Order(4)
	public void insertarMensaje() {
		sm.save(new MensajeVO(0,"Prueba",LocalDateTime.now(),su.findById(1).get(),su.findById(2).get()));
		sm.save(new MensajeVO(0,"Prueba nº2",LocalDateTime.of(2015, 1, 1, 12, 40),su.findById(1).get(),su.findById(2).get()));
		sm.save(new MensajeVO(0,"Prueba nº3",LocalDateTime.now(),su.findById(1).get(),su.findById(2).get()));
		sm.save(new MensajeVO(0,"Prueba nº5",LocalDateTime.of(2016, 1, 1, 12, 40),su.findById(2).get(),su.findById(1).get()));
		sm.save(new MensajeVO(0,"Prueba nº6",LocalDateTime.of(2019, 1, 1, 12, 2),su.findById(2).get(),su.findById(1).get()));
		assertNotNull(sm.findById(1).get());
	}
	
	@Test
	@Order(5)
	public void insertarEntrada() {
		se.save(new EntradaVO(0, "Ejemplo", "Contenido", "image", LocalDateTime.now(), su.findById(1).get()));
		se.save(new EntradaVO(0, "Ejemplos", "Contenido", "image", LocalDateTime.of(2020, 1, 1, 12, 40), su.findById(2).get()));
		se.save(new EntradaVO(0, "Ejemplo", "Contenido", "image", LocalDateTime.of(2019, 1, 1, 12, 2), su.findById(3).get()));
		assertNotNull(se.findById(1).get());
	}
	
	@Test
	@Order(6)
	public void insertarMeGusta() {
		smg.save(new MegustaVO(1,su.findById(2).get(),se.findById(1).get()));
		assertNotNull(smg.findById(1).get());
	}
	
	@Test
	@Order(7)
	public void insertarComentarios() {
		sc.save(new ComentarioVO(0,"Hola",LocalDateTime.now(),null,se.findById(1).get(),su.findById(1).get()));
		sc.save(new ComentarioVO(0,"Buenas!",LocalDateTime.now(), sc.findById(1).get(), se.findById(1).get(),su.findById(2).get()));
		sc.save(new ComentarioVO(0,"Klk que tal chicos",LocalDateTime.now(), sc.findById(1).get(), se.findById(1).get(),su.findById(3).get()));
		assertNotNull(sc.findById(1).get());
		}
	
	
	@Test
	@Order(8)
	public void mensajesEscritosDeUsuario() {
		su.getMensajes(su.findById(1).get().getIdusuario()).get().stream().forEach(x->System.out.println(String.format("De %s a %s: %s %s",x.getAutor().getNombre(),x.getReceptor().getNombre(),x.getContenido(),x.getFecha())));
	}
	
	@Test
	@Order(9)
	public void amigosAceptadosDeUsuario() {
		su.amigosOrdenados(1).get().stream().forEach(x->System.out.println(String.format("%s amigo de %s", x.getAmigo1().getNombre(),x.getAmigo2().getNombre())));

	}
	
	@Test
	@Order(10)
	public void entradasDeUsuario() {
		su.getEntradas(su.findById(1).get().getIdusuario()).get().stream().forEach(x->System.out.println(String.format("Autor: %s Titulo: %s", x.getAutor().getNombre(),x.getTitulo())));
	}
	
	@Test
	@Order(11)
	public void comentariosDeEntrada() {
		se.getComentarios(se.findById(1).get().getIdentrada()).get().stream().forEach(x->System.out.println(String.format("%s %s %s", x.getEntrada().getTitulo(),x.getUsuario().getNombre(),x.getContenido())));
	}
	
	@Test
	@Order(12)
	public void meGustasDeEntrada() {
		se.getMegustas(se.findById(1).get().getIdentrada()).get().stream().forEach(x->System.out.println(String.format("%s %s",x.getEntrada().getTitulo(),x.getUsuario().getNombre())));
	}
	
	@Test
	@Order(13)
	public void subComentariosDeComentario() {
		sc.getSubComentarios(sc.findById(1).get().getIdcomentario()).get().stream().forEach(x->System.out.printf("%s %s %s %s\n",x.getComentario().getUsuario().getNombre(),x.getComentario().getContenido(),x.getUsuario().getNombre(),x.getContenido()));
	}
	
	@Test
	@Order(14)
	public void modificarUsuario() {
		UsuarioVO u=su.findById(1).get();
		u.setNombre("UnaiM");
		su.save(u);
		assertEquals("UnaiM", su.findById(1).get().getNombre());
	}
	
	
	@Test
	@Order(15)
	public void conversacionEntreUsuarios(){
		su.getConversacionEntreAmigos(1, 2).get().stream().forEach(x->System.out.println(x.getContenido()));;
	}
	
	@Test
	@Order(16)
	public void numAmigosDeUsuario() {
		assertEquals(2, su.numeroAmigos(1));
	}
	
	@Test
	@Order(17)
	public void numComentariosEntradas() {
		assertEquals(3, se.numeroComentarios(1));
	}
	
	@Test
	@Order(18)
	public void numLikesEntradas() {
		assertEquals(1, se.numeroDeLikes(1));
	}
	
	@Test
	@Order(19)
	public void buscarSimilitud() {
		System.out.println(su.findByUsernameContaining("usu").get());
	}
	
	@Test
	@Order(20)
	public void tablonEntradasParaUsuario() {
//		assertEquals(1, su.entradasTablon(1, PageRequest.of(0, 1)).get().size());
	}
//	@Test
//	@Order(21)
//	public void borrarAmistad() {
//		su.borrarAmistad(1, 3);
//	}
	

	
}

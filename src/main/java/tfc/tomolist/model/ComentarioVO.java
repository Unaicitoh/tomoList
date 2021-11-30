package tfc.tomolist.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "comentarios")
public class ComentarioVO {
	@Min(0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idcomentario;
	@Column(length = 150, nullable = false)
	@NotEmpty(message = "Inserta texto en tu comentario")
	private String contenido;
	@Column(nullable = false)
	private LocalDateTime fecha;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "idcomentpadre")
	private ComentarioVO comentario;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "identrada")
	private EntradaVO entrada;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "idusuario")
	private UsuarioVO usuario;
}

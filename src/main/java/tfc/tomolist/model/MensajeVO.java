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
@Table(name = "mensajes")
public class MensajeVO {
	@Min(0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idmensaje;
	@Column(length = 1000, nullable = false)
	@NotEmpty
	private String contenido;
	@Column(nullable = false)
	private LocalDateTime fecha;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="idautor")
	private UsuarioVO autor;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name="idreceptor")
	private UsuarioVO receptor;
}

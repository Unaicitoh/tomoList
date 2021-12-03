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
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "entradas")
public class EntradaVO {
	@Min(0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int identrada;
	@NotEmpty(message = "Titulo obligatorio")
	@Column(length = 70, nullable = false)
	private String titulo;
	@Column(length = 250)
	private String contenido;
	@Size(message = "Tamaño superior a 250 carácteres")
	@NotEmpty(message = "URL de imagen obligatorio")
	@Column(length = 250)
	private String imagen;
	@Column(nullable = false)
	private LocalDateTime fecha;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "idautor")
	private UsuarioVO autor;
	
}

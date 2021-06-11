package tfc.tomolist.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "megustas")
public class MegustaVO {

	@Min(0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idmegusta;
	@ManyToOne
	@JoinColumn(name = "idusuario")
	private UsuarioVO usuario;
	@ManyToOne
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JoinColumn(name = "identrada")
	private EntradaVO entrada;
}

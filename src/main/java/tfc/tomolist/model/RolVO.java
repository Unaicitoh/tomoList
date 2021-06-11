package tfc.tomolist.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "roles")
public class RolVO {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idrol;
	@Column(nullable = false)
	@NotEmpty
	private String nombre;

}

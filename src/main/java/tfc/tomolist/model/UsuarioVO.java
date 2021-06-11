package tfc.tomolist.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "usuarios")
public class UsuarioVO implements UserDetails{
	
	@Min(0)
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idusuario;
	@Column(unique = true, length = 50, nullable = false)
	private String nombre;
	@Email
	@Column(unique = true, length = 45, nullable = false)
	private String email;
	@NotEmpty
	@Column(length = 50, nullable = false, unique = true)
	private String username;
	@NotEmpty
	@Column(length = 500, nullable = false)
	private String password;
	@Column(length = 500, nullable = false)
	private String rawpass;
	@Column(nullable = false)
	private LocalDate fecha;
	@Column(length = 1)
	private char genero;
	@Column(length = 500)
	private String foto;
	@Size(min = 0, max = 1000)
	@Column(length = 1000)
	private String biografia;
	@Column(length = 40)
	private String telefono;
	@Column(length = 45)
	private String direccion;
	private int edad;
	@ManyToOne
	@JoinColumn(name = "idrol")
	private RolVO rol;
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		ArrayList<GrantedAuthority> priv =new ArrayList<GrantedAuthority>();
		
		priv.add(new SimpleGrantedAuthority(rol.getNombre()));
		return priv;
	}
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}

	

	

}

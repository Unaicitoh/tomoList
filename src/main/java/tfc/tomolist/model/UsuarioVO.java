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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import tfc.tomolist.model.validpass.ValidPassword;

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
	
	@Pattern(regexp = "^([a-zA-Z]{2,}\\s[a-zA-Z]{1,}'?-?[a-zA-Z]{2,}\\s?([a-zA-Z]{1,})?)", message = "Nombre Completo solo permite carácteres alfabéticos o expresiones especiales")
	@NotBlank(message="Nombre de usuario no puede estar vacío")
	@Column(length = 50, nullable = false)
	private String nombre;
	@NotBlank(message = "E-mail es obligatorio")
	@Email(message = "Formato de E-mail invalido",regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	@Size(max = 320, message = "E-mail debe ser menor a 320 carácteres")
	@Column(unique = true, length = 320, nullable = false)
	private String email;
	@Pattern(regexp = "^[a-zA-Z0-9._-]+$", message = "Nickname solo permite carácteres alfanuméricos o \"-\",\"_\" y \".\"")
	@NotBlank(message="Nickname no puede estar vacio")
	@Size(min=4,max = 18,message = "El nickname debe tener entre 4 y 14 carácteres")
	@Column(length = 14, nullable = false, unique = true)
	private String username;
	@Column(length = 500, nullable = false)
	private String password;
	@Column(length = 500, nullable = false)
	@ValidPassword
	private String rawpass;
	@Column(nullable = false)
	private LocalDate fecha;
	@Pattern(message = "Género permite Hombre:'H', Mujer:M' u Otro:'O'", regexp = "^[HMO]$")
	@Size(max = 1, message = "Solo un cáracter")
	@Column(length = 1)
	private String genero;
	@Size(max=500, message = "URL de imagen superior a 500 carácteres")
	@Column(length = 500)
	private String foto;
	@Size(min = 0, max = 1000, message = "Biografía superior a 1000 carácteres")
	@Column(length = 1000)
	private String biografia;
	@Size(min = 0, max = 40, message = "Nº telefónico debe tener menos 40 dígitos")
	@Column(length = 40)
	private String telefono;
	@Size(min = 0, max = 45, message = "Dirección demasiado extensa")
	@Column(length = 45)
	private String direccion;
	@Min(16)
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

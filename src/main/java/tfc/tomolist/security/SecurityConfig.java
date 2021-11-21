package tfc.tomolist.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import tfc.tomolist.servicesImp.ServiciosUsuarioImp;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	ServiciosUsuarioImp su;

	@Bean
    public BCryptPasswordEncoder encriptadorPassword() {
        return new BCryptPasswordEncoder();
    }

    public String encriptarPassword(String password) {
        return encriptadorPassword().encode(password);
    }
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(su);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/","/register","/login").permitAll();
		http.authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers("/app/**").hasRole("ESTANDAR");
		http.formLogin().loginPage("/login").defaultSuccessUrl("/success",true).permitAll();
		http.logout().logoutSuccessUrl("/login?logout");

	}
	
	

}

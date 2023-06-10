package com.osba.agenteP.security;

import com.osba.agenteP.repository.EmpleadoRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig{
    @Autowired private JWTFilter jwtFilter;
    @Autowired private MyUserDetailService userDetailService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .httpBasic().disable()
                .cors()
                .and()
                .authorizeHttpRequests((authorize) -> authorize
                    .requestMatchers(HttpMethod.POST,"/auth/login").permitAll()
                    .requestMatchers(HttpMethod.GET, "/empleados/userdata", "/contrato", "/ciudad/**", "/lugar/buscar/**", "/empleados/productividad").authenticated()


                    .requestMatchers(HttpMethod.POST,"/empleados", "/auth/register", "/contrato","/empleados/falta").hasAnyAuthority("Recursos_Humanos", "Admin")
                    .requestMatchers(HttpMethod.DELETE, "/contrato").hasAnyAuthority("Recursos_Humanos", "Admin")
                    .requestMatchers(HttpMethod.PATCH, "/empleados/despedir/**","/contrato/cambiarlugar").hasAnyAuthority("Recursos_Humanos", "Admin")
                    .requestMatchers(HttpMethod.GET, "/empleados", "/empleados/**","/contrato/reportemodificaciones","/contrato/concluir","/contrato/concluir/{id}","/productividad/promedio","/contrato/vacaciones/**", "contrato/diasvacaciones/**",
                            "/contrato/empleadosLugar/**","/empleados/avance/**","/objetivo/objetivos","/objetivo/crearObjetivo","/inventario/productosCaducar","/inventario/reabasteciemtoRecomendado", "/inventario/productos","/inventario/porLugar/**",
                            "/lugar", "/inventario/articulosDistintos", "/reabastecimiento/crearReabastecimiento","/externo/datosProveedor/**", "/externo/ExistenciaProveedor/**").hasAnyAuthority("Recursos_Humanos", "Admin")


                        .anyRequest().denyAll()

                )

                .userDetailsService(userDetailService)
                .exceptionHandling()
                    .authenticationEntryPoint( (request, response, authException) ->
                        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "No autorizado")

                )
                .and()
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
        http.httpBasic(withDefaults());

        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager customAuthenticationManager(MyUserDetailService userDetailsService, PasswordEncoder encoder) {
        return authentication -> {
            String username = authentication.getPrincipal() + "";
            String password = authentication.getCredentials() + "";

            UserDetails user = userDetailsService.loadUserByUsername(username);

            if (!encoder.matches(password, user.getPassword())) {
                throw new BadCredentialsException("Bad credentials");
            }

            if (!user.isEnabled()) {
                throw new DisabledException("User account is not active");
            }

            return new UsernamePasswordAuthenticationToken(username, null, user.getAuthorities());
        };
    }



}

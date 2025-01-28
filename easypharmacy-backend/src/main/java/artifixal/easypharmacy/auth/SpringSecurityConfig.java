package artifixal.easypharmacy.auth;

import artifixal.easypharmacy.auth.jwt.JWTAuthConverter;
import artifixal.easypharmacy.auth.jwt.JWTAuthenticationManager;
import artifixal.easypharmacy.auth.jwt.JwtRequestFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 *
 * @author ArtiFixal
 */
@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SpringSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityWebFilterChain webFilterChain(ServerHttpSecurity http,JWTAuthenticationManager jwtAuthenticationManager,
        JWTAuthConverter jwtAuthConverter,JwtRequestFilter jwtRequestFilter) throws Exception
    {
        jwtRequestFilter.setServerAuthenticationConverter(jwtAuthConverter);
        return http
            .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable)
            .formLogin(ServerHttpSecurity.FormLoginSpec::disable)
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .addFilterAt(jwtRequestFilter,SecurityWebFiltersOrder.AUTHENTICATION)
            .authorizeExchange((exchange)->{
                exchange.pathMatchers("/v1/*/login","/v1/client/register")
                    .permitAll()
                    .anyExchange()
                    .authenticated();
            }).build();
    }
}

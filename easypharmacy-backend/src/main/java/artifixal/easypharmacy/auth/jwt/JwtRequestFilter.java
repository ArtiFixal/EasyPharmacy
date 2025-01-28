package artifixal.easypharmacy.auth.jwt;

import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * Filters JWT authentication.
 * 
 * @author ArtiFixal
 */
@Component
public class JwtRequestFilter extends AuthenticationWebFilter{
    
    private final JWTAuthConverter jwtAuthConverter;
    private final JWTAuthenticationManager jwtAuthenticationManager;

    public JwtRequestFilter(JWTAuthConverter jwtAuthConverter,JWTAuthenticationManager authenticationManager){
        super(authenticationManager);
        this.jwtAuthenticationManager=authenticationManager;
        this.jwtAuthConverter=jwtAuthConverter;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange,WebFilterChain chain){
        // Ignore these endpoints they don't have token yet
        String path=exchange.getRequest().getPath().value();
        if(path.equals("/client/login")||path.equals("/employee/login")||path.equals("/client/register"))
            return chain.filter(exchange);
        return jwtAuthConverter.convert(exchange)
            .flatMap((auth)->{
                return jwtAuthenticationManager.authenticate(auth)
                    .flatMap((mgrAuth)->{
                        return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder
                        .withAuthentication(mgrAuth));
                    });
            });
    }

}

package artifixal.easypharmacy.auth.jwt;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.server.authentication.ServerAuthenticationConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * Converts auth header into authentiaction token.
 * 
 * @author ArtiFixal
 */
@Component
@AllArgsConstructor
public class JWTAuthConverter implements ServerAuthenticationConverter{

    private final static String BEARER_HEADER="Bearer ";
    private JwtService jwtService;
    
    @Override
    public Mono<Authentication> convert(ServerWebExchange exchange){
        return Mono.justOrEmpty(exchange.getRequest()
            .getHeaders()
            .getFirst(HttpHeaders.AUTHORIZATION)
        ).filter((authHeader)->authHeader.startsWith(BEARER_HEADER))
            .map((authHeader)->authHeader.substring(BEARER_HEADER.length()))
            .map((token)->{
                Claims claims=jwtService.getClaims(token);
                String email=jwtService.validateToken(token);
                return new UsernamePasswordAuthenticationToken(email,token,AuthorityUtils.NO_AUTHORITIES);
            });
    }
}

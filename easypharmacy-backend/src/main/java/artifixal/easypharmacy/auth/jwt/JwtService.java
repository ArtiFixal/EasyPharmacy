package artifixal.easypharmacy.auth.jwt;

import artifixal.easypharmacy.auth.AuthenticationException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecureDigestAlgorithm;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.crypto.SecretKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Generates JWT and retreives data from it.
 * 
 * @author ArtiFixal
 */
@Service
public class JwtService{
    
    private String jwtSecret;
    
    private final SecretKey key;

    @Autowired
    public JwtService(){
        jwtSecret="487708bff86ecc701bd32947abb7d18e24cb6614253f2967ee4055e1f2271dc84da74a1dbfccc";
        key=Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }
    
    /**
     * Default expire date 15min.
     */
    private final static long EXPIRE_DATE=15;
    
    /**
     * Generates new signed token for the given user.
     * 
     * @param username For whom to generate the token.
     * 
     * @return Generated and signed token.
     */
    public String generateToken(String username){
        return Jwts.builder()
            .subject(username)
            .issuedAt(new Date())
            .expiration(new Date(Instant.now()
                .plus(EXPIRE_DATE,ChronoUnit.MINUTES)
                .toEpochMilli())
            ).signWith(key,(SecureDigestAlgorithm)Jwts.SIG.HS256)
            .compact();
    }
    
    public String getEmail(String token){
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }
    
    public Claims getClaims(String token){
        return Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }
    
    /**
     * Validates token.
     * 
     * @param token What to validate.
     * 
     * @return User email.
     * 
     * @throws AuthenticationException If token expired or there is no subject.
     */
    public String validateToken(String token){
        Claims claims=Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token)
            .getPayload();
        if(claims.getSubject()==null)
            throw new AuthenticationException("Malformed token");
        if(claims.getExpiration().before(new Date()))
            throw new AuthenticationException("Token expired");
        return claims.getSubject();
    }
}

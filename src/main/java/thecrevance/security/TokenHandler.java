package thecrevance.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by emmanuel on 10/5/17.
 */
@Component
public class TokenHandler {

    private final String SECRET = "wfgcompetence";

    private final long EXPIRATION = 1000 * 60 * 60 * 24 * 10;//ten days

    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser()
                    .setSigningKey(SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    public String getUsernameFromToken(String token) {
        String username;
        try {
            final Claims claims = getClaimsFromToken(token);
            username = claims.get("username", String.class);
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    public Date getExpirationDateFromToken(String token) {
        Date expiration;
        try {
            final Claims claims = getClaimsFromToken(token);
            expiration = claims.getExpiration();
        } catch (Exception e) {
            expiration = null;
        }
        return expiration;
    }


    private Date generateCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + EXPIRATION * 1000);
    }

    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }



    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", userDetails.getUsername());
        //claims.put("device", generateDevice(device));
        return generateClaims(claims);
    }

    public String generateClaims(Map<String, Object> claims) {
        JwtBuilder jwtBuilder = Jwts.builder();
        jwtBuilder.setClaims(claims);
        jwtBuilder.setIssuedAt(generateCurrentDate());
        jwtBuilder.setExpiration(generateExpirationDate());
        jwtBuilder.signWith(SignatureAlgorithm.HS512, SECRET);
        return jwtBuilder.compact();
    }

    public Boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (
                username.equals(userDetails.getUsername())
                        && !isTokenExpired(token)

        );
    }
}

package org.fitlink.fitlinkbackend.Security;


import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cglib.core.internal.Function;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
public class JwtUtils {
    private static  final Logger logger = LoggerFactory.getLogger(JwtUtils.class);


    @org.springframework.beans.factory.annotation.Value("${fitlink.jwt.secret:mySecretKey}")
    private String jwtSecret ;
    @Value("${fitlink.jwt.expiration:86400}")
    private int jwtExpirationMs;
    private SecretKey getSignIngKey() {
        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
    }

    public String generateToken( UserDetails userDetails) {
        Map<String, Object>  claims = new HashMap<>();
        return generateToken(claims, userDetails.getUsername());
    }

    public String extractUsername ( String token ) {
        return extractClaim( token, Claims::getSubject);

    }
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims, T> claimsResolver) {
         final Claims claims= extractAllClaims( token);
         return claimsResolver.apply(claims);

    }
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignIngKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }




    public String generateToken(Map<String, Object> extraClaims, String username) {
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs * 1000))
                .signWith(getSignIngKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

   public Boolean  validateToken(String token, UserDetails userDetails) {
final String username= extractUsername(token );

 return ( username.equals(  userDetails.getUsername()) && !isTokenExpired(token));



   }
   public boolean   validateJwtToken( String authToken) {
       try {
           Jwts.parserBuilder()
                   .setSigningKey(getSignIngKey())
                   .build()
                   .parseClaimsJws(authToken);
           return true;
       } catch (MalformedJwtException e) {
           logger.error("Invalid JWT token: {}", e.getMessage());
       } catch (ExpiredJwtException e) {
           logger.error("JWT token is expired: {}", e.getMessage());
       } catch (UnsupportedJwtException e) {
           logger.error("JWT token is unsupported: {}", e.getMessage());
       } catch (IllegalArgumentException e) {
           logger.error("JWT claims string is empty: {}", e.getMessage());
       }
       return false;
   }



}

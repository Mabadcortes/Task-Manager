package com.mabadcortes.taskmanager.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/*
 * Service responsible for generating, parsing, and validating JSON Web Tokens (JWT).
 */
@Service
public class JwtService {

    /*
     * Secret key injected from application.properties.
     */
    @Value("${security.jwt.secret-key}")
    private String secretKey;

    /*
     * Token expiration time (in milliseconds) injected from application.properties.
     */
    @Value("${security.jwt.expiration-time}")
    private long jwtExpiration;

    /*
     * Extracts the username (subject) from the token.
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /*
     * Extracts a specific claim from the token using a resolver function.
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    /*
     * Generates a token for a user whithout extra claims.
     */
    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails) {
        return Jwts
                .builder()
                .claims(extraClaims)
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSignInKey())
                .compact();
    }

    /*
     * Validates if the token belongs to the given user and is not expired.
     */
    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    /*
     * Extracts the expiration date from the token.
     */
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    /*
     * Extracts the expiration date from the token.
     */
    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /*
     * Parses the token to extract all embedded claims.
     */
    public Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    /*
     * Decodes the secret key and returns the cryptographic key used for signing.
     */
    private SecretKey getSignInKey() {
        byte[] keyBites = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBites);
    }
}

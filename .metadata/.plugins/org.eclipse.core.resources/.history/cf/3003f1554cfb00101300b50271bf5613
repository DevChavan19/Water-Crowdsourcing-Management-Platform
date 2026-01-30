package com.watercrowdsourcing.ums_service.security;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;


@Component //to declare a spring bean 
@Slf4j
public class JwtUtils {
	@Value("${jwt.expiration.time}")
	private long jwtExpirationTime;
	@Value("${jwt.secret}")
	private String jwtSecret;
	private SecretKey secretKey;
	
	@PostConstruct
	public void myInit()
	{
		log.info("****** creating secret key {} {} ",jwtSecret,jwtExpirationTime);
		secretKey=Keys.hmacShaKeyFor(jwtSecret.getBytes());		
	}
	//create JWT - header , payload, signature
	public String generateToken(UserPrincipal principal) {
		//iat  
		Date now=new Date();
		//exp
		Date expiresAt=new Date(now.getTime()+jwtExpirationTime);
		return Jwts.builder() //creates a builder fro JWT creation
				.subject(principal.getEmail()) //setting subject
				.issuedAt(now) //iat
				.expiration(expiresAt) //exp
				//custom claims - user id & user role
				.claims(Map.of("user_id",String.valueOf(principal.getUserId())
						, "user_role", principal.getUserRole()))
				.signWith(secretKey)//sign the JWT
				.compact();
				
	}
	public Claims validateToken(String jwt) {
		return Jwts.parser() //attach a parser
				.verifyWith(secretKey)
				.build() //builds JwtsParser
				.parseSignedClaims(jwt)
				.getPayload();
		
	}
	
	// Extract username/email from token
	public String extractUsername(String token) {
        Claims claims = validateToken(token);
        return claims != null ? claims.getSubject() : null;
    }

    public boolean isTokenExpired(String token) {
        Claims claims = validateToken(token);
        return claims != null && claims.getExpiration().before(new Date());
    }

    public String extractUserRole(String token) {
        return extractClaim(token, claims -> claims.get("user_role", String.class));
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = validateToken(token);
        return claimsResolver.apply(claims);
    }


}

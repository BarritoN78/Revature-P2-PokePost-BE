package com.personal.revaturep2pokepostbe.advice;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import com.personal.revaturep2pokepostbe.annotations.Authorized;
import com.personal.revaturep2pokepostbe.exceptions.UnauthorizedException;
import com.personal.revaturep2pokepostbe.models.User;
import com.personal.revaturep2pokepostbe.services.TokenService;

public class AuthAspect {
	private final TokenService tokenServ;
	private final HttpServletRequest httpReq;

	public AuthAspect(TokenService tokenServ, HttpServletRequest httpReq) {
		this.tokenServ = tokenServ;
		this.httpReq = httpReq;
	}

	@Pointcut("@annotation(com.personal.revaturep2pokepostbe.annotations.Authorized)")
	public Object authenticate(ProceedingJoinPoint joinpoint) throws Throwable {
		Authorized authAnnotation = ((MethodSignature) joinpoint.getSignature()).getMethod()
				.getAnnotation(Authorized.class);
		final boolean selfOrAdminReq = authAnnotation.requireSelfOrAdmin();
		final String jwt = httpReq.getHeader("Auth");
		final String username = httpReq.getHeader("username");
		Optional<User> user;

		// Validating Token
		if (jwt == null || jwt.equals("")) {
			throw new UnauthorizedException("No token found!");
		}
		user = tokenServ.validateToken(jwt);

		// Validating Username If required
		if (selfOrAdminReq) {
			try {
				if (!username.equals(user.get().getUsername()) && !user.get().getRole().equals("admin")) {
					throw new UnauthorizedException("This action requires you to be logged in as the author or an admin!");
				}
			} catch (NoSuchElementException e) {
				throw new UnauthorizedException("No user object found!");
			}
		}
		return joinpoint.proceed();

	}
}

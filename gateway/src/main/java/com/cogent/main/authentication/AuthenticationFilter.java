package com.cogent.main.authentication;

import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;

import com.cogent.main.utility.JWTUtil;
import com.cogent.main.utility.RouteValidator;



@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

	public static class Config {

	}

	public AuthenticationFilter() {
		super(Config.class);
	}

	@Autowired
	private RouteValidator validator;

	@Autowired
	private JWTUtil jwtUtil;


	@Override
	public GatewayFilter apply(Config config) {

		return ((exchange, chain) -> {

			if (validator.isSecured.test(exchange.getRequest())) {

				if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
					throw new RuntimeException("Header not Found");
				}
				String jwtToken = "";
				String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
				if (authHeader != null && authHeader.startsWith("Bearer ")) {
					jwtToken = authHeader.substring(7);
				 
				}

			    jwtUtil.validateToken(jwtToken);
			    
			}

			return chain.filter(exchange);
		});
	}

}


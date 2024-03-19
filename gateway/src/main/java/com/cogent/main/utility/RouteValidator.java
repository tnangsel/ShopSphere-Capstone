package com.cogent.main.utility;

import java.util.List;
import java.util.function.Predicate;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

@Component
public class RouteValidator {
	
	List<String> openApiEndPoints = List.of("/auth/**", "/products/**", "/discount/**");
	
	public Predicate<ServerHttpRequest> isSecured = request -> openApiEndPoints.stream()
											.noneMatch(uri -> request.getURI().getPath().contains(uri));

}
			
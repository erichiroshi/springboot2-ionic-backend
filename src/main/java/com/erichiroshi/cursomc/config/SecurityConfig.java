package com.erichiroshi.cursomc.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.erichiroshi.cursomc.security.JWTAuthenticationFilter;
import com.erichiroshi.cursomc.security.JWTAuthorizationFilter;
import com.erichiroshi.cursomc.security.JWTUtil;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
	private AuthenticationConfiguration configuration;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
    private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;

	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	private static final String[] PUBLIC_MATCHERS_GET = { "/produtos/**", "/categorias/**" };

	private static final String[] PUBLIC_MATCHERS_POST = { "/clientes/**" };

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.authorizeHttpRequests().requestMatchers("/h2-console/**").permitAll();
			http.headers().frameOptions().sameOrigin();
		    http.headers().frameOptions().disable();
		}

	        http.cors().and().csrf().disable();
	        http.authorizeHttpRequests()
    			.requestMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
                .requestMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
                .requestMatchers(PUBLIC_MATCHERS).permitAll()
                .anyRequest().authenticated();
	        
			http.addFilter(new JWTAuthenticationFilter(configuration.getAuthenticationManager(), jwtUtil));
			http.addFilter(new JWTAuthorizationFilter(configuration.getAuthenticationManager(), jwtUtil, userDetailsService));
			http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			return http.build();
		}

		public void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
		}

		@Bean
		CorsConfigurationSource corsConfigurationSource() {
			final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
			source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
			return source;
		}

		@Bean
		public BCryptPasswordEncoder bCryptPasswordEncoder() {
			return new BCryptPasswordEncoder();
		}

	}
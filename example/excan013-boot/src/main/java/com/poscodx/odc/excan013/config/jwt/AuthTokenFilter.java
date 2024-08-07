package com.poscodx.odc.excan013.config.jwt;

import com.poscdx.odc.excan013.domain.lifecycle.ServiceLifecycle;
import com.poscodx.odc.excan013.config.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthTokenFilter extends OncePerRequestFilter {
  @Autowired
  private JwtUtils jwtUtils;

  @Autowired
  private ServiceLifecycle serviceLifecycle;

  @Autowired
  private UserDetailsServiceImpl userDetailsService;

//  private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    String jwt = parseJwt(request);
    if(serviceLifecycle.requestExcanAccessTokenService().findByToken(jwt).isEmpty()){//check token in blacklist
      if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
//          String username = jwtUtils.getUserNameFromJwtToken(jwt);
        String id = jwtUtils.getUserIdFromJwtToken(jwt);

//          UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UserDetails userDetails = userDetailsService.loadUserById(id);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        null,
                        userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        SecurityContextHolder.getContext().setAuthentication(authentication);
      }
    }

    filterChain.doFilter(request, response);
  }

  private String parseJwt(HttpServletRequest request) {
    String headerAuth = request.getHeader("Authorization");

    if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
      return headerAuth.substring(7);
    }

    return null;
  }
}

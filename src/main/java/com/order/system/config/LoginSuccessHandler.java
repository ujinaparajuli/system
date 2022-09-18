package com.order.system.config;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.order.system.model.ItemCart;
import com.order.system.service.WelcomeService;
import com.order.system.service.impl.WelcomeServiceImpl;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
	
	@Autowired
	WelcomeService welcomeService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
    	SimpleUrlAuthenticationSuccessHandler chefSuccessHandler =
                new SimpleUrlAuthenticationSuccessHandler("/chef");
        SimpleUrlAuthenticationSuccessHandler adminSuccessHandler =
                new SimpleUrlAuthenticationSuccessHandler("/admin");
        SimpleUrlAuthenticationSuccessHandler userSuccessHandler =
                new SimpleUrlAuthenticationSuccessHandler("/checkout");
        SimpleUrlAuthenticationSuccessHandler defaultSuccessHandler =
                new SimpleUrlAuthenticationSuccessHandler("/");
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        for (final GrantedAuthority grantedAuthority : authorities) {
            String authorityName = grantedAuthority.getAuthority();
            if (authorityName.equals("ROLE_ADMIN")) {
                // if the user is an ADMIN delegate to the adminSuccessHandler
                adminSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            }else if (authorityName.equals("ROLE_CHEF")) {
                // if the user is an Chef delegate to the adminSuccessHandler
                chefSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            }else if (authorityName.equals("ROLE_USER")) {
//            	List<ItemCart> itemsInSession = (List<ItemCart>) request.getSession().getAttribute("CART_SESSION");
//            	welcomeService.saveCartSession(itemsInSession);
                userSuccessHandler.onAuthenticationSuccess(request, response, authentication);
                return;
            }
        }
        // if the user is not an admin delegate to the userSuccessHandler
        defaultSuccessHandler.onAuthenticationSuccess(request, response, authentication);
    }
}

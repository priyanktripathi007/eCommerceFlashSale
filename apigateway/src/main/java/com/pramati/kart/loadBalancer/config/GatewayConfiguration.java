package com.pramati.kart.loadBalancer.config;


import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

}/*extends ResourceServerConfigurerAdapter {
    @Override
    public void configure(final HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index.html","/login.html", "/login", "/")
                .permitAll()
                .antMatchers("/**")
                .authenticated()
                .and()
                .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
        // @formatter:on
              //  .antMatchers("/user").hasRole("ADMIN")

    }


}*/
package iyun.anche.auth.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * 〈资源认证服务器〉
 */
@Configuration
@EnableResourceServer
//@Order(3)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
//    @Override
//    public void configure(HttpSecurity http) throws Exception {
//        http
//                .csrf().disable()
//                .exceptionHandling()
//                .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//                .and()
//                .requestMatchers().antMatchers("/api/**")
//                .requestMatchers().antMatchers("/**")
//                .and()
//                .authorizeRequests()
////                .mvcMatchers("/right/**").authenticated()
//                .antMatchers("/api/**").authenticated()
//                .anyRequest().authenticated()
//                .and()
//                .httpBasic();
//    }

    @Override
    public void configure(HttpSecurity http) throws Exception {

        http.
                csrf().disable()
                .exceptionHandling()
                .and()
                .authorizeRequests()
                .mvcMatchers("/imageCode").permitAll()
                .mvcMatchers("/swagger-ui.html").permitAll()
                .mvcMatchers("/swagger-resources/**").permitAll()
                .mvcMatchers("/v2/api-docs").permitAll()
                .mvcMatchers("/token/**").permitAll()
                .antMatchers("/druid/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .requestMatchers().antMatchers("/**");
//                .and()
//                .httpBasic();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 配置自定义异常
        resources.authenticationEntryPoint(new AuthExceptionEntryPoint())
                .accessDeniedHandler(new CustomAccessDeniedHandler());
    }
}

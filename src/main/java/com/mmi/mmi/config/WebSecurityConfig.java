//package com.mmi.mmi.config;
//
//import org.springframework.data.web.config.EnableSpringDataWebSupport;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//@EnableSpringDataWebSupport
//public class WebSecurityConfig extends WebSecurityConfigur {
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//            // by default uses a Bean by the name of corsConfigurationSource
//            .cors().and()
//            ...
//    }
//
//    @Bean
//    CorsConfigurationSource corsConfigurationSource() {
//        CorsConfiguration configuration = new CorsConfiguration();
//        configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
//        configuration.setAllowedMethods(Arrays.asList("GET","POST"));
//        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//        source.registerCorsConfiguration("/**", configuration);
//        return source;
//    }
//}

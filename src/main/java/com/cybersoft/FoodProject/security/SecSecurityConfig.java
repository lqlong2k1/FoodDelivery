package com.cybersoft.FoodProject.security;

import com.cybersoft.FoodProject.jwt.JwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecSecurityConfig {
    /*
     * Dùng để khởi tạo danh sách user cứng và danh sách user này sẽ được lưu trong RAM
     * */
    /*
    @Bean
    public InMemoryUserDetailsManager userDetailService() {
        UserDetails user1 = User.withUsername("user1")
                .password(passwordEncoder().encode("user1Pass"))
                .roles("USER")
                .build();
        UserDetails user2 = User.withUsername("user2")
                .password(passwordEncoder().encode("user2Pass"))
                .roles("USER")
                .build();
        UserDetails admin = User.withUsername("admin")
                .password(passwordEncoder().encode("adminPass"))
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user1, user2, admin);
    }
    */

    @Autowired
    CustomeAuthenProvider customeAuthenProvider;

    @Autowired
    JwtTokenFilter jwtTokenFilter;

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(customeAuthenProvider);
        return authenticationManagerBuilder.build();
    }

    //Mã hóa password
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Quy định các rule  liên quan tới bảo mật và quyền truy cập...
     * */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        /*
         * anMatchers(): Định nghĩa link cần xác thực
         * authenticated(): Bắt buộc phải chứng thực (đăng nhập) mới dc thực hiện dc
         * permitAll(): Cho phép truy cập full quyền truy cập vào linh đã xác định ở anMatchers
         * anyRequest(): toàn bộ link
         * */
        http.csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                //DÒNG TRÊN: không dùng session. Nếu ở những nơi khác setSesion thì cũng ko thể dùng session dc.. vì cái này đã chặn dùng session
                .and()
                .authorizeRequests()
                .antMatchers("/signin").permitAll()
                .antMatchers("/refresh-token").permitAll()
                .antMatchers("/signin/test").authenticated()
                .anyRequest().authenticated();
        /*
         * Thêm 1 filer trc một filter nào đó
         * */
        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }


}

//package com.xiao.zuul.config;
//
//import com.xiao.zuul.service.MyUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@EnableWebSecurity
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//    @Autowired
//    MyUserDetailsService myUserDetailsService;
//
//    /**
//     * 匹配 "/" 路径，不需要权限即可访问
//     * 登录地址为 "/login"
//     * 退出登录的地址为 "/logout"，退出成功后跳转到页面 "/login"
//     * 默认启用 CSRF 赋权
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .and()
//                .formLogin()
//                .defaultSuccessUrl("/home")
//                .permitAll()
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .logoutSuccessUrl("/login")
//                .permitAll();
//    }
//
////    /**
////     * 在内存中创建一个名为 "user" 的用户，密码为 "pwd"，拥有 "USER" 权限
////     */
////    @Bean
////    @Override
////    protected UserDetailsService userDetailsService() {
////        User.UserBuilder users = User.withDefaultPasswordEncoder();
////        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
////        manager.createUser(users.username("usercenter").password("usercenter").roles("USER").build());
////        return manager;
////    }
//
//    /**
//     * 添加 UserDetailsService， 实现自定义登录校验
//     */
//    @Override
//    protected void configure(AuthenticationManagerBuilder builder) throws Exception{
//        builder.userDetailsService(myUserDetailsService);
//    }
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance();
//    }
//}
//

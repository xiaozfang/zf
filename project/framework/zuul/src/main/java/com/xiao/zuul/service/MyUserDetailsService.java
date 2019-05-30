//package com.xiao.zuul.service;
//
//import com.xiao.zuul.pojo.RoleInfo;
//import com.xiao.zuul.pojo.UserInfo;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//@Slf4j
//@Service
//public class MyUserDetailsService implements UserDetailsService {
//    private final IUserService userService;
//
//    @Autowired
//    public MyUserDetailsService(IUserService userService) {
//        this.userService = userService;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
//        UserInfo user = userService.getUserInfo(userName);
//        if (user == null){
//            log.error("用户名错误【{}】",userName);
//            throw new UsernameNotFoundException(userName + "NOT FOUND");
//        }
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//        for (RoleInfo role : user.getRoles()){
//            authorities.add(new SimpleGrantedAuthority(role.getRolecode()));
//        }
//        return new User(user.getUsername(), user.getPassword(), authorities);
//    }
//}

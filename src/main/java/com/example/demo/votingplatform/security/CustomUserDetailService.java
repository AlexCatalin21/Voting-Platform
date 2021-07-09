//package com.example.demo.votingplatform.security;
//
//import com.example.demo.votingplatform.auth.model.User;
//import com.example.demo.votingplatform.auth.repository.UserRepository;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Component;
//
//import java.util.stream.Collectors;
//
//@Component
//public class CustomUserDetailService implements UserDetailsService {
//
//    private UserRepository users;
//
//    public CustomUserDetailService(UserRepository users) {
//        this.users = users;
//    }
//
//    /**
//     * Loads the user from the DB and converts it to Spring Security's internal User object.
//     * Spring will call this code to retrieve a user upon login from the DB.
//     */
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        User user = users.getByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException("Email: " + email + " not found"));
//
//        return new User(user.getEmail(), user.getPassword(),
//                user.getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));
//    }
//}
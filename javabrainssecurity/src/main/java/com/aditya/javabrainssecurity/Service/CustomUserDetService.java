package com.aditya.javabrainssecurity.Service;

import com.aditya.javabrainssecurity.Models.Role;
import com.aditya.javabrainssecurity.Models.UserEntity;
import com.aditya.javabrainssecurity.Repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class CustomUserDetService implements UserDetailsService {

    @Autowired
    private UserRepo userrepo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = userrepo.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User Name Not found"));

        return new User(user.getUsername(), user.getPassword(),  mapRolesToAuthorities(user.getRoles()));
    }
        private Collection<GrantedAuthority> mapRolesToAuthorities(List<Role> roles) {
            return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
        }
    }


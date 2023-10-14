package com.simple.project.security;

import com.simple.project.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String userName;
    private final String password;
    private final List<GrantedAuthority> grantedAuthorities;

    public CustomUserDetails(User user) {
        this.userName = user.getEmail();
        this.password = user.getPassword();
//        this.grantedAuthorities= Arrays.stream(user.getRoles().split(","))
//                .map(SimpleGrantedAuthority::new)
//                .collect(Collectors.toList());
        this.grantedAuthorities = null;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

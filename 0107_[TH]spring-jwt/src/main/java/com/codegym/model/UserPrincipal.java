package com.codegym.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

//------ lớp UserPrinciple để có thể custom lại các thuộc tính của lớp UserDetails có sẵn trong Spring.
public class UserPrincipal implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String password;

    private Collection<? extends GrantedAuthority> roles;

    public UserPrincipal() {
    }

    public UserPrincipal(Long id, String username, String password, Collection<? extends GrantedAuthority> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public static UserPrincipal build(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        Set<Role> roles = user.getRoles();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return new UserPrincipal(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPrincipal user = (UserPrincipal) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

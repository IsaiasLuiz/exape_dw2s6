package br.edu.ifsp.arq.exape_dw2.domain.model;

import java.util.ArrayList;
import java.util.Collection;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;


public class UserCustomDetails extends User {

    private static final long serialVersionUID = 1L;

    @Getter
    @Setter
    private Long id;

    public UserCustomDetails(UserEntity user) {
        super(user.getUsername(), user.getPassword(), getRoles());
        this.id = user.getId();
    }

    private static Collection<GrantedAuthority> getRoles() {
        Collection<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        return roles;
    }

}

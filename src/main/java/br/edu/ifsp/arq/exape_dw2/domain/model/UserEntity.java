package br.edu.ifsp.arq.exape_dw2.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    private Long id;

    private String username;

    private String password;

    private String name;

    private String mail;

    private Role role;

    public UserEntity(UserEntity userEntity) {
        super();
        this.id = userEntity.getId();
        this.username = userEntity.getUsername();
        this.password = userEntity.getPassword();
        this.name = userEntity.getName();
        this.mail = userEntity.getMail();
        this.role = userEntity.getRole();
    }

}

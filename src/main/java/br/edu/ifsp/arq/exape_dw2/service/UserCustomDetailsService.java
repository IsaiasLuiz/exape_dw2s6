package br.edu.ifsp.arq.exape_dw2.service;

import br.edu.ifsp.arq.exape_dw2.domain.model.UserEntity;
import br.edu.ifsp.arq.exape_dw2.domain.model.UserCustomDetails;
import br.edu.ifsp.arq.exape_dw2.repository.UserRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserCustomDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsernameIgnoreCase(username);
        if (userEntity != null) {
            return new UserCustomDetails(userEntity);
        }
        throw new UsernameNotFoundException("Usuário e/ou senha inválida");
    }

}


package gym.gymbackend.service;


import gym.gymbackend.dto.PersonDto;
import gym.gymbackend.model.Authority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonService personService;

    @Override
    public UserDetails loadUserByUsername(String username) {
        PersonDto userDto = personService.getPerson(username);
        String password = userDto.getPassword();
        Set<Authority> authorities = userDto.getAuthorities();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        for (Authority authority: authorities) {
            grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority()));
        }

        return new org.springframework.security.core.userdetails.User(username, password, grantedAuthorities);
    }

}

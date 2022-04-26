package gym.gymbackend.service;

import gym.gymbackend.config.JwtUtil;
import gym.gymbackend.exceptions.BadRequestException;
import gym.gymbackend.payload.AuthenticationRequest;
import gym.gymbackend.payload.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class PersonAuthenticateService {

    @Autowired
    JwtUtil jwtUtl;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private CustomUserDetailsService userDetailsService;

    public AuthenticationResponse authenticatePerson(AuthenticationRequest authenticationRequest) {
        try {
            String username = authenticationRequest.getUsername();
            String password = authenticationRequest.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );
            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            final String jwt = jwtUtl.generateToken(userDetails);
            return new AuthenticationResponse(jwt);
        } catch (Exception ex) {
            throw new BadRequestException("Incorrect username or password");
        }
    }

}

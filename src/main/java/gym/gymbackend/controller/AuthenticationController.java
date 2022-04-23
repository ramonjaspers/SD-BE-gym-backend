package gym.gymbackend.controller;

import gym.gymbackend.payload.AuthenticationRequest;
import gym.gymbackend.payload.AuthenticationResponse;
import gym.gymbackend.service.PersonAuthenticateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(value = "")
public class AuthenticationController {

    PersonAuthenticateService personAuthenticateService;

    @Autowired
    public AuthenticationController(PersonAuthenticateService personAuthenticateService) {
        this.personAuthenticateService = personAuthenticateService;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {

        AuthenticationResponse authenticationResponse = personAuthenticateService.authenticatePerson(authenticationRequest);

        return ResponseEntity.ok(authenticationResponse);
    }
}

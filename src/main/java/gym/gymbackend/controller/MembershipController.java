package gym.gymbackend.controller;

import gym.gymbackend.dto.MembershipDto;
import gym.gymbackend.model.Membership;
import gym.gymbackend.service.MembershipService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/memberships")
public class MembershipController {

    private final MembershipService service;

    public MembershipController(MembershipService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Membership>> getMemberships() {
        List<Membership> employees = service.getMemberships();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Object> getMembership(@PathVariable String name) {
        try {
            Membership membership = service.getMembership(name);
            return new ResponseEntity<>(membership, HttpStatus.OK);
        } catch (Error e) {
            return new ResponseEntity<>("No membership found", HttpStatus.NOT_FOUND);
        }
    }


    @PostMapping(value = "")
    public ResponseEntity<Object> createMembership(@Valid @RequestBody MembershipDto membership, BindingResult br) {
        if (br.hasErrors()) {
            StringBuilder sb = new StringBuilder();
            for (FieldError fe : br.getFieldErrors()) {
                sb.append(fe.getField()).append(" ").append(fe.getDefaultMessage());
                sb.append("\n");
            }
            return new ResponseEntity<>(sb.toString(), HttpStatus.BAD_REQUEST);
        }

        service.createMembership(membership);
        return new ResponseEntity<>("Membership created", HttpStatus.CREATED);
    }


    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Object> deleteMembership(@PathVariable String name) {
        try {
            service.getMembership(name);
            service.deleteMembership(name);
            return new ResponseEntity<>(name + " removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Membership does not exist", HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "/{name}")
    public ResponseEntity<Object> updateMembership(@PathVariable String name, @RequestBody MembershipDto membershipDto) {
        service.updateMembership(name, membershipDto);
        return new ResponseEntity<>(name + " membership fields updated", HttpStatus.OK);
    }

}

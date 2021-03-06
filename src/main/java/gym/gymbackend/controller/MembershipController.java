package gym.gymbackend.controller;

import gym.gymbackend.dto.MembershipDto;
import gym.gymbackend.model.Membership;
import gym.gymbackend.service.MembershipService;
import gym.gymbackend.utils.BindingResultErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/memberships")
public class MembershipController {
    private final MembershipService service;

    @Autowired
    public MembershipController(MembershipService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Membership>> getMemberships() {
        List<Membership> memberships = service.getMemberships();
        return new ResponseEntity<>(memberships, HttpStatus.OK);
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<Object> getMembership(@PathVariable String name) {
        try {
            Membership membership = service.getMembership(name);
            return new ResponseEntity<>(membership, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No membership found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createMembership(@Valid @RequestBody MembershipDto membership, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(BindingResultErrorHandler.bindingErrorsToString(br), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createMembership(membership);
            return new ResponseEntity<>("Membership created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{name}")
    public ResponseEntity<Object> deleteMembership(@PathVariable String name) {
        try {
            service.deleteMembership(name);
            return new ResponseEntity<>(name + " removed", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed removing membership: Membership does not exist or subscriptions are connected", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "")
    public ResponseEntity<Object> updateMembership(@Valid @RequestBody MembershipDto membershipDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(BindingResultErrorHandler.bindingErrorsToString(br), HttpStatus.BAD_REQUEST);
        }
        try {
            service.updateMembership(membershipDto);
            return new ResponseEntity<>(membershipDto.getName() + " updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}

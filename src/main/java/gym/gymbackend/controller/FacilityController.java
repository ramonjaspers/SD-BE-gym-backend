package gym.gymbackend.controller;

import gym.gymbackend.dto.FacilityDto;
import gym.gymbackend.model.Facility;
import gym.gymbackend.service.FacilityService;
import gym.gymbackend.utils.BindingResultErrorHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/facilities")
public class FacilityController {
    private final FacilityService service;

    @Autowired
    public FacilityController(FacilityService service) {
        this.service = service;
    }

    @GetMapping(value = "")
    public ResponseEntity<List<Facility>> getFacilities() {
        List<Facility> facilities = service.getFacilities();
        return new ResponseEntity<>(facilities, HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getFacility(@PathVariable Long id) {
        try {
            Facility facilities = service.getFacility(id);
            return new ResponseEntity<>(facilities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No facility found", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/membership/{membership}")
    public ResponseEntity<Object> getFacilityByMembership(@PathVariable String membership) {
        try {
            List<Facility> facilities = service.getFacilitiesByMembership(membership);
            return new ResponseEntity<>(facilities, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("No facility found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(value = "")
    public ResponseEntity<Object> createFacility(@Valid @RequestBody FacilityDto facilityDto, BindingResult br) {
        if (br.hasErrors()) {
            return new ResponseEntity<>(BindingResultErrorHandler.bindingErrorsToString(br), HttpStatus.BAD_REQUEST);
        }
        try {
            service.createFacility(facilityDto);
            return new ResponseEntity<>("Facility created", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> deleteFacility(@PathVariable Long id) {
        try {
            service.deleteFacility(id);
            return new ResponseEntity<>("Facility deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed removing facility, check if there are activities connected to this facility and remove these first", HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateFacility(@PathVariable Long id, @Valid @RequestBody FacilityDto facilityDto) {
        service.updateFacility(id, facilityDto);
        return new ResponseEntity<>("Facility fields updated", HttpStatus.OK);
    }
}

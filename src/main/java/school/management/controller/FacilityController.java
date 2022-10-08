package school.management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import school.management.entity.Facility;
import school.management.service.FacilityService;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/api/v1/facilities")
@RequiredArgsConstructor
public class FacilityController {

    private final FacilityService facilityService;

    @GetMapping
    public Page<Facility> getFacilities(Pageable pageable) {
        return facilityService.getAllFacilities(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Facility> getFacility(@PathVariable Integer id) {
        Facility facility = facilityService.getFacility(id);
        return ResponseEntity.ok().body(facility);
    }

    @PostMapping
    public ResponseEntity<Facility> createFacility(@RequestBody @Valid Facility facilityRequest) {
        Facility facility = facilityService.createFacility(facilityRequest);
        URI location = URI.create("/api/v1/facilities/" + facility.getId());
        return ResponseEntity.created(location).body(facility);
    }


}

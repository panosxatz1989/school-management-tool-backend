package school.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.management.entity.Facility;
import school.management.repository.FacilityRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class FacilityService {

    private final FacilityRepository facilityRepository;

    public Page<Facility> getAllFacilities(Pageable pageable) {
        return facilityRepository.findAll(pageable);
    }

    public Facility getFacility(Integer id) {
        return facilityRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Facility with id %s not found.", id)));
    }

    public Facility createFacility(Facility facility) {
        return facilityRepository.save(facility);
    }
}

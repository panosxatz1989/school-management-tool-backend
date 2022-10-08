package school.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.management.entity.Provision;
import school.management.repository.ProvisionRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProvisionService {

    private final ProvisionRepository provisionRepository;

    public Page<Provision> getAllProvisions(Pageable pageable) {
        return provisionRepository.findAll(pageable);
    }

    public Provision getProvision(Long id) {
        return provisionRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Provision with id %s not found.", id)));
    }

    public Provision createProvision(Provision provision) {
        return provisionRepository.save(provision);
    }
}

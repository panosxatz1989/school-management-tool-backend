package school.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import school.management.entity.ProvisionType;
import school.management.repository.ProvisionTypeRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProvisionTypeService {

    private final ProvisionTypeRepository provisionTypeRepository;

    public Page<ProvisionType> getAllProvisionTypes(Pageable pageable) {
        return provisionTypeRepository.findAll(pageable);
    }

    public ProvisionType getProvisionType(Integer id) {
        return provisionTypeRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Provision type with id %s not found.", id)));
    }

    public ProvisionType createProvisionType(ProvisionType provisionType) {
        return provisionTypeRepository.save(provisionType);
    }
}

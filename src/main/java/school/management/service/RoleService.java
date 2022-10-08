package school.management.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import school.management.entity.Role;
import school.management.repository.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getByName(String name) {
        return roleRepository.findByName(name);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }
}

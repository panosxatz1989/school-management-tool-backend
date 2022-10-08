package school.management.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import school.management.entity.Role;
import school.management.entity.User;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;
    private String email;
    private String name;
    private String lastName;
    private List<RoleResponse> roles;

    public static UserResponse convertFromEntity(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .name(user.getName())
                .lastName(user.getLastName())
                .roles(user.getRoles().stream().map(RoleResponse::convertFromEntity).collect(Collectors.toList()))
                .build();
    }
}

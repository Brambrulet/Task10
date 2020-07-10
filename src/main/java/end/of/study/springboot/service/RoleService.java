package end.of.study.springboot.service;

import end.of.study.springboot.entity.Role;
import end.of.study.springboot.repository.RoleRepository;
import java.util.Objects;
import javax.annotation.Resource;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class RoleService {

    public static final String ADMIN_ROLE_NAME = "Admin";
    public static final String USER_ROLE_NAME = "User";

    @Setter(onMethod_ = @Resource)
    RoleRepository repository;

    @Transactional
    public Role getAdminRole() {
        return findOrCreateRole(ADMIN_ROLE_NAME);
    }

    @Transactional
    public Role getUserRole() {
        return findOrCreateRole(USER_ROLE_NAME);
    }

    public static boolean isAdmin(Role role) {
        assert !Objects.isNull(role);

        return ADMIN_ROLE_NAME.equals(role.getName());
    }

    private Role findOrCreateRole(String roleName) {
        assert !StringUtils.isEmpty(roleName);

        return repository.getByName(roleName).orElseGet(() -> repository.save(new Role().setName(roleName)));
    }
}

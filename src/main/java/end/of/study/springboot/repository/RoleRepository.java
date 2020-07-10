package end.of.study.springboot.repository;

import end.of.study.springboot.entity.Role;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

    Optional<Role> getByName(@Param("name") String name);
}

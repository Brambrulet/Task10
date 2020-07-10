package end.of.study.springboot.repository;

import end.of.study.springboot.entity.User;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface UserRepository extends CrudRepository<User, Long> {

    Optional<User> getByName(@Param("name") String name);

}

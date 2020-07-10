package end.of.study.springboot.repository;

import end.of.study.springboot.entity.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface UserRestRepository extends PagingAndSortingRepository<User, Long> {

    User getByName(@Param("name") String name);

}

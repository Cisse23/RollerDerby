package cb.swd20.RollerDerby.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Team, Long>{
List<Team>findByCity(String city);
}

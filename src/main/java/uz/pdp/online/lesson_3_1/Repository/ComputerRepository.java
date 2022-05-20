package uz.pdp.online.lesson_3_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.lesson_3_1.Entity.Computer;
import uz.pdp.online.lesson_3_1.Projection.ComputerCustom;

@RepositoryRestResource(path = "computer", excerptProjection = ComputerCustom.class)
public interface ComputerRepository extends JpaRepository<Computer,Integer> {
}

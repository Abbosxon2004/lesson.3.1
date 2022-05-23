package uz.pdp.online.lesson_3_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.lesson_3_1.Entity.Computer;

public interface ComputerRepository extends JpaRepository<Computer,Integer> {
}

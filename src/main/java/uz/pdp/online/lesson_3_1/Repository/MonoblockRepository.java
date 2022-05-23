package uz.pdp.online.lesson_3_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.lesson_3_1.Entity.Monoblock;

public interface MonoblockRepository extends JpaRepository<Monoblock,Integer> {
}

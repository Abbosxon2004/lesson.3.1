package uz.pdp.online.lesson_3_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.lesson_3_1.Entity.Laptop;
import uz.pdp.online.lesson_3_1.Projection.LaptopCustom;

@RepositoryRestResource(path = "laptop", excerptProjection = LaptopCustom.class)
public interface LaptopRepository extends JpaRepository<Laptop,Integer> {
}

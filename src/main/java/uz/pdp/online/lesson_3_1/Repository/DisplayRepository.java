package uz.pdp.online.lesson_3_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.lesson_3_1.Entity.Display;
import uz.pdp.online.lesson_3_1.Projection.DisplayCustom;

@RepositoryRestResource(path = "display", excerptProjection = DisplayCustom.class)
public interface DisplayRepository extends JpaRepository<Display,Integer> {
}

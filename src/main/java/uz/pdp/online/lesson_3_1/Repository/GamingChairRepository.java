package uz.pdp.online.lesson_3_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.lesson_3_1.Entity.GamingChair;
import uz.pdp.online.lesson_3_1.Projection.GamingChairCustom;

@RepositoryRestResource(path = "gaming_chair", excerptProjection = GamingChairCustom.class)
public interface GamingChairRepository extends JpaRepository<GamingChair,Integer> {
}

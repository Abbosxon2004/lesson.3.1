package uz.pdp.online.lesson_3_1.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import uz.pdp.online.lesson_3_1.Entity.Printer;
import uz.pdp.online.lesson_3_1.Projection.PrinterCustom;

@RepositoryRestResource(path = "printer", excerptProjection = PrinterCustom.class)
public interface PrinterRepository extends JpaRepository<Printer,Integer> {
}

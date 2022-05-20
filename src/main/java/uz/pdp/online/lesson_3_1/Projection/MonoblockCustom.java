package uz.pdp.online.lesson_3_1.Projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.lesson_3_1.Entity.Monoblock;
import uz.pdp.online.lesson_3_1.Entity.Product;

@Projection(types = Monoblock.class)
public interface MonoblockCustom {
    Integer getId();

    String getBrand();

    Integer getRAM();

    String getCPU();

    Integer getSSD();

    String getScreenSize();

    String getHardDisk();

    Product getProduct();
}

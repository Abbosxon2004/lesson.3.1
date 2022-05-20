package uz.pdp.online.lesson_3_1.Projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.lesson_3_1.Entity.Category;
import uz.pdp.online.lesson_3_1.Entity.Product;

@Projection(types = Product.class)
public interface ProductCustom {
    Integer getId();

    String getName();

    Integer getPrice();

    String getDescription();

    Category getCategory();
}

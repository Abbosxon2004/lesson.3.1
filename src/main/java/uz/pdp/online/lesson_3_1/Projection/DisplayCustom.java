package uz.pdp.online.lesson_3_1.Projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.lesson_3_1.Entity.Display;
import uz.pdp.online.lesson_3_1.Entity.Product;

@Projection(types = Display.class)
public interface DisplayCustom {
    Integer getId();

    boolean getCurvedScreen();

    String  getScreenRefreshRate();

    Integer getDiagonalScreen();

    String  getVideoType();

    Integer getResponseTime();

    Product getProduct();
}

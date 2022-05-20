package uz.pdp.online.lesson_3_1.Projection;

import org.springframework.data.rest.core.config.Projection;
import uz.pdp.online.lesson_3_1.Entity.Printer;
import uz.pdp.online.lesson_3_1.Entity.Product;

@Projection(types = Printer.class)
public interface PrinterCustom {
    Integer getId();

    String getBrand();

    boolean getColorfull();

    String getTwoSidedPrinting();

    String getDeviceFunction();

    String getMaxFormat();

    String getNonColorPrintingSpeed();

    String getColorPrintingSpeed();

    String getPrintingTechnology();

    String getConnectivity();

    Product getProduct();
}

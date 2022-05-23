package uz.pdp.online.lesson_3_1.payloadDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrinterDto {
    private String brand;

    private boolean isColorfull;

    private String twoSidedPrinting;

    private String deviceFunction;

    private String maxFormat;

    private String nonColorPrintingSpeed;

    private String colorPrintingSpeed;

    private String printingTechnology;

    private String connectivity;

    private Integer productId;
}

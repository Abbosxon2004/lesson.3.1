package uz.pdp.online.lesson_3_1.payloadDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LaptopDto {
    private String brand;

    private Integer RAM;

    private String CPU;

    private Integer SSD;

    private String videoCard;

    private String hardDisk;

    private Integer productId;
}

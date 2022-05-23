package uz.pdp.online.lesson_3_1.payloadDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MonoblockDto {
    private String brand;

    private Integer RAM;

    private String CPU;

    private String screenSize;

    private Integer SSD;

    private String hardDisk;

    private Integer productId;
}

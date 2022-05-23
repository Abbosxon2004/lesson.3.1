package uz.pdp.online.lesson_3_1.payloadDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GamingChairDto {
    private String brand;

    private String color;

    private Integer weightLimit;

    private boolean headrest;

    private Integer productId;
}

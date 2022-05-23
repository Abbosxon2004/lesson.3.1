package uz.pdp.online.lesson_3_1.payloadDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DisplayDto {
    private String brand;

    private boolean curvedScreen;

    private String screenRefreshRate;

    private Integer diagonalScreen;

    private String videoType;

    private Integer responseTime;

    private Integer productId;
}

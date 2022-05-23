package uz.pdp.online.lesson_3_1.payloadDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

    private String name;

    private Integer categoryId;
}

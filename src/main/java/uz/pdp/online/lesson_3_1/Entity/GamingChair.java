package uz.pdp.online.lesson_3_1.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class GamingChair {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private String color;

    private Integer weightLimit;

    private boolean headrest;

    @ManyToOne
    private Product product;
}

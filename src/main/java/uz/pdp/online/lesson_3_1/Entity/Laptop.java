package uz.pdp.online.lesson_3_1.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Laptop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private Integer RAM;

    private String CPU;

    private Integer SSD;

    private String videoCard;

    private String hardDisk;

    @ManyToOne
    private Product product;
}

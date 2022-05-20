package uz.pdp.online.lesson_3_1.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Display {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private boolean curvedScreen;

    private String screenRefreshRate;

    private Integer diagonalScreen;

    private String videoType;

    private Integer responseTime;

    @ManyToOne
    private Product product;
}

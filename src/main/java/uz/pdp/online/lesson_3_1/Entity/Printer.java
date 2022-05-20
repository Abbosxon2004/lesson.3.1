package uz.pdp.online.lesson_3_1.Entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Printer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String brand;

    private boolean isColorfull;

    private String twoSidedPrinting;

    private String deviceFunction;

    private String maxFormat;

    private String nonColorPrintingSpeed;

    private String colorPrintingSpeed;

    private String printingTechnology;

    private String connectivity;

    @ManyToOne
    private Product product;
}

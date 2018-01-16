package anderson.katherine.dec16lab.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Entity
public class MenuItem {

    @Id
    @GeneratedValue
    @Column(name = "MENU_ITEM_ID")
    private Long id;

    @Size(min = 3, max = 20)
    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
//    private BigDecimal price;
    private double price;

    @Size(min = 3, max = 20)
    @Column(name = "CATEGORY")
    private String category;

//    public MenuItem(String name, BigDecimal price, String category) {
//        this.name = name;
//        this.price = price;
//        this.category = category;
//    }


    public MenuItem(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public MenuItem() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public BigDecimal getPrice() {
//        return price;
//    }
//
//    public void setPrice(BigDecimal price) {
//        this.price = price;
//    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}

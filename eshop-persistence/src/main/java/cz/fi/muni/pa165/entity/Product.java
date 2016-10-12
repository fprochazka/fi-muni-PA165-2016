package cz.fi.muni.pa165.entity;

import cz.fi.muni.pa165.enums.Color;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "eshop_products")
public class Product
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private Color color;

    @Column
    @Temporal(TemporalType.DATE)
    private java.util.Date addedDate;

    public Product(String name, Color color, Date addedDate)
    {
        this.name = name;
        this.color = color;
        this.addedDate = addedDate;
    }

    public Product()
    {
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }

    public Date getAddedDate()
    {
        return addedDate;
    }

    public void setAddedDate(Date addedDate)
    {
        this.addedDate = addedDate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Product)) {
            return false;
        }

        Product product = (Product) o;

        if (!getName().equals(product.getName())) {
            return false;
        }

        return true;

    }

    @Override
    public int hashCode()
    {
        int result = 0;
        result = 31 * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }
}

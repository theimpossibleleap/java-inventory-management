package com.example.demo.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="part_type",discriminatorType = DiscriminatorType.INTEGER)
@Table(name="Parts")
public abstract class Part implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    String name;

    @Min(value = 0, message = "Price value must be positive")
    double price;

    @Min(value = 0, message = "Inventory value must be positive")
    int inv;

    // Added Min/Max fields for inventory parts.
    @Min(value = 0, message = "Part inventory value must be positive")
    int minPartInv;
    int maxPartInv;

    @ManyToMany
    @JoinTable(name="product_part", joinColumns = @JoinColumn(name="part_id"),
            inverseJoinColumns=@JoinColumn(name="product_id"))
    Set<Product> products= new HashSet<>();

    public Part() {
    }

    public Part(String name, double price, int inv) {
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    public Part(long id, String name, double price, int inv) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.inv = inv;
    }

    // Adds constructor for new fields minPartInv and maxPartInv
    public Part(int minPartInv, int maxPartInv) {
        this.minPartInv = minPartInv;
        this.maxPartInv = maxPartInv;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getInv() {
        return inv;
    }

    public void setInv(int inv) {
        this.inv = inv;
    }

    @Min(value = 0, message = "Part inventory value must be positive")
    @Max(value = 250, message = "Part inventory must be maintained below 250 per part")
    public int getMinPartInv() {
        return minPartInv;
    }

    public void setMinPartInv(int minPartInv) {
        this.minPartInv = minPartInv;
    }
    @Min(value = 0, message = "Part inventory value must be positive")
    @Max(value = 250, message = "Part inventory must be maintained below 250 per part")
    public int getMaxPartInv() {
        return maxPartInv;
    }

    public void setMaxPartInv(int maxPartInv) {
        this.maxPartInv = maxPartInv;
    }

    public boolean invIsValid() {
        return this.inv >= this.minPartInv && this.inv <= this.maxPartInv;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String toString(){
        return this.name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Part part = (Part) o;

        return id == part.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }
}

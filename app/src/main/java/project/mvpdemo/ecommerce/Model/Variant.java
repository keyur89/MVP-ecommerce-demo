package project.mvpdemo.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Variant implements Serializable {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("color")
    @Expose
    private String color;
    @SerializedName("size")
    @Expose
    private Integer size;
    @SerializedName("price")
    @Expose
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }


}

package project.mvpdemo.ecommerce.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Ranking implements Serializable {

    @SerializedName("ranking")
    @Expose
    private String ranking;
    @SerializedName("products")
    @Expose
    private List<ProductAsPerRanking> products = null;

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public List<ProductAsPerRanking> getProducts() {
        return products;
    }

    public void setProducts(List<ProductAsPerRanking> products) {
        this.products = products;
    }

}

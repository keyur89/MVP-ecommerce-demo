package project.mvpdemo.ecommerce.Model;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Comparator;

public class ProductAsPerRanking implements Serializable, Comparable<ProductAsPerRanking> {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("view_count")
    @Expose
    private Integer viewCount;
    @SerializedName("order_count")
    @Expose
    private Integer orderCount;
    @SerializedName("shares")
    @Expose
    private Integer shares;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getViewCount() {
        return viewCount;
    }

    public void setViewCount(Integer viewCount) {
        this.viewCount = viewCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Integer getShares() {
        return shares;
    }

    public void setShares(Integer shares) {
        this.shares = shares;
    }

    @Override
    public int compareTo(@NonNull ProductAsPerRanking productAsPerRanking) {
        return Comparators.VIEW_COUNT.compare(this, productAsPerRanking);
    }

    @Override
    public String toString() {
        return id + " " + viewCount + " " + orderCount + " " + shares;
    }

    /**
     * Since the release of Java 8 the inner class Comparators may be greatly simplified using lambdas.
     */
    public static class Comparators {
        public static final Comparator<ProductAsPerRanking> VIEW_COUNT = (ProductAsPerRanking o1, ProductAsPerRanking o2) -> o2.viewCount.compareTo(o1.viewCount);
        public static final Comparator<ProductAsPerRanking> ORDER_COUNT = (ProductAsPerRanking o1, ProductAsPerRanking o2) -> o2.orderCount.compareTo(o1.orderCount);
        public static final Comparator<ProductAsPerRanking> SHARES = (ProductAsPerRanking o1, ProductAsPerRanking o2) -> o2.shares.compareTo(o1.shares);
    }
}

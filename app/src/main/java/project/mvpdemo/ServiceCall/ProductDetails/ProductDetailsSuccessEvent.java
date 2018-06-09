package project.mvpdemo.ServiceCall.ProductDetails;

import project.mvpdemo.ecommerce.Model.ProductModelMain;

/**
 * Created by Keyur Tailor on 01-Nov-17.
 */

public class ProductDetailsSuccessEvent {
    boolean successful;
    ProductModelMain productModelMain;

    public ProductDetailsSuccessEvent(boolean successful, ProductModelMain productModelMain) {
        this.successful = successful;
        this.productModelMain = productModelMain;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public ProductModelMain getProductModelMain() {
        return productModelMain;
    }
}

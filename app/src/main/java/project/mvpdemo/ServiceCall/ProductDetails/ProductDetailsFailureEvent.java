package project.mvpdemo.ServiceCall.ProductDetails;

/**
 * Created by Keyur Tailor on 01-Nov-17.
 */

public class ProductDetailsFailureEvent {
    boolean successful;
    String errorMessageDisplay;

    public ProductDetailsFailureEvent(boolean successful, String errorMessageDisplay) {
        this.successful = successful;
        this.errorMessageDisplay = errorMessageDisplay;
    }

    public boolean isSuccessful() {
        return successful;
    }

    public String getErrorMessageDisplay() {
        return errorMessageDisplay;
    }
}

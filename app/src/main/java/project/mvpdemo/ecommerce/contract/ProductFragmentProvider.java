package project.mvpdemo.ecommerce.contract;

import java.util.List;

import project.mvpdemo.ecommerce.Model.Product;

/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */

public interface ProductFragmentProvider {
    void showError(String message);

    void setAdapterData(List<Product> products);

    void setListVisible();

    void setNoData();
}

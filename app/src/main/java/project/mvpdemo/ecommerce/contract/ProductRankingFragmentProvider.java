package project.mvpdemo.ecommerce.contract;

import java.util.List;

import project.mvpdemo.ecommerce.Model.Product;

/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */

public interface ProductRankingFragmentProvider {
    void showError(String message);

    void setListVisible();

    void setAdapterData(List<Product> productArrayList);

    void setNoData();
}

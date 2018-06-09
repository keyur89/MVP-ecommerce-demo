package project.mvpdemo.ecommerce.contract;

import java.util.List;

import project.mvpdemo.ecommerce.Model.Category;

/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */

public interface ProductDashboardProvider {
    void showError(String message);

    void setDrawerItems(List<Category> categories);
    void setNoData();
}

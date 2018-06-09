package project.mvpdemo.ecommerce.presenter;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import project.mvpdemo.ApiManager.ApiManager;
import project.mvpdemo.ServiceCall.ProductDetails.CallProductDetails;
import project.mvpdemo.ecommerce.Model.Category;
import project.mvpdemo.ecommerce.Model.Product;
import project.mvpdemo.ecommerce.Model.ProductAsPerRanking;
import project.mvpdemo.ecommerce.Model.Ranking;
import project.mvpdemo.ecommerce.contract.ProductDashboardProvider;
import project.mvpdemo.ecommerce.contract.ProductFragmentProvider;
import project.mvpdemo.ecommerce.contract.ProductRankingFragmentProvider;

/**
 * Created by Keyur Tailor on 07-Jun-2018.
 */

public class ProductPresenter {

    @Inject
    public ProductPresenter() {

    }

    public void callProductDetails(ApiManager apiManager) {
        CallProductDetails sampleDataDetails = new CallProductDetails();
        sampleDataDetails.callProductDetails(apiManager);
    }


    public void fillDrawerItems(ProductDashboardProvider productProvider, List<Category> categories) {
        productProvider.setDrawerItems(categories);
    }

    public void setupProductAdapterData(ProductFragmentProvider provider, List<Product> products) {
        if (products.size() > 0) {
            provider.setAdapterData(products);
            provider.setListVisible();
        } else {
            provider.setNoData();
        }
    }

    public void setupRankingAdapterData(ProductRankingFragmentProvider provider, Ranking ranking, List<ProductAsPerRanking> products, List<Category> categories) {
        List<ProductAsPerRanking> productAsPerRankingList = products;
        List<Product> productArrayList = new ArrayList<>();
        List<Product> productArrayListAll = new ArrayList<>();
        if (ranking.getRanking().contains("Viewed")) {
            Collections.sort(productAsPerRankingList, ProductAsPerRanking.Comparators.VIEW_COUNT);
        } else if (ranking.getRanking().contains("OrdeRed")) {
            Collections.sort(productAsPerRankingList, ProductAsPerRanking.Comparators.ORDER_COUNT);
        } else if (ranking.getRanking().contains("ShaRed")) {
            Collections.sort(productAsPerRankingList, ProductAsPerRanking.Comparators.SHARES);
        }

        for (int i = 0; i < categories.size(); i++) {
            productArrayListAll.addAll(categories.get(i).getProducts());
        }

        for (int k = 0; k < productAsPerRankingList.size(); k++) {
            for (int i = 0; i < productArrayListAll.size(); i++) {
                if (productAsPerRankingList.get(k).getId().intValue() == productArrayListAll.get(i).getId().intValue()) {
                    productArrayList.add(productArrayListAll.get(i));
                }
            }
        }
        if (productArrayList.size() > 0) {
            provider.setListVisible();
            provider.setAdapterData(productArrayList);
        } else {
            provider.setNoData();
        }
    }

    public void setNoData(ProductDashboardProvider provider) {
        provider.setNoData();
    }
}

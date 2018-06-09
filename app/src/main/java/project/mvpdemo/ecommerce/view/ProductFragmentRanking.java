package project.mvpdemo.ecommerce.view;

import android.app.ProgressDialog;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.mvpdemo.R;
import project.mvpdemo.ecommerce.Model.Product;
import project.mvpdemo.ecommerce.Model.ProductModelMain;
import project.mvpdemo.ecommerce.Model.Ranking;
import project.mvpdemo.ecommerce.contract.ProductRankingFragmentProvider;
import project.mvpdemo.ecommerce.presenter.ProductPresenter;


public class ProductFragmentRanking extends Fragment implements ProductRankingFragmentProvider {

    ProductPresenter productPresenter = new ProductPresenter();

    @BindView(R.id.product_main)
    RelativeLayout product_main;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.txtNoData)
    TextView txtNoData;

    ProductAdapter productAdapter;

    ProgressDialog progressDialog;
    Ranking ranking;
    ProductModelMain productModelMain;

    public ProductFragmentRanking() {
    }

    public static ProductFragmentRanking newInstance(ProductModelMain productModelMain, Ranking ranking) {
        ProductFragmentRanking fragment = new ProductFragmentRanking();
        Bundle bundle = new Bundle();
        bundle.putSerializable("ProductModelMain", productModelMain);
        bundle.putSerializable("Ranking", ranking);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            productModelMain = (ProductModelMain) getArguments().getSerializable("ProductModelMain");
            ranking = (Ranking) getArguments().getSerializable("Ranking");
        }

        assert ((DashboardActivity) getActivity()) != null;
        ((DashboardActivity) getActivity()).toolbar.setTitle(ranking.getRanking());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.product_fragment, container, false);
        ButterKnife.bind(this, rootView);
        setRetainInstance(true);
        progressDialog = new ProgressDialog(getActivity());

        RecyclerView.LayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        productPresenter.setupRankingAdapterData(this, ranking, ranking.getProducts(), productModelMain.getCategories());
        return rootView;
    }

    @Override
    public void showError(String message) {
        try {
            Snackbar snackbar = Snackbar.make(product_main, message, Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (Exception e) {

        }
    }

    @Override
    public void setListVisible() {
        recyclerView.setVisibility(View.VISIBLE);
        txtNoData.setVisibility(View.GONE);
    }

    @Override
    public void setAdapterData(List<Product> productArrayList) {
        productAdapter = new ProductAdapter(getActivity(), productArrayList);
        recyclerView.setAdapter(productAdapter);
    }

    @Override
    public void setNoData() {
        recyclerView.setVisibility(View.GONE);
        txtNoData.setVisibility(View.VISIBLE);
    }

    public void showLoading() {
        progressDialog.setMessage(getResources().getString(R.string.loading));
        progressDialog.show();
    }

    public void hideLoading() {
        if (progressDialog != null) {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}

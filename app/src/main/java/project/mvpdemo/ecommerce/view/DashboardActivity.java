package project.mvpdemo.ecommerce.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.mvpdemo.ApiManager.ApiManager;
import project.mvpdemo.R;
import project.mvpdemo.ServiceCall.ProductDetails.ProductDetailsFailureEvent;
import project.mvpdemo.ServiceCall.ProductDetails.ProductDetailsSuccessEvent;
import project.mvpdemo.Utils.Injector;
import project.mvpdemo.Utils.NetworkUtils;
import project.mvpdemo.dagger.DaggerInjector;
import project.mvpdemo.ecommerce.Model.Category;
import project.mvpdemo.ecommerce.Model.ProductModelMain;
import project.mvpdemo.ecommerce.contract.ProductDashboardProvider;
import project.mvpdemo.ecommerce.presenter.ProductPresenter;

public class DashboardActivity extends AppCompatActivity implements ProductDashboardProvider, NavigationView.OnNavigationItemSelectedListener {

    @Inject
    ProductPresenter productPresenter;

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;
    @BindView(R.id.nav_view)
    NavigationView navigationView;
    @BindView(R.id.txtNoData)
    TextView txtNoData;

    ProductModelMain productModelMain;
    private ApiManager apiManager;
    private EventBus bus;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_drawer);

        ButterKnife.bind(this);
        DaggerInjector.get().inject(this);
        apiManager = new ApiManager();
        bus = Injector.provideEventBus();
        progressDialog = new ProgressDialog(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setTitle(getResources().getString(R.string.app_name));
        navigationView.setNavigationItemSelectedListener(this);

        if (checkIfNetworkConnected()) {
            showLoading();
            productPresenter.callProductDetails(apiManager);
        } else {
            hideLoading();
            productPresenter.setNoData(this);
            showError(getResources().getString(R.string.network_error));
        }

    }

    public boolean checkIfNetworkConnected() {
        return new NetworkUtils(this).getConnectivityStatus();
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

    public void showError(String message) {
        try {
            Snackbar snackbar = Snackbar.make(drawer, message, Snackbar.LENGTH_SHORT);
            snackbar.show();
        } catch (Exception e) {

        }
    }

    public void loadFragemnt(Fragment fragment) {
        FragmentManager mFragmentManager;
        FragmentTransaction mFragmentTransaction;
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container, fragment).commit();
    }

    @Override
    public void setDrawerItems(List<Category> categories) {
        Menu menu = navigationView.getMenu();
        if (categories.size() > 0) {
            for (int i = 0; i < categories.size(); i++) {
                menu.add(Menu.NONE, i, Menu.NONE, categories.get(i).getName());
            }
            navigationView.invalidate();
            loadFragemnt(ProductFragment.newInstance(productModelMain.getCategories().get(0)));
        }
    }

    @Override
    public void setNoData() {
        txtNoData.setVisibility(View.VISIBLE);
    }

    @Subscribe()
    public void ProductDetailSuccessEvent(ProductDetailsSuccessEvent event) {
        hideLoading();
        productModelMain = event.getProductModelMain();
        productPresenter.fillDrawerItems(this, event.getProductModelMain().getCategories());
    }

    @Subscribe()
    public void ProductDetailFailureEvent(ProductDetailsFailureEvent event) {
        hideLoading();
        showError(getResources().getString(R.string.unexpected_error));
        productPresenter.setNoData(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawer.openDrawer(GravityCompat.START);
                return true;

            case R.id.action_rankings:
                if (productModelMain != null) {
                    if (productModelMain.getRankings().size() > 0) {
                        showRankingOptions(findViewById(R.id.action_rankings));
                    }
                }
        }
        return super.onOptionsItemSelected(item);
    }

    public void showRankingOptions(View v) {
        PopupMenu menu = new PopupMenu(this, v);
        for (int i = 0; i < productModelMain.getRankings().size(); i++) {
            menu.getMenu().add(Menu.NONE, i, Menu.NONE, productModelMain.getRankings().get(i).getRanking());
        }
        menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                loadFragemnt(ProductFragmentRanking.newInstance(productModelMain, productModelMain.getRankings().get(menuItem.getItemId())));
                return false;
            }
        });
        menu.show();
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        loadFragemnt(ProductFragment.newInstance(productModelMain.getCategories().get(id)));
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        try {
            if (!EventBus.getDefault().isRegistered(this)) {
                bus.register(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        bus.unregister(this);
    }
}

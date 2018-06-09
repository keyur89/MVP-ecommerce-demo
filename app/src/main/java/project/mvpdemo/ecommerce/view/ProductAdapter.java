package project.mvpdemo.ecommerce.view;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.mvpdemo.R;
import project.mvpdemo.ecommerce.Model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context mContext;
    private List<Product> productList;

    public ProductAdapter(Context mContext, List<Product> productList) {
        this.mContext = mContext;
        this.productList = productList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int itemPosition = position;
        Product product = productList.get(itemPosition);

        holder.txtProductName.setText(product.getName());
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(mContext, 2);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(new ProductVariantsAdapter(mContext, product.getVariants()));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.txtProductName)
        TextView txtProductName;
        @BindView(R.id.recyclerView)
        RecyclerView recyclerView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, mView);

        }
    }

}

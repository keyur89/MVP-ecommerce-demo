package project.mvpdemo.ecommerce.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import project.mvpdemo.R;
import project.mvpdemo.ecommerce.Model.Variant;

public class ProductVariantsAdapter extends RecyclerView.Adapter<ProductVariantsAdapter.ViewHolder> {

    Context mContext;
    private List<Variant> variantList;

    public ProductVariantsAdapter(Context mContext, List<Variant> variantList) {
        this.mContext = mContext;
        this.variantList = variantList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_sub_item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final int itemPosition = position;
        Variant variant = variantList.get(itemPosition);

        holder.txtProductColor.setText(String.valueOf(variant.getColor()));
        holder.txtProductSize.setText(mContext.getResources().getString(R.string.size) + String.valueOf(variant.getSize()!=null?variant.getSize():"N/A"));
        holder.txtProductPrice.setText(mContext.getResources().getString(R.string.rupees) + String.valueOf(variant.getPrice()));

    }

    @Override
    public int getItemCount() {
        return variantList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        @BindView(R.id.txtProductColor)
        TextView txtProductColor;
        @BindView(R.id.txtProductSize)
        TextView txtProductSize;
        @BindView(R.id.txtProductPrice)
        TextView txtProductPrice;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            ButterKnife.bind(this, mView);

        }
    }

}

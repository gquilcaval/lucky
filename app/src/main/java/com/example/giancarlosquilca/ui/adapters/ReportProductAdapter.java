package com.example.giancarlosquilca.ui.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giancarlosquilca.R;
import com.example.giancarlosquilca.data.db.DbPointSale;
import com.example.giancarlosquilca.domain.models.Product;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class ReportProductAdapter extends RecyclerView.Adapter<ReportProductAdapter.ViewHolder> {

    @NotNull
    private List<Product> listProduct;

    public ReportProductAdapter(List<Product> mListProduct) {
        this.listProduct = mListProduct;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvProduct;
        private final EditText tvCosto;
        private final EditText tvPriceRev;
        private final EditText tvStk;

        public ViewHolder(View v) {
            super(v);
            tvProduct = v.findViewById(R.id.tvProduct);
            tvCosto = v.findViewById(R.id.tvCosto);
            tvPriceRev = v.findViewById(R.id.tvPriceRevent);
            tvStk = v.findViewById(R.id.tvStock);
        }

        public void bind(Product product) {
            tvProduct.setText(product.getName());
            tvCosto.setText(Integer.toString(product.getCost()));
            tvPriceRev.setText(Integer.toString(product.getPriceRev()));
            tvStk.setText(Integer.toString(product.getStock()));

            tvCosto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        product.setCost(Integer.parseInt(tvCosto.getText().toString()));
                        final DbPointSale dbUser = new DbPointSale(itemView.getContext());
                        Boolean x = dbUser.updateProduct(product.getId(),product.getCost(), product.getPriceRev(), product.getStock());
                    }
                }
            });

            tvCosto.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        product.setCost(Integer.parseInt(tvCosto.getText().toString()));
                        final DbPointSale db = new DbPointSale(itemView.getContext());
                        Boolean x = db.updateProduct(product.getId(),product.getCost(), product.getPriceRev(), product.getStock());
                    }
                }
            });

            tvPriceRev.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        product.setPriceRev(Integer.parseInt(tvPriceRev.getText().toString()));
                        final DbPointSale db = new DbPointSale(itemView.getContext());
                        Boolean x = db.updateProduct(product.getId(),product.getCost(), product.getPriceRev(), product.getStock());
                    }
                }
            });

            tvStk.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                public void onFocusChange(View v, boolean hasFocus) {
                    if(!hasFocus) {
                        product.setStock(Integer.parseInt(tvStk.getText().toString()));
                        final DbPointSale db = new DbPointSale(itemView.getContext());
                        Boolean x = db.updateProduct(product.getId(),product.getCost(), product.getPriceRev(), product.getStock());
                        Log.d("AQUII", x.toString());
                        Log.d("AQUII", tvStk.getText().toString());
                    }
                }
            });

        }

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.table_row_products, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportProductAdapter.ViewHolder holder, int position) {
        holder.bind(listProduct.get(position));
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public void update(List<Product> mList) {
        this.listProduct = mList;
        notifyDataSetChanged();
    }

}

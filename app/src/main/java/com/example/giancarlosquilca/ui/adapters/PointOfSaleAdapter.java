package com.example.giancarlosquilca.ui.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.giancarlosquilca.R;
import com.example.giancarlosquilca.domain.models.PointSale;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Giancarlos Quilca on 27/05/2022.
 */
public class PointOfSaleAdapter extends RecyclerView.Adapter<PointOfSaleAdapter.ViewHolder> {

    public interface Callback {
        void imgMapSelect(PointSale pointSale);
        void itemSelect(PointSale pointSale);
    }

    public PointOfSaleAdapter.Callback mCallback;
    @NotNull
    private List<PointSale> listPointSales;

    public void setFilterList(List<PointSale> filterList) {
        this.listPointSales = filterList;
        notifyDataSetChanged();
    }

    public PointOfSaleAdapter(List<PointSale> mListPointSales, PointOfSaleAdapter.Callback callback) {
        this.listPointSales = mListPointSales;
        this.mCallback = callback;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvName;
        private final TextView tvCode;
        private final TextView tvDirection;
        private final ImageButton imgMap;

        public ViewHolder(View v) {
            super(v);
            tvName = v.findViewById(R.id.tvNamePoint);
            tvCode = v.findViewById(R.id.tvCode);
            tvDirection = v.findViewById(R.id.tvDirection);
            imgMap = v.findViewById(R.id.imgMap);
        }

        public void bind(PointSale pointSale) {
            tvName.setText(pointSale.getName());
            tvCode.setText(pointSale.getCode());
            tvDirection.setText(pointSale.getDirection());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_point_sale, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PointOfSaleAdapter.ViewHolder holder, int position) {
        holder.bind(listPointSales.get(position));
        holder.imgMap.setOnClickListener(view -> {
            this.mCallback.imgMapSelect(listPointSales.get(position));
        });
        holder.itemView.setOnClickListener(view -> {
            this.mCallback.itemSelect(listPointSales.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return listPointSales.size();
    }

    public void update(List<PointSale> mList) {
        this.listPointSales = mList;
        notifyDataSetChanged();
    }
}

package com.sunbeam.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        // Bind order data to the ViewHolder
        holder.tvOrderId.setText(String.valueOf(order.getId()));
        holder.tvOrderDate.setText(order.getTimestamp());
        holder.textOrderName.setText(order.getP_name());
        holder.textOrderAmount.setText(String.valueOf(order.getTotalAmount()));
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textOrderName;
        TextView textOrderAmount;
        TextView tvOrderId;
        TextView tvOrderDate;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textOrderName = itemView.findViewById(R.id.textOrderName);
            textOrderAmount = itemView.findViewById(R.id.textOrderAmount);
            tvOrderId=itemView.findViewById(R.id.tvOrderId);
            tvOrderDate=itemView.findViewById(R.id.tvOrderDate);
        }
    }
}

package com.example.super_app.db;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.super_app.DeleteOrderInterface;
import com.example.super_app.R;
import com.example.super_app.RecycleViewInterface;
import com.example.super_app.db.entity.Order;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {
    private static List<Order> orderList;
    private static Context context;
    private DeleteOrderInterface deleteOrderInterface;


    public OrderAdapter(List<Order> orderList, Context context, DeleteOrderInterface deleteOrderInterface) {
        this.orderList = orderList;
        this.context = context;
        this.deleteOrderInterface = deleteOrderInterface;

    }
    public void setItems(List<Order> list)
    {
        this.orderList = list;
    }

    public static List<Order> getOrderList() {
        return orderList;
    }

    public static Context getContext() {
        return context;
    }

    public DeleteOrderInterface getDeleteOrderInterface() {
        return deleteOrderInterface;
    }



    @Override
    public OrderAdapter.OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vieworderinfo, parent, false);
        return new OrderViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull OrderAdapter.OrderViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.setData(order);
    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        private TextView vName;
        private TextView vPhone;
        private TextView vAddress;
        private TextView vDate;
        private ImageButton vBtn;
        private Order order = null;

        private ImageButton vShopping;

        public OrderViewHolder(View v) {
            super(v);
            vName = v.findViewById(R.id.txtCreatorName);
            vPhone = v.findViewById(R.id.txtPhone);
            vAddress = v.findViewById(R.id.txtAddress);
            vDate = v.findViewById(R.id.txtDeliveryDate);
            vBtn = v.findViewById(R.id.Btn);
            vBtn.setImageResource(R.drawable.baseline_add_circle_24);
            vBtn.setColorFilter(Color.GREEN);
            vShopping = v.findViewById(R.id.goShoppingBtn);
            vShopping.setVisibility(View.GONE);
            vBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (deleteOrderInterface != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                                deleteOrderInterface.onJoinClick(position);


                        }
                    }

                }
            });
        }

        public void setData(Order order) {
            this.order = order;
            vName.setText(order.getFullNameOwner());
            vPhone.setText(order.getPhoneNumberOwner());
            vAddress.setText(order.getAddress());
            vDate.setText(order.getDeliveryDate());
        }
    }
}

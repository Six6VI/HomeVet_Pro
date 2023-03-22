package com.example.homevetpro.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.List;

import kotlinx.coroutines.channels.ProduceKt;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> {

    class CustomerViewHolder extends RecyclerView.ViewHolder{

        private final TextView customerTextView;
        private CustomerViewHolder(View itemview){
            super(itemview);
            customerTextView=itemview.findViewById(R.id.customerTextView);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Customer current = mCustomer.get(position);
                    Intent intent=new Intent(context, CustomerDetails.class);
                    intent.putExtra("customerID", current.getCustomerID());
                    intent.putExtra("customerName", current.getCustomerName());
                    intent.putExtra("customerAddress", current.getCustomerAddress());
                    intent.putExtra("customerZip", current.getCustomerZip());
                    intent.putExtra("customerPhone", current.getCustomerPhone());
                    intent.putExtra("customerEnterDate", current.getCustomerEnterDate());
                    intent.putExtra("customerModifyDate", current.getCustomerModifyDate());
                    context.startActivity(intent);

                }
            });
        }
    }

    private List<Customer> mCustomer;
    private final Context context;
    private final LayoutInflater mInflater;
    public CustomerAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.customer_list_item,parent,false);
        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.CustomerViewHolder holder, int position) {
        if (mCustomer!=null){
            Customer current = mCustomer.get(position);
            String name = current.getCustomerName();
            holder.customerTextView.setText(name);

        }
        else {
            holder.customerTextView.setText("No Customers to Show");
        }

    }

    @Override
    public int getItemCount() {
        return mCustomer.size();
    }

    public void setCustomers(List<Customer> customers){
        mCustomer=customers;
        notifyDataSetChanged();
    }


}

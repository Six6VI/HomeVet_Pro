package com.example.homevetpro.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.ArrayList;
import java.util.List;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.CustomerViewHolder> implements Filterable {

    private final List<Customer> customerListFull;
    private final Context context;
    private final LayoutInflater mInflater;
    private List<Customer> customerList;
    private final Filter customerFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Customer> filteredCustomers = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                filteredCustomers.addAll(customerListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Customer item : customerListFull) {
                    if (item.getCustomerName().toLowerCase().contains(filterPattern)) {
                        filteredCustomers.add(item);
                    }
                }
            }
            results.values = filteredCustomers;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            customerList.clear();
            customerList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

    public CustomerAdapter(Context context, List<Customer> customerList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.customerList = customerList;
        customerListFull = new ArrayList<>(customerList);

    }

    @NonNull
    @Override
    public CustomerAdapter.CustomerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.customer_list_item, parent, false);
        return new CustomerViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.CustomerViewHolder holder, int position) {
        if (customerList != null) {
            Customer current = customerList.get(position);
            holder.customerTextView.setText(current.getCustomerName());
            holder.customerTextView2.setText(current.getCustomerZip());


        } else {
            holder.customerTextView.setText("No Customers to Show");
        }

    }

    @Override
    public int getItemCount() {
        if (customerList != null) {
            return customerList.size();
        } else {
            return 0;
        }
    }

    public void setCustomers(List<Customer> customerList) {
        this.customerList = customerList;
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return customerFilter;
    }

    class CustomerViewHolder extends RecyclerView.ViewHolder {

        private final TextView customerTextView;
        private final TextView customerTextView2;

        private CustomerViewHolder(View itemView) {
            super(itemView);
            customerTextView = itemView.findViewById(R.id.customerTextView);
            customerTextView2 = itemView.findViewById(R.id.customerTextView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Customer current = customerList.get(position);
                    Intent intent = new Intent(context, CustomerAdd.class);
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

}

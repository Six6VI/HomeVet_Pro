package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.List;

public class CustomerList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        RecyclerView recyclerView =findViewById(R.id.recyclerViewCustomer);
        final CustomerAdapter customerAdapter=new CustomerAdapter(this);
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository=new Repository(getApplication());
        List<Customer> allCustomers =repository.getmAllCustomers();
        customerAdapter.setCustomers(allCustomers);

        Button addCustomer = findViewById(R.id.custAdd);
        Button exitCustomer = findViewById(R.id.custExit);
        exitCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(CustomerList.this, HomeScreen.class);
                startActivity(intent2);
            }
        });
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerList.this, CustomerDetails.class);
                startActivity(intent);
            }

        });
    }
}
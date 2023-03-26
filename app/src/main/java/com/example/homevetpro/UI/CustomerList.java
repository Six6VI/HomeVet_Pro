package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.Toast;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CustomerList extends AppCompatActivity {

    RecyclerView recyclerView;
    Repository repository;
    List<Customer> mCustomer;
    CustomerAdapter customerAdapter;
    int custID;
    SearchView searchView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_list);
        repository = new Repository(getApplication());

        mCustomer = repository.getmAllCustomers();
        customerAdapter = new CustomerAdapter(this, mCustomer);
        custID = getIntent().getIntExtra("customerID", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        customerRecyclerView();

        Button addCustomer = findViewById(R.id.custAdd);
        Button exitCustomer = findViewById(R.id.custExit);
        exitCustomer.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent2 = new Intent(CustomerList.this, HomeScreen.class);
                startActivity(intent2);
            }
        });
        addCustomer.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick (View v){
                Intent intent = new Intent(CustomerList.this, CustomerDetails.class);
                startActivity(intent);
            }

        });
    }

    public void customerRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewCustomer);
        recyclerView.setAdapter(customerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }





    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Type here to search");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                customerAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

}
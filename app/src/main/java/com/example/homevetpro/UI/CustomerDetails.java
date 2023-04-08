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
import android.widget.EditText;
import android.widget.Toast;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.User;
import com.example.homevetpro.R;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomerDetails extends AppCompatActivity {

    EditText editID;
    EditText editName;
    EditText editAddress;
    EditText editZip;
    EditText editPhone;
    EditText editEnterDate;
    EditText editModifyDate;

    int customerID;
    String customerName;
    String customerAddress;
    String customerZip;
    String customerPhone;
    String customerEnterDate;
    String customerModifyDate;

    Customer customer;
    Customer current;
    Repository repository;

    List<Animal> animalList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        editID = findViewById(R.id.editTextCustomerID);
        editID.setEnabled(false);
        editName = findViewById(R.id.editTextCustomerName);
        editAddress = findViewById(R.id.editTextCustAdd);
        editZip = findViewById(R.id.editTextCustZip);
        editPhone = findViewById(R.id.editTextCustPhone);
        String myFormat = "MM-dd-yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editEnterDate = findViewById(R.id.editTextCustAdded);
        editEnterDate.setEnabled(false);
        editModifyDate = findViewById(R.id.editTextCustModify);

        customerID = getIntent().getIntExtra("customerID", -1);
        customerName = getIntent().getStringExtra("customerName");
        customerAddress = getIntent().getStringExtra("customerAddress");
        customerZip = getIntent().getStringExtra("customerZip");
        customerPhone = getIntent().getStringExtra("customerPhone");
        customerEnterDate = getIntent().getStringExtra("customerEnterDate");
        customerModifyDate = getIntent().getStringExtra("customerModifyDate");

        String custID = String.valueOf(customerID);
        String modDate = sdf.format(new Date());

        editID.setText(custID);
        editName.setText(customerName);
        editAddress.setText(customerAddress);
        editZip.setText(customerZip);
        editPhone.setText(customerPhone);
        editModifyDate.setText(customerModifyDate);

        /**
         * This will set the timestamp when we create a new Customer
         */

        if(customerID==-1){
            editEnterDate.setText(sdf.format(new Date()));
        }else {
            editEnterDate.setText(customerEnterDate);
        }

        repository = new Repository(getApplication());

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAnimals);
        repository = new Repository(getApplication());
        animalList = repository.getmAllAnimals();
        final AnimalAdapter animalAdapter = new AnimalAdapter(this, animalList);
        recyclerView.setAdapter(animalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Animal> filteredAnimals = new ArrayList<>();
        for (Animal p : repository.getmAllAnimals()) {
            if (p.getAnimalCustID() == customerID) filteredAnimals.add(p);
        }
        animalAdapter.setAnimals(filteredAnimals);

        Button button = findViewById(R.id.buttonCustomerSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customerID == -1) {
                    customer = new Customer(0, editName.getText().toString(), editAddress.getText().toString(), editZip.getText().toString(), editPhone.getText().toString(), editEnterDate.getText().toString(), modDate);
                    repository.insert(customer);
                } else {
                    customer = new Customer(customerID, editName.getText().toString(), editAddress.getText().toString(), editZip.getText().toString(), editPhone.getText().toString(), editEnterDate.getText().toString(), modDate);
                    repository.update(customer);
                }
                Intent intent = new Intent(CustomerDetails.this, CustomerList.class);
                startActivity(intent);
            }
        });
        Button delete = findViewById(R.id.buttonCustDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CustomerDetails.this, CustomerList.class);
                startActivity(intent);
            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.basic_home, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home_basic:

                Intent intent = new Intent(CustomerDetails.this, HomeScreen.class);
                startActivity(intent);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}





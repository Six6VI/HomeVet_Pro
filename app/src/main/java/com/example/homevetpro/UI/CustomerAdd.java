package com.example.homevetpro.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CustomerAdd extends AppCompatActivity {

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
        setContentView(R.layout.activity_customer_add);
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
        editModifyDate.setEnabled(false);

        customerID = getIntent().getIntExtra("customerID", -1);
        customerName = getIntent().getStringExtra("customerName");
        customerAddress = getIntent().getStringExtra("customerAddress");
        customerZip = getIntent().getStringExtra("customerZip");
        customerPhone = getIntent().getStringExtra("customerPhone");
        customerEnterDate = getIntent().getStringExtra("customerEnterDate");
        customerModifyDate = getIntent().getStringExtra("customerModifyDate");

        String custID = String.valueOf(customerID);

        editID.setText(custID);
        editName.setText(customerName);
        editAddress.setText(customerAddress);
        editZip.setText(customerZip);
        editPhone.setText(customerPhone);
        editEnterDate.setText(customerEnterDate);
        editModifyDate.setText(sdf.format(new Date()));

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
                    customer = new Customer(0, editName.getText().toString(), editAddress.getText().toString(), editZip.getText().toString(), editPhone.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString());
                    repository.insert(customer);
                } else {
                    customer = new Customer(customerID, editName.getText().toString(), editAddress.getText().toString(), editZip.getText().toString(), editPhone.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString());
                    repository.update(customer);
                }
                Intent intent = new Intent(CustomerAdd.this, CustomerList.class);
                startActivity(intent);
            }
        });

        Button add = findViewById(R.id.buttonCustAddAnimal);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (customerID == -1) {
                    customer = new Customer(0, editName.getText().toString(), editAddress.getText().toString(), editZip.getText().toString(), editPhone.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString());
                    repository.insert(customer);
                } else {
                    customer = new Customer(customerID, editName.getText().toString(), editAddress.getText().toString(), editZip.getText().toString(), editPhone.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString());
                    repository.update(customer);
                }

                int id = Integer.parseInt(editID.getText().toString());

                Intent intent = new Intent(v.getContext(), AnimalDetails.class);
                intent.putExtra("animalCustID", id);
                startActivity(intent);

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_screen, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.home:

                Intent intent = new Intent(CustomerAdd.this, HomeScreen.class);
                startActivity(intent);

                return true;

            case R.id.delete:

                for (Customer customer : repository.getmAllCustomers()) {
                    if (customer.getCustomerID() == customerID)
                        current = customer;
                }
                repository.delete(current);
                Toast.makeText(CustomerAdd.this, current.getCustomerName() + " was deleted", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(CustomerAdd.this, CustomerList.class);
                startActivity(intent2);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

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
    Repository repository;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_details);
        editID=findViewById(R.id.editTextCustomerID);
        editName=findViewById(R.id.editTextCustomerName);
        editAddress=findViewById(R.id.editTextCustAdd);
        editZip=findViewById(R.id.editTextCustZip);
        editPhone=findViewById(R.id.editTextCustPhone);
        editEnterDate=findViewById(R.id.editTextCustAdded);
        editModifyDate=findViewById(R.id.editTextCustModify);

        customerID = getIntent().getIntExtra("customerID",-1);
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
        editModifyDate.setText(customerModifyDate);

        repository = new Repository(getApplication());

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
                Intent intent = new Intent(CustomerDetails.this, CustomerList.class);
                startActivity(intent);
            }
        });


    }
}
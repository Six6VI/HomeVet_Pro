package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.Report;
import com.example.homevetpro.R;

import java.util.ArrayList;
import java.util.List;

public class ReportInvoice extends AppCompatActivity {

    EditText editID;
    EditText editCustomer;
    EditText editAnimal;
    EditText editAppDate;
    EditText editAppNotes;
    EditText editAppCost;
    EditText editAppId;

    int reportID;
    String custName;
    String animalName;
    String appDate;
    String appNotes;
    double appCost;
    int appID;

    Customer customer;
    Animal animal;
    Repository repository;
    List<Report> reportList;
    Report report;
    Report current;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_invoice);
        editID = findViewById(R.id.editTextReportID);
        editCustomer = findViewById(R.id.editTextReportCustomerName);
        editAnimal = findViewById(R.id.editTextReportAnimalName);
        editAppDate = findViewById(R.id.editTextReportAppDate);
        editAppNotes = findViewById(R.id.editTextReportAppNotes);
        editAppCost = findViewById(R.id.editTextReportAppCost);
        editAppId = findViewById(R.id.editTextReportAppID);

        reportID=getIntent().getIntExtra("reportID",-1);
        custName=getIntent().getStringExtra("customerName");
        animalName=getIntent().getStringExtra("animalName");
        appDate=getIntent().getStringExtra("appDate");
        appNotes=getIntent().getStringExtra("appNotes");
        appCost=getIntent().getDoubleExtra("appCost",0);
        appID=getIntent().getIntExtra("appID",-1);

        String sReportID = String.valueOf(reportID);
        String appointmentCost = String.valueOf(appCost);
        String appointmentID = String.valueOf(appID);

        editID.setText(sReportID);
        editCustomer.setText(custName);
        editAnimal.setText(animalName);
        editAppDate.setText(appDate);
        editAppNotes.setText(appNotes);
        editAppCost.setText(appointmentCost);
        editAppId.setText(appointmentID);

        repository= new Repository(getApplication());


        Button button = findViewById(R.id.buttonReportSave);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (reportID == -1) {
                    report = new Report(0, editCustomer.getText().toString(),editAnimal.getText().toString(),editAppDate.getText().toString(),editAppNotes.getText().toString(),Double.valueOf(editAppCost.getText().toString()),Integer.parseInt(editAppId.getText().toString()));
                    repository.insert(report);
                } else {
                    report = new Report(reportID, editCustomer.getText().toString(),editAnimal.getText().toString(),editAppDate.getText().toString(),editAppNotes.getText().toString(),Double.valueOf(editAppCost.getText().toString()),Integer.parseInt(editAppId.getText().toString()));
                    repository.update(report);
                }
                Intent intent = new Intent(ReportInvoice.this, ReportList.class);
                startActivity(intent);
            }
        });
        Button delete = findViewById(R.id.buttonReportDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ReportInvoice.this, ReportList.class);
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

                Intent intent = new Intent(ReportInvoice.this, HomeScreen.class);
                startActivity(intent);

                return true;

            case R.id.delete:

                for (Report report : repository.getmAllReports()) {
                    if (report.getReportID() == reportID)
                        current = report;
                }
                repository.delete(current);
                Toast.makeText(ReportInvoice.this, current.getReportCustName() + " was deleted", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(ReportInvoice.this, ReportList.class);
                startActivity(intent2);

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}
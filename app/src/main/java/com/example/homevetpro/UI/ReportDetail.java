package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.Report;
import com.example.homevetpro.R;

import java.util.ArrayList;
import java.util.List;

public class ReportDetail extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_detail);
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

        repository= new Repository(getApplication());
        Spinner spinnerCust = findViewById(R.id.spinnerCustomer);
        Spinner spinnerAnimal = findViewById(R.id.spinnerAnimal);
        Spinner spinnerAppointment = findViewById(R.id.spinnerAppointment);
        List<String> customerNames = new ArrayList<>();
        for(Customer customer : repository.getmAllCustomers()){
            customerNames.add(customer.getCustomerName());
        }
        ArrayAdapter<String> customerArrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item,customerNames);
        spinnerCust.setAdapter(customerArrayAdapter);
        spinnerCust.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                editCustomer.setText(customerArrayAdapter.getItem(position));
                String selectedCustomerName = customerArrayAdapter.getItem(position);
                int selectedCustomerId = repository.getmIDByName(selectedCustomerName);

                List<String> animalNames = new ArrayList<>();
                for(Animal animal : repository.getmAllAnimals()){
                    if(animal.getAnimalCustID()==selectedCustomerId){
                        animalNames.add(animal.getAnimalName());
                    }
                }
                ArrayAdapter<String> animalArrayAdapter = new ArrayAdapter<>(ReportDetail.this,android.R.layout.simple_spinner_item, animalNames);
                spinnerAnimal.setAdapter(animalArrayAdapter);

                spinnerAnimal.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                        editAnimal.setText(animalArrayAdapter.getItem(position));
                        String selectedAnimalName = animalArrayAdapter.getItem(position);
                        int selectedAnimalId = repository.getmIDByAnimal(selectedAnimalName);

                        List<String> appDates = new ArrayList<>();
                        for(Appointment appointment: repository.getmAllAppointments()){
                            if(appointment.getAppAnimalID()==selectedAnimalId){
                                appDates.add(appointment.getAppointmentDate());
                            }
                        }
                        ArrayAdapter<String> appointmentArrayAdapter = new ArrayAdapter<>(ReportDetail.this,android.R.layout.simple_spinner_item,appDates);
                        spinnerAppointment.setAdapter(appointmentArrayAdapter);

                        spinnerAppointment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView1, View view, int i, long l) {
                                editAppDate.setText(appointmentArrayAdapter.getItem(i));
                                editAppId.setText(String.valueOf(selectedAnimalId));

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView1) {

                                editAppDate.setText("Nothing selected");

                            }
                        });

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {
                        editAnimal.setText("Nothing selected");
                    }
                });

                }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                editCustomer.setText("Nothing selected");
            }
        });

    }
}
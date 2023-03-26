package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class HomeScreen extends AppCompatActivity {

    private Repository repository;
    RadioButton radioAll;
    RadioButton radioWeek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        RecyclerView recyclerView =findViewById(R.id.recyclerViewHomescreen);
        final AppointmentAdapter appointmentAdapter=new AppointmentAdapter(this);
        recyclerView.setAdapter(appointmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository=new Repository(getApplication());
        List<Appointment> allAppointments =repository.getmAllAppointments();
        appointmentAdapter.setAppointments(allAppointments);
        Button customers = findViewById(R.id.buttonCustomers);
        Button animals = findViewById(R.id.buttonAnimals);
        Button appointments =findViewById(R.id.buttonAppointments);
        Button reports = findViewById(R.id.buttonReports);
        Button exit = findViewById(R.id.buttonExit);

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(HomeScreen.this, MainActivity.class);
                startActivity(intent5);
            }
        });
        reports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(HomeScreen.this, ReportList.class);
                startActivity(intent4);
            }
        });
        appointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(HomeScreen.this, AppointmentList.class);
                startActivity(intent3);
            }
        });
        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(HomeScreen.this, AnimalList.class);
                startActivity(intent2);
            }
        });
        customers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(HomeScreen.this, CustomerList.class);
                startActivity(intent);
            }
        });
    }
}
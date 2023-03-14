package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homevetpro.R;

public class HomeScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Button customers = findViewById(R.id.buttonCustomers);
        Button animals = findViewById(R.id.buttonAnimals);
        Button appointments =findViewById(R.id.buttonAppointments);
        Button reports = findViewById(R.id.buttonReports);
        Button exit = findViewById(R.id.buttonExit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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
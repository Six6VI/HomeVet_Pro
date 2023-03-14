package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homevetpro.R;

public class AppointmentList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        Button addCustomer = findViewById(R.id.appointmentAdd);
        Button exitCustomer = findViewById(R.id.appointmentExit);
        exitCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AppointmentList.this, HomeScreen.class);
                startActivity(intent2);
            }
        });
        addCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentList.this, AppointmentDetails.class);
                startActivity(intent);
            }
        });
    }
}
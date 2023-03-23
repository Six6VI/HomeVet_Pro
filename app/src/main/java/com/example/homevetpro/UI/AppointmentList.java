package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.List;

public class AppointmentList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);

        RecyclerView recyclerView =findViewById(R.id.recyclerViewAppointment);
        final AppointmentAdapter appointmentAdapter=new AppointmentAdapter(this);
        recyclerView.setAdapter(appointmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository=new Repository(getApplication());
        List<Appointment> allAppointments =repository.getmAllAppointments();
        appointmentAdapter.setAppointments(allAppointments);

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
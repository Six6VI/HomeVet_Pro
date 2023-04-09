package com.example.homevetpro.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.List;

public class HomeScreen extends AppCompatActivity {

    Repository repository;
    RecyclerView recyclerView;

    List<Appointment> appointmentList;
    List<Customer> customerList;
    AppointmentAdapter appointmentAdapter;
    int appID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        repository = new Repository(getApplication());

        appointmentList = repository.getmAllAppointments();
        customerList = repository.getmAllCustomers();
        appointmentAdapter = new AppointmentAdapter(this,appointmentList);
        appID=getIntent().getIntExtra("appID",-1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appRecyclerView();

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

    public void appRecyclerView() {

        recyclerView = findViewById(R.id.recyclerViewHomescreen);
        recyclerView.setAdapter(appointmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Type here to search by Date");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                appointmentAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }



}
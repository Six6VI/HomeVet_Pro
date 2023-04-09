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
import com.example.homevetpro.R;

import java.util.List;

public class AppointmentList extends AppCompatActivity {

    Repository repository;
    RecyclerView recyclerView;

    List<Appointment> appointmentList;
    AppointmentAdapter appointmentAdapter;
    int appID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_list);
        repository = new Repository(getApplication());

        appointmentList = repository.getmAllAppointments();
        appointmentAdapter = new AppointmentAdapter(this, appointmentList);
        appID = getIntent().getIntExtra("appID", -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        appRecyclerView();

        Button exitCustomer = findViewById(R.id.appointmentExit);
        exitCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AppointmentList.this, HomeScreen.class);
                startActivity(intent2);
            }
        });
    }

    public void appRecyclerView() {
        recyclerView = findViewById(R.id.recyclerViewAppointment);
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
package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdd extends AppCompatActivity {

    EditText editID;
    EditText editName;
    EditText editType;
    EditText editGender;
    EditText editBirthday;
    EditText editColor;
    EditText editWeight;
    EditText editNotes;
    EditText editEnterDate;
    EditText editModifyDate;
    EditText editCustAnimalID;
    EditText getEditCustAnimalName;

    int animalID;
    String animalName;
    String animalType;
    String animalGender;
    String animalBirthday;
    String animalColor;
    int animalWeight;
    String animalNotes;
    String animalEnterDate;
    String animalModifyDate;
    int animalCustID;

    Animal animal;
    Repository repository;

    List<Appointment> appointmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_add);

        editID = findViewById(R.id.editTextAnimalID);
        editName = findViewById(R.id.editTextAnimalName);
        editType = findViewById(R.id.editTextAnimalType);
        editGender = findViewById(R.id.editTextAnimalGender);
        editBirthday = findViewById(R.id.editTextAnimalBirth);
        editColor = findViewById(R.id.editTextAnimalColor);
        editWeight = findViewById(R.id.editTextAnimalWeight);
        editNotes = findViewById(R.id.editTextAnimalNotes);
        editEnterDate = findViewById(R.id.editTextAnimalEnter);
        editModifyDate = findViewById(R.id.editTextAnimalModify);
        editCustAnimalID = findViewById(R.id.editTextAnimalCustID);


        animalID = getIntent().getIntExtra("animalID", -1);
        animalName = getIntent().getStringExtra("animalName");
        animalType = getIntent().getStringExtra("animalType");
        animalGender = getIntent().getStringExtra("animalGender");
        animalBirthday = getIntent().getStringExtra("animalBirthday");
        animalColor = getIntent().getStringExtra("animalColor");
        animalWeight = getIntent().getIntExtra("animalWeight", 0);
        animalNotes = getIntent().getStringExtra("animalNotes");
        animalEnterDate = getIntent().getStringExtra("animalEnterDate");
        animalModifyDate = getIntent().getStringExtra("animalModifyDate");
        animalCustID = getIntent().getIntExtra("animalCustID", -1);

        String aniID = String.valueOf(animalID);
        String aniCustID = String.valueOf(animalCustID);
        String aniWeight = String.valueOf(animalWeight);

        editID.setText(aniID);
        editName.setText(animalName);
        editType.setText(animalType);
        editGender.setText(animalGender);
        editBirthday.setText(animalBirthday);
        editColor.setText(animalColor);
        editWeight.setText(aniWeight);
        editNotes.setText(animalNotes);
        editEnterDate.setText(animalEnterDate);
        editModifyDate.setText(animalModifyDate);
        editCustAnimalID.setText(aniCustID);

        repository = new Repository(getApplication());
        appointmentList = repository.getmAllAppointments();

        RecyclerView recyclerView = findViewById(R.id.recyclerViewAppointment);
        repository = new Repository(getApplication());
        final AppointmentAdapter appointmentAdapter = new AppointmentAdapter(this, appointmentList);
        recyclerView.setAdapter(appointmentAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        List<Appointment> filteredApp = new ArrayList<>();
        for (Appointment p : repository.getmAllAppointments()) {
            if (p.getAppAnimalID() == animalID) filteredApp.add(p);
        }

        appointmentAdapter.setAppointments(filteredApp);

        Button save = findViewById(R.id.buttonAnimalSave);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animalID == -1) {
                    animal = new Animal(0, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.valueOf(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.valueOf(editCustAnimalID.getText().toString()));
                    repository.insert(animal);
                } else {
                    animal = new Animal(animalID, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.valueOf(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.valueOf(editCustAnimalID.getText().toString()));
                    repository.update(animal);
                }
                Intent intent = new Intent(AnimalAdd.this, AnimalList.class);
                startActivity(intent);
            }
        });
        Button add = findViewById(R.id.buttonAnimalAddApp);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animalID == -1) {
                    animal = new Animal(0, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.valueOf(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.valueOf(editCustAnimalID.getText().toString()));
                    repository.insert(animal);
                } else {
                    animal = new Animal(animalID, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.valueOf(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.valueOf(editCustAnimalID.getText().toString()));
                    repository.update(animal);
                }

                int animalID = Integer.parseInt(editID.getText().toString());
                int animalCustomerID =Integer.parseInt(editCustAnimalID.getText().toString());
                Intent intent = new Intent(AnimalAdd.this, AppointmentDetails.class);
                intent.putExtra("appAnimalID", animalID);
                intent.putExtra("appCustID", animalCustomerID);
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

                Intent intent = new Intent(AnimalAdd.this, HomeScreen.class);
                startActivity(intent);

                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
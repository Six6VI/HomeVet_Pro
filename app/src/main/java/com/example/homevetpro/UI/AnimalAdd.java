package com.example.homevetpro.UI;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AnimalAdd extends AppCompatActivity {

    final Calendar mCalendarBirth = Calendar.getInstance();
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
    DatePickerDialog.OnDateSetListener birthDate;
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
    Animal current;
    Repository repository;

    List<Appointment> appointmentList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_add);

        editID = findViewById(R.id.editTextAnimalID);
        editID.setEnabled(false);
        editName = findViewById(R.id.editTextAnimalName);
        editType = findViewById(R.id.editTextAnimalType);
        editGender = findViewById(R.id.editTextAnimalGender);
        editBirthday = findViewById(R.id.editTextAnimalBirth);
        editColor = findViewById(R.id.editTextAnimalColor);
        editWeight = findViewById(R.id.editTextAnimalWeight);
        editNotes = findViewById(R.id.editTextAnimalNotes);
        String myFormat = "MM-dd-yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        editEnterDate = findViewById(R.id.editTextAnimalEnter);
        editEnterDate.setEnabled(false);
        editModifyDate = findViewById(R.id.editTextAnimalModify);
        editModifyDate.setEnabled(false);
        editCustAnimalID = findViewById(R.id.editTextAnimalCustID);
        editCustAnimalID.setEnabled(false);


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
        editModifyDate.setText(sdf.format(new Date()));
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
                    animal = new Animal(0, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.parseInt(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.parseInt(editCustAnimalID.getText().toString()));
                    repository.insert(animal);
                } else {
                    animal = new Animal(animalID, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.parseInt(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.parseInt(editCustAnimalID.getText().toString()));
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
                    animal = new Animal(0, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.parseInt(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.parseInt(editCustAnimalID.getText().toString()));
                    repository.insert(animal);
                } else {
                    animal = new Animal(animalID, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.parseInt(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.parseInt(editCustAnimalID.getText().toString()));
                    repository.update(animal);
                }

                int animalID = Integer.parseInt(editID.getText().toString());
                int animalCustomerID = Integer.parseInt(editCustAnimalID.getText().toString());
                Intent intent = new Intent(AnimalAdd.this, AppointmentDetails.class);
                intent.putExtra("appAnimalID", animalID);
                intent.putExtra("appCustID", animalCustomerID);
                startActivity(intent);

            }
        });

        editBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = editBirthday.getText().toString();
                try {
                    mCalendarBirth.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AnimalAdd.this, birthDate, mCalendarBirth
                        .get(Calendar.YEAR), mCalendarBirth.get(Calendar.MONTH), mCalendarBirth.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        birthDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendarBirth.set(Calendar.YEAR, year);
                mCalendarBirth.set(Calendar.MONTH, month);
                mCalendarBirth.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelBirth();
            }
        };
    }

    private void updateLabelBirth() {
        String myFormat = "MM-dd-yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editBirthday.setText(sdf.format(mCalendarBirth.getTime()));
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

            case R.id.delete:

                for (Animal animal : repository.getmAllAnimals()) {
                    if (animal.getAnimalID() == animalID)
                        current = animal;
                }
                repository.delete(current);
                Toast.makeText(AnimalAdd.this, current.getAnimalName() + " was deleted", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(AnimalAdd.this, AnimalList.class);
                startActivity(intent2);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
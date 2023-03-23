package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.R;

public class AppointmentDetails extends AppCompatActivity {

    EditText editID;
    EditText editDate;
    EditText editNotes;
    EditText editDuration;
    EditText editCost;
    EditText editEnterDate;
    EditText editModifyDate;
    EditText editAppAnimalID;

    int appID;
    String appDate;
    String appNotes;
    int appDuration;
    Double appCost;
    String appEnterDate;
    String appModifyDate;
    int appAnimalID;

    Appointment appointment;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        editID=findViewById(R.id.editTextAppID);
        editDate=findViewById(R.id.editTextAppDate);
        editNotes=findViewById(R.id.editTextAppNotes);
        editDuration=findViewById(R.id.editTextAppDuration);
        editCost=findViewById(R.id.editTextAppCost);
        editEnterDate=findViewById(R.id.editTextAppAdded);
        editModifyDate=findViewById(R.id.editTextAppModify);
        editAppAnimalID=findViewById(R.id.editTextAppAnimalID);

        appID = getIntent().getIntExtra("appID",-1);
        appDate = getIntent().getStringExtra("appDate");
        appNotes = getIntent().getStringExtra("appNotes");
        appDuration = getIntent().getIntExtra("appDuration", 0);
        appCost = getIntent().getDoubleExtra("appCost",0.00);
        appEnterDate = getIntent().getStringExtra("appEnterDate");
        appModifyDate = getIntent().getStringExtra("appModifyDate");
        appAnimalID = getIntent().getIntExtra("appAnimalID",-1);

        String sAppID = String.valueOf(appID);
        String sAppDuration = String.valueOf(appDuration);
        String sAppCost = String.valueOf(appCost);
        String sAppAnimalID = String.valueOf(appAnimalID);

        editID.setText(sAppID);
        editDate.setText(appDate);
        editNotes.setText(appNotes);
        editDuration.setText(sAppDuration);
        editCost.setText(sAppCost);
        editEnterDate.setText(appEnterDate);;
        editModifyDate.setText(appModifyDate);
        editAppAnimalID.setText(sAppAnimalID);

        repository = new Repository(getApplication());



    }
}
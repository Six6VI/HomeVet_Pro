package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.time.LocalDate;

public class AppointmentDetails extends AppCompatActivity {

    EditText editID;
    EditText editDate;
    EditText editNotes;
    EditText editDuration;
    EditText editCost;
    EditText editEnterDate;
    EditText editModifyDate;
    EditText editAppAnimalID;
    EditText editAppCustID;

    int appID;
    String appDate;
    String appNotes;
    int appDuration;
    Double appCost;
    String appEnterDate;
    String appModifyDate;
    int appAnimalID;
    int appCustID;

    Appointment appointment;
    Appointment current;
    String customerName;
    String custName;
    Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment_details);

        editID=findViewById(R.id.editTextAppID);
        editID.setEnabled(false);
        editDate=findViewById(R.id.editTextAppDate);
        editNotes=findViewById(R.id.editTextAppNotes);
        editDuration=findViewById(R.id.editTextAppDuration);
        editCost=findViewById(R.id.editTextAppCost);
        editEnterDate=findViewById(R.id.editTextAppAdded);
        editModifyDate=findViewById(R.id.editTextAppModify);
        editAppAnimalID=findViewById(R.id.editTextAppAnimalID);
        editAppAnimalID.setEnabled(false);
        editAppCustID=findViewById(R.id.editTextAppCustID);
        editAppCustID.setEnabled(false);

        appID = getIntent().getIntExtra("appID",-1);
        appDate = getIntent().getStringExtra("appDate");
        appNotes = getIntent().getStringExtra("appNotes");
        appDuration = getIntent().getIntExtra("appDuration", 0);
        appCost = getIntent().getDoubleExtra("appCost",0.00);
        appEnterDate = getIntent().getStringExtra("appEnterDate");
        appModifyDate = getIntent().getStringExtra("appModifyDate");
        appAnimalID = getIntent().getIntExtra("appAnimalID",-1);
        appCustID = getIntent().getIntExtra("appCustID", -1);


        String sAppID = String.valueOf(appID);
        String sAppDuration = String.valueOf(appDuration);
        String sAppCost = String.valueOf(appCost);
        String sAppAnimalID = String.valueOf(appAnimalID);
        String sAppCustID = String.valueOf(appCustID);

        editID.setText(sAppID);
        editDate.setText(appDate);
        editNotes.setText(appNotes);
        editDuration.setText(sAppDuration);
        editCost.setText(sAppCost);
        editEnterDate.setText(appEnterDate);;
        editModifyDate.setText(appModifyDate);
        editAppAnimalID.setText(sAppAnimalID);
        editAppCustID.setText(sAppCustID);

        repository = new Repository(getApplication());

        Button button = findViewById(R.id.buttonAppSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (appID == -1) {
                    appointment = new Appointment(0, editDate.getText().toString(), editNotes.getText().toString(), Integer.parseInt(editDuration.getText().toString()), Double.valueOf(editCost.getText().toString()), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.parseInt(editAppAnimalID.getText().toString()), Integer.parseInt(editAppCustID.getText().toString()));
                    repository.insert(appointment);
                } else {
                    appointment = new Appointment(appID, editDate.getText().toString(), editNotes.getText().toString(), Integer.parseInt(editDuration.getText().toString()), Double.valueOf(editCost.getText().toString()), editEnterDate.getText().toString(), editModifyDate.getText().toString(), Integer.parseInt(editAppAnimalID.getText().toString()), Integer.parseInt(editAppCustID.getText().toString()));
                    repository.update(appointment);
                }
                Intent intent = new Intent(AppointmentDetails.this, HomeScreen.class);
                startActivity(intent);
            }
        });
        Button delete = findViewById(R.id.buttonAppDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(AppointmentDetails.this, AnimalList.class);
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

                Intent intent = new Intent(AppointmentDetails.this, HomeScreen.class);
                startActivity(intent);

                return true;

            case R.id.delete:

                for (Appointment appointment : repository.getmAllAppointments()) {
                    if (appointment.getAppointmentID() == appID)
                        current = appointment;
                }
                repository.delete(current);
                for (Customer customer : repository.getmAllCustomers()){
                    if (customer.getCustomerID() == appCustID)
                        customerName = customer.getCustomerName();
                }
                Toast.makeText(AppointmentDetails.this, customerName + "'s appointment was deleted", Toast.LENGTH_LONG).show();
                Intent intent2 = new Intent(AppointmentDetails.this, AppointmentList.class);
                startActivity(intent2);

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
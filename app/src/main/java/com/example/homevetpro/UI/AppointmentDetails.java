package com.example.homevetpro.UI;

import android.app.Application;
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

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

    DatePickerDialog.OnDateSetListener appointmentDate;
    final Calendar mCalendarApp = Calendar.getInstance();

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
    static String customerName;
    static Repository repository;

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
        editEnterDate.setEnabled(false);
        editModifyDate=findViewById(R.id.editTextAppModify);
        editModifyDate.setEnabled(false);
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

        String myFormat = "MM-dd-yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
        String sAppID = String.valueOf(appID);
        String sAppDuration = String.valueOf(appDuration);
        String sAppCost = String.valueOf(appCost);
        String sAppAnimalID = String.valueOf(appAnimalID);
        String sAppCustID = String.valueOf(appCustID);
        String modDate = sdf.format(new Date());

        editID.setText(sAppID);
        editDate.setText(appDate);
        editNotes.setText(appNotes);
        editDuration.setText(sAppDuration);
        editCost.setText(sAppCost);
        editModifyDate.setText(appModifyDate);
        editAppAnimalID.setText(sAppAnimalID);
        editAppCustID.setText(sAppCustID);

        /**
         * This will set the timestamp when we create a new Customer
         */

        if(appID==-1){
            editEnterDate.setText(sdf.format(new Date()));
        }else {
            editEnterDate.setText(appEnterDate);
        }
        if(appCustID == -1){
            editModifyDate.setText(sdf.format(new Date()));
        }else{
            editModifyDate.setText(modDate);

        }

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

        editDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String info = editDate.getText().toString();
                try {
                    mCalendarApp.setTime(sdf.parse(info));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                new DatePickerDialog(AppointmentDetails.this, appointmentDate, mCalendarApp
                        .get(Calendar.YEAR), mCalendarApp.get(Calendar.MONTH), mCalendarApp.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        appointmentDate = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                mCalendarApp.set(Calendar.YEAR, year);
                mCalendarApp.set(Calendar.MONTH, month);
                mCalendarApp.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                updateLabelAppDate();
            }
        };
    }
    private void updateLabelAppDate() {
        String myFormat = "MM-dd-yy";
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editDate.setText(sdf.format(mCalendarApp.getTime()));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_screen, menu);

        return true;
    }

    public static String getcustName(int id) {
        Repository repository = new Repository(new Application());
        for (Customer customer : repository.getmAllCustomers()) {
            if (customer.getCustomerID() == id)
                customerName = customer.getCustomerName();
        }
        return customerName;

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
package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.User;
import com.example.homevetpro.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button=findViewById(R.id.login);


        //Test if database is working
        /*
        User user = new User(0, "test", "test");
        Repository repository=new Repository(getApplication());
        repository.insert(user);
        */

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.loadSAmpleDAta:
                //Test if database is working

        User user = new User(0, "test", "test");
        Customer customer = new Customer(0, "Sam Smith", "123 first st", "46577", "111-222-3333", "01-01-2023", "02-01-2023");
        Animal animal = new Animal(0,"sox","cat","male","01-01-2023","orange",12,"He likes fish","01-01-2023","02-03-2023",1);
        Appointment appointment = new Appointment(0,"02-12-2023","Sox is allergic to grass and wheat", 34, 76.88, "02-02-2023", "02-05-2023", 1);
        Repository repository=new Repository(getApplication());
        repository.insert(user);
        repository.insert(customer);
        repository.insert(animal);
        repository.insert(appointment);

           return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
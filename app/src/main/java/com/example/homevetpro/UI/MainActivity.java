package com.example.homevetpro.UI;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.Entities.User;
import com.example.homevetpro.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;

    String user;
    String userPass;

    User users;

    public static int numAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = username.getText().toString();
                userPass = password.getText().toString();

                Repository repository = new Repository(getApplication());

                List<String> allUsers = new ArrayList<>();
                for(User user: repository.getmAllUsers()){
                    allUsers.add(user.getUserName());
                }

                List<String> allPasswords = new ArrayList<>();
                for(User user: repository.getmAllUsers()){
                    allPasswords.add(user.getUserPassword());
                }

                if(username.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this,"Please Enter Username and Password",Toast.LENGTH_SHORT).show();
                }
                else if(allUsers.contains(user) && allPasswords.contains(userPass)){
                    Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MainActivity.this, HomeScreen.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this," Login Failed", Toast.LENGTH_SHORT).show();
                    return;
                }

            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.login_menu, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.loadSAmpleDAta:
                //Test if database is working

                User user = new User(0, "test", "test");
                Customer customer = new Customer(1, "Justin Hogle", "448 Elliot St", "46577", "111-222-3333", "01-01-2018", "02-01-2023");
                Customer customer2 = new Customer(2, "Bill Thompson", "847 White Ave", "66567", "665-325-4875", "02-14-1998", "02-01-2023");
                Customer customer3 = new Customer(3, "Tina White", "357 Cook Lake", "45784", "258-554-3333", "05-15-2002", "02-01-2023");
                Customer customer4 = new Customer(4, "Greg Black", "951 River Rd", "98956", "359-685-5474", "07-24-1993", "02-01-2023");
                Customer customer5 = new Customer(5, "Aaron Johnson", "123 first st", "77481", "234-254-4125", "06-14-2015", "02-01-2023");
                Customer customer6 = new Customer(6, "Mike Richer", "5569 Whitehead Lane", "90210", "145-254-8521", "11-11-2015", "02-01-2023");
                Customer customer7 = new Customer(7, "Rick Martin", "5547 South Town St", "23547", "147-854-8854", "10-30-2016", "02-01-2023");
                Customer customer8 = new Customer(8, "Tom Cruise", "123 First Second St", "48858", "260-215-1140", "05-06-2018", "02-01-2023");
                Customer customer9 = new Customer(9, "Pete Richardson", "3696 Horse Court", "45784", "214-578-3757", "09-20-2021", "02-01-2023");
                Animal animal = new Animal(1, "Jaxon", "Cat", "Male", "01-01-2023", "Black", 12, "Hips are uneven.", "04-05-2023", "02-03-2023", 1);
                Animal animal2 = new Animal(2, "Sox", "Cat", "Female", "03-18-1999", "White", 8, "Deaf", "12-01-2022", "02-03-2023", 2);
                Animal animal3 = new Animal(3, "Steve", "Dog", "Female", "04-01-2021", "Brown", 35, "Does not like strangers.", "08-06-2022", "02-03-2023", 3);
                Animal animal4 = new Animal(4, "Toby", "Dog", "Male", "01-20-2018", "Tan", 40, "Caution, bites when excited.", "09-01-2012", "02-03-2023", 4);
                Animal animal5 = new Animal(5, "Blue", "Dog", "Male", "05-01-2016", "Grey", 55, "Very sweet dog.", "04-20-2021", "02-03-2023", 5);
                Animal animal6 = new Animal(6, "Stripes", "Cat", "Male", "07-24-2020", "Orange", 10, "Allergic to grass.", "08-13-2022", "02-03-2023", 6);
                Animal animal7 = new Animal(7, "Spot", "Cat", "Female", "11-18-2021", "Black", 9, "Prone to hairballs.", "03-29-2020", "02-03-2023", 7);
                Animal animal8 = new Animal(8, "Goldie", "Cat", "Male", "10-17-2022", "Tan", 11, "Missing right eye.", "11-13-2021", "02-03-2023", 8);
                Animal animal9 = new Animal(9, "Duke", "Dog", "Female", "08-23-2012", "Black", 65, "Has joint pain.", "09-06-2020", "02-03-2023", 9);
                Repository repository = new Repository(getApplication());
                repository.insert(user);
                repository.insert(customer);
                repository.insert(customer2);
                repository.insert(customer3);
                repository.insert(customer4);
                repository.insert(customer5);
                repository.insert(customer6);
                repository.insert(customer7);
                repository.insert(customer8);
                repository.insert(customer9);

                repository.insert(animal);
                repository.insert(animal2);
                repository.insert(animal3);
                repository.insert(animal4);
                repository.insert(animal5);
                repository.insert(animal6);
                repository.insert(animal7);
                repository.insert(animal8);
                repository.insert(animal9);


                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
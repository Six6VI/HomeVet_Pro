package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.List;

public class AnimalList extends AppCompatActivity {

    private Repository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_list);

        RecyclerView recyclerView =findViewById(R.id.recyclerViewAnimal);
        final AnimalAdapter animalAdapter=new AnimalAdapter(this);
        recyclerView.setAdapter(animalAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        repository=new Repository(getApplication());
        List<Animal> allAnimals =repository.getmAllAnimals();
        animalAdapter.setAnimals(allAnimals);

        Button addAnimal = findViewById(R.id.animalAdd);
        Button exitAnimal =findViewById(R.id.animalExit);
        exitAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(AnimalList.this, HomeScreen.class);
                startActivity(intent2);
            }
        });
        addAnimal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AnimalList.this, AnimalDetails.class);
                startActivity(intent);
            }
        });
    }
}
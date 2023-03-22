package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

public class AnimalDetails extends AppCompatActivity {

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





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_details);

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
        animalBirthday= getIntent().getStringExtra("animalBirthday");
        animalColor=getIntent().getStringExtra("animalColor");
        animalWeight = getIntent().getIntExtra("animalWeight",0);
        animalNotes = getIntent().getStringExtra("animalNotes");
        animalEnterDate = getIntent().getStringExtra("animalEnterDate");
        animalModifyDate = getIntent().getStringExtra("animalModifyDate");
        animalCustID = getIntent().getIntExtra("animalCustID",-1);

        String aniID = String.valueOf(animalID);
        String aniCustID = String.valueOf(animalCustID);
        String aniWeight =String.valueOf(animalWeight);

        editID.setText(aniID);
        editName.setText(animalName);
        editType.setText(animalType);
        editGender.setText(animalGender);;
        editBirthday.setText(animalBirthday);
        editColor.setText(animalColor);;
        editWeight.setText(aniWeight);
        editNotes.setText(animalNotes);
        editEnterDate.setText(animalEnterDate);
        editModifyDate.setText(animalModifyDate);
        editCustAnimalID.setText(aniCustID);

        repository = new Repository(getApplication());

        Button button = findViewById(R.id.buttonAnimalSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (animalID == -1) {
                    animal = new Animal(0, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.valueOf(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(),editModifyDate.getText().toString(),Integer.valueOf(editCustAnimalID.getText().toString()));
                    repository.insert(animal);
                } else {
                    animal = new Animal(0, editName.getText().toString(), editType.getText().toString(), editGender.getText().toString(), editBirthday.getText().toString(), editColor.getText().toString(), Integer.valueOf(editWeight.getText().toString()), editNotes.getText().toString(), editEnterDate.getText().toString(),editModifyDate.getText().toString(),Integer.valueOf(editCustAnimalID.getText().toString()));
                    repository.update(animal);
                }
                Intent intent = new Intent(AnimalDetails.this, AnimalList.class);
                startActivity(intent);
            }
        });

    }
}
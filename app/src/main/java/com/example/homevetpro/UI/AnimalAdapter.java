package com.example.homevetpro.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    class AnimalViewHolder extends RecyclerView.ViewHolder{

        private final TextView animalTextView;
        private AnimalViewHolder(View itemview){
            super(itemview);
            animalTextView=itemview.findViewById(R.id.animalTextView);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Animal current = mAnimal.get(position);
                    Intent intent=new Intent(context, CustomerDetails.class);
                    intent.putExtra("animalID", current.getAnimalID());
                    intent.putExtra("animalName", current.getAnimalName());
                    intent.putExtra("animalType", current.getAnimalType());
                    intent.putExtra("animalGender", current.getAnimalGender());
                    intent.putExtra("animalBirthday", current.getAnimalBirthday());
                    intent.putExtra("animalColor", current.getAnimalColor());
                    intent.putExtra("animalWeight", current.getAnimalWeight());
                    intent.putExtra("animalNotes", current.getAnimalNotes());
                    intent.putExtra("animalEnterDate", current.getAnimalEnterDate());
                    intent.putExtra("animalModifyDate", current.getAnimalModifyDate());
                    intent.putExtra("animalCustID", current.getAnimalCustID());
                    context.startActivity(intent);

                }
            });
        }
    }

    private List<Animal> mAnimal;
    private final Context context;
    private final LayoutInflater mInflater;
    public AnimalAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AnimalAdapter.AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.animal_list_item,parent,false);
        return new AnimalAdapter.AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.AnimalViewHolder holder, int position) {
        if (mAnimal!=null){
            Animal current = mAnimal.get(position);
            String name = current.getAnimalName();
            holder.animalTextView.setText(name);

        }
        else {
            holder.animalTextView.setText("No Animals to Show");
        }

    }

    @Override
    public int getItemCount() {
        return mAnimal.size();
    }

    public void setAnimals(List<Animal> animal){
        mAnimal=animal;
        notifyDataSetChanged();
    }


}


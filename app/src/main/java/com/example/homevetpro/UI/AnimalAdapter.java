package com.example.homevetpro.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.util.ArrayList;
import java.util.List;

public class AnimalAdapter extends RecyclerView.Adapter<AnimalAdapter.AnimalViewHolder> {

    private List<Animal> animalList;
    private final List<Animal> animalListFull;

    private final Context context;
    private final LayoutInflater mInflater;

    public AnimalAdapter(Context context, List<Animal> animalList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.animalList = animalList;
        animalListFull = new ArrayList<>(animalList);
    }

    class AnimalViewHolder extends RecyclerView.ViewHolder {

        private final TextView animalTextView;
        private final TextView animalTextView2;

        private AnimalViewHolder(View itemview) {
            super(itemview);
            animalTextView2 = itemview.findViewById(R.id.animalCustomerTextView);
            animalTextView = itemview.findViewById(R.id.animalNameTextView);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Animal current = animalList.get(position);
                    Intent intent = new Intent(context, AnimalAdd.class);
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


    @NonNull
    @Override
    public AnimalAdapter.AnimalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.animal_list_item, parent, false);
        return new AnimalViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalAdapter.AnimalViewHolder holder, int position) {
        Animal current = animalList.get(position);
        int animalCustID = current.getAnimalCustID();
        String customerName = AppointmentDetails.getcustName(animalCustID);

        if (animalList != null) {
            holder.animalTextView2.setText(current.getAnimalName());
            holder.animalTextView.setText(customerName);
        } else {
            holder.animalTextView.setText("No Animals to Show");
        }

    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public void setAnimals(List<Animal> animalList) {
        this.animalList = animalList;
        notifyDataSetChanged();
    }

    public Filter getFilter() {
        return animalFilter;
    }

    private Filter animalFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Animal> filteredAnimal = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                filteredAnimal.addAll(animalListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Animal item : animalListFull) {
                    if (item.getAnimalName().toLowerCase().contains(filterPattern)) {
                        filteredAnimal.add(item);
                    }
                }
            }
            results.values = filteredAnimal;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            animalList.clear();
            animalList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}


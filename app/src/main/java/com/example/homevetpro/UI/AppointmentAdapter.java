package com.example.homevetpro.UI;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.DAO.CustomerDAO;
import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Animal;
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.Entities.Customer;
import com.example.homevetpro.R;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>{


    class AppointmentViewHolder extends RecyclerView.ViewHolder{

        private final TextView appCustTextView;
        private final TextView appAnimaltTextView;
        private final TextView appDateTextView;
        private AppointmentViewHolder(View itemview){
            super(itemview);
            appCustTextView=itemview.findViewById(R.id.appCustTextView);
            appAnimaltTextView=itemview.findViewById(R.id.appAnimalTextView);
            appDateTextView=itemview.findViewById(R.id.appDateTextView);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Appointment current = mAppointment.get(position);
                    Intent intent=new Intent(context, AppointmentDetails.class);
                    intent.putExtra("appID", current.getAppointmentID());
                    intent.putExtra("appDate", current.getAppointmentDate());
                    intent.putExtra("appNotes", current.getAppointmentNotes());
                    intent.putExtra("appDuration", current.getAppointmentDuration());
                    intent.putExtra("appCost", current.getAppointmentCost());
                    intent.putExtra("appEnterDate", current.getAppEnterDate());
                    intent.putExtra("appModifyDate", current.getAppModifyDate());
                    intent.putExtra("appAnimalID", current.getAppAnimalID());
                    intent.putExtra("appCustID",current.getAppCustID());

                    context.startActivity(intent);

                }
            });
        }
    }

    private static List<Appointment> mAppointment;
    private static List<Customer> mCustomer;
    private Repository repository;
    private final Context context;
    private final LayoutInflater mInflater;
    public AppointmentAdapter(Context context){
        mInflater= LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public AppointmentAdapter.AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.appointment_list_item,parent,false);
        return new AppointmentViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull AppointmentAdapter.AppointmentViewHolder holder, int position) {
        if (mAppointment!=null) {
            Appointment current = mAppointment.get(position);
            String customer = String.valueOf(current.getAppCustID());
            String animal = (" " + String.valueOf(current.getAppAnimalID()));
            String date = (" " + current.getAppointmentDate());


            holder.appCustTextView.setText(customer);
            holder.appAnimaltTextView.setText(animal);
            holder.appDateTextView.setText(date);

        }
        else {
            holder.appCustTextView.setText("No Appointments to Show");
        }

    }

    @Override
    public int getItemCount() {
        return mAppointment.size();
    }

    public void setAppointments(List<Appointment> appointment){
        mAppointment=appointment;
        notifyDataSetChanged();
    }
}

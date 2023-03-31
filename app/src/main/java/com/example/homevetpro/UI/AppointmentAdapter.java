package com.example.homevetpro.UI;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
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

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> implements Filterable {

    private List<Appointment> appointmentList;
    private final List<Appointment> appointmentListFull;
    Repository repository;
    String customerName;

    private final Context context;
    private final LayoutInflater mInflater;

    public AppointmentAdapter(Context context, List<Appointment> appointmentList){
        mInflater= LayoutInflater.from(context);
        this.context=context;
        this.appointmentList=appointmentList;
        appointmentListFull=new ArrayList<>(appointmentList);
    }

    class AppointmentViewHolder extends RecyclerView.ViewHolder{

        private  TextView appDateTextView;


        private AppointmentViewHolder(View itemview){
            super(itemview);

            appDateTextView=itemview.findViewById(R.id.appDateTextView);
            itemview.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position=getAdapterPosition();
                    final Appointment current = appointmentList.get(position);
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

    @NonNull
    @Override
    public AppointmentAdapter.AppointmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView=mInflater.inflate(R.layout.appointment_list_item,parent,false);
        return new AppointmentViewHolder(itemView);
    }



    @Override
    public void onBindViewHolder(@NonNull AppointmentViewHolder holder, int position) {

        if (appointmentList != null) {
            Appointment currentApp = appointmentList.get(position);
            holder.appDateTextView.setText(currentApp.getAppointmentDate());
        }
        else {
            holder.appDateTextView.setText("No Appointments to Show");
        }

    }

    @Override
    public int getItemCount() {
        return appointmentList.size();
    }

    public void setAppointments(List<Appointment> appointmentList){
        this.appointmentList=appointmentList;
        notifyDataSetChanged();
    }

    public Filter getFilter(){
        return appFilter;
    }
    private Filter appFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Appointment> filterdApps = new ArrayList<>();
            FilterResults results = new FilterResults();

            if(constraint==null || constraint.length() == 0){
                filterdApps.addAll(appointmentListFull);
            }else{
                String filterPattern = constraint.toString().toLowerCase().trim();

                for(Appointment item: appointmentListFull){
                    if(item.getAppointmentDate().toLowerCase().contains(filterPattern)){
                        filterdApps.add(item);
                    }
                }
            }
            results.values = filterdApps;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            appointmentList.clear();
            appointmentList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };
}

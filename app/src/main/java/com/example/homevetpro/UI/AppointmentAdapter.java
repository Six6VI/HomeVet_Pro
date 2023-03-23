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
import com.example.homevetpro.Entities.Appointment;
import com.example.homevetpro.R;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder>{


    class AppointmentViewHolder extends RecyclerView.ViewHolder{

        private final TextView appointmentTextView;
        private AppointmentViewHolder(View itemview){
            super(itemview);
            appointmentTextView=itemview.findViewById(R.id.appointmentTextView);
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
                    context.startActivity(intent);

                }
            });
        }
    }

    private static List<Appointment> mAppointment;
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
        if (mAppointment!=null){
            Appointment current = mAppointment.get(position);
            String name = current.getAppointmentDate();
            holder.appointmentTextView.setText(name);

        }
        else {
            holder.appointmentTextView.setText("No Appointments to Show");
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

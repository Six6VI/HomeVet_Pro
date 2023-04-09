package com.example.homevetpro.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.homevetpro.Entities.Report;
import com.example.homevetpro.R;

import java.util.ArrayList;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> implements Filterable {

    private final List<Report> reportListFull;
    private final Context context;
    private final LayoutInflater mInflater;
    private final List<Report> reportList;
    private final Filter appFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Report> filterdReports = new ArrayList<>();
            FilterResults results = new FilterResults();

            if (constraint == null || constraint.length() == 0) {
                filterdReports.addAll(reportListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (Report item : reportListFull) {
                    if (item.getReportCustName().toLowerCase().contains(filterPattern)) {
                        filterdReports.add(item);
                    }
                }
            }
            results.values = filterdReports;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            reportList.clear();
            reportList.addAll((List) results.values);
            notifyDataSetChanged();

        }
    };

    public ReportAdapter(Context context, List<Report> reportList) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        this.reportList = reportList;
        reportListFull = new ArrayList<>(reportList);
    }

    @NonNull
    @Override
    public ReportAdapter.ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.report_list_item, parent, false);
        return new ReportAdapter.ReportViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull ReportAdapter.ReportViewHolder holder, int position) {
        Report currentReport = reportList.get(position);


        holder.customerNameTextView.setText(currentReport.getReportCustName());
        holder.animalNameTextView.setText(currentReport.getReportAnimalName());
        holder.reportDateTextView.setText(currentReport.getReportAppDate());

    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public Filter getFilter() {
        return appFilter;
    }

    class ReportViewHolder extends RecyclerView.ViewHolder {

        private final TextView customerNameTextView;
        private final TextView animalNameTextView;
        private final TextView reportDateTextView;


        private ReportViewHolder(View itemview) {
            super(itemview);

            customerNameTextView = itemview.findViewById(R.id.custNameTextView);
            animalNameTextView = itemview.findViewById(R.id.animalNameTextView);
            reportDateTextView = itemview.findViewById(R.id.reportDateTextView);
            itemview.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Report current = reportList.get(position);
                    Intent intent = new Intent(context, ReportInvoice.class);
                    intent.putExtra("reportID", current.getReportID());
                    intent.putExtra("customerName", current.getReportCustName());
                    intent.putExtra("animalName", current.getReportAnimalName());
                    intent.putExtra("appDate", current.getReportAppDate());
                    intent.putExtra("appNotes", current.getReportAppNotes());
                    intent.putExtra("appCost", current.getReportAppCost());
                    intent.putExtra("appID", current.getReportAppID());
                    context.startActivity(intent);

                }
            });
        }
    }
}



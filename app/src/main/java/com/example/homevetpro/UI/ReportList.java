package com.example.homevetpro.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import androidx.appcompat.widget.SearchView;

import com.example.homevetpro.Database.Repository;
import com.example.homevetpro.Entities.Report;
import com.example.homevetpro.R;

import java.util.List;

public class ReportList extends AppCompatActivity {

    RecyclerView recyclerView;
    Repository repository;
    List<Report> mReports;
    ReportAdapter reportAdapter;
    int reportID;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_list);
        repository = new Repository(getApplication());

        mReports = repository.getmAllReports();
        reportAdapter = new ReportAdapter(this, mReports);
        reportID = getIntent().getIntExtra("reportID" , -1);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        reportRecyclerView();

        Button addReport = findViewById(R.id.reportAdd);
        Button exitReport = findViewById(R.id.reportExit);
        addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(ReportList.this, ReportDetail.class);
                startActivity(intent2);
            }
        });
        exitReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReportList.this, HomeScreen.class);
                startActivity(intent);
            }
        });
    }
    public void reportRecyclerView(){
        recyclerView = findViewById(R.id.recyclerViewReport);
        recyclerView.setAdapter(reportAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.search_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.actionSearch);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);
        searchView.setQueryHint("Type here to search by Customer");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                reportAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }
}
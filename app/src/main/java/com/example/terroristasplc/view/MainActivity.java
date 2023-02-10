package com.example.terroristasplc.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.terroristasplc.databinding.ActivityMainBinding;
import com.example.terroristasplc.dataclass.Terrorists;
import com.example.terroristasplc.interfaces.iPresenter;
import com.example.terroristasplc.interfaces.iView;
import com.example.terroristasplc.presenter.PresenterImpl;
import com.example.terroristasplc.view.adapter.TerroristAdapter;
import java.util.List;

public class MainActivity extends AppCompatActivity implements iView, SearchView.OnQueryTextListener {

    private iPresenter presenter;
    private ActivityMainBinding binding;
    private TerroristAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter = new PresenterImpl(this);
        search();
        binding.btnReintentar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.linearRetry.setVisibility(View.GONE);
                binding.progress.setVisibility(View.VISIBLE);
                search();
            }
        });
    }

    public void search(){
        binding.progress.setVisibility(View.VISIBLE);
        presenter.presenterSearch();
    }


    @Override
    public void viewShowResults(List<Terrorists> terroristsList) {
        LinearLayoutManager linear = new LinearLayoutManager(this,RecyclerView.VERTICAL, false);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, linear.getOrientation());
        binding.progress.setVisibility(View.GONE);
        adapter = new TerroristAdapter(terroristsList);
        binding.recycler.setAdapter(adapter);
        binding.recycler.addItemDecoration(dividerItemDecoration);
        binding.recycler.setLayoutManager(linear);
        binding.searchView.setOnQueryTextListener(this);
    }

    @Override
    public void showError(String error) {
        binding.progress.setVisibility(View.GONE);
        binding.linearRetry.setVisibility(View.VISIBLE);
        binding.txtReintentar.setText(error);
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        adapter.filter(query);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if (newText.isEmpty()) adapter.filter("");
        return false;
    }
}
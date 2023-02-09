package com.example.terroristasplc.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.terroristasplc.R;
import com.example.terroristasplc.databinding.ActivityMainBinding;
import com.example.terroristasplc.dataclass.SdnList;
import com.example.terroristasplc.dataclass.Terrorists;
import com.example.terroristasplc.interfaces.ApiService;
import com.example.terroristasplc.interfaces.iPresenter;
import com.example.terroristasplc.interfaces.iView;
import com.example.terroristasplc.presenter.PresenterImpl;
import com.example.terroristasplc.view.adapter.TerroristAdapter;
import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class MainActivity extends AppCompatActivity implements iView {

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

    public void searchByFilter(View view){
        binding.progress.setVisibility(View.VISIBLE);
        String search = binding.etSearch.getText().toString();
        presenter.presenterSearch();
    }

    public void search(){
        binding.progress.setVisibility(View.VISIBLE);
        presenter.presenterSearch();
    }


    @Override
    public void viewShowResults(List<Terrorists> terroristsList) {
        binding.progress.setVisibility(View.GONE);
        adapter = new TerroristAdapter(terroristsList);
        binding.recycler.setAdapter(adapter);
        binding.recycler.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL, false));
    }

    @Override
    public void showError(String error) {
        binding.progress.setVisibility(View.GONE);
        binding.linearRetry.setVisibility(View.VISIBLE);
        binding.txtReintentar.setText(error);
    }
}
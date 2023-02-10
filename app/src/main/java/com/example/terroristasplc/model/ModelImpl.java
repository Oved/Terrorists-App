package com.example.terroristasplc.model;


import com.example.terroristasplc.dataclass.SdnList;
import com.example.terroristasplc.dataclass.Terrorists;
import com.example.terroristasplc.interfaces.ApiService;
import com.example.terroristasplc.interfaces.iModel;
import com.example.terroristasplc.interfaces.iPresenter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class ModelImpl implements iModel {

    private final String BASE_URL = "https://www.treasury.gov";

    private iPresenter presenter;

    public ModelImpl(iPresenter presenter){
        this.presenter = presenter;
    }

    @Override
    public void modelSearch() {
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(100, TimeUnit.SECONDS)
                .readTimeout(100,TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(SimpleXmlConverterFactory.create())
                .build();

        ApiService api = retrofit.create(ApiService.class);
        Call<SdnList> call = api.serviceTwo();

        call.enqueue(new Callback<SdnList>() {
            @Override
            public void onResponse(Call<SdnList> call, Response<SdnList> response) {
                List<Terrorists> list = new ArrayList<>();
                if (response.isSuccessful()) {
                    SdnList res = response.body();
                    for (Terrorists terrorist : res.getSdnEntry()){
                        if (terrorist.getFirstName()!=null){
                            list.add(terrorist);
                        }
                    }
                    presenter.presenterShowResults(list);
                }else {
                    presenter.showError("La consulta no fue exitosa");
                }
            }

            @Override
            public void onFailure(Call<SdnList> call, Throwable t) {
                presenter.showError("Ha ocurrido un error");
            }
        });
    }
}

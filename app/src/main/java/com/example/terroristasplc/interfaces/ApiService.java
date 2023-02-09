package com.example.terroristasplc.interfaces;

import com.example.terroristasplc.dataclass.SdnList;
import com.example.terroristasplc.dataclass.Terrorists;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {

    @GET("/ofac/downloads/consolidated/consolidated.xml")
    Call<SdnList> serviceTwo();
}

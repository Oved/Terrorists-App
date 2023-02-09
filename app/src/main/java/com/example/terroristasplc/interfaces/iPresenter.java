package com.example.terroristasplc.interfaces;

import com.example.terroristasplc.dataclass.Terrorists;

import java.util.List;

public interface iPresenter {

    void presenterSearch();
    void presenterShowResults(List<Terrorists> terroristsList);
    void showError(String error);
}

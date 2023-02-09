package com.example.terroristasplc.interfaces;

import com.example.terroristasplc.dataclass.Terrorists;

import java.util.List;

public interface iView {

    void viewShowResults(List<Terrorists> terroristsList);
    void showError(String error);
}

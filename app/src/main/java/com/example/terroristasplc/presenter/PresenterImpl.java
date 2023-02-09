package com.example.terroristasplc.presenter;

import com.example.terroristasplc.dataclass.Terrorists;
import com.example.terroristasplc.interfaces.iModel;
import com.example.terroristasplc.interfaces.iPresenter;
import com.example.terroristasplc.interfaces.iView;
import com.example.terroristasplc.model.ModelImpl;
import com.example.terroristasplc.view.MainActivity;

import java.util.List;

public class PresenterImpl implements iPresenter {

    private iView view;
    private iModel model;

    public PresenterImpl(iView view){
        this.view = view;
        model = new ModelImpl(this);
    }

    @Override
    public void presenterSearch() {
        model.modelSearch();
    }

    @Override
    public void presenterShowResults(List<Terrorists> terroristsList) {
        view.viewShowResults(terroristsList);
    }

    @Override
    public void showError(String error) {
        view.showError(error);
    }
}

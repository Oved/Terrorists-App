package com.example.terroristasplc.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terroristasplc.R;
import com.example.terroristasplc.databinding.CardviewTerroristsBinding;
import com.example.terroristasplc.dataclass.Terrorists;

import java.util.ArrayList;
import java.util.List;

public class TerroristAdapter extends RecyclerView.Adapter<TerroristAdapter.ViewHolder> {

    private List<Terrorists> terroristsList;
    private List<Terrorists> newList;

    public TerroristAdapter(List<Terrorists> list){
        this.terroristsList = list;
        this.newList = new ArrayList<>();
        newList.addAll(terroristsList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_terrorists, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(position);
    }

    @Override
    public int getItemCount() {
        return terroristsList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private CardviewTerroristsBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = CardviewTerroristsBinding.bind(itemView);
        }
        public void onBind(int position){
            binding.tvFirstName.setText(terroristsList.get(position).getFirstName());
            binding.tvLastName.setText(terroristsList.get(position).getLastName());
        }
    }

    public void filter(String textSearch) {
        int textLong = textSearch.length();
        if (textLong == 0) {
            terroristsList.clear();
            terroristsList.addAll(newList);
        } else {
            terroristsList.clear();
            for (Terrorists t : newList) {
                if (t.getFirstName().toLowerCase().contains(textSearch.toLowerCase()) || t.getLastName().toLowerCase().contains(textSearch.toLowerCase())) {
                    terroristsList.add(t);
                }
            }
        }
        notifyDataSetChanged();
    }
}

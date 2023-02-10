package com.example.terroristasplc.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.terroristasplc.R;
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
        private TextView tv_first_name;
        private TextView tv_last_name;
        //private CardviewTerroristsBinding binding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //binding = CardviewTerroristsBinding.inflate(itemView.layo);
            tv_first_name = itemView.findViewById(R.id.tv_first_name);
            tv_last_name = itemView.findViewById(R.id.tv_last_name);
        }
        public void onBind(int position){
            tv_first_name.setText(terroristsList.get(position).getFirstName());
            tv_last_name.setText(terroristsList.get(position).getLastName());
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

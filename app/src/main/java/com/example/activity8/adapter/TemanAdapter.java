package com.example.activity8.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.activity8.MainActivity;
import com.example.activity8.R;
import com.example.activity8.database.DBController;
import com.example.activity8.database.Teman;
import com.example.activity8.edit_teman;

import java.util.ArrayList;
import java.util.HashMap;

public class TemanAdapter extends RecyclerView.Adapter<TemanAdapter.TemanViewHolder> {
    private ArrayList<Teman> listData;
    private Context control;

    public TemanAdapter(ArrayList<Teman> listData) {
        this.listData = listData;
    }

    @Override
    public TemanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInf = LayoutInflater.from(parent.getContext());
        View v = layoutInf.inflate(R.layout.row_data_teman, parent,false);
        control = parent.getContext();
        return new TemanViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TemanViewHolder holder, int position) {
        String id,nm,tlp;
        
        id = listData.get(position).getId();
        nm = listData.get(position).getNama();
        tlp = listData.get(position).getTelepon();
        DBController db = new DBController(control);

        holder.namaTxt.setText(nm);
        holder.namaTxt.setTextSize(20);
        holder.namaTxt.setTextColor(Color.BLUE);
        holder.teleponTxt.setText(tlp);
        
        holder.mycard.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                PopupMenu popupMenu = new PopupMenu(control, holder.mycard);
                popupMenu.inflate(R.menu.popup_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.mnEdit:
                                Intent i = new Intent(control, edit_teman.class);
                                i.putExtra("id",id);
                                i.putExtra("nama",nm);
                                i.putExtra("telepon",tlp);
                                control.startActivity(i);
                                break;
                            case R.id.mnHapus:
                                HashMap<String,String> values = new HashMap<>();
                                values.put("id",id);
                                db.DeleteData(values);
                                Intent j = new Intent(control, MainActivity.class);
                                control.startActivity(j);
                                break;
                        }
                        return true;
                    }
                });
                popupMenu.show();
                return false;
            }
        })
    ;}

    @Override
    public int getItemCount() {
        return (listData != null)?listData.size() : 0;
    }

    public class TemanViewHolder extends RecyclerView.ViewHolder {
        private CardView mycard;
        private TextView namaTxt, teleponTxt;
        public TemanViewHolder(View view) {
            super(view);
            mycard = (CardView) view.findViewById(R.id.mycard);
            namaTxt = (TextView) view.findViewById(R.id.textNama);
            teleponTxt = (TextView) view.findViewById(R.id.textTelepon);
        }
    }
}
package com.n18dcat093.test_database.TTPCB;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.n18dcat093.test_database.R;
import com.n18dcat093.test_database.TTPCB.PCB;
import com.n18dcat093.test_database.validate;

import java.util.ArrayList;

public class TTPCBAdapter extends ArrayAdapter<PCB> {

    Context context;
    int resource;
    ArrayList<PCB> data;

    public TTPCBAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PCB> data) {
        super(context, resource, data);
        this.context=context;
        this.resource=resource;
        this.data=data;
    }

    public static class ViewHolder{
        TextView maphieu;
        TextView mamonhoc;
        TextView sobai;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        ViewHolder holder =null;
        if(row != null){
            holder = (ViewHolder) row.getTag();
        }else{
            holder = new ViewHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.thongtinpcb_item,parent,false);

            holder.maphieu = (TextView) row.findViewById(R.id.maphieu);
            holder.mamonhoc = (TextView) row.findViewById(R.id.mamh);
            holder.sobai = (TextView) row.findViewById(R.id.sobai);
            row.setTag(holder);
        }
        PCB pcb = data.get(position);
        holder.maphieu.setText(pcb.getMaPhieu());
        holder.mamonhoc.setText((pcb.getMaMH()));
        holder.sobai.setText(pcb.getSoBai());
        return row;
    }
}

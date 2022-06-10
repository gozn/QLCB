package com.n18dcat093.test_database.GiaoVien;

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

import java.util.ArrayList;

public class GiaoVienAdapter extends ArrayAdapter<GiaoVien> {

    Context context;
    int resource;
    ArrayList<GiaoVien> data;

    public GiaoVienAdapter(@NonNull Context context, int resource, @NonNull ArrayList<GiaoVien> data) {
        super(context, resource, data);
        this.context=context;
        this.resource=resource;
        this.data=data;
    }

    public static class ViewHolder{
        TextView magv;
        TextView hoten;
        TextView sdt;
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
            row = inflater.inflate(R.layout.giaovien_item,parent,false);

            holder.magv = (TextView) row.findViewById(R.id.magv);
            holder.hoten = (TextView) row.findViewById(R.id.hotengv);
            holder.sdt = (TextView) row.findViewById(R.id.sdt);
            row.setTag(holder);
        }
        GiaoVien gv = data.get(position);
        holder.magv.setText(gv.getId());
        holder.hoten.setText(gv.getHoTen());
        holder.sdt.setText(gv.getSdt());
        return row;
    }
}

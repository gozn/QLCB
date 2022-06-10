package com.n18dcat093.test_database.PhieuChamBai;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.n18dcat093.test_database.MonHoc.MonHoc;
import com.n18dcat093.test_database.R;

import java.util.ArrayList;

public class PhieuChamBaiAdapter extends ArrayAdapter<PhieuChamBai> {
    Context context;
    int resource;
    ArrayList<PhieuChamBai> data;

    public PhieuChamBaiAdapter(@NonNull Context context, int resource, @NonNull ArrayList<PhieuChamBai> data) {
        super(context, resource, data);
        this.context=context;
        this.resource=resource;
        this.data=data;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    public static class ViewHolder{
        TextView SoPhieu;
        TextView NgayGiao;
        TextView MaGV;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row = convertView;
        PhieuChamBaiAdapter.ViewHolder holder =null;
        if(row != null){
            holder = (PhieuChamBaiAdapter.ViewHolder) row.getTag();
        }else{
            holder = new PhieuChamBaiAdapter.ViewHolder();
            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            row = inflater.inflate(R.layout.phieuchambai_item,parent,false);

            holder.SoPhieu = (TextView) row.findViewById(R.id.rowSoPhieu);
            holder.NgayGiao = (TextView) row.findViewById(R.id.rowNgayGiao);
            holder.MaGV = (TextView) row.findViewById(R.id.rowMaGV);
            row.setTag(holder);
        }
        PhieuChamBai pcb = data.get(position);
        holder.SoPhieu.setText(pcb.getSoPhieu());
        holder.NgayGiao.setText(pcb.getNgayGiao());
        holder.MaGV.setText(pcb.getMaGV());
        return row;
    }
}

package com.example.kelvinbun.pipancaindra;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class ListAdapter extends ArrayAdapter<PancaItem> {
    public ListAdapter(@NonNull Context context, @NonNull List<PancaItem> objects) {
        super(context, 0, objects);
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        PancaItem panca = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.panca_item, parent, false);
        }
        ImageView img = (ImageView)convertView.findViewById(R.id.gambarpanca);
        TextView tvName = (TextView) convertView.findViewById(R.id.judulpanca);
        img.setImageResource(panca.getGambarpanca());
        tvName.setText(panca.getJudulpanca());
        return convertView;
    }
}

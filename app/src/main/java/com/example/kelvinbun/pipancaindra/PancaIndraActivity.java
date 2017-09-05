package com.example.kelvinbun.pipancaindra;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PancaIndraActivity extends AppCompatActivity {
    private ListView listView;
    private List<PancaItem> pancaItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panca_indra);

        listView = (ListView) findViewById(R.id.list);

        getPancaItem();
        ListAdapter adapter = new ListAdapter (this, pancaItem);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(onItemClick);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { //Buat klik list ke form berbeda
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent e = new Intent(PancaIndraActivity.this, DetailActivity.class);
                PancaItem ab = pancaItem.get(position);
                e.putExtra("Nilai", position);
                Log.d("Info", String.valueOf(position));
                startActivity(e);
            }
        });

    }


    public List<PancaItem> getPancaItem() {
        pancaItem = new ArrayList<>();
        pancaItem.add(new PancaItem(R.drawable.mata,"Mata"));
        pancaItem.add(new PancaItem(R.drawable.hidung,"Hidung"));
        pancaItem.add(new PancaItem(R.drawable.telinga,"Telingan"));
        pancaItem.add(new PancaItem(R.drawable.kulit,"Kulit"));
        pancaItem.add(new PancaItem(R.drawable.lidah,"Lidah"));
        return pancaItem;
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(getApplicationContext(), pancaItem.get(position).getJudulpanca(), Toast.LENGTH_LONG).show();
        }

    };
}

package com.example.kelvinbun.pipancaindra;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import br.com.bloder.magic.view.MagicButton;

public class MainActivity extends AppCompatActivity {
    MagicButton btnPancaindra, btnLatihan,btnKeluar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPancaindra = (MagicButton) findViewById(R.id.magic_button1);
        btnLatihan = (MagicButton) findViewById(R.id.magic_button2);
        btnKeluar = (MagicButton) findViewById(R.id.magic_button4);

        btnPancaindra.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent(MainActivity.this, PancaIndraActivity.class);
                startActivity(a);
                Toast.makeText(getApplicationContext(), "Panca Indra", Toast.LENGTH_SHORT).show();
            }

        });
        btnLatihan.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent b = new Intent(MainActivity.this, KuisActivity.class);
                startActivity(b);
                Toast.makeText(getApplicationContext(), "Latihan", Toast.LENGTH_SHORT).show();
            }

        });
        btnKeluar.setMagicButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {

                            case DialogInterface.BUTTON_POSITIVE:
                                finish();
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                break;
                        }
                    }
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setMessage("Apakah Anda Yakin Ingin Keluar?").setPositiveButton("Ya", dialogClickListener)
                        .setNegativeButton("Tidak", dialogClickListener).show();
            }
        });
    }
    public void onBackPressed(){
        AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
        alert.setMessage("Apakah Anda Yakin Ingin Keluar?");
        alert.setCancelable(true);
        alert.setPositiveButton("YA", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int i){
                finish();
            }
        });
        alert.setNegativeButton("TIDAK", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int i){
                dialog.cancel();
            }
        });
        AlertDialog al = alert.create();
        al.show();
    }

}

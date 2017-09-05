package com.example.kelvinbun.pipancaindra;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class KuisActivity extends AppCompatActivity {
    DataBaseHelper penulisan;
    TextView soaltextview, skortextview;
    int posisi = 1, skor = 0, jawabanPosisi, soalPosisi;
    Button pilihan1, pilihan2, pilihan3, pilihan4;
    String soalString, pilihan1String,pilihan2String,pilihan3String,pilihan4String;
    List<Integer> listAcak;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kuis);



        try {
            penulisan = new DataBaseHelper(this);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> list = new ArrayList<>();

        listAcak = new ArrayList<>();
        for(int i =1; i<=24; i++ ){
            listAcak.add(i);
        }

        soaltextview = (TextView) findViewById(R.id.soal1);
        pilihan1 = (Button) findViewById(R.id.pilihan1);
        pilihan2 = (Button) findViewById(R.id.pilihan2);
        pilihan3 = (Button) findViewById(R.id.pilihan3);
        pilihan4 = (Button) findViewById(R.id.pilihan4);
        skortextview = (TextView) findViewById(R.id.score);

        updateSoal();

        pilihan1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soalPosisi = 0;
                if(soalPosisi==jawabanPosisi){
                    skor = skor+1;
                    updateSkor(skor);
                    updateSoal();
                    Toast.makeText(KuisActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(KuisActivity.this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                    updateSoal();
                }
            }
        });

        pilihan2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soalPosisi = 1;
                if(soalPosisi==jawabanPosisi){
                    skor = skor+1;
                    updateSkor(skor);
                    updateSoal();
                    Toast.makeText(KuisActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(KuisActivity.this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                    updateSoal();
                }
            }
        });

        pilihan3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soalPosisi = 2;
                if(soalPosisi==jawabanPosisi){
                    skor = skor+1;
                    updateSkor(skor);
                    updateSoal();
                    Toast.makeText(KuisActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(KuisActivity.this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                    updateSoal();
                }
            }
        });

        pilihan4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                soalPosisi = 3;
                if(soalPosisi==jawabanPosisi){
                    skor = skor+1;
                    updateSkor(skor);
                    updateSoal();
                    Toast.makeText(KuisActivity.this, "Jawaban Benar", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(KuisActivity.this, "Jawaban Salah", Toast.LENGTH_SHORT).show();
                    updateSoal();
                }
            }
        });
    }

    public void updateSoal() {
        if (posisi <= 15) {

            Collections.shuffle(listAcak);

            soalString = String.valueOf(penulisan.getsoal(listAcak.get(0)).get(0));
            pilihan1String = String.valueOf(penulisan.getsoal(listAcak.get(0)).get(1));
            pilihan2String = String.valueOf(penulisan.getsoal(listAcak.get(0)).get(2));
            pilihan3String = String.valueOf(penulisan.getsoal(listAcak.get(0)).get(3));
            pilihan4String = String.valueOf(penulisan.getsoal(listAcak.get(0)).get(4));
            jawabanPosisi = Integer.valueOf(penulisan.getsoal(listAcak.get(0)).get(5));

            soaltextview.setText(soalString);
            pilihan1.setText(pilihan1String);
            pilihan2.setText(pilihan2String);
            pilihan3.setText(pilihan3String);
            pilihan4.setText(pilihan4String);

            posisi++;
        } else {
            final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(KuisActivity.this);
            final AlertDialog alertDialog = alertDialogBuilder.create();

            alertDialogBuilder.setCancelable(false);
            alertDialogBuilder.setMessage("Skor Kamu adalah  " + String.valueOf(skor));
            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    alertDialog.dismiss();
                    finish();

                }
            });
            alertDialogBuilder.show();

        }

    }

    private void updateSkor(int skor){
        skortextview.setText(""+skor+"/15");
    }
    
}

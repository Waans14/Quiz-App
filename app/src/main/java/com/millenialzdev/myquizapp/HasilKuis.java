package com.millenialzdev.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HasilKuis extends AppCompatActivity {

    private TextView tvNilai, tvMessage,tvHasil;
    private Button btnUlang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_kuis);

        tvNilai = findViewById(R.id.tvNilai);
        tvMessage = findViewById(R.id.tvMessage);
        btnUlang = findViewById(R.id.btnUlang);
        tvHasil = findViewById(R.id.tvhasil);

        int nilai = getIntent().getExtras().getInt("nilai");
        int benar = getIntent().getExtras().getInt("benar");
        int salah = getIntent().getExtras().getInt("salah");

        tvMessage.setText("Jawaban Benar: " + benar +" , "+ "Jawaban Salah: " + salah);
        tvNilai.setText(String.valueOf(nilai));

        if (nilai == 100){
            tvHasil.setText("Kamu Mendapatkan Nilai  A, Kamu Lulus!!");
        }else if (nilai >= 80){
            tvHasil.setText("Kamu Mendapatkan Nilai  B, Kamu Lulus!!");
        }else if (nilai >= 60){
            tvHasil.setText("Kamu Mendapatkan Nilai  C, Kamu Remedi!!");
        }else if (nilai >= 40){
            tvHasil.setText("Kamu Mendapatkan Nilai  D, Kamu Remedi!!");
        }else if (nilai >= 20){
            tvHasil.setText("Kamu Mendapatkan Nilai  E, Kamu Remedi!!");
        }

        btnUlang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent back = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(back);
            }
        });


    }
}
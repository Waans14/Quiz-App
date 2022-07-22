package com.millenialzdev.myquizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView tvSoal, tvTime;
    private Button btnSelanjutnya;
    private RadioGroup rgPilihan;
    private RadioButton rbA, rbB, rbC, rbD;
    private ImageView ivImage;
    int nomor = 0;
    int score;
    int benar  = 0, salah = 0;

    int gambar[] = new int[]{
            R.drawable.niat,
            R.drawable.takbir,
            R.drawable.ruku,
            R.drawable.duduk_antara_dua_sujud,
            R.drawable.salam
    };

    String Soal[] = new String[]{
            "1. Nama Rukun Diatas Adalah (Merupakan Rukun Salat Ke-2",
            "2. Nama Rukun Diatas Adalah (Merupakan Rukun Salat Ke-3",
            "3. Nama Rukun Diatas Adalah (Merupakan Rukun Salat Ke-5",
            "4. Nama Rukun Diatas Adalah (Merupakan Rukun Salat Ke-8",
            "5. Nama Rukun Diatas Adalah (Merupakan Rukun Salat Ke-12"
    };

    String Option[] = new String[]{
            "Niat", "Berdiri Bagi Yang Mampu", "Menghadap Kiblat", "I'tidal",
            "Menghadap Kiblat", "Takbir", "Duduk Tasyahud Akhir", "Bershalawat Kepada Nabi Setelah Mengucapkan Tasyahud Akhir",
            "I'tidal", "Takbir", "Salam", "Ruku",
            "Duduk Antara Dua Sujud", "Duduk Tasyahud Akhir", "Tertib", "Bershalawat Kepada Nabi Setelah Mengucapkan Tasyahud Akhir",
            "Duduk Tasyahud Akhir", "Salam", "Tertib", "Duduk Diantara Dua Sujud"
    };

    String Jawaban[] = new String[]{
            "Niat",
            "Takbir",
            "Ruku",
            "Duduk Antara Dua Sujud",
            "Salam"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSoal = findViewById(R.id.tvSoal);
        tvTime = findViewById(R.id.tvTime);
        btnSelanjutnya = findViewById(R.id.btnSelanjutnya);
        rgPilihan = findViewById(R.id.rgPilihan);
        rbA = findViewById(R.id.rbA);
        rbB = findViewById(R.id.rbB);
        rbC = findViewById(R.id.rbC);
        rbD = findViewById(R.id.rbD);
        ivImage = findViewById(R.id.ivImage);

        rgPilihan.check(0);

        tvSoal.setText(Soal[nomor]);
        rbA.setText(Option[0]);
        rbB.setText(Option[1]);
        rbC.setText(Option[2]);
        rbD.setText(Option[3]);

        new CountDownTimer(30000,  1000){

            @Override
            public void onTick(long millisUntilFinished) {
                tvTime.setText(millisUntilFinished / 1000 + "s");
            }

            @Override
            public void onFinish() {
                tvTime.setText("Waktu Habis!!");
                score = benar * 20;
                Intent next = new Intent(getApplicationContext(), HasilKuis.class);
                next.putExtra("nilai", score);
                next.putExtra("benar", benar);
                next.putExtra("salah", salah);
                startActivity(next);
            }
        }.start();

        btnSelanjutnya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbA.isChecked() || rbB.isChecked() || rbC.isChecked() || rbD.isChecked()){

                    RadioButton Pilihan_User = findViewById(rgPilihan.getCheckedRadioButtonId());
                    String Jawaban_User = Pilihan_User.getText().toString();
                    rgPilihan.check(0);

                    if (Jawaban_User.equalsIgnoreCase(Jawaban[nomor])){
                        benar++;
                    }else{
                        salah++;
                    }
                    nomor++;
                    if (nomor < Soal.length){
                        tvSoal.setText(Soal[nomor]);
                        ivImage.setImageResource(gambar[nomor]);

                        rbA.setText(Option[(nomor * 4 ) + 0]);
                        rbB.setText(Option[(nomor * 4 ) + 1]);
                        rbC.setText(Option[(nomor * 4 ) + 2]);
                        rbD.setText(Option[(nomor * 4 ) + 3]);

                    }else{
                        score = benar * 20;
                        Intent next = new Intent(getApplicationContext(), HasilKuis.class);
                        next.putExtra("nilai", score);
                        next.putExtra("benar", benar);
                        next.putExtra("salah", salah);
                        startActivity(next);
                    }
                } else{
                    Toast.makeText(MainActivity.this, "Silahkan Pilih Jawaban Terlebih Dahulu!!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.TextView;
import androidx.activity.ComponentActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SecondActivity extends ComponentActivity {

    private TextView tvNik;
    private TextView tvNama;
    private TextView tvTglLahir;
    private TextView tvTptLahir;
    private TextView tvAlamat;
    private TextView tvUsia;
    private TextView tvJk;
    private TextView tvStatus;
    private TextView tvKompetensi;
    private TextView tvEmail;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            v.setOnApplyWindowInsetsListener((view, windowInsets) -> {
                WindowInsetsCompat insetsCompat = ViewCompat.onApplyWindowInsets(view, WindowInsetsCompat.toWindowInsetsCompat(windowInsets));
                if (insetsCompat.isConsumed()) {
                    return insetsCompat.consumeSystemWindowInsets().toWindowInsets();
                }
                return insetsCompat.toWindowInsets();
            });
            return ViewCompat.onApplyWindowInsets(v, insets);
        });

        // Initialize the views
        tvNik = findViewById(R.id.tvNik);
        tvNama = findViewById(R.id.tvNama);
        tvTglLahir = findViewById(R.id.tvTglLahir);
        tvTptLahir = findViewById(R.id.tvTptLahir);
        tvAlamat = findViewById(R.id.tvAlamat);
        tvUsia = findViewById(R.id.tvUsia);
        tvJk = findViewById(R.id.tvJk);
        tvStatus = findViewById(R.id.tvStatus);
        tvKompetensi = findViewById(R.id.tvKompetensi);
        tvEmail = findViewById(R.id.tvEmail);

        // Get the data from the intent
        String nik = getIntent().getStringExtra("nik");
        String nama = getIntent().getStringExtra("nama");
        String tglLahir = getIntent().getStringExtra("tglLahir");
        String tptLahir = getIntent().getStringExtra("tptLahir");
        String alamat = getIntent().getStringExtra("alamat");
        String usia = getIntent().getStringExtra("usia");
        String jk = getIntent().getStringExtra("jk");
        String status = getIntent().getStringExtra("status");
        String kompetensi = getIntent().getStringExtra("kompetensi");
        String email = getIntent().getStringExtra("email");

        // Set the data to the views
        tvNik.setText(nik != null ? nik : "");
        tvNama.setText(nama != null ? nama : "");
        tvTglLahir.setText(tglLahir != null ? tglLahir : "");
        tvTptLahir.setText(tptLahir != null ? tptLahir : "");
        tvAlamat.setText(alamat != null ? alamat : "");
        tvUsia.setText(usia != null ? usia : "");
        tvJk.setText(jk != null ? jk : "");
        tvStatus.setText(status != null ? status : "");
        tvKompetensi.setText(kompetensi != null ? kompetensi : "");
        tvEmail.setText(email != null ? email : "");

        // Initialize the back button
        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> finish());
    }
}

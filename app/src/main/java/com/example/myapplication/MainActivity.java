package com.example.myapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.IdRes;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.R;
import com.example.myapplication.SecondActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private EditText etNik;
    private EditText etNama;
    private EditText etTglLahir;
    private EditText etTptLahir;
    private EditText etAlamat;
    private EditText etUsia;
    private Spinner spJk;
    private RadioGroup rgStatus;
    private CheckBox cb1;
    private CheckBox cb2;
    private CheckBox cb3;
    private CheckBox cb4;
    private CheckBox cb5;
    private EditText etEmail;

    private Button btnClear;
    private Button btnSubmit;

    private String nik = "";
    private String nama = "";
    private String tglLahir = "";
    private String tptLahir = "";
    private String alamat = "";
    private String usia = "";
    private String jk = "";
    private String status = "";
    private Set<String> competencies = new HashSet<>();
    private String email = "";

    private int year;
    private int month;
    private int day;

    private Calendar myCalendar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        etNik = findViewById(R.id.etNik);
        etNama = findViewById(R.id.etNama);
        etTglLahir = findViewById(R.id.etTglLahir);
        etTptLahir = findViewById(R.id.etTptLahir);
        etAlamat = findViewById(R.id.etAlamat);
        etUsia = findViewById(R.id.etUsia);
        spJk = findViewById(R.id.spJk);
        rgStatus = findViewById(R.id.rgStatus);
        cb1 = findViewById(R.id.cb1);
        cb2 = findViewById(R.id.cb2);
        cb3 = findViewById(R.id.cb3);
        cb4 = findViewById(R.id.cb4);
        cb5 = findViewById(R.id.cb5);
        etEmail = findViewById(R.id.etEmail);
        btnClear = findViewById(R.id.btnClear);
        btnSubmit = findViewById(R.id.btnSubmit);

        // Set default value for spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jk_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spJk.setAdapter(adapter);
        spJk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                jk = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });

        // Initialize Calendar instance
        myCalendar = Calendar.getInstance();

        // Set up OnClickListener for Date EditText
        etTglLahir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });
        rgStatus.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.rbWni) {
                    status = "WNI";
                } else if (checkedId == R.id.rbWna) {
                    status = "WNA";
                } else {
                    status = "WNI"; // Default value
                }
            }
        });


        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearForm();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitForm();
            }
        });
    }

    private void showDatePickerDialog() {
        new DatePickerDialog(MainActivity.this, date, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    // DatePickerDialog.OnDateSetListener
    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, month);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            calculateAge();
        }
    };

    private void updateLabel() {
        String myFormat = "MM/dd/yy";
        SimpleDateFormat dateFormat = new SimpleDateFormat(myFormat, Locale.US);
        etTglLahir.setText(dateFormat.format(myCalendar.getTime()));
    }

    private void calculateAge() {
        // Get current date
        Calendar today = Calendar.getInstance();

        // Calculate age based on selected date of birth
        int age = today.get(Calendar.YEAR) - myCalendar.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < myCalendar.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }

        // Set the calculated age to the EditText
        etUsia.setText(String.valueOf(age));
    }

    private void clearForm() {
        etNik.setText("");
        etNama.setText("");
        etTglLahir.setText("");
        etTptLahir.setText("");
        etAlamat.setText("");
        etUsia.setText("");
        spJk.setSelection(0);
        rgStatus.clearCheck();
        cb1.setChecked(false);
        cb2.setChecked(false);
        cb3.setChecked(false);
        cb4.setChecked(false);
        cb5.setChecked(false);
        etEmail.setText("");

        nik = "";
        nama = "";
        tglLahir = "";
        alamat = "";
        usia = "";
        jk = "";
        status = "";
        competencies.clear();
        email = "";
    }

    private void submitForm() {
        nik = etNik.getText().toString();
        nama = etNama.getText().toString();
        tglLahir = etTglLahir.getText().toString();
        tptLahir = etTptLahir.getText().toString();
        alamat = etAlamat.getText().toString();
        usia = etUsia.getText().toString();
        email = etEmail.getText().toString();

        if (cb1.isChecked()) {
            competencies.add("Development & IT");
        }
        if (cb2.isChecked()) {
            competencies.add("AI Services");
        }
        if (cb3.isChecked()) {
            competencies.add("Design Creative");
        }
        if (cb4.isChecked()) {
            competencies.add("Writing");
        }
        if (cb5.isChecked()) {
            competencies.add("Finance & Accounting");
        }

        // Validasi form
        if (!validateForm()) {
            return;
        }

        // Kirim data ke SecondActivity
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("nik", nik);
        intent.putExtra("nama", nama);
        intent.putExtra("tglLahir", tglLahir);
        intent.putExtra("tptLahir", tptLahir);
        intent.putExtra("alamat", alamat);
        intent.putExtra("usia", usia);
        intent.putExtra("jk", jk);
        intent.putExtra("status", status);
        intent.putExtra("kompetensi", String.join(", ", competencies));
        intent.putExtra("email", email);

        startActivity(intent);
    }

    private boolean validateForm() {
        if (nik.isEmpty()) {
            etNik.setError("NIK tidak boleh kosong");
            return false;
        }

        if (nik.length() != 16) {
            etNik.setError("NIK harus 16 digit");
            return false;
        }

        if (nama.isEmpty()) {
            etNama.setError("Nama tidak boleh kosong");
            return false;
        }

        if (tglLahir.isEmpty()) {
            etTglLahir.setError("Tanggal Lahir tidak boleh kosong");
            return false;
        }

        if (tptLahir.isEmpty()) {
            etTptLahir.setError("Tempat Lahir tidak boleh kosong");
            return false;
        }

        if (alamat.isEmpty()) {
            etAlamat.setError("Alamat tidak boleh kosong");
            return false;
        }

        if (usia.isEmpty()) {
            etUsia.setError("Usia tidak boleh kosong");
            return false;
        }

        if (jk.isEmpty()) {
            Toast.makeText(this, "Jenis Kelamin tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (status.isEmpty()) {
            Toast.makeText(this, "Status kewarganegaraan tidak boleh kosong", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (email.isEmpty()) {
            etEmail.setError("Email tidak boleh kosong");
            return false;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Email tidak valid");
            return false;
        }

        return true;
    }
}

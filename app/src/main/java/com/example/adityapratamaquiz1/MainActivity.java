package com.example.adityapratamaquiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adityapratamaquiz1.R;
import com.example.adityapratamaquiz1.detail;

public class MainActivity extends AppCompatActivity {

    private EditText pelangganEditText, kodeBarangEditText, jumlahBarangEditText;
    private RadioGroup radioGroup;
    private Button prosesButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pelangganEditText = findViewById(R.id.pelanggan);
        kodeBarangEditText = findViewById(R.id.kodebarang);
        jumlahBarangEditText = findViewById(R.id.jumlahbarang);
        radioGroup = findViewById(R.id.radiogroup);
        prosesButton = findViewById(R.id.proses);

        prosesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prosesTransaksi();
            }
        });
    }

    private void prosesTransaksi() {
        String namaPelanggan = pelangganEditText.getText().toString();
        String kodeBarang = kodeBarangEditText.getText().toString();
        int jumlahBarang = Integer.parseInt(jumlahBarangEditText.getText().toString());

        long harga = getHarga(kodeBarang);
        if (harga == -1) {
            Toast.makeText(MainActivity.this, getString(R.string.kode_barang_tidak_valid), Toast.LENGTH_SHORT).show();
            return;
        }

        long totalHarga = harga * jumlahBarang;
        long diskonHarga = hitungDiskonHarga(totalHarga);
        long diskonMember = hitungDiskonMember(totalHarga);

        long jumlahBayar = totalHarga - diskonHarga - diskonMember;

        Intent intent = new Intent(MainActivity.this, detail.class);
        intent.putExtra(getString(R.string.nama_pelanggan), namaPelanggan);
        intent.putExtra(getString(R.string.kode_barang), kodeBarang);
        intent.putExtra(getString(R.string.nama_barang), getNamaBarang(kodeBarang));
        intent.putExtra(getString(R.string.harga), harga);
        intent.putExtra(getString(R.string.jumlah_barang), jumlahBarang);
        intent.putExtra(getString(R.string.total_harga), totalHarga);
        intent.putExtra(getString(R.string.diskon_harga), diskonHarga);
        intent.putExtra(getString(R.string.diskon_member), diskonMember);
        intent.putExtra(getString(R.string.jumlah_bayar), jumlahBayar);
        startActivity(intent);
    }

    private long getHarga(String kodeBarang) {
        switch (kodeBarang) {
            case "SGS":
                return 12999999;
            case "IPX":
                return 5725300;
            case "MP3":
                return 28999999;
            default:
                return -1;
        }
    }

    private String getNamaBarang(String kodeBarang) {
        switch (kodeBarang) {
            case "SGS":
                return "Samsung Galaxy S";
            case "IPX":
                return "Iphone X";
            case "MP3":
                return "Macbook Pro M3";
            default:
                return "";
        }
    }

    private long hitungDiskonHarga(long totalHarga) {
        if (totalHarga > 10000000) {
            return 100000;
        }
        return 0;
    }

    private long hitungDiskonMember(long totalHarga) {
        RadioButton radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String membership = radioButton.getText().toString();
        switch (membership) {
            case "Gold":
                return (long) (totalHarga * 0.10);
            case "Silver":
                return (long) (totalHarga * 0.05);
            case "Biasa":
                return (long) (totalHarga * 0.02);
            default:
                return 0;
        }
    }
}

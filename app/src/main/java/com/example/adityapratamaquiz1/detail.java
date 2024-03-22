package com.example.adityapratamaquiz1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.adityapratamaquiz1.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class detail extends AppCompatActivity {

    private TextView textViewNamaPelanggan, textViewKodeBarang, textViewNamaBarang,
            textViewHarga, textViewTotalHarga, textViewDiskonHarga, textViewDiskonMember, textViewJumlahBayar;
    private Button shareButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewNamaPelanggan = findViewById(R.id.nama);
        textViewKodeBarang = findViewById(R.id.kode);
        textViewNamaBarang = findViewById(R.id.namabrg);
        textViewHarga = findViewById(R.id.harga);
        textViewTotalHarga = findViewById(R.id.totalharga);
        textViewDiskonHarga = findViewById(R.id.diskonharga);
        textViewDiskonMember = findViewById(R.id.diskonmember);
        textViewJumlahBayar = findViewById(R.id.jumlahbayar);
        shareButton = findViewById(R.id.share);

        shareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareData();
            }
        });

        Intent intent = getIntent();
        String namaPelanggan = intent.getStringExtra(getString(R.string.nama_pelanggan));
        String kodeBarang = intent.getStringExtra(getString(R.string.kode_barang));
        String namaBarang = intent.getStringExtra(getString(R.string.nama_barang));
        long harga = intent.getLongExtra(getString(R.string.harga), 0);
        int jumlahBarang = intent.getIntExtra(getString(R.string.jumlah_barang), 0);
        long totalHarga = intent.getLongExtra(getString(R.string.total_harga), 0);
        long discountHarga = intent.getLongExtra(getString(R.string.diskon_harga), 0);
        long discountMember = intent.getLongExtra(getString(R.string.diskon_member), 0);
        long jumlahBayar = intent.getLongExtra(getString(R.string.jumlah_bayar), 0);

        NumberFormat formatter = new DecimalFormat("#,###,###,###");
        String formattedHarga = "Rp " + formatter.format(harga);
        String formattedTotalHarga = "Rp " + formatter.format(totalHarga);
        String formattedDiscountHarga = "Rp " + formatter.format(discountHarga);
        String formattedDiscountMember = "Rp " + formatter.format(discountMember);
        String formattedJumlahBayar = "Rp " + formatter.format(jumlahBayar);

        textViewNamaPelanggan.setText(getString(R.string.nama_pelanggan) + ": " + namaPelanggan);
        textViewKodeBarang.setText(getString(R.string.kode_barang) + ": " + kodeBarang);
        textViewNamaBarang.setText(getString(R.string.nama_barang) + ": " + namaBarang);
        textViewHarga.setText(getString(R.string.harga) + ": " + formattedHarga);
        textViewTotalHarga.setText(getString(R.string.total_harga) + ": " + formattedTotalHarga);
        textViewDiskonHarga.setText(getString(R.string.diskon_harga) + ": " + formattedDiscountHarga);
        textViewDiskonMember.setText(getString(R.string.diskon_member) + ": " + formattedDiscountMember);
        textViewJumlahBayar.setText(getString(R.string.jumlah_bayar) + ": " + formattedJumlahBayar);
    }

    private void shareData() {
        String dataToShare = "Detail Transaksi\n" +
                textViewNamaPelanggan.getText().toString() + "\n" +
                textViewKodeBarang.getText().toString() + "\n" +
                textViewNamaBarang.getText().toString() + "\n" +
                textViewHarga.getText().toString() + "\n" +
                textViewTotalHarga.getText().toString() + "\n" +
                textViewDiskonHarga.getText().toString() + "\n" +
                textViewDiskonMember.getText().toString() + "\n" +
                textViewJumlahBayar.getText().toString();

        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, dataToShare);
        startActivity(Intent.createChooser(shareIntent, getString(R.string.share_via)));
    }
}

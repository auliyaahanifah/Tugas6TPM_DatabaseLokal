package com.example.tugas6_database.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas6_database.R;
import com.example.tugas6_database.entity.AppDatabase;
import com.example.tugas6_database.entity.DataUntung;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainContact.view{

    private AppDatabase appDatabase;
    private MainPresenter presenter;
    private MainAdapter adapter;

    private Button btnSubmit;
    private RecyclerView recyclerView;
    private EditText etTanggal, etBruto, etPengeluaran;

    private boolean edit = false;
    private int id = 0;

    private int bruto, pengeluaran, netto;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appDatabase = AppDatabase.iniDb(getApplicationContext());

        btnSubmit = findViewById(R.id.btn_submit);
        btnSubmit.setOnClickListener(this);
        etTanggal = findViewById(R.id.et_tanggal);
        etBruto = findViewById(R.id.et_masukkotor);
        etPengeluaran = findViewById(R.id.et_pengeluaran);
        recyclerView = findViewById(R.id.rc_main);

        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        presenter = new MainPresenter(this);

        presenter.readData(appDatabase);


    }

    @Override
    public void successAdd() {
        Toast.makeText(this, "Berhasil", Toast.LENGTH_SHORT).show();
        presenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(this, "Berhasil menghapus data", Toast.LENGTH_SHORT).show();
        presenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        etTanggal.setText("");
        etBruto.setText("");
        etPengeluaran.setText("");
        btnSubmit.setText("submit");
    }

    @Override
    public void getData(List<DataUntung> list) {
        adapter = new MainAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void editData(DataUntung item) {
        etTanggal.setText(item.getTanggal());
        etPengeluaran.setText(String.valueOf(item.getPengeluaran()));
        etBruto.setText(String.valueOf(item.getBruto()));
        id = item.getId();


        edit = true;
        btnSubmit.setText("Update");
    }

    @Override
    public void deleteData(DataUntung item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(this);
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        presenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    @Override
    public void onClick(View view) {
        if(view == btnSubmit){
            if(etTanggal.getText().toString().equals(""))  {
                Toast.makeText(this, "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {

                bruto = Integer.parseInt(etBruto.getText().toString());
                pengeluaran = Integer.parseInt(etPengeluaran.getText().toString());
                netto = bruto - pengeluaran;

                if(!edit) presenter.insertData(etTanggal.getText().toString(), bruto, pengeluaran, netto,  appDatabase);
                else{
                    // Jika mode edit, panggil fungsi edit DB
                    presenter.editData(etTanggal.getText().toString(), bruto, pengeluaran, netto,id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }

    }
}
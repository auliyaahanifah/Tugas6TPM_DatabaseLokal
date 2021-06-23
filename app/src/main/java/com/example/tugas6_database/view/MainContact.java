package com.example.tugas6_database.view;

import android.view.View;

import com.example.tugas6_database.entity.AppDatabase;
import com.example.tugas6_database.entity.DataUntung;

import java.util.List;

public interface MainContact {

    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataUntung> list);
        void editData(DataUntung item);
        void deleteData(DataUntung item);
    }

    interface presenter {
        void insertData(String tanggal, int bruto, int pengeluaran, int netto, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String tanggal, int bruto, int pengeluaran, int netto, int id, AppDatabase database);
        void deleteData(DataUntung dataUntung, AppDatabase database);
    }
}

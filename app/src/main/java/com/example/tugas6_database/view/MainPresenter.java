package com.example.tugas6_database.view;

import android.os.AsyncTask;
import android.util.Log;

import com.example.tugas6_database.entity.AppDatabase;
import com.example.tugas6_database.entity.DataUntung;

import java.util.List;


public class MainPresenter implements MainContact.presenter{

    private MainContact.view viewContact;

    public MainPresenter(MainContact.view viewContact) {
        this.viewContact = viewContact;
    }

    class InsertData extends AsyncTask<Void, Void, Long>{
        private AppDatabase appDatabase;
        private DataUntung dataUntung;


        InsertData(AppDatabase appDatabase, DataUntung dataUntung) {
            this.appDatabase = appDatabase;
            this.dataUntung = dataUntung;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return appDatabase.dao().insertData(dataUntung);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            viewContact.successAdd();
        }
    }

    @Override
    public void insertData(String tanggal, int bruto, int pengeluaran, int netto, AppDatabase database) {
        final DataUntung dataUntung = new DataUntung();
        dataUntung.setTanggal(tanggal);
        dataUntung.setBruto(bruto);
        dataUntung.setPengeluaran(pengeluaran);
        dataUntung.setNetto(netto);
        new InsertData(database, dataUntung).execute();
    }

    @Override
    public void readData(AppDatabase database) {
        List<DataUntung> list;
        list = database.dao().getData();
        viewContact.getData(list);
    }


    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase database;
        private DataUntung dataUntung;

        public EditData(AppDatabase database, DataUntung dataUntung) {
            this.database = database;
            this.dataUntung = dataUntung;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataUntung);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            viewContact.successAdd();
        }
    }

    @Override
    public void editData(String tanggal, int bruto, int pengeluaran, int netto, int id, AppDatabase database) {
        final DataUntung dataUntung = new DataUntung();
        dataUntung.setTanggal(tanggal);
        dataUntung.setBruto(bruto);
        dataUntung.setPengeluaran(pengeluaran);
        dataUntung.setNetto(netto);
        new EditData(database, dataUntung).execute();

    }


    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private DataUntung dataUntung;

        public DeleteData(AppDatabase database, DataUntung dataUntung) {
            this.database = database;
            this.dataUntung = dataUntung;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataUntung);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewContact.successDelete();
        }

    }

    @Override
    public void deleteData(DataUntung dataUntung, AppDatabase database) {
        new DeleteData(database, dataUntung).execute();
    }
}

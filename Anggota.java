package com.tugas;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class Anggota extends User {

    public Anggota(String nama, String email, String idUser, String password) {
        super(nama, email, idUser, password);
    }

    public void pinjam(Buku pinjam, ArrayList<Transaksi> daftarTransaksi, String tglPinjam){
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        try{
            Date tanggalPinjam = sdf.parse(tglPinjam);
            daftarTransaksi.add(new Transaksi(pinjam, idUser, tanggalPinjam));
        }catch (ParseException e) {
            e.printStackTrace();
        }
    }
}

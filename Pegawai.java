package com.tugas;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Pegawai extends User {

    private String nip;

    public Pegawai(String nama, String email, String idUser, String password,String nip) {
        super(nama, email, idUser, password);
        this.nip = nip;
    }

    @Override
    public void details() {
        super.details();
        System.out.println("Nip\t: " + nip);
    }

    public boolean tambahAnggota(ArrayList<Anggota> daftarAnggota){
        Scanner in = new Scanner(System.in);

        System.out.print("Masukkan nama : ");
        String nama = in.nextLine();
        System.out.print("Masukkan email: ");
        String email = in.nextLine();
        System.out.print("Masukkan id : ");
        String id = in.nextLine();
        System.out.print("Masukkan password: ");
        String password = in.nextLine();
        System.out.print("Masukkan password konfirmasi: ");
        String passKonf = in.nextLine();

        if(!password.equals(passKonf)){
            System.out.println("Password konfirmasi tidak cocok");
            return false;
        }
       return  daftarAnggota.add(new Anggota(nama,email,password, id));
    }

    public boolean tambahBuku(Katalog daftarBuku, Buku baru){
        return daftarBuku.tambahBuku(baru);
    }
    public boolean hapusBuku(Katalog daftarBuku, Buku target){
        return daftarBuku.hapusBuku(target);
    }

    public boolean layaniPeminjaman(ArrayList<Transaksi> daftarTransaksi, ArrayList<Transaksi> daftarPinjam, String idAnggota){
        for (Transaksi pinjam: daftarTransaksi) {
            if(pinjam.idAnggota.equals(idAnggota) && pinjam.statusKembali == false){
                pinjam.idPegawai = this.idUser;
                daftarPinjam.add(pinjam);
                daftarTransaksi.remove(pinjam);
                return true;
            }
        }
        System.out.println("Anggota tidak memiliki buku pinjaman");
        return false;
    }

    public boolean layaniPengembalian(ArrayList<Transaksi> daftarKembali ,ArrayList<Transaksi> daftarPinjam, String idAnggota, String isbn){
        Scanner in = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Transaksi kembali = null;

        for (Transaksi pinjam: daftarPinjam) {
            if(pinjam.idAnggota.equalsIgnoreCase(idAnggota) && pinjam.buku.getIsbn().equalsIgnoreCase(isbn)){
                kembali = pinjam;
                break;
            }
        }

        System.out.print("Masukkan tanggal Pengembalian (dd-MM-yyyy) : ");

        String tglKembali = in.next();
        try{
            Date tanggalKembali = sdf.parse(tglKembali);
            kembali.kembali(tanggalKembali,daftarKembali);
            return true;
        }catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }


}

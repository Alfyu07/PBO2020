package com.tugas;
import com.tugaskelompok.CommandLineTable;

import java.util.ArrayList;
import java.util.Scanner;
public class Katalog {
    ArrayList<Buku> daftarBuku = new ArrayList<Buku>();
    int numberofBooks = 0;

    public boolean tambahBuku(Buku baru){
        if(numberofBooks == 0){
            if(daftarBuku.add(baru)){
                numberofBooks++;
                return true;
            }else return false;

        }
        long isbnBaru = ISBNtoInt(baru.getIsbn());

        if(isbnBaru < ISBNtoInt(daftarBuku.get(0).getIsbn())){
            daftarBuku.add(0,baru);
            return true;
        }

        for (int i = 0; i<numberofBooks; i++){
            if(i+1 < numberofBooks){
                if(isbnBaru > ISBNtoInt(daftarBuku.get(i).getIsbn()) && isbnBaru < ISBNtoInt(daftarBuku.get(i+1).getIsbn())){
                    daftarBuku.add(i+1,baru);
                    numberofBooks++;
                    return true;
                }
            }
        }
        if(daftarBuku.add(baru)){
            numberofBooks++;
            return true;
        }else return false;
    }

    private long ISBNtoInt(String isbn){
        String[] parts = isbn.split("-");
        isbn = "";
        for (String part:parts) {
            isbn+=part;
        }
        return Long.parseLong(isbn);
    }

    public boolean hapusBuku(Buku target){
        if(daftarBuku.remove(target)){
            numberofBooks--;
            return true;
        }
        return false;
    }
    public Buku getBookByISBN(String input){
        int first = 0;
        int last = daftarBuku.size()-1;
        long isbnCari = ISBNtoInt(input);
        while(first <= last){
            int mid = (first+last)/2;
            long isbnMid = ISBNtoInt((daftarBuku.get(mid).getIsbn()));

            if(daftarBuku.get(mid).getIsbn().equals(input)){
                return daftarBuku.get(mid);
            }else if(isbnMid < isbnCari){
                first = mid+1;
            }else if(isbnMid > isbnCari){
                last = mid-1;
            }else if(first == last && !daftarBuku.get(mid).getIsbn().equalsIgnoreCase(input)){
                return null;
            }
        }
        return null;
    }

    public Buku getBookByTitle(String input){
        ArrayList<Buku> hasilPencarian = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        CommandLineTable st = new CommandLineTable();
        for (Buku buku:daftarBuku) {
            if(buku.getJudul().equalsIgnoreCase(input)) hasilPencarian.add(buku);
        }
        if(hasilPencarian.size() == 1){
            return hasilPencarian.get(0);
        }else if(hasilPencarian.size()>1){
            System.out.println("Hasil Pencarian : ");
            int i = 1;
            st.setHeaders("No","Judul","ISBN","Penulis","Penerbit","Tahun Terbit");
            for (Buku buku: hasilPencarian) {
                st.addRow(i+". ",buku.getJudul(),buku.getIsbn(),buku.getPenulis(),buku.getPenerbit(),buku.getTahun());
                i++;
            }
            st.print();
            System.out.println("Pilih No : ");
            byte no = sc.nextByte();
            if(no > hasilPencarian.size()){
                System.out.println("No yang dicari tidak valid");
            }else{
                return hasilPencarian.get(no-1);
            }
        }
        return null;
    }

    public void tampilkanDaftar(){
        CommandLineTable st = new CommandLineTable();
        int i = 1;
        st.setHeaders("No","Judul","ISBN","Penulis","Penerbit","Tahun Terbit");
        for (Buku buku: daftarBuku) {
            st.addRow(i+". ",buku.getJudul(),buku.getIsbn(),buku.getPenulis(),buku.getPenerbit(),buku.getTahun());
            i++;
        }
        st.print();
    }

}

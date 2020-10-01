package com.tugas;

public class Buku {
    private String judul;
    private String penulis;
    private String isbn;
    private String penerbit;
    private String tahun;

    public Buku(String judul, String penulis, String isbn, String penerbit) {
        this.judul = judul;
        this.penulis = penulis;
        this.isbn = isbn;
        this.penerbit = penerbit;
    }

    public Buku(String judul, String penulis, String isbn, String penerbit, String tahun) {
        this.judul = judul;
        this.penulis = penulis;
        this.isbn = isbn;
        this.penerbit = penerbit;
        this.tahun = tahun;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }
}

    package com.tugas;
    
    import com.tugaskelompok.CommandLineTable;
    
    import java.util.ArrayList;
    import java.util.Scanner;
    
    
    
    public class Perpustakaan {
        ArrayList<Pegawai> daftarPegawai = new ArrayList<>();
        ArrayList<Anggota> daftarAnggota = new ArrayList<>();
        ArrayList<Transaksi> daftarTransaksi = new ArrayList<>();
        ArrayList<Transaksi> daftarPengembalian = new ArrayList<>();
        ArrayList<Transaksi> daftarPeminjaman = new ArrayList<>();
    
        Katalog katalog = new Katalog();
    
        void run(){
            Scanner in = new Scanner(System.in);
            byte role = 0;
            while(role != 3){
                System.out.println("================ Selamat Datang ================");
                System.out.println("1. Login sebagai Pegawai");
                System.out.println("2. Login sebagai Anggota");
                System.out.println("3. Keluar");
                System.out.print("Pilih : ");
                role = in.nextByte();
                System.out.println("======================================");
                if(role == 1){
                    Pegawai akun = loginPegawai();
                    if(akun != null){
                        byte pilih = 0;
                        while(pilih != 5){
                            System.out.println("======================================");
                            akun.details();
                            System.out.println("================ Menu ================");
                            System.out.println("1. Tambah Anggota");
                            System.out.println("2. Manajemen Buku");
                            System.out.println("3. Peminjaman");
                            System.out.println("4. Pengembalian");
                            System.out.println("5. logout");
                            System.out.print("Pilih : ");
                            pilih = in.nextByte();
                            System.out.println("======================================");
    
                            switch (pilih){
                                case 1:
                                    if(akun.tambahAnggota(daftarAnggota)){
                                        System.out.println("Anggota berhasil ditambahkan...");
                                    }
                                    break;
                                case 2:
                                    byte pilih2 = 0;
                                    while(pilih2 != 3){
                                        System.out.println("======================================");
                                        System.out.println("Manajemen Buku : ");
                                        System.out.println("1. Tambah Buku");
                                        System.out.println("2. Hapus Buku");
                                        System.out.println("3. Kembali");
                                        System.out.print("Pilih : ");
                                        pilih2 = in.nextByte();
                                        System.out.println("======================================");
                                        switch (pilih2){
                                            case 1 :
                                                System.out.print("Masukkan judul buku : ");
                                                in.nextLine();
                                                String judul = in.nextLine();
                                                System.out.print("Masukkan ISBN buku : ");
                                                String isbn = in.nextLine();
                                                System.out.print("Masukkan nama penulis : ");
                                                String penulis = in.nextLine();
                                                System.out.print("Masukkan nama penerbit : ");
                                                String penerbit = in.nextLine();
                                                System.out.print("Masukkan tahun terbit (dd-mm-yyyy): ");
                                                String tahun = in.nextLine();
                                                if(akun.tambahBuku(katalog, new Buku(judul, penulis,  isbn, penerbit, tahun))){
                                                    System.out.println("Buku berhasil ditambahkan ke katalog...");
                                                }
    
                                                break;
                                            case 2 :
                                                System.out.print("Masukkan ISBN : ");
                                                in.nextLine();
                                                isbn = in.nextLine();
                                                if(akun.hapusBuku(katalog, katalog.getBookByISBN(isbn))){
                                                    System.out.println("Buku berhasil di hapus");
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.print("Masukkan id Anggota : ");
                                    String idAnggota = in.next();
                                    akun.layaniPeminjaman(daftarTransaksi,daftarPeminjaman,idAnggota);
                                    break;
                                case 4:
                                    System.out.print("Masukkan id Anggota : ");
                                    idAnggota = in.next();
                                    System.out.print("Masukkan isbn buku : ");
                                    String isbn = in.next();
                                    akun.layaniPengembalian(daftarPengembalian,daftarPeminjaman,idAnggota,isbn);
                                    break;
                            }
                        }
                    }else{
                        System.out.println("Invalid Login");
                    }
                }else if(role == 2){
                    Anggota akun = loginAnggota();
                    if(akun != null){
                        byte pilih3 = 0;
                        while(pilih3 != 3){
                            System.out.println("======================================");
                            akun.details();
                            System.out.println("================ Menu ================");
                            System.out.println("1. Pinjam Buku");
                            System.out.println("2. Tampilkan Buku yang dipinjam");
                            System.out.println("3. Logout");
                            System.out.print("Pilih : ");
                            pilih3 = in.nextByte();
                            System.out.println("======================================");
                            switch(pilih3){
                                case 1:
                                    if(katalog.daftarBuku.size() <= 0){
                                        System.out.println("Buku Kosong");
                                    }else{
                                        katalog.tampilkanDaftar();
                                        System.out.print("Masukkan judul buku : ");
                                        in.nextLine();
                                        String judul = in.nextLine();
                                        Buku pinjam = katalog.getBookByTitle(judul);
                                        if(pinjam != null){
                                            System.out.print("Masukkan tanggal peminjaman (dd-MM-yyyy) : ");
                                            String tanggalPinjam = in.next();
                                            akun.pinjam(pinjam,daftarTransaksi,tanggalPinjam);
                                        }else{
                                            System.out.println("Judul Buku Salah");
                                        }
                                    }
                                    break;
                                case 2:
                                    CommandLineTable st = new CommandLineTable();
                                    ArrayList<Transaksi> peminjaman = new ArrayList<>();
                                    int i = 1;
                                    st.setHeaders("No","Judul","ISBN","Penulis","Penerbit","Tahun Terbit");
                                    for (Transaksi transaksi: daftarPeminjaman) {
                                        if(transaksi.idAnggota.equalsIgnoreCase(akun.idUser) && transaksi.statusKembali == false){
                                            peminjaman.add(transaksi);
                                            st.addRow(i+". ",transaksi.buku.getJudul(),transaksi.buku.getIsbn(),transaksi.buku.getPenulis(),transaksi.buku.getPenerbit(),transaksi.buku.getTahun());
                                          i++;
                                        }
                                    }
                                    st.print();
                                    break;
                            }
                        }
                    }else{
                        System.out.println("Invalid Login");
                    }
    
                }
            }
        }
        Pegawai loginPegawai(){
            Scanner in = new Scanner(System.in);
            System.out.print("Masukkan userID : ");
            String userID = in.next();
            System.out.print("Masukkan password : ");
            String password = in.next();
            for (Pegawai peg:daftarPegawai) {
                if(peg.login(userID, password)) return peg;
            }
            return null;
        }
        Anggota loginAnggota(){
            Scanner in = new Scanner(System.in);
            System.out.print("Masukkan userID : ");
            String userID = in.next();
            System.out.print("Masukkan password : ");
            String password = in.next();
            for (Anggota anggota:daftarAnggota) {
                if(anggota.login(userID, password)) return anggota;
            }
            return null;
        }
    
        public static void main(String[] args) {
    
            Perpustakaan perpus = new Perpustakaan();
            perpus.daftarPegawai.add(new Pegawai("Wahyu Alfandi","w.alfa433@gmail.com","F1D018060","250700","00100123"));
            perpus.daftarAnggota.add(new Anggota("Ayu Rezki","ayurezki@gmail.com","F1D018008","123456"));
            perpus.katalog.tambahBuku(new Buku("Habis gelap terbit terang","R.A. Kartini","1001-01-01","RI","1928"));
            perpus.katalog.tambahBuku(new Buku("UnOrdinary","Uru","1001-01-02","Webtoon","2020"));
            perpus.katalog.tambahBuku(new Buku("Solo Leveling","Absolute","1001-03-02","Webtoon","2020"));
    
    
            perpus.run();
            System.out.println("Terimakasih");
    
    
    
        }
    }
    

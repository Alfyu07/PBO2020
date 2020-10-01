    package com.tugas;

    import java.util.ArrayList;
    import java.util.Date;
    import java.util.Scanner;

    public class Transaksi {
        Buku buku;
        String idAnggota;
        String idPegawai;
        Date tanggalPinjam;
        Date tanggalKembali;
        boolean statusKembali= false;
        int denda = 0;

        public Transaksi() {

        }

        public Transaksi(Buku buku, String idAnggota, Date tanggalPinjam) {
            this.buku = buku;
            this.idAnggota = idAnggota;
            this.tanggalPinjam = tanggalPinjam;
        }

        public Transaksi(Buku buku, String idAnggota, String idPegawai, Date tanggalPinjam) {
            this.buku = buku;
            this.idAnggota = idAnggota;
            this.idPegawai = idPegawai;
            this.tanggalPinjam = tanggalPinjam;
        }

        public void hitungDenda(){
            long selisihHari = selisihHari();
            if(selisihHari > 7){
                this.denda = (int) (selisihHari-7) * 1000;
            }
        }

        private long selisihHari(){
            long selisihWaktu = tanggalKembali.getTime() - tanggalPinjam.getTime();
            return (selisihWaktu / (1000 * 60 * 60 * 24)) % 365;
        }

        public void kembali(Date tglKembali, ArrayList<Transaksi> daftarKembali){
            Scanner in = new Scanner(System.in);
            this.tanggalKembali = tglKembali;
            hitungDenda();
            if(denda > 0){
                System.out.println("Denda anda : Rp. " + denda);
                System.out.print("Bayar denda : Rp.");
                int bayar = in.nextInt();
                System.out.println("Uang yang dibayar : Rp." + bayar);
                int kembalian = bayar-denda;
                denda = denda-bayar;
                if(denda > 0){
                    System.out.println("Sisa denda anda : Rp."+ denda);
                    return;
                }
                if(denda == 0){
                    System.out.println("Pengembalian buku berhasil");
                    this.statusKembali= true;
                    daftarKembali.add(this);
                }else if(kembalian > 0){
                    System.out.println("Pengembalian berhasil");
                    System.out.println("Uang kembali : Rp." + kembalian);
                    this.statusKembali= true;
                    daftarKembali.add(this);
                }
            }
        }

    }

package id.ac.uniska.pbo2.p01;

/**
 * Latihan Mandiri Bagian D: Kartu Mahasiswa
 * Menampilkan data mahasiswa dan alasan mengambil mata kuliah PBO 2.
 */
public class KartuMahasiswa {

    public static void main(String[] args) {
        String nama = "Muhammad Azka Arota Faslah";
        String npm = "2410010513";
        String prodi = "S1 Informatika";
        int semester = 5;
        String alasan = "Ingin memperdalam arsitektur OOP dan membangun aplikasi enterprise";

        System.out.println("==============================");
        System.out.println("    KARTU MAHASISWA PBO 2     ");
        System.out.println("==============================");
        System.out.println("Nama     : " + nama);
        System.out.println("NPM      : " + npm);
        System.out.println("Prodi    : " + prodi);
        System.out.println("Semester : " + semester);
        System.out.println("Alasan   : " + alasan);
        System.out.println("==============================");
    }
}

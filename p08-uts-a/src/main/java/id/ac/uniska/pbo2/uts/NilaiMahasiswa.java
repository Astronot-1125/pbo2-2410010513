// Nama: Muhammad Azka Arota Faslah
// NPM: 2410010513
package id.ac.uniska.pbo2.uts;

import java.util.Locale;

/**
 * Class model data Nilai Mahasiswa beserta perhitungan nilai akhir dan huruf mutu.
 */
public class NilaiMahasiswa {

    private final String npm;
    private final String nama;
    private final int tugas;
    private final int uts;
    private final int uas;

    public NilaiMahasiswa(String npm, String nama, int tugas, int uts, int uas) {
        this.npm = npm;
        this.nama = nama;
        this.tugas = tugas;
        this.uts = uts;
        this.uas = uas;
    }

    public String getNpm() {
        return npm;
    }

    public String getNama() {
        return nama;
    }

    public int getTugas() {
        return tugas;
    }

    public int getUts() {
        return uts;
    }

    public int getUas() {
        return uas;
    }

    /**
     * Nilai akhir = 30% tugas + 30% UTS + 40% UAS.
     */
    public double hitungNilaiAkhir() {
        return (0.30 * tugas) + (0.30 * uts) + (0.40 * uas);
    }

    /**
     * Konversi nilai akhir ke huruf mutu berdasarkan tabel spesifikasi:
     * 80 - 100 : A
     * 75 - < 80 : B+
     * 70 - < 75 : B
     * 65 - < 70 : C+
     * 60 - < 65 : C
     * 40 - < 60 : D
     * < 40      : E
     */
    public String hitungHurufMutu() {
        double na = hitungNilaiAkhir();
        if (na >= 80.0) {
            return "A";
        } else if (na >= 75.0) {
            return "B+";
        } else if (na >= 70.0) {
            return "B";
        } else if (na >= 65.0) {
            return "C+";
        } else if (na >= 60.0) {
            return "C";
        } else if (na >= 40.0) {
            return "D";
        } else {
            return "E";
        }
    }

    /**
     * Mengembalikan string nilai akhir dengan format 2 angka di belakang koma (koma desimal).
     */
    public String getNilaiAkhirFormatted() {
        return String.format(Locale.forLanguageTag("id-ID"), "%.2f", hitungNilaiAkhir());
    }
}

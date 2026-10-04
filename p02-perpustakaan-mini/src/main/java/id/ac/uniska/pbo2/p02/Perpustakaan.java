package id.ac.uniska.pbo2.p02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Mengelola daftar koleksi dan data peminjam.
 */
public class Perpustakaan {
    private final List<Koleksi> daftarKoleksi = new ArrayList<>();
    private final Map<String, Anggota> peminjam = new HashMap<>();

    public void tambah(Koleksi koleksi) {
        daftarKoleksi.add(koleksi);
    }

    /** Mencari koleksi berdasarkan kode. Mengembalikan null jika tidak ditemukan. */
    public Koleksi cari(String kode) {
        for (Koleksi k : daftarKoleksi) {
            if (k.getKode().equals(kode)) {
                return k;
            }
        }
        return null;
    }

    /** Meminjamkan koleksi kepada anggota. Mengembalikan false jika gagal. */
    public boolean pinjam(String kode, Anggota anggota) {
        Koleksi koleksi = cari(kode);
        if (koleksi == null || !koleksi.pinjam()) {
            return false;
        }
        peminjam.put(kode, anggota);
        return true;
    }

    /** Mengembalikan koleksi dan menghitung dendanya. */
    public long kembalikan(String kode, int hariTerlambat) {
        Koleksi koleksi = cari(kode);
        if (koleksi == null || koleksi.getStatus() == StatusKoleksi.TERSEDIA) {
            return 0;
        }
        koleksi.kembalikan();
        peminjam.remove(kode);
        // Polymorphism: rumus denda mengikuti jenis object (Buku atau Majalah)
        return koleksi.hitungDenda(hariTerlambat);
    }

    public Anggota getPeminjam(String kode) {
        return peminjam.get(kode);
    }

    public int jumlahTersedia() {
        int jumlah = 0;
        for (Koleksi k : daftarKoleksi) {
            if (k.getStatus() == StatusKoleksi.TERSEDIA) {
                jumlah++;
            }
        }
        return jumlah;
    }

    /** Salinan daftar yang tidak dapat diubah, agar data asli tetap terlindungi. */
    public List<Koleksi> getDaftarKoleksi() {
        return List.copyOf(daftarKoleksi);
    }
}

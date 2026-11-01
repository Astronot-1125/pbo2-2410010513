package id.ac.uniska.pbo2.p07;

/**
 * Data satu kontak. Record dipakai karena data kontak tidak diubah langsung;
 * perubahan dilakukan dengan mengganti object lama dengan object baru.
 */
public record Kontak(String nama, String telepon, String email, Kategori kategori) {
}
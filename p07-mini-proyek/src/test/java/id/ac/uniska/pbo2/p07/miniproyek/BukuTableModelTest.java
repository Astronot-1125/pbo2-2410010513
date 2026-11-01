package id.ac.uniska.pbo2.p07.miniproyek;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BukuTableModelTest {

    private BukuTableModel model;

    @BeforeEach
    void setUp() {
        model = new BukuTableModel();
    }

    @Test
    @DisplayName("Model baru harus kosong")
    void testModelBaruKosong() {
        assertEquals(0, model.getRowCount());
        assertEquals(5, model.getColumnCount());
        assertEquals(0, model.hitungTersedia());
        assertEquals(0, model.hitungDipinjam());
    }

    @Test
    @DisplayName("Tambah buku menambah baris dan menghitung status")
    void testTambahBuku() {
        Buku b1 = new Buku("BK-001", "Pemrograman Java", "Penulis A", 2021, StatusBuku.TERSEDIA);
        Buku b2 = new Buku("BK-002", "Basis Data", "Penulis B", 2019, StatusBuku.DIPINJAM);

        model.tambah(b1);
        model.tambah(b2);

        assertEquals(2, model.getRowCount());
        assertEquals("BK-001", model.getValueAt(0, 0));
        assertEquals("Pemrograman Java", model.getValueAt(0, 1));
        assertEquals("Penulis A", model.getValueAt(0, 2));
        assertEquals(2021, model.getValueAt(0, 3));
        assertEquals("Tersedia", model.getValueAt(0, 4));

        assertEquals(1, model.hitungTersedia());
        assertEquals(1, model.hitungDipinjam());
    }

    @Test
    @DisplayName("Ubah buku memperbarui data")
    void testUbahBuku() {
        Buku b1 = new Buku("BK-001", "Buku Lama", "Penulis Lama", 2020, StatusBuku.TERSEDIA);
        model.tambah(b1);

        Buku bUpdate = new Buku("BK-001", "Buku Baru", "Penulis Baru", 2022, StatusBuku.DIPINJAM);
        model.ubah(0, bUpdate);

        assertEquals("Buku Baru", model.getValueAt(0, 1));
        assertEquals(2022, model.getValueAt(0, 3));
        assertEquals(StatusBuku.DIPINJAM, model.get(0).status());
        assertEquals(0, model.hitungTersedia());
        assertEquals(1, model.hitungDipinjam());
    }

    @Test
    @DisplayName("Hapus buku mengurangi baris")
    void testHapusBuku() {
        Buku b1 = new Buku("BK-001", "Buku A", "Penulis A", 2021, StatusBuku.TERSEDIA);
        Buku b2 = new Buku("BK-002", "Buku B", "Penulis B", 2022, StatusBuku.TERSEDIA);
        model.tambah(b1);
        model.tambah(b2);

        assertEquals(2, model.getRowCount());
        model.hapus(0);

        assertEquals(1, model.getRowCount());
        assertEquals("BK-002", model.get(0).kode());
    }

    @Test
    @DisplayName("Tipe kolom tahun terbit harus Integer")
    void testColumnClass() {
        assertEquals(Integer.class, model.getColumnClass(3));
        assertEquals(String.class, model.getColumnClass(0));
    }
}


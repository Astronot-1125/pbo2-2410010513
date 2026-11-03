// Nama: Muhammad Azka Arota Faslah
// NPM: 2410010513
package id.ac.uniska.pbo2.uts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class NilaiMahasiswaTest {

    private NilaiMahasiswa mhs1;
    private NilaiMahasiswa mhs2;
    private NilaiMahasiswa mhs3;
    private NilaiTableModel model;

    @BeforeEach
    void setUp() {
        mhs1 = new NilaiMahasiswa("2410010001", "Siti Rahmah", 80, 75, 90);
        mhs2 = new NilaiMahasiswa("2410010002", "Budi Santoso", 70, 72, 74);
        mhs3 = new NilaiMahasiswa("2410010003", "Nur Aisyah", 65, 60, 58);
        model = new NilaiTableModel();
    }

    @Test
    void testPerhitunganNilaiAkhirDanHurufMutu() {
        // Data Uji 1: Siti Rahmah -> Akhir 82.50, Huruf A
        assertEquals(82.50, mhs1.hitungNilaiAkhir(), 0.001);
        assertEquals("A", mhs1.hitungHurufMutu());
        assertEquals("82,50", mhs1.getNilaiAkhirFormatted());

        // Data Uji 2: Budi Santoso -> Akhir 72.20, Huruf B
        assertEquals(72.20, mhs2.hitungNilaiAkhir(), 0.001);
        assertEquals("B", mhs2.hitungHurufMutu());
        assertEquals("72,20", mhs2.getNilaiAkhirFormatted());

        // Data Uji 3: Nur Aisyah -> Akhir 60.70, Huruf C
        assertEquals(60.70, mhs3.hitungNilaiAkhir(), 0.001);
        assertEquals("C", mhs3.hitungHurufMutu());
        assertEquals("60,70", mhs3.getNilaiAkhirFormatted());
    }

    @Test
    void testRentangHurufMutuLengkap() {
        // Rentang 75 - <80 -> B+
        NilaiMahasiswa mBPlus = new NilaiMahasiswa("111", "Tes B+", 78, 78, 78);
        assertEquals("B+", mBPlus.hitungHurufMutu());

        // Rentang 65 - <70 -> C+
        NilaiMahasiswa mCPlus = new NilaiMahasiswa("222", "Tes C+", 68, 68, 68);
        assertEquals("C+", mCPlus.hitungHurufMutu());

        // Rentang 40 - <60 -> D
        NilaiMahasiswa mD = new NilaiMahasiswa("333", "Tes D", 50, 50, 50);
        assertEquals("D", mD.hitungHurufMutu());

        // Rentang <40 -> E
        NilaiMahasiswa mE = new NilaiMahasiswa("444", "Tes E", 35, 35, 35);
        assertEquals("E", mE.hitungHurufMutu());
    }

    @Test
    void testTableModelDanRingkasanDataUji() {
        model.tambah(mhs1);
        model.tambah(mhs2);
        model.tambah(mhs3);

        assertEquals(3, model.getRowCount());
        assertEquals(7, model.getColumnCount());
        assertEquals("2410010001", model.getValueAt(0, 0));
        assertEquals("Siti Rahmah", model.getValueAt(0, 1));
        assertEquals(80, model.getValueAt(0, 2));
        assertEquals(75, model.getValueAt(0, 3));
        assertEquals(90, model.getValueAt(0, 4));
        assertEquals("82,50", model.getValueAt(0, 5));
        assertEquals("A", model.getValueAt(0, 6));

        // Uji rata-rata nilai akhir: (82.50 + 72.20 + 60.70) / 3 = 71.80
        assertEquals(71.80, model.getRataRataNilaiAkhir(), 0.01);
        assertEquals("Jumlah mahasiswa: 3, rata-rata nilai akhir: 71,80", model.getRingkasanText());

        // Uji deteksi NPM terdaftar
        assertTrue(model.apakahNpmSudahAda("2410010001"));
        assertTrue(model.apakahNpmSudahAda("2410010002"));
        assertFalse(model.apakahNpmSudahAda("2410010999"));
    }
}


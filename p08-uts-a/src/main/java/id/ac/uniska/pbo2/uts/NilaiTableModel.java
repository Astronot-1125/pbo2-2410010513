// Nama: Muhammad Azka Arota Faslah
// NPM: 2410010513
package id.ac.uniska.pbo2.uts;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.table.AbstractTableModel;

/**
 * Class table model turunan AbstractTableModel untuk menampilkan data nilai mahasiswa.
 */
public class NilaiTableModel extends AbstractTableModel {

    private static final String[] KOLOM = {
        "NPM", "Nama", "Tugas", "UTS", "UAS", "Akhir", "Huruf"
    };

    private final List<NilaiMahasiswa> daftar = new ArrayList<>();

    @Override
    public int getRowCount() {
        return daftar.size();
    }

    @Override
    public int getColumnCount() {
        return KOLOM.length;
    }

    @Override
    public String getColumnName(int kolom) {
        return KOLOM[kolom];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return switch (columnIndex) {
            case 2, 3, 4 -> Integer.class;
            default -> String.class;
        };
    }

    @Override
    public Object getValueAt(int baris, int kolom) {
        NilaiMahasiswa m = daftar.get(baris);
        return switch (kolom) {
            case 0 -> m.getNpm();
            case 1 -> m.getNama();
            case 2 -> m.getTugas();
            case 3 -> m.getUts();
            case 4 -> m.getUas();
            case 5 -> m.getNilaiAkhirFormatted();
            case 6 -> m.hitungHurufMutu();
            default -> null;
        };
    }

    public void tambah(NilaiMahasiswa mhs) {
        daftar.add(mhs);
        int indeksBaru = daftar.size() - 1;
        fireTableRowsInserted(indeksBaru, indeksBaru);
    }

    public boolean apakahNpmSudahAda(String npm) {
        for (NilaiMahasiswa m : daftar) {
            if (m.getNpm().trim().equals(npm.trim())) {
                return true;
            }
        }
        return false;
    }

    public int getJumlahMahasiswa() {
        return daftar.size();
    }

    public double getRataRataNilaiAkhir() {
        if (daftar.isEmpty()) {
            return 0.0;
        }
        double total = 0.0;
        for (NilaiMahasiswa m : daftar) {
            total += m.hitungNilaiAkhir();
        }
        return total / daftar.size();
    }

    public String getRingkasanText() {
        return String.format(Locale.forLanguageTag("id-ID"),
                "Jumlah mahasiswa: %d, rata-rata nilai akhir: %.2f",
                getJumlahMahasiswa(),
                getRataRataNilaiAkhir());
    }

    public List<NilaiMahasiswa> getDaftar() {
        return daftar;
    }
}

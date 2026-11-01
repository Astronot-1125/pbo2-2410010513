package id.ac.uniska.pbo2.p07.miniproyek;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class BukuTableModel extends AbstractTableModel {

    private static final String[] KOLOM = {
        "Kode", "Judul", "Penulis", "Tahun Terbit", "Status"
    };

    private final List<Buku> daftar = new ArrayList<>();

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
        if (columnIndex == 3) {
            return Integer.class;
        }
        return String.class;
    }

    @Override
    public Object getValueAt(int baris, int kolom) {
        Buku b = daftar.get(baris);
        return switch (kolom) {
            case 0 -> b.kode();
            case 1 -> b.judul();
            case 2 -> b.penulis();
            case 3 -> b.tahunTerbit();
            case 4 -> b.status().getLabel();
            default -> null;
        };
    }

    public Buku get(int baris) {
        return daftar.get(baris);
    }

    public void tambah(Buku buku) {
        daftar.add(buku);
        int baris = daftar.size() - 1;
        fireTableRowsInserted(baris, baris);
    }

    public void ubah(int baris, Buku buku) {
        daftar.set(baris, buku);
        fireTableRowsUpdated(baris, baris);
    }

    public void hapus(int baris) {
        daftar.remove(baris);
        fireTableRowsDeleted(baris, baris);
    }

    public int hitungTersedia() {
        int total = 0;
        for (Buku b : daftar) {
            if (b.status() == StatusBuku.TERSEDIA) {
                total++;
            }
        }
        return total;
    }

    public int hitungDipinjam() {
        int total = 0;
        for (Buku b : daftar) {
            if (b.status() == StatusBuku.DIPINJAM) {
                total++;
            }
        }
        return total;
    }
}

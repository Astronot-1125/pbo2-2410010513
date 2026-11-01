package id.ac.uniska.pbo2.p07.miniproyek;

import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class AplikasiKatalogBuku extends javax.swing.JFrame {

    private final BukuTableModel model = new BukuTableModel();
    private final TableRowSorter<BukuTableModel> sorter = new TableRowSorter<>(model);

    public AplikasiKatalogBuku() {
        initComponents();

        cariField.putClientProperty("JTextField.placeholderText", "Cari kode, judul, atau penulis...");
        bukuTable.setRowSorter(sorter);
        bukuTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        if (bukuTable.getColumnModel().getColumnCount() >= 5) {
            bukuTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            bukuTable.getColumnModel().getColumn(1).setPreferredWidth(280);
            bukuTable.getColumnModel().getColumn(2).setPreferredWidth(180);
            bukuTable.getColumnModel().getColumn(3).setPreferredWidth(100);
            bukuTable.getColumnModel().getColumn(4).setPreferredWidth(100);
        }

        bukuTable.getSelectionModel().addListSelectionListener(e -> perbaruiTombol());

        isiContoh();
        perbaruiTombol();
        perbaruiStatus();

        setSize(850, 500);
        setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        atasPanel = new javax.swing.JPanel();
        tombolPanel = new javax.swing.JPanel();
        tambahButton = new javax.swing.JButton();
        ubahButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        cariField = new javax.swing.JTextField();
        tabelPanel = new javax.swing.JPanel();
        tabelScroll = new javax.swing.JScrollPane();
        bukuTable = new javax.swing.JTable();
        statusLabel = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        fileMenu = new javax.swing.JMenu();
        keluarItem = new javax.swing.JMenuItem();
        bukuMenu = new javax.swing.JMenu();
        tambahItem = new javax.swing.JMenuItem();
        ubahItem = new javax.swing.JMenuItem();
        bukuSeparator = new javax.swing.JPopupMenu.Separator();
        hapusItem = new javax.swing.JMenuItem();
        bantuanMenu = new javax.swing.JMenu();
        tentangItem = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Katalog Buku Perpustakaan");

        atasPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(12, 4, 8, 12));
        atasPanel.setLayout(new java.awt.BorderLayout(8, 0));

        tombolPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 8, 0));

        tambahButton.setText("Tambah");
        tambahButton.addActionListener(this::tambahButtonActionPerformed);
        tombolPanel.add(tambahButton);

        ubahButton.setText("Ubah");
        ubahButton.addActionListener(this::ubahButtonActionPerformed);
        tombolPanel.add(ubahButton);

        hapusButton.setText("Hapus");
        hapusButton.addActionListener(this::hapusButtonActionPerformed);
        tombolPanel.add(hapusButton);

        atasPanel.add(tombolPanel, java.awt.BorderLayout.WEST);

        cariField.setColumns(20);
        cariField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                cariFieldKeyReleased(evt);
            }
        });
        atasPanel.add(cariField, java.awt.BorderLayout.EAST);

        getContentPane().add(atasPanel, java.awt.BorderLayout.NORTH);

        tabelPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 12, 0, 12));
        tabelPanel.setLayout(new java.awt.BorderLayout());

        bukuTable.setModel(model);
        bukuTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bukuTableMouseClicked(evt);
            }
        });
        tabelScroll.setViewportView(bukuTable);

        tabelPanel.add(tabelScroll, java.awt.BorderLayout.CENTER);

        getContentPane().add(tabelPanel, java.awt.BorderLayout.CENTER);

        statusLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(6, 12, 12, 12));
        statusLabel.setText(" ");
        getContentPane().add(statusLabel, java.awt.BorderLayout.SOUTH);

        fileMenu.setMnemonic('F');
        fileMenu.setText("File");

        keluarItem.setText("Keluar");
        keluarItem.addActionListener(this::keluarItemActionPerformed);
        fileMenu.add(keluarItem);

        jMenuBar1.add(fileMenu);

        bukuMenu.setMnemonic('B');
        bukuMenu.setText("Buku");

        tambahItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        tambahItem.setText("Tambah");
        tambahItem.addActionListener(this::tambahItemActionPerformed);
        bukuMenu.add(tambahItem);

        ubahItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        ubahItem.setText("Ubah");
        ubahItem.addActionListener(this::ubahItemActionPerformed);
        bukuMenu.add(ubahItem);
        bukuMenu.add(bukuSeparator);

        hapusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        hapusItem.setText("Hapus");
        hapusItem.addActionListener(this::hapusItemActionPerformed);
        bukuMenu.add(hapusItem);

        jMenuBar1.add(bukuMenu);

        bantuanMenu.setMnemonic('T');
        bantuanMenu.setText("Bantuan");

        tentangItem.setText("Tentang");
        tentangItem.addActionListener(this::tentangItemActionPerformed);
        bantuanMenu.add(tentangItem);

        jMenuBar1.add(bantuanMenu);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
        tambah();
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void ubahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahButtonActionPerformed
        ubah();
    }//GEN-LAST:event_ubahButtonActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        hapus();
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void cariFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cariFieldKeyReleased
        saring();
    }//GEN-LAST:event_cariFieldKeyReleased

    private void bukuTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bukuTableMouseClicked
        if (evt.getClickCount() == 2 && bukuTable.getSelectedRow() >= 0) {
            ubah();
        }
    }//GEN-LAST:event_bukuTableMouseClicked

    private void keluarItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_keluarItemActionPerformed
        dispose();
    }//GEN-LAST:event_keluarItemActionPerformed

    private void tambahItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahItemActionPerformed
        tambah();
    }//GEN-LAST:event_tambahItemActionPerformed

    private void ubahItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ubahItemActionPerformed
        ubah();
    }//GEN-LAST:event_ubahItemActionPerformed

    private void hapusItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusItemActionPerformed
        hapus();
    }//GEN-LAST:event_hapusItemActionPerformed

    private void tentangItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tentangItemActionPerformed
        JOptionPane.showMessageDialog(this,
                "Katalog Buku Perpustakaan\n"
                + "Mini Proyek Individual Pertemuan 7 PBO 2\n\n"
                + "Nama: Muhammad Azka Arota Faslah\n"
                + "NPM: 2410010513\n"
                + "Kelas: 5C Regular Pagi\n"
                + "Fakultas Teknologi Informasi - UNISKA Banjarmasin",
                "Tentang Aplikasi",
                JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_tentangItemActionPerformed

    private void isiContoh() {
        model.tambah(new Buku("BK-001", "Pemrograman Berorientasi Objek dengan Java", "Indrajani", 2021, StatusBuku.TERSEDIA));
        model.tambah(new Buku("BK-002", "Struktur Data dan Algoritma Dasar", "Rinaldi Munir", 2020, StatusBuku.DIPINJAM));
        model.tambah(new Buku("BK-003", "Clean Code: A Handbook of Agile Software Craftsmanship", "Robert C. Martin", 2008, StatusBuku.TERSEDIA));
        model.tambah(new Buku("BK-004", "Rekayasa Perangkat Lunak: Pendekatan Praktisi", "Roger S. Pressman", 2019, StatusBuku.TERSEDIA));
        model.tambah(new Buku("BK-005", "Basis Data Edisi Ketiga", "Fathansyah", 2018, StatusBuku.DIPINJAM));
        model.tambah(new Buku("BK-006", "Design Patterns: Elements of Reusable Object-Oriented Software", "Erich Gamma et al.", 2015, StatusBuku.TERSEDIA));
    }

    private void saring() {
        String kata = cariField.getText().trim();
        if (kata.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(kata), 0, 1, 2));
        }
        perbaruiStatus();
    }

    private int barisTerpilih() {
        int barisTampilan = bukuTable.getSelectedRow();
        if (barisTampilan < 0) {
            return -1;
        }
        return bukuTable.convertRowIndexToModel(barisTampilan);
    }

    private void tambah() {
        Buku baru = new DialogBuku(this, "Tambah Buku Baru", null).tampilkan();
        if (baru != null) {
            model.tambah(baru);
            perbaruiStatus();
        }
    }

    private void ubah() {
        int baris = barisTerpilih();
        if (baris < 0) {
            return;
        }
        Buku bukuLama = model.get(baris);
        Buku bukuBaru = new DialogBuku(this, "Ubah Data Buku", bukuLama).tampilkan();
        if (bukuBaru != null) {
            model.ubah(baris, bukuBaru);
            perbaruiStatus();
        }
    }

    private void hapus() {
        int baris = barisTerpilih();
        if (baris < 0) {
            return;
        }
        Buku buku = model.get(baris);
        int konfirmasi = JOptionPane.showConfirmDialog(this,
                "Apakah Anda yakin ingin menghapus buku berikut?\n\n"
                + "Kode   : " + buku.kode() + "\n"
                + "Judul  : " + buku.judul() + "\n"
                + "Penulis: " + buku.penulis(),
                "Konfirmasi Hapus Buku",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (konfirmasi == JOptionPane.YES_OPTION) {
            model.hapus(baris);
            perbaruiStatus();
        }
    }

    private void perbaruiTombol() {
        boolean adaPilihan = bukuTable.getSelectedRow() >= 0;
        ubahButton.setEnabled(adaPilihan);
        hapusButton.setEnabled(adaPilihan);
        ubahItem.setEnabled(adaPilihan);
        hapusItem.setEnabled(adaPilihan);
    }

    private void perbaruiStatus() {
        statusLabel.setText("Menampilkan " + bukuTable.getRowCount()
                + " dari " + model.getRowCount() + " buku | Tersedia: "
                + model.hitungTersedia() + " | Dipinjam: " + model.hitungDipinjam());
    }

    public static void main(String args[]) {
        com.formdev.flatlaf.FlatLightLaf.setup();

        java.awt.EventQueue.invokeLater(() -> {
            new AplikasiKatalogBuku().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel atasPanel;
    private javax.swing.JMenu bantuanMenu;
    private javax.swing.JMenu bukuMenu;
    private javax.swing.JPopupMenu.Separator bukuSeparator;
    private javax.swing.JTable bukuTable;
    private javax.swing.JTextField cariField;
    private javax.swing.JMenu fileMenu;
    private javax.swing.JButton hapusButton;
    private javax.swing.JMenuItem hapusItem;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem keluarItem;
    private javax.swing.JLabel statusLabel;
    private javax.swing.JPanel tabelPanel;
    private javax.swing.JScrollPane tabelScroll;
    private javax.swing.JButton tambahButton;
    private javax.swing.JMenuItem tambahItem;
    private javax.swing.JMenuItem tentangItem;
    private javax.swing.JPanel tombolPanel;
    private javax.swing.JButton ubahButton;
    private javax.swing.JMenuItem ubahItem;
    // End of variables declaration//GEN-END:variables
}

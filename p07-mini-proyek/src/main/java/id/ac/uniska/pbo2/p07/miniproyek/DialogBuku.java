package id.ac.uniska.pbo2.p07.miniproyek;

import com.formdev.flatlaf.FlatLaf;
import java.awt.Color;
import java.time.Year;

public class DialogBuku extends javax.swing.JDialog {

    private Buku hasil;

    public DialogBuku(java.awt.Frame pemilik, String judul, Buku awal) {
        super(pemilik, judul, true);
        initComponents();

        if (awal != null) {
            kodeField.setText(awal.kode());
            judulField.setText(awal.judul());
            penulisField.setText(awal.penulis());
            tahunTerbitField.setText(String.valueOf(awal.tahunTerbit()));
            statusCombo.setSelectedItem(awal.status());
        }

        kodeField.putClientProperty("JTextField.placeholderText", "Contoh: BK-001");
        judulField.putClientProperty("JTextField.placeholderText", "Judul lengkap buku");
        penulisField.putClientProperty("JTextField.placeholderText", "Nama penulis / pengarang");
        tahunTerbitField.putClientProperty("JTextField.placeholderText", "1900 - " + Year.now().getValue());

        pesanLabel.putClientProperty("FlatLaf.styleClass", "small");
        pesanLabel.setForeground(FlatLaf.isLafDark()
                ? new Color(0xFF8A80) : new Color(0xC62828));

        getRootPane().setDefaultButton(simpanButton);
        pack();
        setLocationRelativeTo(pemilik);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bawahPanel = new javax.swing.JPanel();
        pesanLabel = new javax.swing.JLabel();
        tombolPanel = new javax.swing.JPanel();
        batalButton = new javax.swing.JButton();
        simpanButton = new javax.swing.JButton();
        formPanel = new javax.swing.JPanel();
        labelPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        isianPanel = new javax.swing.JPanel();
        kodeField = new javax.swing.JTextField();
        judulField = new javax.swing.JTextField();
        penulisField = new javax.swing.JTextField();
        tahunTerbitField = new javax.swing.JTextField();
        statusCombo = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        bawahPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 16, 16, 16));
        bawahPanel.setLayout(new java.awt.BorderLayout(0, 8));

        pesanLabel.setText(" ");
        bawahPanel.add(pesanLabel, java.awt.BorderLayout.NORTH);

        tombolPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT, 8, 0));

        batalButton.setText("Batal");
        batalButton.addActionListener(this::batalButtonActionPerformed);
        tombolPanel.add(batalButton);

        simpanButton.setText("Simpan");
        simpanButton.addActionListener(this::simpanButtonActionPerformed);
        tombolPanel.add(simpanButton);

        bawahPanel.add(tombolPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(bawahPanel, java.awt.BorderLayout.SOUTH);

        formPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(16, 16, 8, 16));
        formPanel.setLayout(new java.awt.BorderLayout(12, 0));

        labelPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 8));

        jLabel1.setText("Kode Buku");
        labelPanel.add(jLabel1);

        jLabel2.setText("Judul");
        labelPanel.add(jLabel2);

        jLabel3.setText("Penulis");
        labelPanel.add(jLabel3);

        jLabel4.setText("Tahun Terbit");
        labelPanel.add(jLabel4);

        jLabel5.setText("Status");
        labelPanel.add(jLabel5);

        formPanel.add(labelPanel, java.awt.BorderLayout.WEST);

        isianPanel.setLayout(new java.awt.GridLayout(0, 1, 0, 8));

        kodeField.setColumns(24);
        isianPanel.add(kodeField);
        isianPanel.add(judulField);
        isianPanel.add(penulisField);
        isianPanel.add(tahunTerbitField);

        statusCombo.setModel(new javax.swing.DefaultComboBoxModel<>(StatusBuku.values()));
        isianPanel.add(statusCombo);

        formPanel.add(isianPanel, java.awt.BorderLayout.CENTER);

        getContentPane().add(formPanel, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void batalButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_batalButtonActionPerformed
        dispose();
    }//GEN-LAST:event_batalButtonActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        simpan();
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void simpan() {
        String kode = kodeField.getText().trim();
        String judul = judulField.getText().trim();
        String penulis = penulisField.getText().trim();
        String tahunStr = tahunTerbitField.getText().trim();

        if (kode.isEmpty()) {
            pesanLabel.setText("Kode buku wajib diisi");
            kodeField.requestFocusInWindow();
            return;
        }

        if (judul.isEmpty()) {
            pesanLabel.setText("Judul buku wajib diisi");
            judulField.requestFocusInWindow();
            return;
        }

        int tahunSekarang = Year.now().getValue();
        int tahun;
        try {
            tahun = Integer.parseInt(tahunStr);
        } catch (NumberFormatException e) {
            pesanLabel.setText("Tahun terbit harus berupa angka");
            tahunTerbitField.requestFocusInWindow();
            return;
        }

        if (tahun < 1900 || tahun > tahunSekarang) {
            pesanLabel.setText("Tahun terbit antara 1900 sampai " + tahunSekarang);
            tahunTerbitField.requestFocusInWindow();
            return;
        }

        StatusBuku status = (StatusBuku) statusCombo.getSelectedItem();
        hasil = new Buku(kode, judul, penulis, tahun, status);
        dispose();
    }

    public Buku tampilkan() {
        setVisible(true);
        return hasil;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton batalButton;
    private javax.swing.JPanel bawahPanel;
    private javax.swing.JPanel formPanel;
    private javax.swing.JPanel isianPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JTextField judulField;
    private javax.swing.JTextField kodeField;
    private javax.swing.JPanel labelPanel;
    private javax.swing.JTextField penulisField;
    private javax.swing.JLabel pesanLabel;
    private javax.swing.JButton simpanButton;
    private javax.swing.JComboBox<StatusBuku> statusCombo;
    private javax.swing.JTextField tahunTerbitField;
    private javax.swing.JPanel tombolPanel;
    // End of variables declaration//GEN-END:variables
}

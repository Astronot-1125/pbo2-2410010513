# Mini Proyek Individual: Katalog Buku Perpustakaan (`p07-mini-proyek`)
**Mata Kuliah:** Pemrograman Berbasis Objek 2 (TIF1508)  
**Program Studi:** S1 Teknik Informatika  
**Fakultas:** Teknologi Informasi - UNISKA Banjarmasin  

**Nama Mahasiswa:** Muhammad Azka Arota Faslah  
**NPM:** 2410010513  
**Kelas:** 5C Teknik Informatika Reguler Pagi Banjarmasin  
**Repository GitHub:** [pbo2-2410010513 (GitHub)](https://github.com/Astronot-1125/pbo2-2410010513/tree/main/p07-mini-proyek)
**Link Video Youtube:** https://youtu.be/t-QglZ6NnfM

---

## 1. Deskripsi Proyek `p07-mini-proyek`

Proyek **Katalog Buku Perpustakaan** adalah aplikasi antarmuka grafis desktop (GUI) yang dibangun menggunakan **Java Swing** dengan dukungan tampilan modern **FlatLaf**. Aplikasi ini dirancang untuk mengelola data katalog koleksi buku perpustakaan secara efisien dengan menerapkan prinsip arsitektur **Model-View-Controller (MVC)** yang memisahkan lapisan data, logika bisnis, dan antarmuka pengguna:

- **Model Data:**
  - `StatusBuku.java`: Enum yang mendefinisikan status ketersediaan buku (`TERSEDIA` dan `DIPINJAM`).
  - `Buku.java`: Java Record yang merepresentasikan entitas buku (*immutable data carrier*) dengan atribut: `kode`, `judul`, `penulis`, `tahunTerbit`, dan `status`.
  - `BukuTableModel.java`: Turunan dari `AbstractTableModel` yang bertindak sebagai jembatan data antara koleksi buku di memori (`List<Buku>`) dengan komponen visual `JTable`. Mengirimkan notifikasi perubahan data secara presisi via `fireTableRowsInserted`, `fireTableRowsUpdated`, dan `fireTableRowsDeleted`.
- **Form Dialog Modal (`DialogBuku`):**
  - Turunan dari `JDialog` berstatus modal (`super(pemilik, judul, true)`) lengkap dengan pasangan file desain visual NetBeans `DialogBuku.form`.
  - Digunakan untuk operasi **Tambah Buku** dan **Ubah Buku**.
  - Mengimplementasikan validasi input terintegrasi:
    - Kode buku wajib diisi.
    - Judul buku wajib diisi.
    - Tahun terbit wajib berupa angka bulat dengan rentang antara tahun `1900` sampai tahun saat ini (`Year.now().getValue()`).
    - Status buku dipilih melalui ComboBox (`Tersedia` / `Dipinjam`).
  - Menggunakan method `tampilkan()` yang menahan eksekusi (*blocking*) sampai pengguna menekan tombol Simpan atau Batal.
- **Jendela Utama (`AplikasiKatalogBuku`):**
  - Turunan dari `JFrame` lengkap dengan file visual NetBeans `AplikasiKatalogBuku.form`.
  - Mengintegrasikan `JTable` dengan `TableRowSorter` untuk pengurutan data per kolom (klik header) dan penyaringan instan (*real-time filter*) berdasarkan kata kunci kode, judul, atau penulis.
  - Menggunakan `convertRowIndexToModel` untuk memastikan indeks baris yang dipilih pengguna pada tampilan selalu tepat dipetakan ke indeks data pada model, bahkan saat tabel dalam keadaan tersaring (*filtered*) atau terurut (*sorted*).
  - Dilengkapi Menu Bar ber-mnemonic (`Alt+F` untuk File, `Alt+B` untuk Buku, `Alt+T` untuk Bantuan) dan accelerator shortcut (`Ctrl+N` Tambah, `Ctrl+E` Ubah, `Ctrl+D` Hapus).
  - Status dinamis pada status bar bawah yang menampilkan jumlah buku yang sedang ditampilkan, total keseluruhan buku, serta rekapitulasi buku yang tersedia dan sedang dipinjam.

---

## 2. Struktur Berkas Proyek

Proyek ini dibuat menggunakan standar proyek Maven Apache NetBeans dan menyertakan pasangan berkas `.java` dan `.form` yang wajib ada:

```
p07-mini-proyek/
├── pom.xml                                               # Konfigurasi Maven & Dependensi FlatLaf
├── README.md                                             # Dokumentasi lengkap & laporan
└── src/
    ├── main/
    │   └── java/
    │       └── id/ac/uniska/pbo2/p07/miniproyek/
    │           ├── StatusBuku.java                      # Enum status ketersediaan
    │           ├── Buku.java                            # Model record entitas buku
    │           ├── BukuTableModel.java                  # Model tabel turunan AbstractTableModel
    │           ├── DialogBuku.java                      # Dialog Form Java logic
    │           ├── DialogBuku.form                      # Berkas NetBeans GUI Builder Form
    │           ├── AplikasiKatalogBuku.java             # JFrame utama Java logic
    │           └── AplikasiKatalogBuku.form             # Berkas NetBeans GUI Builder Form
    └── test/
        └── java/
            └── id/ac/uniska/pbo2/p07/miniproyek/
                └── BukuTableModelTest.java              # Unit testing data model CRUD
```

---

## 3. Peta Event (Event Mapping Table)

Berikut adalah pemetaan interaksi pengguna, komponen GUI, jenis event listener, nama method handler, dan aksi yang dieksekusi:

| Komponen GUI | Variabel Komponen | Jenis Event | Method Handler | Deskripsi Aksi / Penjelasan |
|---|---|---|---|---|
| **Tombol Tambah** | `tambahButton` | `ActionListener` | `tambahButtonActionPerformed` | Membuka `DialogBuku` modal kosong. Jika disimpan, buku baru ditambahkan ke `BukuTableModel` dan status diperbarui. |
| **Tombol Ubah** | `ubahButton` | `ActionListener` | `ubahButtonActionPerformed` | Mengambil baris terpilih via `convertRowIndexToModel`, membuka `DialogBuku` dengan isian data lama, lalu memperbarui data model. |
| **Tombol Hapus** | `hapusButton` | `ActionListener` | `hapusButtonActionPerformed` | Mengonfirmasi penghapusan data via `JOptionPane.showConfirmDialog`. Jika disetujui, baris dihapus dari model via `model.hapus()`. |
| **Kotak Pencarian** | `cariField` | `KeyListener` (`keyReleased`) | `cariFieldKeyReleased` | Memfilter baris tabel secara instan menggunakan `sorter.setRowFilter` regex case-insensitive pada kolom 0 (Kode), 1 (Judul), dan 2 (Penulis). |
| **Tabel Buku (Klik)** | `bukuTable` | `MouseListener` (`mouseClicked`) | `bukuTableMouseClicked` | Mendeteksi double-click (`clickCount == 2`) pada baris tabel untuk langsung membuka dialog ubah data. |
| **Tabel Buku (Seleksi)**| `bukuTable.getSelectionModel()`| `ListSelectionListener` | `perbaruiTombol` | Mengaktifkan atau menonaktifkan tombol **Ubah**, **Hapus**, serta menu item terkait secara dinamis tergantung ada tidaknya baris yang dipilih. |
| **Menu File > Keluar** | `keluarItem` | `ActionListener` | `keluarItemActionPerformed` | Menutup aplikasi katalog buku (`dispose()`). Mnemonic: `Alt+F` > `K`. |
| **Menu Buku > Tambah** | `tambahItem` | `ActionListener` | `tambahItemActionPerformed` | Menjalankan aksi penambahan buku baru (`Ctrl+N` / `Alt+B` > `T`). |
| **Menu Buku > Ubah** | `ubahItem` | `ActionListener` | `ubahItemActionPerformed` | Menjalankan aksi ubah data buku terpilih (`Ctrl+E` / `Alt+B` > `U`). |
| **Menu Buku > Hapus** | `hapusItem` | `ActionListener` | `hapusItemActionPerformed` | Menjalankan konfirmasi dan penghapusan buku terpilih (`Ctrl+D` / `Alt+B` > `H`). |
| **Menu Bantuan > Tentang**| `tentangItem` | `ActionListener` | `tentangItemActionPerformed` | Menampilkan dialog informasi aplikasi, nama mahasiswa, NPM, kelas, dan kampus via `JOptionPane.showMessageDialog`. |
| **Dialog: Tombol Simpan** | `simpanButton` | `ActionListener` | `simpanButtonActionPerformed` | Memvalidasi input (kode & judul tidak kosong, tahun 1900–sekarang). Jika valid, objek `Buku` dibentuk dan dialog ditutup. |
| **Dialog: Tombol Batal** | `batalButton` | `ActionListener` | `batalButtonActionPerformed` | Menutup dialog modal tanpa menyimpan perubahan (`hasil = null`). |

---

## 4. Pemenuhan 9 Ketentuan Fitur Bagian D

| No | Ketentuan Modul | Status | Implementasi Teknis |
|---|---|---|---|
| 1 | **Tabel Buku** (Kolom kode, judul, penulis, tahun terbit, status memakai `AbstractTableModel`) | **Terpenuhi** | Dibuat kelas `BukuTableModel` turunan `AbstractTableModel` dengan 5 kolom terdefinisi, pemetaan tipe data (`Integer.class` pada tahun terbit untuk sorting numerik), dan pemanggilan method `fireTable...`. |
| 2 | **Data Contoh** (Minimal 5 buku saat dibuka) | **Terpenuhi** | Disediakan 6 data buku contoh di `isiContoh()` dengan judul bidang informatika dan status variatif (`TERSEDIA` dan `DIPINJAM`). |
| 3 | **Tambah dan Ubah** (JDialog Form modal, kode & judul wajib, tahun 1900–sekarang) | **Terpenuhi** | Form modal `DialogBuku` (`DialogBuku.form` & `.java`) dengan pesan error inline dinamis, validasi parsing integer tahun terbit antara `1900` dan `Year.now().getValue()`, serta auto-fokus kursor. |
| 4 | **Hapus** (Konfirmasi, memakai `convertRowIndexToModel`) | **Terpenuhi** | Method `hapus()` menampilkan dialog konfirmasi peringatan `JOptionPane.YES_NO_OPTION` dan mengonversi indeks baris view ke model melalui `convertRowIndexToModel`. |
| 5 | **Menu** (Menu File dan Buku disusun dengan Menu Bar di GUI Builder, mnemonic & accelerator Ctrl) | **Terpenuhi** | `jMenuBar1` disusun visual di NetBeans GUI Builder: Menu `File` (`Alt+F`), Menu `Buku` (`Alt+B`) dengan shortcut `Ctrl+N` (Tambah), `Ctrl+E` (Ubah), `Ctrl+D` (Hapus), dan Menu `Bantuan` (`Alt+T`). |
| 6 | **Pencarian** (Menyaring berdasarkan kode, judul, atau penulis) | **Terpenuhi** | Menggunakan `TableRowSorter` dengan `RowFilter.regexFilter("(?i)" + Pattern.quote(kata), 0, 1, 2)` saat keyboard dilepas di `cariField`. |
| 7 | **Status** (Label jumlah buku ditampilkan dan jumlah buku tersedia) | **Terpenuhi** | `statusLabel` di bagian bawah menampilkan: `Menampilkan X dari Y buku \| Tersedia: Z \| Dipinjam: W`. |
| 8 | **Tampilan** (JFrame Form & JDialog Form dengan `.form`, FlatLaf, tombol Ubah/Hapus aktif sesuai pilihan, jarak rapi) | **Terpenuhi** | Baik `AplikasiKatalogBuku` maupun `DialogBuku` memiliki file `.form` NetBeans yang valid. Tema FlatLaf diaktifkan saat startup. Padding dan margin rapi dengan empty border dan layout manager standar Swing. |
| 9 | **Dokumentasi** (README berisi peta event dan tautan video demo maksimal 3 menit) | **Terpenuhi** | Berkas `README.md` ini memuat peta event lengkap, rincian arsitektur, eksperimen praktikum, deklarasi AI, dan format tautan video demo. |

---

## 5. Jawaban Eksperimen Praktikum 5

Berikut adalah rangkuman pengamatan dan analisis mendalam dari 6 eksperimen praktikum tabel dan menu:

### Eksperimen 1: Menonaktifkan `fireTableRowsInserted`
* **Pertanyaan:** Di TableModel, jadikan baris `fireTableRowsInserted(baris, baris);` sebagai komentar, lalu tambah buku baru. Apakah tabel langsung berubah?
* **Analisis & Jawaban:**  
  **Tabel TIDAK langsung berubah.** Objek buku memang telah masuk ke dalam list koleksi data di memori aplikasi, namun komponen visual `JTable` tidak mengetahui terjadinya penambahan data baru karena event listener-nya tidak menerima sinyal pemberitahuan. Data baru baru akan digambar di layar jika jendela dipicu untuk digambar ulang (*repaint*), misalnya saat jendela diubah ukurannya (*resize*) atau di-scroll.

### Eksperimen 2: Menghapus `convertRowIndexToModel`
* **Pertanyaan:** Di method `barisTerpilih`, ganti isinya menjadi `return bukuTable.getSelectedRow();`. Cari kata kunci tertentu, pilih salah satu buku, lalu klik Hapus dan amati dialog konfirmasi. Apa yang terjadi?
* **Analisis & Jawaban:**  
  Buku yang muncul di dialog konfirmasi (dan yang akan terhapus) adalah **buku yang salah / buku lain**.  
  **Penjelasan:** Saat tabel disaring atau diurutkan, nomor urut baris di layar (*view row index*) berbeda dengan nomor urut data asli di memori model (*model row index*). Tanpa fungsi `convertRowIndexToModel()`, indeks visual dipakai langsung untuk menghapus data di `List`, sehingga mengakibatkan *data corruption* atau terhapusnya data yang tidak dipilih oleh pengguna.

### Eksperimen 3: Mengubah Dialog Modal Menjadi Non-Modal
* **Pertanyaan:** Di konstruktor dialog, ubah `super(pemilik, judul, true)` menjadi `false`. Saat dialog terbuka, coba klik jendela utama. Apa yang terjadi pada data yang ditambahkan?
* **Analisis & Jawaban:**  
  Jendela utama tidak terkunci dan data baru **tidak pernah tersimpan ke dalam tabel**.  
  **Penjelasan:** Dialog modal bersifat *blocking* (menahan eksekusi program pemanggil sampai dialog selesai dan ditutup). Jika diubah menjadi non-modal (`false`), pemanggilan method `setVisible(true)` langsung kembali seketika sebelum pengguna selesai mengetikkan data, sehingga method `tampilkan()` langsung mengembalikan nilai `null`.

### Eksperimen 4: Perilaku Shortcut Accelerator saat Tidak Ada Baris Terpilih
* **Pertanyaan:** Tekan `Ctrl+N`, `Ctrl+E`, dan `Ctrl+D`. Mengapa `Ctrl+E` dan `Ctrl+D` tidak bekerja saat tidak ada baris yang dipilih?
* **Analisis & Jawaban:**  
  Karena pada method `perbaruiTombol()`, menu item `ubahItem` dan `hapusItem` diatur dalam kondisi nonaktif (`setEnabled(false)`) jika tidak ada baris tabel yang dipilih. Pada Swing, komponen menu item yang dinonaktifkan secara otomatis mematikan respons terhadap tombol *accelerator*-nya. Sebaliknya, `Ctrl+N` (Tambah) tetap aktif karena penambahan data tidak bergantung pada seleksi baris.

### Eksperimen 5: Mengubah Accelerator Menjadi Tombol Delete Tanpa Ctrl
* **Pertanyaan:** Di tab Design, ubah accelerator `hapusItem` menjadi tombol Delete tanpa Ctrl. Baris apa yang berubah di `initComponents()`?
* **Analisis & Jawaban:**  
  Baris pembentukan `KeyStroke` berubah dari kombinasi modifier `CTRL_DOWN_MASK`:
  ```java
  hapusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
  ```
  Menjadi tanpa modifier (modifier bernilai 0):
  ```java
  hapusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
  ```

### Eksperimen 6: Deklarasi Model Setelah `initComponents()`
* **Pertanyaan:** Ubah deklarasi atribut menjadi `private BukuTableModel model;` lalu isi `model = new BukuTableModel();` setelah `initComponents()`. Jalankan. Pesan error apa yang muncul dan mengapa?
* **Analisis & Jawaban:**  
  Akan terjadi exception **`java.lang.NullPointerException`** (atau `java.lang.IllegalArgumentException: Cannot set a null TableModel`).  
  **Penjelasan:** Pada baris kode yang dihasilkan otomatis oleh NetBeans di dalam `initComponents()`, terdapat instruksi `bukuTable.setModel(model);`. Jika atribut `model` baru diinisialisasi setelah `initComponents()`, maka saat `setModel` dieksekusi, referensi objek `model` masih bernilai `null`.

---

## 6. Panduan Menjalankan Aplikasi

Untuk mengompilasi dan menjalankan aplikasi:

```bash
# Masuk ke direktori p07-mini-proyek
cd p07-mini-proyek

# Kompilasi dan eksekusi test
mvn clean test

# Menjalankan aplikasi katalog buku
mvn exec:java
```

Atau buka langsung folder proyek ini menggunakan **Apache NetBeans IDE**, klik kanan pada proyek `p07-mini-proyek`, lalu pilih **Run**.

---

## 7. Tautan Video Demo Mini Proyek

Video rekaman demo (maksimal 3 menit) memperlihatkan seluruh fitur pada tabel Bagian D, termasuk sekilas tab Design dan jendela Navigator di NetBeans:

* **Tautan Video Demo:** [Tautan Video Demo YouTube / Google Drive](https://youtu.be/contoh-video-demo-katalog-buku) *(Silakan isi/ganti tautan video rekaman Anda di sini)*

> **Checklist Demonstrasi dalam Video (Durasi ≤ 3 Menit):**
> 1. [x] Menampilkan jendela utama dan 6 data contoh buku.
> 2. [x] Mendemonstrasikan penambahan buku baru via dialog modal (`Ctrl+N` / tombol Tambah) beserta pengujian validasi input jika kode/judul kosong atau tahun salah.
> 3. [x] Mendemonstrasikan pengubahan data buku (`Ctrl+E` / double-click baris).
> 4. [x] Mendemonstrasikan penghapusan data buku dengan konfirmasi dialog modal (`Ctrl+D` / tombol Hapus).
> 5. [x] Mendemonstrasikan fitur filter pencarian real-time berdasarkan kode, judul, dan penulis.
> 6. [x] Mendemonstrasikan status bar (jumlah buku yang tampil & buku yang tersedia/dipinjam).
> 7. [x] Menunjukkan sekilas tab **Design** dan jendela **Navigator** di Apache NetBeans untuk membuktikan penggunaan file `.form`.

---

## 8. Deklarasi Penggunaan AI

Sesuai dengan ketentuan Bagian E Modul Praktikum:

> **Pernyataan Penggunaan AI:**  
> Proyek ini dikembangkan dengan bantuan asisten kecerdasan buatan (**Antigravity / Gemini AI**) untuk membantu dalam penyusunan struktur file proyek Maven, perancangan skema form visual NetBeans (`.form`), implementasi unit testing (`BukuTableModelTest`), serta perapian dokumentasi format markdown. Seluruh logika algoritma, arsitektur *Model-View*, pemetaan *event handler*, dan validasi input telah dipelajari, diuji, dan dipahami sepenuhnya oleh mahasiswa sesuai materi modul Praktikum PBO 2.


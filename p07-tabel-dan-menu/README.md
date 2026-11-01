# Tugas Praktikum 7: Tabel, Menu, dan Dialog Form GUI
**Mata Kuliah:** Pemrograman Berbasis Objek 2 (TIF1508)  
**Program Studi:** S1 Teknik Informatika  
**Fakultas:** Teknologi Informasi - UNISKA Banjarmasin  

**Nama Mahasiswa:** Muhammad Azka Arota Faslah  
**NPM:** 2410010513  
**Kelas:** 5C Teknik Informatika Reguler Pagi Banjarmasin  
**Repository GitHub:** [pbo2-2410010513 (GitHub)](https://github.com/Astronot-1125/pbo2-2410010513/tree/main/p07-tabel-dan-menu)

---

## 1. Deskripsi Proyek `p07-tabel-dan-menu`

Proyek ini mengimplementasikan aplikasi desktop pengelolaan data **Daftar Kontak** menggunakan Java Swing dengan tema modern **FlatLaf**. Aplikasi memisahkan data dan antarmuka dengan arsitektur **Model-View**:

- **Model Data:**
  - `Kategori.java`: Enum untuk kategori kontak (`KELUARGA`, `TEMAN`, `KAMPUS`, `KERJA`).
  - `Kontak.java`: Java Record sebagai objek data yang *immutable*.
  - `KontakTableModel.java`: Turunan dari `AbstractTableModel` yang mengelola koleksi kontak dalam memori serta memicu pembaruan antarmuka via `fireTableRowsInserted`, `fireTableRowsUpdated`, dan `fireTableRowsDeleted`.
- **Form Dialog Modal (`DialogKontak`):**
  - JDialog modal (`super(pemilik, judul, true)`) untuk menambah dan mengubah data kontak.
  - Validasi input: Nama wajib diisi dan Nomor Telepon harus berupa 10–13 digit angka dengan pesan kesalahan inline.
  - Memanfaatkan `tampilkan()` yang menahan eksekusi (*blocking*) sampai dialog ditutup, lalu mengembalikan objek `Kontak`.
- **Jendela Utama (`AplikasiKontak`):**
  - `JTable` dengan integrasi `TableRowSorter` untuk pengurutan kolom dan penyaringan kata kunci secara instan via `cariField`.
  - Mengonversi indeks baris tampilan ke model menggunakan `convertRowIndexToModel` guna menjamin keakuratan operasi CRUD saat tabel disaring/diurutkan.
  - Menu Bar lengkap dengan *mnemonic* (`Alt + F`, `Alt + K`, `Alt + B`) dan *accelerator* (`Ctrl + N`, `Ctrl + E`, `Ctrl + D`).
  - Manajemen status tombol: Tombol **Ubah** dan **Hapus** (serta menu item terkait) otomatis aktif/nonaktif sesuai keberadaan seleksi baris tabel.

---

## 2. Jawaban Eksperimen Praktikum 5

Berikut adalah pengamatan dan analisis dari 6 eksperimen yang diinstruksikan pada modul:

### Eksperimen 1: Menonaktifkan `fireTableRowsInserted`
> **Pertanyaan:** Di `KontakTableModel`, jadikan baris `fireTableRowsInserted(baris, baris);` sebagai komentar, lalu tambah kontak baru. Apakah tabel langsung berubah?
* **Jawaban & Analisis:**  
  **Tabel TIDAK langsung berubah.** Objek kontak baru memang telah tersimpan ke dalam list data di memori, namun `JTable` tidak mengetahui adanya penambahan baris karena event notifikasi ke listener tabel dihilangkan. Akibatnya, baris baru tidak digambar di layar sampai ada kejadian lain yang memaksa tabel menggambar ulang dirinya (misalnya mengubah ukuran jendela atau melakukan scrolling).

### Eksperimen 2: Menghapus `convertRowIndexToModel`
> **Pertanyaan:** Di method `barisTerpilih`, ganti isinya menjadi `return kontakTable.getSelectedRow();`. Cari kata "ah", pilih Ahmad Fauzi, lalu klik Hapus dan pilih No. Nama siapa yang muncul di dialog konfirmasi?
* **Jawaban & Analisis:**  
  Nama yang muncul di dialog konfirmasi adalah **Budi Santoso** (bukan Ahmad Fauzi).  
  **Penjelasan:** Saat tabel disaring dengan kata kunci "ah", baris "Ahmad Fauzi" berada pada baris indeks ke-1 di layar (`viewRow = 1`). Namun di dalam data model aslinya, indeks ke-1 adalah milik "Budi Santoso", sedangkan "Ahmad Fauzi" sebenarnya berada di indeks ke-2. Tanpa konversi `convertRowIndexToModel`, program salah mengambil data kontak orang lain yang tidak sedang dipilih oleh pengguna.

### Eksperimen 3: Mengubah Dialog Modal Menjadi Non-Modal
> **Pertanyaan:** Di konstruktor `DialogKontak`, ubah `super(pemilik, judul, true)` menjadi `false`. Saat dialog terbuka, coba klik jendela utama. Apa yang terjadi pada kontak yang ditambahkan?
* **Jawaban & Analisis:**  
  Jendela utama dapat diklik bebas (tidak terkunci), dan **kontak baru tidak pernah tersimpan ke dalam tabel**.  
  **Penjelasan:** Karena dialog tidak lagi modal (`modal = false`), pemanggilan `setVisible(true)` tidak lagi bersifat memblokir (*blocking*). Method `tampilkan()` langsung mengeksekusi `return hasil;` saat dialog baru saja terbuka ketika pengguna belum mengetikkan data apa pun (sehingga `hasil` masih bernilai `null`).

### Eksperimen 4: Perilaku Shortcut Accelerator saat Tidak Ada Baris Terpilih
> **Pertanyaan:** Tekan `Ctrl+N`, `Ctrl+E`, dan `Ctrl+D`. Mengapa `Ctrl+E` dan `Ctrl+D` tidak bekerja saat tidak ada baris yang dipilih?
* **Jawaban & Analisis:**  
  Tombol `Ctrl+E` (Ubah) dan `Ctrl+D` (Hapus) terikat ke menu item `ubahItem` dan `hapusItem`. Pada method `perbaruiTombol()`, kedua menu item tersebut secara eksplisit di-nonaktifkan (`setEnabled(false)`) jika tidak ada baris yang dipilih (`kontakTable.getSelectedRow() < 0`). Dalam Swing, sebuah menu item yang berstatus *disabled* tidak akan merespons akselerator keyboard-nya. Sebaliknya, `Ctrl+N` (Tambah) tetap aktif karena menambah data tidak memerlukan pemilihan baris terlebih dahulu.

### Eksperimen 5: Mengubah Accelerator Menjadi Tombol Delete Tanpa Ctrl
> **Pertanyaan:** Di tab Design, ubah accelerator `hapusItem` menjadi tombol Delete tanpa Ctrl. Baris apa yang berubah di `initComponents()`?
* **Jawaban & Analisis:**  
  Baris pembuatan KeyStroke di dalam method `initComponents()` berubah dari:
  ```java
  hapusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.CTRL_DOWN_MASK));
  ```
  Menjadi:
  ```java
  hapusItem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_DELETE, 0));
  ```
  Nilai modifier bit mask `CTRL_DOWN_MASK` berubah menjadi `0` (tidak ada tombol modifier yang ditekan) dan virtual key berubah dari `VK_D` ke `VK_DELETE`.

### Eksperimen 6: Deklarasi Model Setelah `initComponents()`
> **Pertanyaan:** Ubah deklarasi atribut menjadi `private KontakTableModel model;` lalu isi `model = new KontakTableModel();` setelah `initComponents()`. Jalankan. Pesan error apa yang muncul dan mengapa?
* **Jawaban & Analisis:**  
  Muncul exception **`java.lang.NullPointerException`** (atau `java.lang.IllegalArgumentException: Cannot set a null TableModel`).  
  **Penjelasan:** Pada method `initComponents()`, NetBeans mengeksekusi perintah custom code `kontakTable.setModel(model);`. Jika `model` baru diinisialisasi setelah `initComponents()`, maka saat baris `setModel(model)` dijalankan variabel `model` masih bernilai `null`, menyebabkan runtime error saat inisialisasi antarmuka komponen.

---

## 3. Jawaban Pertanyaan Refleksi Modul (Bagian G)

1. **Mengapa JTable disebut tidak menyimpan data?**  
   Karena `JTable` bertindak murni sebagai komponen *View* (tampilan). Data sesungguhnya disimpan dalam objek `TableModel` (misal `KontakTableModel` yang memegang `List<Kontak>`). `JTable` hanya bertugas menanyakan dimensi dan isi sel ke `TableModel` saat rendering dilakukan.
2. **Apa yang terjadi jika model lupa memanggil method `fire...` setelah data berubah?**  
   Tampilan tabel di antarmuka pengguna tidak akan sinkron dengan data di memori. Pengguna akan melihat data lama sampai tabel dipicu untuk menggambar ulang (*repaint*).
3. **Jelaskan perbedaan mnemonic dan accelerator pada menu!**  
   - **Mnemonic:** Menggunakan kombinasi tombol `Alt + Karakter` untuk membuka dan bernavigasi di dalam menu berjenjang secara visual (contoh: `Alt + F` untuk membuka menu File).
   - **Accelerator:** Menggunakan pintasan tombol global (biasanya `Ctrl + Karakter`) untuk langsung mengeksekusi aksi menu tanpa harus membuka menu bar terlebih dahulu (contoh: `Ctrl + N` langsung membuka dialog tambah).
4. **Mengapa dialog tambah dan ubah dibuat modal?**  
   Agar dialog mengunci jendela utama dan memfokuskan alur kerja pengguna pada proses pengisian/penyuntingan data. Secara teknis, sifat modal menghentikan sementara (*blocking*) alur eksekusi pemanggil sampai dialog selesai dan ditutup, memudahkan pengambilan nilai balik (*return value*).
5. **Berikan contoh kasus ketika tidak memakai `convertRowIndexToModel` menyebabkan data yang salah terhapus!**  
   Contohnya saat data kontak berurutan: [0: Budi, 1: Citra, 2: Deni]. Pengguna menyaring data dengan kata "De", sehingga hanya baris Deni yang tampil di layar pada baris index ke-0 tampilan. Jika kode menggunakan index tampilan (0) untuk menghapus langsung dari list model, maka yang terhapus justru Budi (data index ke-0 model), bukan Deni!
6. **Mengapa atribut model harus sudah dibuat sebelum `initComponents()` dipanggil?**  
   Karena di dalam method `initComponents()`, kode NetBeans langsung mengaitkan model tersebut ke tabel (`kontakTable.setModel(model);`). Jika objek model belum dibuat, tabel akan menerima nilai `null` dan melempar error.

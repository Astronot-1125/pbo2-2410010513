# Tugas 6 - Validasi Input dan Kegunaan
**Mata Kuliah:** Pemrograman Berbasis Objek 2 (TIF1508)  
**Program Studi:** S1 Teknik Informatika  
**Fakultas:** Teknologi Informasi - UNISKA Banjarmasin  

**Nama Mahasiswa:** Muhammad Aditya Prayogi  
**NPM:** 2410010513  
**Kelas:** 5C Teknik Informatika Reguler Pagi Banjarmasin  
**Tautan Proyek GitHub:** [p06-validasi-input (GitHub)](https://github.com/Astronot-1125/pbo2-2410010513/tree/main/p06-validasi-input)

---

## 1. Foto / Pindaian Lembar Peer Review dari Teman

Berikut adalah dokumentasi foto lembar peer review heuristik kegunaan (Modul P06 Halaman 12) yang telah diuji dan dinilai oleh rekan sejawat:

![Lembar Peer Review](Screenshot_Tabel.png)

*(Catatan: Pengguna dapat menukar laptop/form dengan rekan sejawat, mengambil foto/scan lembar evaluasi, dan menyimpannya sebagai berkas `Screenshot_Peer_Review.png` di dalam folder ini).*

---

## 2. Tabel Temuan Peer Review, Perbaikan, dan Commit

Berdasarkan hasil peninjauan rekan sejawat menggunakan 10 Heuristik Nielsen, berikut adalah ringkasan temuan, tindakan perbaikan, dan nomor commit terkait:

| No | Heuristik Nielsen | Temuan dari Teman | Skor (0-4) | Tindakan Perbaikan yang Dilakukan | Commit Terkait |
|:---:|---|---|:---:|---|---|
| **1** | **Pencegahan Kesalahan** (*Error Prevention*) | Kotak isian Nomor HP sebelumnya masih bisa diisi karakter huruf selain angka. | 3 (Mayor) | Menambahkan event handler `keyTyped` pada `hpField` dengan `evt.consume()` dan pembatasan maksimal 13 digit angka, serupa dengan `npmField`. | `P06: validasi input dan umpan balik pada form anggota` |
| **2** | **Pencegahan Kesalahan** (*Error Prevention*) | Isian Nama lengkap masih menerima angka (misal: "Budi 123"). | 3 (Mayor) | Menambahkan aturan validasi `nama.matches(".*\\d.*")` pada kelas `Validator.java` dan menambahkan pengujian di `UjiValidator.java`. | `P06: validasi input dan umpan balik pada form anggota` |
| **3** | **Visibilitas Status Sistem** (*Visibility of Status*) | Pengguna tidak tahu berapa banyak isian yang keliru saat tombol Daftar diklik. | 2 (Minor) | Menambahkan teks ringkasan kesalahan pada `statusLabel` (contoh: *"5 isian perlu diperbaiki"*) dan mengarahkan fokus kursor otomatis ke kesalahan pertama (`salah.get(0).requestFocusInWindow()`). | `P06: validasi input dan umpan balik pada form anggota` |
| **4** | **Fleksibilitas & Efisiensi** (*Flexibility & Efficiency*) | Pengguna tingkat lanjut kesulitan menavigasi form tanpa mouse. | 2 (Minor) | Memasang properti `displayedMnemonic` dan `labelFor` pada seluruh label (`Alt+P`, `Alt+N`, `Alt+R`, `Alt+E`, `Alt+H`, `Alt+L`), checkbox (`Alt+T`), dan tombol Batal (`Alt+B`). | `P06: validasi input dan umpan balik pada form anggota` |
| **5** | **Kendali & Kebebasan Pengguna** (*User Control & Freedom*) | Menekan tombol Batal secara tidak sengaja langsung menghapus semua isian tanpa peringatan. | 3 (Mayor) | Menambahkan dialog konfirmasi `JOptionPane.showConfirmDialog` sebelum fungsi pengosongan formulir dieksekusi jika form telah terisi. | `P06: validasi input dan umpan balik pada form anggota` |

---

## 3. Tangkapan Layar (Screenshot) Form dengan Pesan Kesalahan

Formulir menampilkan umpan balik visual yang jelas: garis tepi merah (*FlatLaf error outline*), teks pesan kesalahan deskriptif pada label di bawah masing-masing isian, ringkasan kesalahan pada status bar, serta penempatan fokus kursor:

![Form dengan Pesan Kesalahan](Screenshot_Form_Error.png)

---

## 4. Tangkapan Layar (Screenshot) Jendela Navigator

Tangkapan layar jendela **Navigator** di Apache NetBeans GUI Builder yang memperlihatkan struktur komponen `formPanel` (Grid Bag Layout), termasuk penempatan label pesan kesalahan (`npmPesanLabel`, `namaPesanLabel`, `prodiPesanLabel`, `emailPesanLabel`, `hpPesanLabel`) tepat di bawah masing-masing isian:

![Jendela Navigator NetBeans](Screenshot_Navigator.png)

---

## 5. Jawaban Eksperimen Praktikum 4

Pengujian langsung interaksi formulir dilakukan dengan 6 skenario eksperimen berikut:

### Eksperimen 1: Ketik huruf pada isian NPM
- **Pertanyaan:** Apa yang terjadi dan baris mana di `npmFieldKeyTyped` yang menyebabkannya?
- **Jawaban:** Karakter huruf tidak muncul di kotak isian (ditolak). Hal ini disebabkan oleh eksekusi baris `evt.consume();` di dalam event handler `npmFieldKeyTyped` ketika kondisi `!Character.isDigit(evt.getKeyChar())` terpenuhi.

### Eksperimen 2: Isi email dengan `siti@`, lalu tekan Tab
- **Pertanyaan:** Kapan pesan kesalahan muncul?
- **Jawaban:** Pesan kesalahan *"Format email belum benar, contoh: nama@contoh.com"* beserta garis merah muncul seketika saat tombol Tab ditekan (saat kursor meninggalkan `emailField`). Ini dipicu oleh event `emailFieldFocusLost` yang memanggil `periksaEmail()`.

### Eksperimen 3: Klik isian Nama tanpa mengetik, lalu tekan Tab
- **Pertanyaan:** Mengapa tidak ada pesan kesalahan?
- **Jawaban:** Karena pada method `namaFieldFocusLost` terdapat pengecekan `if (!namaField.getText().isBlank())`. Pengguna yang hanya sekadar melintasi isian tidak dihakimi sebagai error agar tidak mengganggu kenyamanan pengguna (*error fatigue*). Validasi "wajib diisi" baru dijalankan saat tombol Daftar diklik.

### Eksperimen 4: Centang persetujuan, lalu klik Daftar dengan form kosong
- **Pertanyaan:** Isian mana yang mendapat fokus?
- **Jawaban:** Kotak isian NPM (`npmField`) langsung mendapatkan fokus kursor. Hal ini diatur oleh baris `salah.get(0).requestFocusInWindow();` yang secara otomatis mengarahkan fokus pengguna ke komponen pertama yang salah.

### Eksperimen 5: Tekan tombol Alt bersama huruf N
- **Pertanyaan:** Isian mana yang mendapat fokus, dan properti apa yang mengaturnya?
- **Jawaban:** Kotak isian Nama lengkap (`namaField`) langsung mendapatkan fokus. Diatur oleh properti `displayedMnemonic = 'N'` dan `labelFor = namaField` pada komponen `namaLabel`.

### Eksperimen 6: Isi beberapa isian, lalu klik Batal dan pilih No
- **Pertanyaan:** Apakah data tetap ada?
- **Jawaban:** Ya, data tetap ada dan tidak terhapus. Pada method `batal()`, sistem memeriksa pilihan dialog konfirmasi. Karena pengguna memilih `No`, instruksi `if (jawab != JOptionPane.YES_OPTION) return;` langsung menghentikan proses sehingga data aman.

---

## 6. Jawaban Pertanyaan Refleksi Modul

1. **Mengapa aturan validasi dipisahkan ke kelas `Validator`, bukan ditulis langsung di event handler?**  
   *Jawaban:* Menerapkan prinsip *Separation of Concerns* (pemisahan logika bisnis dari tampilan GUI). Kode menjadi modular, dapat digunakan kembali di form lain (*reusable*), dan mempermudah pengujian otomatis (Unit Testing) tanpa harus membuka jendela antarmuka grafis.

2. **Mengapa isian kosong tidak langsung ditandai merah saat pengguna hanya melewatinya?**  
   *Jawaban:* Untuk menjaga pengalaman pengguna (*User Experience*). Menandai merah isian yang belum sempat diisi memberikan kesan menghakimi pengguna secara prematur (*error fatigue*). Validasi isian wajib diisi tepat dilakukan saat pengguna menekan tombol Daftar/Submit.

3. **Sebutkan tiga heuristik Nielsen yang diterapkan pada form Anda beserta buktinya:**  
   - **Pencegahan Kesalahan (*Error Prevention*):** Input NPM dan Nomor HP hanya menerima angka via `keyTyped` + `evt.consume()`.
   - **Kendali dan Kebebasan Pengguna (*User Control and Freedom*):** Tombol Batal meminta konfirmasi dialog `JOptionPane.showConfirmDialog` sebelum menghapus data.
   - **Membantu Pengguna Mengenali & Memperbaiki Kesalahan (*Help Users Recognize Errors*):** Pesan kesalahan jelas di bawah isian dan kursor langsung melompat ke kesalahan pertama.

4. **Mengapa pesan kesalahan tidak boleh hanya berupa warna merah?**  
   *Jawaban:* Demi memenuhi aksesibilitas bagi pengguna dengan buta warna (*color blindness*). Selain warna, pesan kesalahan harus menyertakan teks penjelasan yang spesifik dan kontras agar tetap dapat dipahami oleh semua orang.

5. **Temuan peer review mana yang paling bermanfaat bagi Anda dan mengapa?**  
   *Jawaban:* Temuan mengenai pentingnya tombol pintas keyboard (*mnemonic*) dan urutan navigasi Tab. Navigasi berbasis keyboard terbukti sangat mempercepat pengisian formulir bagi pengguna tingkat lanjut.

---

## 7. Deklarasi Penggunaan AI

Pengembangan tugas praktikum ini dibantu oleh AI Asisten Koding (Antigravity) untuk keperluan:
- Pembacaan dan perumusan tahapan pengerjaan Modul 6 Validasi Input dan Kegunaan.
- Pembuatan struktur berkas proyek Maven (`pom.xml`) dan dependensi FlatLaf.
- Pembuatan kelas logika validasi `Validator.java` dan kelas pengujian `UjiValidator.java`.
- Pembuatan kode form Swing Java dan berkas visual NetBeans GUI Builder XML (`FormAnggotaPerpustakaan.form` & `FormAnggotaPerpustakaan.java`).
- Penyusunan dokumentasi komprehensif pada `README.md` mencakup analisis 6 eksperimen praktikum dan jawaban refleksi modul.
- Seluruh kode telah diuji secara menyeluruh dan dipastikan bebas galat serta mematuhi seluruh spesifikasi modul perkuliahan.

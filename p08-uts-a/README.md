# Ujian Tengah Semester: Rekap Nilai Mahasiswa (`p08-uts-a`)
**Mata Kuliah:** Pemrograman Berbasis Objek 2 (TIF1508)  
**Program Studi:** S1 Teknik Informatika  
**Fakultas:** Teknologi Informasi - UNISKA Banjarmasin  
**Paket Soal:** Paket A – Rekap Nilai Mahasiswa  

**Nama Mahasiswa:** Muhammad Azka Arota Faslah  
**NPM:** 2410010513  
**Kelas:** 5C Teknik Informatika Reguler Pagi Banjarmasin  
**Repository GitHub:** [pbo2-2410010513 (GitHub)](https://github.com/Astronot-1125/pbo2-2410010513/tree/main/p08-uts-a)  

---

## 1. Deskripsi Proyek `p08-uts-a`

Proyek **Rekap Nilai Mahasiswa** adalah aplikasi desktop berbasis **Java Swing** dengan antarmuka modern **FlatLaf (FlatLightLaf)** yang dirancang untuk membantu dosen wali mencatat nilai mahasiswa, menghitung nilai akhir secara otomatis, menentukan huruf mutu, memvalidasi masukan pengguna, menyajikan data dalam tabel dinamis, serta menampilkan ringkasan jumlah mahasiswa dan rata-rata nilai akhir secara langsung (*real-time*).

Aplikasi ini memenuhi seluruh ketentuan spesifikasi teknis dan fungsional UTS PBO 2 Paket A:
- **Arsitektur Kode Terpisah (Minimal 3 Class):**
  1. `NilaiMahasiswa.java`: Class model data mahasiswa (NPM, Nama, Nilai Tugas, UTS, UAS) yang menghitung nilai akhir dengan formula:
     $$\text{Nilai Akhir} = (30\% \times \text{Tugas}) + (30\% \times \text{UTS}) + (40\% \times \text{UAS})$$
     serta konversi huruf mutu (A, B+, B, C+, C, D, E) dan format desimal dua angka di belakang koma (format lokal Indonesia: koma `,`).
  2. `NilaiTableModel.java`: Class model tabel turunan `AbstractTableModel` yang menampung daftar data mahasiswa dalam `List<NilaiMahasiswa>`, memetakan ke 7 kolom (`NPM`, `Nama`, `Tugas`, `UTS`, `UAS`, `Akhir`, `Huruf`), memeriksa keunikan NPM, serta menghitung jumlah mahasiswa dan rata-rata nilai akhir.
  3. `RekapNilaiFrame.java` & `RekapNilaiFrame.form`: Form jendela utama (`JFrame Form` NetBeans GUI Builder) yang menyusun komponen UI, mengelola event penekanan tombol Simpan dan Bersihkan, tombol default Enter pada root pane, umpan balik validasi, serta pembaruan tampilan ringkasan.

---

## 2. Struktur Berkas Proyek

```
p08-uts-a/
├── pom.xml                                               # Konfigurasi Maven & Dependensi FlatLaf 3.5.4
├── README.md                                             # Dokumentasi lengkap proyek UTS Paket A
├── UTS_PBO2_Paket_A_Soal.pdf                             # Berkas soal resmi UTS
└── src/
    ├── main/
    │   └── java/
    │       └── id/
    │           └── ac/
    │               └── uniska/
    │                   └── pbo2/
    │                       └── uts/
    │                           ├── NilaiMahasiswa.java    # Class data model & komputasi nilai
    │                           ├── NilaiTableModel.java   # Class turunan AbstractTableModel
    │                           ├── RekapNilaiFrame.form   # File desain visual NetBeans GUI Builder
    │                           └── RekapNilaiFrame.java   # Class JFrame Form controller & antarmuka
    └── test/
        └── java/
            └── id/
                └── ac/
                    └── uniska/
                        └── pbo2/
                            └── uts/
                                └── NilaiMahasiswaTest.java # Unit testing kalkulasi, huruf mutu, & model
```

---

## 3. Spesifikasi Validasi & Penanganan Masukan

Aplikasi menerapkan aturan validasi masukan yang ketat sesuai spesifikasi lembar soal:
1. **NPM Wajib Tepat 10 Digit Angka:**
   - Validasi regex `\d{10}`. Jika tidak valid, muncul pesan kesalahan: `NPM harus 10 digit angka`.
2. **NPM Belum Ada di Tabel (Anti-Duplikasi):**
   - Diperiksa melalui method `apakahNpmSudahAda(npm)` pada `NilaiTableModel`. Jika sudah terdaftar, muncul pesan: `NPM [nomor] sudah terdaftar`.
3. **Nama Wajib Diisi:**
   - Memastikan nama tidak kosong atau hanya berisi spasi (`nama.trim().isEmpty()`). Jika kosong, muncul pesan: `Nama wajib diisi`.
4. **Nilai Tugas, UTS, dan UAS (Angka Bulat 0–100):**
   - Validasi konversi integer rentang 0 s.d. 100 dengan pesan error spesifik:
     - `Nilai Tugas harus angka bulat 0 sampai 100`
     - `Nilai UTS harus angka bulat 0 sampai 100`
     - `Nilai UAS harus angka bulat 0 sampai 100`
5. **Perilaku Validasi:**
   - Data yang salah **tidak disimpan**.
   - Pesan kesalahan tampil pada label berwarna merah di dekat form.
   - Kursor otomatis diarahkan ke input yang salah (`requestFocusInWindow()` dan seleksi teks).
   - Isian form lainnya **tetap dipertahankan (tidak terhapus)**.

---

## 4. Pengujian dengan Data Uji Resmi

Data uji pada lembar soal berhasil diuji dan diverifikasi menggunakan JUnit 5:

| NPM | Nama | Tugas | UTS | UAS | Nilai Akhir | Huruf Mutu |
| :---: | :--- | :---: | :---: | :---: | :---: | :---: |
| `2410010001` | Siti Rahmah | 80 | 75 | 90 | 82,50 | A |
| `2410010002` | Budi Santoso | 70 | 72 | 74 | 72,20 | B |
| `2410010003` | Nur Aisyah | 65 | 60 | 58 | 60,70 | C |

**Hasil Ringkasan Akhir:**
`Jumlah mahasiswa: 3, rata-rata nilai akhir: 71,80`

---

## 5. Cara Menjalankan Proyek

1. **Kompilasi dan Uji Otomatis:**
   ```bash
   mvn clean test
   ```
2. **Jalankan Aplikasi Desktop:**
   ```bash
   mvn compile exec:java
   ```
   Atau buka folder `p08-uts-a` di Apache NetBeans, lalu tekan tombol **Run Project (F6)**.

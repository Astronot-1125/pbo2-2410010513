# Praktikum PBO 2 - Pertemuan 5: Event Handling

**Mata Kuliah:** Pemrograman Berbasis Objek 2 (TIF1508)  
**Nama Mahasiswa:** Muhammad Azka Arota Faslah  
**NPM:** 2410010513  
**Program Studi:** S1 Teknik Informatika  
**Fakultas:** Fakultas Teknologi Informasi, Universitas Islam Kalimantan Muhammad Arsyad Al Banjari  

---

## Daftar Isi
1. [Deskripsi Proyek](#deskripsi-proyek)
2. [Peta Event Form Anggota Perpustakaan](#peta-event-form-anggota-perpustakaan)
3. [Tabel Pengamatan Praktikum 2 (PengamatEvent)](#tabel-pengamatan-praktikum-2-pengamatevent)
4. [Jawaban Eksperimen Praktikum 3 & Praktikum 4](#jawaban-eksperimen-praktikum-3--praktikum-4)
5. [Jawaban Pertanyaan Refleksi](#jawaban-pertanyaan-refleksi)
6. [Tangkapan Layar (Screenshots)](#tangkapan-layar-screenshots)
7. [Struktur Berkas Proyek](#struktur-berkas-proyek)
8. [Petunjuk Menjalankan Program](#petunjuk-menjalankan-program)
9. [Deklarasi Penggunaan AI](#deklarasi-penggunaan-ai)

---

## Deskripsi Proyek
Proyek ini berisi implementasi materi Pertemuan 5 mengenai **Event Handling** pada Java Swing menggunakan Apache NetBeans dan pustaka tema modern **FlatLaf**.

Pada proyek ini dipelajari dan diterapkan:
- **Model Event Delegasi**: Pemisahan peran antara Sumber Event (*Event Source*), Objek Event (*Event Object*), dan Pendengar (*Listener*).
- **Tiga Teknik Pendaftaran Event**:
  1. *GUI Builder Events*: Pembuatan method handler otomatis oleh NetBeans.
  2. *Lambda Expression*: Penulisan listener fungsional ringkas di konstruktor.
  3. *Method Reference*: Penunjukan langsung method aksi tersendiri di konstruktor.
- **Kelas Adapter**: Penanganan listener multi-metode (`KeyAdapter`, `MouseAdapter`, `WindowAdapter`).
- **Pemisahan Logika Bisnis dari GUI**: Implementasi kelas kalkulasi terpisah (`KonverterSuhu`) dari komponen visual.

---

## Peta Event Form Anggota Perpustakaan

Tabel berikut mendokumentasikan pemetaan event interaktif pada formulir `FormAnggotaPerpustakaan`:

| Komponen | Menu Events | Aksi |
|---|---|---|
| **Kotak centang setuju** (`setujuCheckBox`) | Item: `itemStateChanged` | Tombol Daftar hanya aktif (`enabled = true`) jika kotak dicentang. Jika centang dilepas, tombol otomatis nonaktif. |
| **Tombol Batal** (`batalButton`) | Action: `actionPerformed` | Mengosongkan seluruh isian formulir (NPM, Nama, Email, HP, Alamat), mereset combo box prodi, mereset radio button ke "Mahasiswa", menghapus centang setuju, me-reset label counter digit, dan mengembalikan fokus keyboard langsung ke isian NPM. |
| **Tombol Daftar** (`daftarButton`) | Action: `actionPerformed` | Memvalidasi kelengkapan isian wajib (NPM dan Nama), lalu menampilkan kotak dialog ringkasan seluruh data pendaftaran menggunakan `JOptionPane.showMessageDialog`. |
| **Isian NPM** (`npmField`) | Key: `keyReleased` | Membaca panjang karakter yang sedang diketikkan pengguna secara real-time dan menampilkannya pada label kecil di samping isian (misalnya `"8 dari 10 digit"`). |
| **Form** (`FormAnggotaPerpustakaan`) | Window: `windowClosing` | Menampilkan dialog konfirmasi penutupan aplikasi menggunakan `JOptionPane.showConfirmDialog`. Jendela hanya akan ditutup (`dispose()`) jika pengguna memilih opsi "Yes". Didukung dengan pengaturan properti `defaultCloseOperation = DO_NOTHING_ON_CLOSE`. |

---

## Tabel Pengamatan Praktikum 2 (PengamatEvent)

Berikut adalah hasil pencatatan log event pada formulir `PengamatEvent` berdasarkan 6 tindakan pengguna:

| No | Tindakan | Event yang Tercatat |
|:---:|---|---|
| 1 | Arahkan kursor ke tombol, lalu jauhkan | `MouseEvent` kursor masuk ke tombol, kemudian `MouseEvent` kursor keluar dari tombol saat dijauhkan. |
| 2 | Klik tombol "Klik saya" | `MouseEvent` kursor masuk ke tombol saat mengarahkan, diikuti `ActionEvent` tombol diklik saat mouse ditekan dan dilepas pada tombol. |
| 3 | Centang lalu hapus centang "Aktifkan fitur" | `ItemEvent` kotak dicentang saat pertama kali diklik, lalu `ItemEvent` centang dihapus saat diklik kembali. |
| 4 | Ganti pilihan dari "Pagi" menjadi "Malam" | `ItemEvent` memilih Malam. *(Catatan: Karena disaring dengan pengecekan `evt.getStateChange() == ItemEvent.SELECTED`, yang tercatat hanya aksi pemilihan item baru).* |
| 5 | Klik isian, lalu ketik kata `"abc"` | Saat isian diklik muncul `FocusEvent` isian mendapat fokus. Kemudian saat mengetik huruf berturut-turut muncul: `KeyEvent` mengetik huruf a, `KeyEvent` mengetik huruf b, dan `KeyEvent` mengetik huruf c. |
| 6 | Klik tombol setelah mengetik di isian | `FocusEvent` isian kehilangan fokus (fokus berpindah), diikuti `MouseEvent` kursor masuk ke tombol, dan `ActionEvent` tombol diklik. |

---

## Jawaban Eksperimen Praktikum 3 & Praktikum 4

### Eksperimen Praktikum 3: Konversi Suhu
1. **Pertanyaan:** Hapus pemeriksaan `if (evt.getStateChange() == ItemEvent.SELECTED)` dan tambahkan `System.out.println("item berubah");` di dalam event handler. Berapa kali pesan tercetak setiap pilihan diganti?  
   **Jawaban:** Pesan tercetak sebanyak **2 kali** setiap kali pilihan combo box diganti. Hal ini karena `ItemListener` pada `JComboBox` melaporkan dua perubahan state secara berurutan: pertama untuk item lama yang dilepas (`DESELECTED`), dan kedua untuk item baru yang dipilih (`SELECTED`). Oleh sebab itu, kondisi `ItemEvent.SELECTED` wajib disertakan agar aksi kalkulasi suhu hanya dieksekusi satu kali.

2. **Pertanyaan:** Di jendela Properties bagian Events, kosongkan event `keyReleased` pada `celsiusField`, lalu buat event `keyPressed` yang memanggil `konversi()`. Mengapa hasil yang tampil terlambat satu karakter?  
   **Jawaban:** Event `keyPressed` ditembakkan tepat saat tombol ditekan ke bawah, yaitu **sebelum** karakter baru selesai disisipkan ke dalam model data dokumen (`Document`) milik textfield. Akibatnya, saat `celsiusField.getText()` dipanggil, teks yang terbaca masih teks lama. Sebaliknya, event `keyReleased` ditembakkan setelah tombol keyboard dilepas dan karakter baru sudah selesai masuk ke dalam textfield, sehingga teks yang dibaca selalu up-to-date.

3. **Pertanyaan:** Hapus blok `try` dan `catch` di method `konversi`, lalu ketik huruf pada isian. Apa yang muncul di jendela Output?  
   **Jawaban:** Pada jendela Output muncul pesan exception `java.lang.NumberFormatException: For input string: "..."` yang berasal dari pemanggilan `Double.parseDouble()`. Karena exception tidak ditangani (*uncaught*), eksekusi method `konversi()` terhenti seketika dan antarmuka tidak memberikan respons atau umpan balik yang ramah kepada pengguna.

---

### Eksperimen Praktikum 4: Kalkulator Sederhana
1. **Pertanyaan:** Buat event `actionPerformed` untuk `kaliButton` melalui Events tanpa menghapus lambda di konstruktor, lalu tambahkan `System.out.println("kali");` di kedua tempat. Berapa kali pesan tercetak? Mengapa satu tombol sebaiknya hanya memakai satu cara?  
   **Jawaban:** Pesan tercetak sebanyak **2 kali** setiap kali tombol diklik. Hal ini terjadi karena Swing mengadopsi model *multicast event*, di mana satu sumber event dapat mendaftarkan banyak listener sekaligus. Satu tombol sebaiknya hanya memakai satu cara pendaftaran agar tidak terjadi duplikasi eksekusi proses bisnis yang dapat memboroskan sumber daya sistem dan berpotensi menimbulkan inkonsistensi data atau efek samping yang tidak diinginkan.

2. **Pertanyaan:** Tambahkan tombol `%` untuk sisa bagi (modulus). Apa saja yang diubah di tab Design dan di tab Source?  
   **Jawaban:**  
   - **Tab Design:** Mengubah layout `operatorPanel` dari 4 kolom menjadi 5 kolom (`GridLayout` rows 1, cols 5, hgap 8), kemudian menyeret satu tombol `JButton` baru dengan teks `%` (misal `modulusButton`).  
   - **Tab Source:**  
     - Mendaftarkan listener untuk tombol modulus: `modulusButton.addActionListener(e -> hitung('%'));`.  
     - Pada method `hitung(char operator)`, memperbarui validasi pembagi: `if ((operator == '/' || operator == '%') && b == 0)`.  
     - Menambahkan cabang baru pada switch expression: `case '%' -> a % b;`.

3. **Pertanyaan:** Tekan tombol Hapus. Mengapa kursor langsung berada di isian pertama?  
   **Jawaban:** Karena di dalam method `hapus(ActionEvent e)` terdapat pemanggilan instruksi `angka1Field.requestFocusInWindow();`. Pemanggilan ini memerintahkan sistem manajemen fokus antarmuka Swing untuk mengalihkan fokus kursor keyboard secara otomatis ke komponen `angka1Field`.

---

## Jawaban Pertanyaan Refleksi

1. **Jelaskan peran sumber event, object event, dan listener dengan contoh dari kode Anda.**
   - **Sumber Event (*Event Source*):** Komponen GUI tempat interaksi fisik pengguna berlangsung. Contoh dalam kode: `klikButton` (`JButton`), `satuanCombo` (`JComboBox`), dan `npmField` (`JTextField`).
   - **Objek Event (*Event Object*):** Objek pembawa data yang merangkum informasi detail tentang kejadian yang baru saja terjadi. Contoh dalam kode: `ActionEvent evt` (membawa informasi aksi tombol), `ItemEvent evt` (membawa info item yang dipilih dan status perubahannya), dan `KeyEvent evt` (membawa karakter tombol melalui `evt.getKeyChar()`).
   - **Listener (*Event Listener*):** Objek penerima yang bertugas mendengarkan event dan mendefinisikan tanggapan yang harus dijalankan. Contoh dalam kode: antarmuka `ActionListener` dengan method `actionPerformed()` atau `ItemListener` dengan method `itemStateChanged()`.

2. **Mengapa `KeyListener` tidak dapat ditulis dengan lambda, dan bagaimana NetBeans mengatasinya saat Anda memilih event `keyReleased`?**
   - Lambda expression di Java hanya dapat diterapkan pada *Functional Interface* (antarmuka yang hanya memiliki tepat 1 method abstrak / SAM). Antarmuka `KeyListener` memiliki 3 method abstrak (`keyPressed`, `keyReleased`, `keyTyped`), sehingga tidak memenuhi syarat antarmuka fungsional.
   - NetBeans mengatasinya dengan menggunakan kelas adapter `java.awt.event.KeyAdapter`. Kelas adapter ini telah menyediakan implementasi rintisan kosong untuk semua metode `KeyListener`. Ketika kita memilih event `keyReleased` di GUI Builder, NetBeans secara otomatis membentuk *anonymous inner class* turunan dari `KeyAdapter` yang hanya meng-override method `keyReleased(KeyEvent evt)`.

3. **Apa keuntungan memisahkan `KonverterSuhu` dari form `KonversiSuhu`?**
   - **Pemisahan Tanggung Jawab (*Separation of Concerns*):** Kode logika perhitungan matematika terisolasi bersih dari kode tata letak dan tampilan grafis.
   - **Dapat Digunakan Kembali (*Reusability*):** Kelas `KonverterSuhu` dapat dipakai ulang di tempat lain (misalnya pada antarmuka konsol, servlet, atau form lainnya) tanpa perlu membawa komponen Swing.
   - **Mudah Diuji (*Testability*):** Logika konversi dapat diuji secara otomatis menggunakan *Unit Testing* (JUnit) secara independen tanpa memerlukan inisialisasi window grafis.

4. **Mengapa aksi pada `itemStateChanged` perlu memeriksa `ItemEvent.SELECTED`?**
   - Setiap pergantian item pada `JComboBox` memicu dua kali event berurutan: satu event dengan state `ItemEvent.DESELECTED` (item lama yang ditinggalkan) dan satu event dengan state `ItemEvent.SELECTED` (item baru yang dipilih). Tanpa penyaringan kondisi `evt.getStateChange() == ItemEvent.SELECTED`, setiap kode di dalam event handler akan dieksekusi sebanyak dua kali.

5. **Kapan Anda memakai event handler dari GUI Builder, lambda, atau method reference?**
   - **Event Handler GUI Builder:** Digunakan sebagai standar utama dalam pengembangan `JFrame Form` NetBeans, terutama saat ingin menghubungkan event secara visual dan terdokumentasi rapi di file `.form`.
   - **Lambda Expression:** Dipakai ketika aksi berukuran ringkas (1 baris), menargetkan *functional interface* (seperti `ActionListener`), dan ditulis langsung setelah `initComponents()` di konstruktor.
   - **Method Reference (`this::namaMethod`):** Dipakai ketika aksi sudah dienkapsulasi menjadi method mandiri yang memiliki parameter dan return type yang sesuai dengan method listener, sehingga kode inisialisasi di konstruktor menjadi sangat bersih dan deklaratif.

---

## Tangkapan Layar (Screenshots)

Berikut adalah bukti tangkapan layar pengujian event handling pada aplikasi `FormAnggotaPerpustakaan`:

### 1. Tab Properties Events Tombol Daftar (NetBeans GUI Builder)
Menunjukkan event `actionPerformed` pada tombol Daftar yang terhubung ke event handler `daftarButtonActionPerformed`:

![Properties Events Tombol Daftar](Screenshot%20Action_performe.png)

### 2. Dialog Ringkasan Data Pendaftaran (JOptionPane)
Menampilkan umpan balik ringkasan seluruh data yang diinputkan pengguna saat tombol Daftar diklik:

![Dialog Ringkasan Data](Screenshot%20FormAnggotaPerpustakaan.png)

### 3. Dialog Konfirmasi Keluar (JOptionPane)
Menampilkan dialog konfirmasi saat pengguna menekan tombol tutup [X] jendela, didukung pengaturan `defaultCloseOperation = DO_NOTHING_ON_CLOSE`:

![Dialog Konfirmasi Keluar](Screenshot%20Keluar_Aplikasi.png)

---

## Struktur Berkas Proyek

```text
p05-event-handling/
├── Modul_P05_Event_Handling.pdf
├── pom.xml
├── README.md
└── src/
    └── main/
        └── java/
            └── id/
                └── ac/
                    └── uniska/
                        └── pbo2/
                            └── p05/
                                ├── FormAnggotaPerpustakaan.form
                                ├── FormAnggotaPerpustakaan.java
                                ├── KalkulatorSederhana.form
                                ├── KalkulatorSederhana.java
                                ├── KonversiSuhu.form
                                ├── KonversiSuhu.java
                                ├── KonverterSuhu.java
                                ├── PengamatEvent.form
                                └── PengamatEvent.java
```

---

## Petunjuk Menjalankan Program

Proyek ini menggunakan build tool Apache Maven. Pastikan JDK 21+ sudah terpasang.

### 1. Kompilasi Proyek
```bash
mvn clean compile
```

### 2. Menjalankan Form Tertentu
Untuk menjalankan form tertentu menggunakan `exec:java`:

- **Pengamat Event:**
  ```bash
  mvn exec:java -Dexec.mainClass="id.ac.uniska.pbo2.p05.PengamatEvent"
  ```
- **Konversi Suhu:**
  ```bash
  mvn exec:java -Dexec.mainClass="id.ac.uniska.pbo2.p05.KonversiSuhu"
  ```
- **Kalkulator Sederhana:**
  ```bash
  mvn exec:java -Dexec.mainClass="id.ac.uniska.pbo2.p05.KalkulatorSederhana"
  ```
- **Form Anggota Perpustakaan:**
  ```bash
  mvn exec:java -Dexec.mainClass="id.ac.uniska.pbo2.p05.FormAnggotaPerpustakaan"
  ```

---

## Deklarasi Penggunaan AI
Pengembangan tugas praktikum ini dibantu oleh AI Asisten Koding (Antigravity) untuk keperluan:
- Pembacaan dan perumusan intisari modul praktikum P05 Event Handling.
- Pembuatan struktur awal berkas form NetBeans XML (`.form`) dan kode Java Swing.
- Penyusunan dokumentasi komprehensif pada `README.md` mencakup tabel pengamatan, analisis eksperimen, dan jawaban pertanyaan refleksi.
- Seluruh logika, struktur komponen, event handling, serta verifikasi build telah diuji dan divalidasi kesesuaiannya dengan spesifikasi modul perkuliahan.

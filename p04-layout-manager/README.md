# Praktikum PBO 2 - Pertemuan 4: Layout Manager dan Desain Form

**Mata Kuliah:** Pemrograman Berbasis Objek 2 (TIF1508)  
**Program Studi:** S1 Teknik Informatika - FTI UNISKA Banjarmasin  
**NPM:** 2410010513  
**Package:** `id.ac.uniska.pbo2.p04`  

---

## A. Ringkasan Proyek

Proyek ini bertujuan untuk mempelajari dan mengimplementasikan **Layout Manager** dalam Java Swing, beralih dari *Free Design* (GroupLayout kaku) ke penyusunan antarmuka terstruktur dengan pendekatan **Layout Bersarang (Nested Layout)** dan **GridBagLayout**, serta dipadukan dengan pustaka tampilan modern **FlatLaf**.

Proyek ini mencakup tiga form utama:
1. **`GaleriLayout.java` / `.form`**: Demonstrasi interaktif 5 layout manager (`BorderLayout`, `FlowLayout`, `GridLayout`, `BoxLayout`, dan panel Gabungan) di dalam `JTabbedPane`.
2. **`FormBiodata.java` / `.form`**: Form isian biodata mahasiswa yang menerapkan layout bersarang (`BorderLayout` + `GridBagLayout`) dan styling modern FlatLaf.
3. **`FormAnggotaPerpustakaan.java` / `.form`**: Studio desain pendaftaran anggota perpustakaan (lanjutan studi kasus Perpustakaan Mini) yang memenuhi 8 standar prinsip desain antarmuka responsif.

---

## B. Studio Desain: Form Anggota Perpustakaan

### Tahap 1 & 2: Tabel Rancangan Layout

| Bagian | Panel | Layout | Empty Border (T, L, B, R) | Deskripsi Komponen & Peran |
| :--- | :--- | :--- | :--- | :--- |
| **Jendela Utama** | `JFrame` | `BorderLayout` | - | Kerangka utama jendela form. |
| **Header (North)** | `headerPanel` | `BoxLayout` (Y_AXIS) | 16, 16, 8, 16 | Berisi `judulLabel` (h2) dan `keteranganLabel` (disabled foreground). |
| **Pembungkus (Center)** | `isiPanel` | `BorderLayout` | - | Wadah perantara agar `formPanel` tetap berada di posisi atas saat jendela ditinggikan. |
| **Isi Form (North di `isiPanel`)** | `formPanel` | `GridBagLayout` | 8, 16, 8, 16 | Kisi fleksibel untuk seluruh label, field input, radio button, scroll pane alamat, dan checkbox persetujuan. |
| **Tombol Aksi (South)** | `tombolPanel` | `FlowLayout` (Right) | 8, 16, 16, 16 | Berisi tombol `Batal` dan tombol default `Daftar` rata kanan. |

### Detail GridBagLayout pada `formPanel`:

| Komponen | Baris (Grid Y) | Kolom (Grid X) | Grid Width | Fill | Anchor | Weight X | Insets (T, L, B, R) |
| :--- | :---: | :---: | :---: | :---: | :---: | :---: | :---: |
| `dataAnggotaLabel` (h4) | 0 | 0 | 2 | None | West | 0.0 | 0, 0, 6, 0 |
| `npmLabel` & `npmField` | 1 | 0 & 1 | 1 | Horizontal (isian) | West & Center | 0.0 & 1.0 | Label (6, 0, 6, 12), Field (4, 0, 4, 0) |
| `namaLabel` & `namaField` | 2 | 0 & 1 | 1 | Horizontal (isian) | West & Center | 0.0 & 1.0 | Label (6, 0, 6, 12), Field (4, 0, 4, 0) |
| `prodiLabel` & `prodiCombo` | 3 | 0 & 1 | 1 | Horizontal (isian) | West & Center | 0.0 & 1.0 | Label (6, 0, 6, 12), Field (4, 0, 4, 0) |
| `jenisAnggotaLabel` & Radio Group | 4 | 0 & 1 | 1 | Horizontal (isian) | West & Center | 0.0 & 1.0 | Label (6, 0, 6, 12), Radio (4, 0, 4, 0) |
| `kontakLabel` (h4) | 5 | 0 | 2 | None | West | 0.0 | 16, 0, 6, 0 |
| `emailLabel` & `emailField` | 6 | 0 & 1 | 1 | Horizontal (isian) | West & Center | 0.0 & 1.0 | Label (6, 0, 6, 12), Field (4, 0, 4, 0) |
| `hpLabel` & `hpField` | 7 | 0 & 1 | 1 | Horizontal (isian) | West & Center | 0.0 & 1.0 | Label (6, 0, 6, 12), Field (4, 0, 4, 0) |
| `alamatLabel` & `alamatScrollPane` | 8 | 0 & 1 | 1 | Horizontal (isian) | Northwest & Center | 0.0 & 1.0 | Label (6, 0, 6, 12), Area (4, 0, 4, 0) |
| `setujuCheckBox` | 9 | 1 | 1 | Horizontal | West | 1.0 | 10, 0, 6, 0 |

---

## C. Jawaban Eksperimen Modul

### 1. Eksperimen Praktikum 2 (Galeri Layout Manager)
1. **Pada `flowPanel`, ubah Alignment layout menjadi Center, lalu kecilkan jendela. Apa yang berubah? Baris mana di `initComponents()` yang ikut berubah?**
   - **Hasil Perubahan Tampilan:** Deretan tombol berpindah ke posisi tengah horizontal. Saat jendela diperkecil dan lebar tidak mencukupi, tombol yang terdorong ke baris kedua akan tetap berada di tengah (center-aligned).
   - **Baris Kode yang Berubah:** Pada baris inisialisasi layout `flowPanel`, parameter alignment berubah dari `java.awt.FlowLayout.LEFT` menjadi:
     ```java
     flowPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 8, 8));
     ```
2. **Pada `gridPanel`, ubah Rows menjadi 0 dan Columns menjadi 2. Berapa baris yang terbentuk dan mengapa?**
   - **Hasil:** Terbentuk **5 baris** (4 baris berisi 2 tombol dan baris terakhir berisi 1 tombol).
   - **Alasan:** Pada `GridLayout`, jika salah satu nilai `Rows` atau `Columns` diset ke 0, Java Swing akan menghitung dimensi dinamis berdasarkan nilai yang bukan 0. Dengan `Columns = 2` dan total 9 tombol, jumlah baris dihitung melalui rumus matematika pembulatan ke atas: $\lceil 9 / 2 \rceil = 5$ baris.
3. **Pada `boxPanel`, isi properti `maximumSize` keempat tombol dengan lebar 32767 dan tinggi sama dengan tinggi tombol. Bagaimana lebar tombol berubah? Tambahkan juga Vertical Strut dari kategori Swing Fillers di antara tombol.**
   - **Hasil:** Tombol otomatis merenggang melebar penuh mengikuti lebar kontainer `boxPanel` (tidak lagi sebatas teks tombol), karena lebar maksimumnya diset ke batas terbesar (`32767`).
   - **Penggunaan Vertical Strut:** Menambahkan `Box.createVerticalStrut(n)` di antara tombol berfungsi menyisipkan ruang kosong vertikal yang kaku (fixed height), sehingga tombol-tombol vertikal tidak saling menempel rapat.

---

### 2. Eksperimen Praktikum 4 (Form Biodata)
1. **Ubah `Weight X` pada `namaField` menjadi `0.0`, lalu lebarkan jendela. Apa yang terjadi pada isian?**
   - **Hasil:** Komponen `namaField` tidak ikut bertambah lebar saat jendela diperlebar, melainkan tetap pada ukuran alaminya (preferred size). Kolom isian akan tampak menggantung dan menyisakan ruang kosong besar di sebelah kanan.
   - **Kesimpulan:** `Weight X = 1.0` wajib diberikan pada kolom isian agar menyerap sisa lebar kontainer (*horizontal expansion*).
2. **Ubah Direction `formPanel` di dalam `isiPanel` dari `North` menjadi `Center`, lalu tinggikan jendela. Apa perbedaannya?**
   - **Hasil:** Saat diatur ke `Center`, seluruh formulir `formPanel` akan melayang berada di tengah-tengah tinggi jendela (*vertically centered*). Sedangkan jika diletakkan di `North`, seluruh baris form tetap kokoh di posisi atas dan ruang kosong tambahan berada di bagian bawah form, yang merupakan perilaku form desktop yang ergonomis dan standar.
3. **Hapus baris `setMinimumSize(getSize())` di konstruktor, lalu kecilkan jendela sekecil mungkin. Bagian mana yang rusak lebih dulu?**
   - **Hasil:** Teks label dan input field akan saling bertumpuk atau terpotong (*clipped*), tombol di bagian bawah menghilang dari area pandang, dan judul terpotong menjadi tanda elipsis (`...`).
   - **Fungsi `setMinimumSize(getSize())`:** Memastikan pengguna tidak dapat memperkecil jendela di bawah ukuran minimum komponen yang dibutuhkan, menjaga form tidak pernah rusak tampilannya.

---

## D. Jawaban Pertanyaan Refleksi

### 1. Mengapa Null Layout dan Absolute Layout sebaiknya dihindari?
- **Alasan Teknis & Desain:**
  1. Keduanya menggunakan koordinat piksel kaku (*hardcoded pixel coordinates*). Saat aplikasi dijalankan pada sistem operasi berbeda, monitor resolusi tinggi (High-DPI), atau faktor penskalaan (125%, 150%), font teks dapat membesar sehingga terpotong atau tumpang tindih.
  2. Jendela tidak responsif saat pengguna memperbesar, memperkecil, atau memaksimalkan (*maximize*) ukuran jendela.
  3. Absolute Layout NetBeans membutuhkan pustaka tambahan eksternal (`AbsoluteLayout.jar`), yang membuat kompilasi dan distribusi aplikasi di mesin lain rawan gagal bila dependensi tidak tersedia.

### 2. Jelaskan perbedaan perilaku FlowLayout dan GridLayout saat jendela dilebarkan.
- **`FlowLayout`:** Komponen mempertahankan ukuran aslinya (*preferred size*). Saat jendela dilebarkan, komponen yang tadinya melipat ke baris berikutnya akan naik kembali berderet horizontal ke kanan.
- **`GridLayout`:** Komponen dipaksa mengisi kisi sel yang sama besar. Saat jendela dilebarkan, setiap sel membesar secara proporsional sehingga ukuran tombol atau komponen di dalamnya ikut melebar dan meregang memenuhi ukuran sel.

### 3. Apa fungsi Weight X dan Fill pada constraint GridBagLayout?
- **`Weight X`:** Menentukan bagaimana sisa ruang horizontal didistribusikan saat kontainer membesar. `Weight X = 0.0` berarti kolom tidak mendapatkan jatah sisa ruang (ukuran tetap), sedangkan `Weight X = 1.0` (pada kolom isian) menyerap seluruh sisa ruang horizontal.
- **`Fill`:** Menentukan bagaimana komponen mengisi area sel tempat ia diletakkan. `Fill = NONE` (nilai 0) membuat ukuran komponen sesuai aslinya, sedangkan `Fill = HORIZONTAL` (nilai 2) meregangkan komponen hingga memenuhi seluruh lebar selnya.

### 4. Mengapa `formPanel` dibungkus `isiPanel` dan diletakkan di North?
- `isiPanel` bertindak sebagai peredam ekspansi vertikal. Karena `isiPanel` berada di `BorderLayout.CENTER` dari JFrame, ia menyerap sisa ruang tinggi vertikal. Dengan meletakkan `formPanel` di posisi `North` di dalam `isiPanel`, baris form selalu tertambat rapi di bagian atas, mencegah baris formulir terdorong renggang atau melayang ke tengah jendela saat jendela ditinggikan oleh pengguna.

### 5. Sebutkan dua prinsip desain yang Anda terapkan pada Form Anggota Perpustakaan dan tunjukkan buktinya.
1. **Prinsip Keselarasan (*Alignment*):**
   - *Bukti:* Seluruh label di kolom kiri (`Grid X = 0`) menggunakan `Anchor = WEST` dengan garis tepi kiri yang sejajar lurus. Seluruh field isian di kolom kanan (`Grid X = 1`) membentang lurus dengan `Fill = HORIZONTAL` dan `Weight X = 1.0`.
2. **Prinsip Kedekatan (*Proximity*) & Pengelompokan Logis:**
   - *Bukti:* Komponen dikelompokkan secara tematik dengan pemisah sub-judul berjarak konsisten: bagian **Data Anggota** (NPM, Nama, Prodi, Jenis Anggota), bagian **Kontak** (diberi jarak atas lebih lapang `insetsTop = 16px` untuk memisahkan konteks data), dan bagian **Persetujuan**. Tombol aksi utama diletakkan di pojok kanan bawah sebagai penutup alur kerja pengguna (*logical reading flow*).

---

## E. Lembar Tahap 4: Saling Menilai (Checklist Desain)

| No | Kriteria Pemeriksaan | Status | Keterangan Evaluasi |
| :---: | :--- | :---: | :--- |
| 1 | Label singkat, jelas, dan sejajar | **Ya** | Seluruh label rata kiri (*West*) dan teks to-the-point. |
| 2 | Isian dikelompokkan dengan judul bagian | **Ya** | Diberi sub-header tipografi h4 (`Data Anggota` dan `Kontak`). |
| 3 | Placeholder dipakai untuk isian yang punya format | **Ya** | Placeholder diterapkan pada NPM, Email, dan Nomor HP. |
| 4 | Komponen sesuai jenis data | **Ya** | NPM/Nama (TextField), Prodi (ComboBox), Jenis Anggota (RadioGroup), Alamat (TextArea), Persetujuan (CheckBox). |
| 5 | Tombol utama menonjol dan berada di kanan bawah | **Ya** | Tombol `Daftar` menjadi *default button* dan berada di kanan bawah. |
| 6 | Jarak antar komponen konsisten | **Ya** | Konsisten menggunakan insets 4–16 piksel dan Empty Border panel. |
| 7 | Form tetap rapi saat jendela dilebarkan & ditinggikan | **Ya** | Input field memanjang responsif dan tetap berada di atas. |
| 8 | Tampilan dibuat dengan JFrame Form dan layout manager | **Ya** | Menggunakan `BorderLayout` & `GridBagLayout` murni dengan file `.form`. |

**Saran Perbaikan Rekan:**  
> *"Penyusunan form sudah sangat rapi dan konsisten dengan FlatLaf. Pertahankan pengaturan padding/insets agar teks tidak menempel pada batas panel saat dijalankan di berbagai resolusi layar."*

---

## F. Cara Menjalankan Aplikasi

Pastikan berada pada folder `p04-layout-manager`.

### 1. Menjalankan Galeri Layout (Praktikum 2):
```powershell
& "C:\Program Files\Apache NetBeans\java\maven\bin\mvn.cmd" compile exec:java -Dexec.mainClass="id.ac.uniska.pbo2.p04.GaleriLayout"
```

### 2. Menjalankan Form Biodata (Praktikum 3 & 4):
```powershell
& "C:\Program Files\Apache NetBeans\java\maven\bin\mvn.cmd" compile exec:java -Dexec.mainClass="id.ac.uniska.pbo2.p04.FormBiodata"
```

### 3. Menjalankan Form Anggota Perpustakaan (Studio Desain):
```powershell
& "C:\Program Files\Apache NetBeans\java\maven\bin\mvn.cmd" compile exec:java -Dexec.mainClass="id.ac.uniska.pbo2.p04.FormAnggotaPerpustakaan"
```

---

## G. Deklarasi Penggunaan AI

Sesuai dengan ketentuan akademik pada Modul Praktikum PBO 2 Pertemuan 4:
- **Alat AI yang Digunakan:** Antigravity AI Coding Assistant (Google DeepMind).
- **Tujuan Penggunaan:**
  1. Membantu analisis struktur rancangan Layout Manager dan constraint `GridBagLayout`.
  2. Menyusun file pasangan XML `.form` yang kompatibel penuh dengan Apache NetBeans GUI Builder.
  3. Membantu perumusan penjelasan analisis eksperimen dan jawaban refleksi secara mendalam dan komprehensif.

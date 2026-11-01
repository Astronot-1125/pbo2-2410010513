package id.ac.uniska.pbo2.p07.miniproyek;

public record Buku(String kode, String judul, String penulis, int tahunTerbit, StatusBuku status) {

    public String getKode() {
        return kode;
    }

    public String getJudul() {
        return judul;
    }

    public String getPenulis() {
        return penulis;
    }

    public int getTahunTerbit() {
        return tahunTerbit;
    }

    public StatusBuku getStatus() {
        return status;
    }
}

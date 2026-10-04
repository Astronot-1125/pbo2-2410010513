package id.ac.uniska.pbo2.p02;

/**
 * Buku dapat dipinjam selama 7 hari dengan denda Rp1000 per hari keterlambatan.
 */
public class Buku extends Koleksi {
    private final String penulis;

    public Buku(String kode, String judul, int tahunTerbit, String penulis) {
        super(kode, judul, tahunTerbit);
        this.penulis = penulis;
    }

    public String getPenulis() {
        return penulis;
    }

    @Override
    public int batasHariPinjam() {
        return 7;
    }

    @Override
    public long hitungDenda(int hariTerlambat) {
        return hariTerlambat * 1000L;
    }

    @Override
    public String keterangan() {
        return "Buku karya " + penulis;
    }
}

package id.ac.uniska.pbo2.p01;

/**
 * Program pertama PBO 2 untuk memastikan JDK, NetBeans, dan Maven siap digunakan.
 */
public class HaloPBO2 {

    public static void main(String[] args) {
        String nama = "Muhammad Azka Arota Faslah";
        System.out.println("Halo, " + nama + "!");
        System.out.println("Selamat datang di Pemrograman Berbasis Objek 2.");
        System.out.println();
        // Informasi lingkungan Java yang sedang digunakan
        System.out.println("Versi Java     : " + System.getProperty("java.version"));
        System.out.println("Vendor JDK     : " + System.getProperty("java.vendor"));
        System.out.println("Sistem operasi : " + System.getProperty("os.name"));
    }
}

public class Pelanggan {
    private String idPelanggan;
    private String nama;
    private String nomorTelepon;
    private String alamat;

    public Pelanggan(String idPelanggan, String nama, String nomorTelepon, String alamat) {
        this.idPelanggan = idPelanggan;
        this.nama = nama;
        this.nomorTelepon = nomorTelepon;
        this.alamat = alamat;
    }

    public String getIdPelanggan() {
        return idPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public void showDetails() {
        System.out.println("ID: " + idPelanggan + ", Nama: " + nama + ", Telepon: " + nomorTelepon + ", Alamat: " + alamat);
    }

}

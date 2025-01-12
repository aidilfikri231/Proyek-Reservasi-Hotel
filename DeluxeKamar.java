public class DeluxeKamar extends Kamar {
    private boolean termasukSarapan;
    public DeluxeKamar(String nomorKamar, double hargaPerMalam, boolean termasukSarapan) {
        super(nomorKamar, "Deluxe", hargaPerMalam);
    }
    public void showDetails() {
        System.out.println("Kamar Deluxe - Nomor: " + getNomorKamar() +
                ", Harga: " + getHargaPerMalam() +
                ", Termasuk Sarapan: " + termasukSarapan);
    }

}

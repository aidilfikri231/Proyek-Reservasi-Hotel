public class StandarKamar extends Kamar {
    public StandarKamar(String nomorKamar, double hargaPerMalam) {
        super(nomorKamar, "Standar", hargaPerMalam);
    }
    public void showDetails() {
        System.out.println("Kamar Deluxe - Nomor: " + getNomorKamar() +
                ", Harga: " + getHargaPerMalam());
    }
}

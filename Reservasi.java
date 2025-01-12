import java.time.LocalDate;

public class Reservasi extends ReservasiSystem {
    Pelanggan pelanggan;
    Kamar kamar;
    LocalDate checkIn;
    LocalDate checkOut;
    private double totalBayar;

    public Reservasi(Pelanggan pelanggan, Kamar kamar, LocalDate checkIn, LocalDate checkOut) {
        this.pelanggan = pelanggan;
        this.kamar = kamar;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalBayar = calculateTotalBayar();
    }

    public Pelanggan getPelanggan() {
        return pelanggan;
    }

    public Kamar getKamar() {
        return kamar;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }


    public LocalDate getCheckOut() {
        return checkOut;
    }

    public double getTotalBayar() {
        return totalBayar;
    }

    private double calculateTotalBayar() {
        long days = checkOut.toEpochDay() - checkIn.toEpochDay();
        return days * kamar.getHargaPerMalam();
    }

    @Override
    public void showDetails() {
        System.out.println("Reservasi - Pelanggan: " + pelanggan.getNama() +
                ", Kamar: " + kamar.getNomorKamar() +
                ", Check-In: " + checkIn +
                ", Check-Out: " + checkOut);
    }

}

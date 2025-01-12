import javax.swing.*;

public class Pembayaran {
    private Reservasi reservasi;
    private double totalPembayaran;
    private boolean statusPembayaran;

    public Pembayaran(Reservasi reservasi) {
        this.reservasi = reservasi;
        this.totalPembayaran = hitungBiaya();
        this.statusPembayaran = false;
    }

    public double hitungBiaya() {
        long lamaMenginap = java.time.temporal.ChronoUnit.DAYS.between(reservasi.getCheckIn(), reservasi.getCheckOut());
        double hargaPerMalam = reservasi.getKamar().getHargaPerMalam();
        return hargaPerMalam * lamaMenginap;
    }

    public void bayar() {
        double jumlahBayar = 0;

        while (jumlahBayar < totalPembayaran) {
            try {
                String inputBayar = JOptionPane.showInputDialog(null,
                        "Total pembayaran: " + totalPembayaran + "\nMasukkan jumlah pembayaran:");

                if (inputBayar == null) {
                    JOptionPane.showMessageDialog(null, "Pembayaran dibatalkan.");
                    return;
                }

                jumlahBayar = Double.parseDouble(inputBayar);
                if (jumlahBayar >= totalPembayaran) {
                    statusPembayaran = true;
                    JOptionPane.showMessageDialog(null, "Pembayaran berhasil dilakukan.");
                } else {
                    JOptionPane.showMessageDialog(null,
                            "Pembayaran kurang. Silakan bayar penuh. Total masih kurang: " + (totalPembayaran - jumlahBayar));
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Input tidak valid. Masukkan angka yang valid.");
            }
        }
    }

    public void setReservasi(Reservasi reservasi) {
        this.reservasi = reservasi;
    }

    public void setTotalPembayaran(double totalPembayaran) {
        this.totalPembayaran = totalPembayaran;
    }

    public double getTotalPembayaran() {
        return totalPembayaran;
    }

    public Reservasi getReservasi() {
        return reservasi;
    }
}

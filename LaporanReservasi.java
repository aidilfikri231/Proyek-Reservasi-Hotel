import javax.swing.*;
import java.util.List;

public class LaporanReservasi implements Laporan {
    private List<Reservasi> reservasiList;

    public LaporanReservasi(List<Reservasi> reservasiList) {
        this.reservasiList = reservasiList;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();

        for (Reservasi reservasi : reservasiList) {
            report.append("ID Pelanggan: ").append(reservasi.getPelanggan().getIdPelanggan())
                    .append(", Nama: ").append(reservasi.getPelanggan().getNama())
                    .append(", Nomor Kamar: ").append(reservasi.getKamar().getNomorKamar())
                    .append(", Tanggal Check-In: ").append(reservasi.getCheckIn())
                    .append(", Tanggal Check-Out: ").append(reservasi.getCheckOut())
                    .append(", Total Pembayaran: ").append(reservasi.getTotalBayar())
                    .append("\n");
        }

        return report.toString();
    }

    public void saveReportToFile() {
        try {
            String report = generateReport();
            java.nio.file.Files.write(java.nio.file.Paths.get("laporan_reservasi.txt"), report.getBytes());
            JOptionPane.showMessageDialog(null, "Laporan reservasi berhasil disimpan.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Gagal menyimpan laporan reservasi: " + e.getMessage());
        }
    }
}

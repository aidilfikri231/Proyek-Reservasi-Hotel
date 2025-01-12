import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class LaporanKamar implements Laporan {
    private List<Kamar> kamarList;

    public LaporanKamar(List<Kamar> kamarList) {
        this.kamarList = kamarList;
    }

    public String generateReport() {
        StringBuilder report = new StringBuilder();
        report.append("Laporan Kamar:\n");
        for (Kamar kamar : kamarList) {
            report.append("Nomor Kamar: " + kamar.getNomorKamar() + ", Tipe: " + kamar.getTipeKamar()
                    + ", Harga: " + kamar.getHargaPerMalam() + "\n");
        }
        return report.toString();
    }

    public void saveReportToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("laporan_kamar.txt"))) {
            writer.write(generateReport());
            JOptionPane.showMessageDialog(null, "Laporan kamar berhasil disimpan ke laporan_kamar.txt");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Terjadi kesalahan saat menyimpan laporan: " + e.getMessage());
        }
    }
}

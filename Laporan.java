public interface Laporan {
    String generateReport();
    default void saveReportToFile() {

    }
}

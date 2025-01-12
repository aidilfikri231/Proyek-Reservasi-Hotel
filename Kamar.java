public abstract class Kamar {
    private String nomorKamar;
    private String tipeKamar;
    private double hargaPerMalam;
    private boolean available;
    private boolean TermasukSarapan;
    private boolean clearHistory;


    public Kamar(String nomorKamar, String tipeKamar, double hargaPerMalam) {
        this.nomorKamar = nomorKamar;
        this.tipeKamar = tipeKamar;
        this.hargaPerMalam = hargaPerMalam;
        this.available = true;
    }

    public String getNomorKamar() {
        return nomorKamar;
    }

    public String getTipeKamar() {
        return tipeKamar;
    }

    public double getHargaPerMalam() {
        return hargaPerMalam;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setTermasukSarapan(boolean termasukSarapan) {
        TermasukSarapan = termasukSarapan;
    }

    public boolean isTermasukSarapan() {
        return TermasukSarapan;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
    public abstract void showDetails();

    public void clearHistory() {
        this.clearHistory = clearHistory;
    }
}

import java.util.ArrayList;
import java.util.List;

public class ManajemenKamar {
    private List<Kamar> kamarList;

    public ManajemenKamar() {
        this.kamarList = new ArrayList<>();
    }

    public void tambahKamar(Kamar kamar) {
        kamarList.add(kamar);
    }

    public Kamar cariKamar(String nomorKamar) {
        for (Kamar kamar : kamarList) {
            if (kamar.getNomorKamar().equals(nomorKamar)) {
                return kamar;
            }
        }
        return null;
    }

    public void hapusHistoriKamar(String nomorKamar) {
        Kamar kamar = cariKamar(nomorKamar);
        if (kamar != null) {
            kamar.clearHistory();
        }
    }

    public List<Kamar> getKamarList() {
        return kamarList;
    }

}

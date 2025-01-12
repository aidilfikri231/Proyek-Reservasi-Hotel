import java.util.ArrayList;
import java.util.List;

public class ManajemenPelanggan {
    private List<Pelanggan> pelangganList;

    public ManajemenPelanggan() {
        this.pelangganList = new ArrayList<>();
    }

    public void tambahPelanggan(Pelanggan pelanggan) {
        pelangganList.add(pelanggan);
    }

    public Pelanggan cariPelanggan(String idPelanggan) {
        return pelangganList.stream().filter(p -> p.getIdPelanggan().equals(idPelanggan)).findFirst().orElse(null);
    }
}

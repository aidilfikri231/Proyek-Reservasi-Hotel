import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class HotelReservasi {
    private ManajemenKamar manajemenKamar = new ManajemenKamar();
    private ManajemenPelanggan manajemenPelanggan = new ManajemenPelanggan();
    private List<Reservasi> reservasiList = new ArrayList<>();

    public HotelReservasi() {
        JFrame frame = new JFrame("Sistem Reservasi Hotel");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 1));

        JButton addKamarButton = new JButton("Tambah Kamar");
        JButton addPelangganButton = new JButton("Tambah Pelanggan");
        JButton buatReservasiButton = new JButton("Buat Reservasi");
        JButton hapusHistoriKamarButton= new JButton("Hapus Histori Kamar");
        JButton laporanButton = new JButton("Lihat Laporan");

        panel.add(addKamarButton);
        panel.add(addPelangganButton);
        panel.add(buatReservasiButton);
        panel.add(hapusHistoriKamarButton);
        panel.add(laporanButton);

        frame.add(panel);
        frame.setVisible(true);

        addKamarButton.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

                    JTextField nomorField = new JTextField();
                    JComboBox<String> tipeCombo = new JComboBox<>(new String[]{"Deluxe", "Standar"});
                    JTextField hargaField = new JTextField();
                    JCheckBox sarapanCheck = new JCheckBox("Termasuk Sarapan");

                    panel.add(new JLabel("Nomor Kamar:"));
                    panel.add(nomorField);
                    panel.add(new JLabel("Tipe Kamar:"));
                    panel.add(tipeCombo);
                    panel.add(new JLabel("Harga Per Malam:"));
                    panel.add(hargaField);
                    panel.add(new JLabel("Fasilitas Tambahan:"));
                    panel.add(sarapanCheck);

                    tipeCombo.addActionListener(event -> {
                        String tipe = (String) tipeCombo.getSelectedItem();
                        sarapanCheck.setEnabled(tipe.equalsIgnoreCase("Deluxe"));
                        if (!sarapanCheck.isEnabled()) {
                            sarapanCheck.setSelected(false);
                        }
                    });

                    int result = JOptionPane.showConfirmDialog(null, panel, "Tambah Kamar", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result == JOptionPane.OK_OPTION) {
                        try {
                            String nomor = nomorField.getText().trim();
                            if (nomor.isEmpty())
                                throw new IllegalArgumentException("Nomor kamar tidak boleh kosong.");
                            if (manajemenKamar.cariKamar(nomor) != null) {
                                throw new IllegalArgumentException("Kamar dengan nomor tersebut sudah ada.");
                            }

                            String tipe = (String) tipeCombo.getSelectedItem();
                            double harga = Double.parseDouble(hargaField.getText().trim());
                            if (harga <= 0) throw new IllegalArgumentException("Harga harus lebih dari 0.");

                            if ("Deluxe".equalsIgnoreCase(tipe)) {
                                boolean termasukSarapan = sarapanCheck.isSelected();
                                manajemenKamar.tambahKamar(new DeluxeKamar(nomor, harga, termasukSarapan));
                            } else {
                                manajemenKamar.tambahKamar(new StandarKamar(nomor, harga));
                            }

                            JOptionPane.showMessageDialog(null, "Kamar berhasil ditambahkan.");
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(null, "Harga harus berupa angka.");
                        } catch (IllegalArgumentException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
                        }
                    }
                }
        });

        addPelangganButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));

                JTextField idField = new JTextField();
                JTextField namaField = new JTextField();
                JTextField teleponField = new JTextField();
                JTextField alamatField = new JTextField();

                panel.add(new JLabel("ID Pelanggan:"));
                panel.add(idField);
                panel.add(new JLabel("Nama:"));
                panel.add(namaField);
                panel.add(new JLabel("Nomor Telepon:"));
                panel.add(teleponField);
                panel.add(new JLabel("Alamat:"));
                panel.add(alamatField);

                int result = JOptionPane.showConfirmDialog(null, panel, "Tambah Pelanggan", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (result == JOptionPane.OK_OPTION) {
                    try {
                        String id = idField.getText().trim();
                        if (id.isEmpty()) {
                            throw new IllegalArgumentException("ID Pelanggan tidak boleh kosong.");
                        }
                        if (manajemenPelanggan.cariPelanggan(id) != null) {
                            throw new IllegalArgumentException("ID Pelanggan sudah terdaftar.");
                        }

                        String nama = namaField.getText().trim();
                        if (nama.isEmpty()) {
                            throw new IllegalArgumentException("Nama tidak boleh kosong.");
                        }

                        String telepon = teleponField.getText().trim();
                        if (telepon.isEmpty()) {
                            throw new IllegalArgumentException("Nomor Telepon tidak boleh kosong.");
                        }

                        String alamat = alamatField.getText().trim();
                        if (alamat.isEmpty()) {
                            throw new IllegalArgumentException("Alamat tidak boleh kosong.");
                        }

                        manajemenPelanggan.tambahPelanggan(new Pelanggan(id, nama, telepon, alamat));
                        JOptionPane.showMessageDialog(null, "Pelanggan berhasil ditambahkan.");
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
                    }
                }
            }
        });

        buatReservasiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    JPanel panel = new JPanel(new GridLayout(6, 2));

                    panel.add(new JLabel("ID Pelanggan:"));
                    JTextField idPelangganField = new JTextField();
                    panel.add(idPelangganField);

                    panel.add(new JLabel("Nomor Kamar:"));
                    JTextField nomorKamarField = new JTextField();
                    panel.add(nomorKamarField);

                    panel.add(new JLabel("Tanggal Check-In (YYYY-MM-DD):"));
                    JTextField checkInField = new JTextField();
                    checkInField.setToolTipText("Contoh: 2025-01-12");
                    panel.add(checkInField);

                    panel.add(new JLabel("Tanggal Check-Out (YYYY-MM-DD):"));
                    JTextField checkOutField = new JTextField();
                    checkOutField.setToolTipText("Contoh: 2025-01-15");
                    panel.add(checkOutField);

                    int result = JOptionPane.showConfirmDialog(null, panel,
                            "Buat Reservasi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                    if (result != JOptionPane.OK_OPTION) {
                        JOptionPane.showMessageDialog(null, "Reservasi dibatalkan.");
                        return;
                    }

                    String idPelanggan = idPelangganField.getText().trim();
                    String nomorKamar = nomorKamarField.getText().trim();
                    LocalDate checkIn;
                    LocalDate checkOut;

                    try {
                        String tanggalCheckIn = checkInField.getText().trim();
                        String tanggalCheckOut = checkOutField.getText().trim();

                        if (!tanggalCheckIn.matches("\\d{4}-\\d{2}-\\d{2}") ||
                                !tanggalCheckOut.matches("\\d{4}-\\d{2}-\\d{2}")) {
                            JOptionPane.showMessageDialog(null, "Format tanggal harus YYYY-MM-DD.");
                            return;
                        }

                        checkIn = LocalDate.parse(tanggalCheckIn);
                        checkOut = LocalDate.parse(tanggalCheckOut);

                        if (checkOut.isBefore(checkIn) || checkOut.isEqual(checkIn)) {
                            JOptionPane.showMessageDialog(null, "Tanggal Check-Out harus setelah Check-In.");
                            return;
                        }
                    } catch (DateTimeParseException ex) {
                        JOptionPane.showMessageDialog(null, "Tanggal tidak valid. Gunakan format YYYY-MM-DD.");
                        return;
                    }

                    Pelanggan pelanggan = manajemenPelanggan.cariPelanggan(idPelanggan);
                    if (pelanggan == null) {
                        JOptionPane.showMessageDialog(null, "Pelanggan tidak ditemukan.");
                        return;
                    }

                    Kamar kamar = manajemenKamar.cariKamar(nomorKamar);
                    if (kamar == null || !kamar.isAvailable()) {
                        JOptionPane.showMessageDialog(null, "Kamar tidak tersedia.");
                        return;
                    }

                    boolean kamarTersedia = true;
                    for (Reservasi r : reservasiList) {
                        if (r.getKamar().getNomorKamar().equals(kamar.getNomorKamar())) {
                            if (!(checkOut.isBefore(r.getCheckIn()) || checkIn.isAfter(r.getCheckOut()))) {
                                kamarTersedia = false;
                                break;
                            }
                        }
                    }

                    if (!kamarTersedia) {
                        JOptionPane.showMessageDialog(null, "Kamar tidak tersedia pada tanggal yang dipilih.");
                        return;
                    }

                    Reservasi reservasi = new Reservasi(pelanggan, kamar, checkIn, checkOut);

                    Pembayaran pembayaran = new Pembayaran(reservasi);
                    int konfirmasiBayar = JOptionPane.showConfirmDialog(null,
                            "Total biaya: " + pembayaran.getTotalPembayaran() +
                                    "\nApakah Anda ingin melanjutkan pembayaran?",
                            "Konfirmasi Pembayaran",
                            JOptionPane.YES_NO_OPTION);

                    if (konfirmasiBayar == JOptionPane.YES_OPTION) {
                        pembayaran.bayar();
                        reservasiList.add(reservasi);
                        JOptionPane.showMessageDialog(null, "Reservasi berhasil dibuat.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Pembayaran dibatalkan. Reservasi tidak dibuat.");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
                }
            }
        });


        hapusHistoriKamarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nomorKamar = JOptionPane.showInputDialog("Nomor Kamar:");
                    Kamar kamar = manajemenKamar.cariKamar(nomorKamar);
                    if (kamar == null) {
                        JOptionPane.showMessageDialog(null, "Kamar tidak ditemukan.");
                        return;
                    }

                    Reservasi reservasiTerhapus = null;
                    for (Reservasi reservasi : reservasiList) {
                        if (reservasi.getKamar().getNomorKamar().equals(kamar.getNomorKamar())) {
                            reservasiTerhapus = reservasi;
                            break;
                        }
                    }

                    if (reservasiTerhapus != null) {
                        reservasiList.remove(reservasiTerhapus);
                        JOptionPane.showMessageDialog(null, "Reservasi yang terkait dengan kamar ini telah dihapus.");
                    }

                    kamar.setAvailable(true);
                    JOptionPane.showMessageDialog(null, "Histori kamar berhasil dihapus. Kamar kini kosong dan dapat ditempati.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
                }
            }
        });



        laporanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String pilihan = JOptionPane.showInputDialog("Pilih laporan (1: Kamar, 2: Reservasi):");

                    if (pilihan == null || (!pilihan.equals("1") && !pilihan.equals("2"))) {
                        JOptionPane.showMessageDialog(null, "Pilihan tidak valid.");
                        return;
                    }

                    if (pilihan.equals("1")) {
                        LaporanKamar laporanKamar = new LaporanKamar(manajemenKamar.getKamarList());
                        String report = laporanKamar.generateReport();
                        JOptionPane.showMessageDialog(null, report);

                        StringBuilder kamarDetails = new StringBuilder("Laporan Kamar:\n");
                        for (Kamar kamar : manajemenKamar.getKamarList()) {
                            kamar.showDetails();
                        }

                        int saveOption = JOptionPane.showConfirmDialog(null,
                                "Apakah Anda ingin menyimpan laporan kamar ke file?",
                                "Simpan Laporan", JOptionPane.YES_NO_OPTION);

                        if (saveOption == JOptionPane.YES_OPTION) {
                            laporanKamar.saveReportToFile();
                            JOptionPane.showMessageDialog(null, "Laporan kamar berhasil disimpan.");
                        }
                    } else if (pilihan.equals("2")) {
                        LaporanReservasi laporanReservasi = new LaporanReservasi(reservasiList);
                        String report = laporanReservasi.generateReport();
                        JOptionPane.showMessageDialog(null, report);

                        StringBuilder reservasiDetails = new StringBuilder("Laporan Reservasi:\n");
                        for (Reservasi reservasi : reservasiList) {
                            reservasi.showDetails();  // Memanggil showDetails pada setiap objek reservasi
                        }


                        int saveOption = JOptionPane.showConfirmDialog(null,
                                "Apakah Anda ingin menyimpan laporan reservasi ke file?",
                                "Simpan Laporan", JOptionPane.YES_NO_OPTION);

                        if (saveOption == JOptionPane.YES_OPTION) {
                            laporanReservasi.saveReportToFile();
                            JOptionPane.showMessageDialog(null, "Laporan reservasi berhasil disimpan.");
                        }
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Terjadi kesalahan: " + ex.getMessage());
                }
            }
        });

    }

   public static void main (String[] args){
            new HotelReservasi();
        }
    }

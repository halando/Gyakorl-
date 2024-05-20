import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;

import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MainFrame extends JFrame {
    JList<Kolcsonzok> kolcsonzokList = new JList<>();
    JList<Kolcsonzesek> kolcsonzesekList = new JList<>();
    Button visszahozva = new Button("Visszahozva");
    Button bezar = new Button("Bezár");

    public MainFrame() throws SQLException {
        createWindowAndElements();// rendereli a grafikus elemeket
        getKolcsonzok();// lekéri a kölcsönzők adatait és berakja a JList-be
        initEvents();// a kölcsönzések kiválasztását és a visszahozva gombot vezéreljük ezzel a
                     // függvénnyel

    }

    private void createWindowAndElements() {
        this.kolcsonzokList.setBounds(20, 20, 200, 500);// beállítjuk a pozicióját
        this.kolcsonzesekList.setBounds(240, 20, 500, 170); // beállítjuk a poziciót
        this.setLayout(null);// az alap pozicionálást kikapcsoljuk, azért hogy mi tudjuk megadni
        this.kolcsonzokList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));// 1px szürke keret a JList-eknek
        this.kolcsonzesekList.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));// 1px szürke keret a JList-eknek
        this.add(kolcsonzokList);// hozzáadjuk a JList-eket az ablakhoz
        this.add(kolcsonzesekList);// hozzáadjuk a JList-eket az ablakhoz

        this.visszahozva.setBounds(240, 220, 80, 20);// a gombokat is pozicionáljuk
        this.bezar.setBounds(680, 500, 60, 20);// a gombokat is pozicionáljuk
        this.add(visszahozva);// hozzáadjuk a gombokat az ablakhoz
        this.add(bezar);// hozzáadjuk a gombokat az ablakhoz

        this.visszahozva.setEnabled(false);
        bezar.addActionListener((ActionEvent e) -> {
            dispose();
        });// a bezár gombra rakunk egy eseményfigyelőt, ami megnyomásra kikapcsolja a
           // ablakot

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// ha megnyomjuk az x gombot, akkor záródjon be az ablak
        this.setSize(800, 600);// a fő ablak mérete
        this.getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));// keretet adunk neki
        this.setTitle("Könytár_GUI");// a fö ablak címét hozzáadjuk
        this.setResizable(false);// nem engedjük átméretezni
        this.setVisible(true);// a láthatóságát engedélyezzük
    }

    private void getKolcsonzok() throws SQLException {

        DefaultListModel<Kolcsonzok> kolcsonzokListModel = new DefaultListModel<>();// ez tárolja és teszi bele a
                                                                                    // JList-be az adatokat
        ArrayList<Kolcsonzok> kolcsonzok = new Service().getKolcsonzok();// a DataService-ben lekerdezzük a kölcsönzők
                                                                         // adatait a db-ből.
        for (Kolcsonzok kolcsonzokObj : kolcsonzok) {// végigmegyünk a kapott sorokon és
            kolcsonzokListModel.addElement(kolcsonzokObj);// hozzáadjuk az adatokat
        }

        kolcsonzokList.setModel(kolcsonzokListModel);// megjelenítjük a JList-ben

        kolcsonzokList.addListSelectionListener((ListSelectionEvent e) -> {// eseménykezelőt adunk minden sorhoz
                                                                           // (figyeli hogy rákattintunk)
            if (!e.getValueIsAdjusting()) {// csak akkor reagáluk ha az user befejezte a kiválasztást
                Kolcsonzok selectedKolcsonzok = kolcsonzokList.getSelectedValue();// eltároljuk a kiválasztott sor
                                                                                  // adatait...
                if (selectedKolcsonzok != null) {// ...és ha nem üres akkor ...
                    try {
                        getKolcsonzesek(selectedKolcsonzok.getId());// ...meghívjuk a függvényt ami lekérdezi a
                                                                    // kölcsönzött könyveket
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    private void getKolcsonzesek(int id) throws SQLException {// ez kéri le a db-ből a kölcsönzött könyveket
        visszahozva.setEnabled(false);// a visszahozva gombot inaktívvá tesszük hogy amíg a lekérdezés folyik ne
                                      // nyomogassák

        DefaultListModel<Kolcsonzesek> kolcsonzesekListModel = new DefaultListModel<>();// a jobb oldali a JList-be
                                                                                        // hozzáadjuk az adatokat

        ArrayList<Kolcsonzesek> kolcsonzesek = new Service().getKolcsonzesek(id);// a DataService-ben lekerdezzük a
                                                                                 // kölcsönzések adatait a db-ből.

        for (Kolcsonzesek kolcsonzesekObj : kolcsonzesek) {// végigmegyünk...
            kolcsonzesekListModel.addElement(kolcsonzesekObj);// ...és hozzáadjuk az adatokat úgy mint az előbb
        }

        kolcsonzesekList.setModel(kolcsonzesekListModel);
    }

    private void initEvents() {
        kolcsonzesekList.addListSelectionListener((ListSelectionEvent f) -> {// eseményfigyelő hozzáadása
            if (!f.getValueIsAdjusting()) {
                Kolcsonzesek selectedKolcsonzesek = kolcsonzesekList.getSelectedValue();
                if (selectedKolcsonzesek != null) {
                    System.out.println(selectedKolcsonzesek.getId());
                    visszahozva.setEnabled(true);// ha van kiválasztva könyv akkor engedélyezzük a visszahozva gombot
                }
            }
        });
        visszahozva.addActionListener((ActionEvent e) -> {// a visszahozva gomra is teszünk eseményfigyelőt
            int id = kolcsonzesekList.getSelectedValue().getId();// a kiválasztott sornak lekérjük az id-jét, ez ahhoz
                                                                 // kell hogy átadjuk a törlés metódusnak
            int kolcsonzoId = kolcsonzesekList.getSelectedValue().getKolcsonzoId();// lekérjük a kiválasztott sornak az
                                                                                   // id-jét, hogy később frissítsük a
                                                                                   // könyveket
            System.out.println("töröl " + id);
            try {
                new Service().torol(id);// töröljük a db-ben a könyvet
                getKolcsonzesek(kolcsonzoId);// és frissítjük a JList-be az adatokat, azért hogy eltűnjön a törölt elem
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        });
    }
}

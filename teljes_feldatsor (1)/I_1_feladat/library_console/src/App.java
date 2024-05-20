import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    static ArrayList<Kolcsonzok> kolcsonzokLista = new ArrayList<>();
    static ArrayList<Kolcsonzesek> kolcsonzesekLista = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        checkArgsNumber(args);
        checkFileIsExist(args);
        readFile1(args[0]);// meghívjuk a fájlt beolvasását listába
        readFile2(args[1]);// a másik fájlra is

        DataService kolcsonzokService = new DataService(); // létrehozunk egy példányt a DataService-ből
        kolcsonzokService.insertDataKolcsonzok(kolcsonzokLista);// és meghívjuk a feltöltő függvényét

        DataService kolcsonzesekService = new DataService();
        kolcsonzesekService.insertDataKolcsonzesek(kolcsonzesekLista);
    }

    private static void checkArgsNumber(String[] args) {

        if (args.length != 2) { // ha az args tömbnek nem két eleme van
            System.err.println("Hiba! Nincs két paraméter!"); // akkor hibauzenetet jelenik meg
            System.exit(1001); // majd kilép a programból
        }
    }

    private static void checkFileIsExist(String[] args) {
        File param1 = new File(args[0]);// a param1-ben tároljuk az egyik paramétert
        File param2 = new File(args[1]);// a param2-ben tároljuk a màsik paramétert
        if (!param1.exists() || !param2.exists()) { // megvizsgáljuk hogy léteznek-e
            System.err.println("Hiba! A fájl(ok) nem található(ak)!");// ha nem, akkor hibaüzenetet jelenik meg
            System.exit(1002);// majd kilép a programból
        }
    }

    private static void readFile1(String path) throws FileNotFoundException {
        File file = new File(path);// létrehozunk egy példányt a fájlból
        Scanner sc = new Scanner(file);// és átadjuk a Scanner-nek ami olvasni tudja

        sc.nextLine();// kihagyjuk a fájlból az első sort ami tartalmazza a fejlécet /pl név,
                      // születési idő, ha nincs ilyen akkor ez a sor nem kell/
        while (sc.hasNextLine()) {// addig megyünk amíg van sor a fájlban
            String line = sc.nextLine();// beolvassuk a sort
            String[] parts = line.split(";");// feldaraboljuk a sort a ";" karakterekél
            Kolcsonzok kolcsonzok = new Kolcsonzok();// létrehozunk egy Kolcsonzok példányt
            kolcsonzok.nev = parts[0];// a nevet hozzáadjuk a példányhoz
            kolcsonzok.szulIdo = parts[1];// a születési időt is
            kolcsonzokLista.add(kolcsonzok);// hozzáadjuk a kolcsonzok listához
        }
    }

    private static void readFile2(String path) throws FileNotFoundException {

        File file = new File(path);
        Scanner sc = new Scanner(file);
        sc.nextLine();
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            String[] parts = line.split(";");
            Kolcsonzesek kolcsonzesek = new Kolcsonzesek();
            kolcsonzesek.kolcsonzoId = Integer.parseInt(parts[0]);
            kolcsonzesek.iro = parts[1];
            kolcsonzesek.mufaj = parts[2];
            kolcsonzesek.cim = parts[3];
            kolcsonzesekLista.add(kolcsonzesek);
        }
    }
}

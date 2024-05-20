import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class DataService {
    private Connection getConnection() throws SQLException {
        String user = "root";
        String password = "";
        String url = "jdbc:mariadb://localhost:3306/konyvtar";
        return DriverManager.getConnection(url, user, password);
    }

    public void insertDataKolcsonzok(ArrayList<Kolcsonzok> kolcsonzokLista) {
        Connection connection = null;
        try {
            connection = getConnection();// létrehozzuk a kapcsolatot a db-vel a getConnection függvény segítségével
            String sql = "INSERT INTO kolcsonzok (nev, szulIdo) VALUES (?, ?)";//
            int szamlalo = 0;
            for (Kolcsonzok kolcsonzok : kolcsonzokLista) {// végigmegyünk a listán
                PreparedStatement preparedStatement = connection.prepareStatement(sql);// ez futtatja le az sql lekérést
                preparedStatement.setString(1, kolcsonzok.nev);// beállítjuk a feltöltendő adatokat
                preparedStatement.setString(2, kolcsonzok.szulIdo);
                preparedStatement.execute();// feltölti az adatokat a táblába
                szamlalo++;
            }
            connection.close();
            System.out.println(szamlalo + " adat rogzítve a kolcsonzok tablaban");
        } catch (SQLException e) {
            System.err.println("Hiba: " + e.getMessage());
        }
    }

    public void insertDataKolcsonzesek(ArrayList<Kolcsonzesek> kolcsonzesekLista) {
        Connection connection = null;
        try {
            connection = getConnection();
            String sql = "INSERT INTO kolcsonzesek (kolcsonzoId, iro, mufaj, cim) VALUES (?, ?, ?, ?)";
            int szamlalo = 0;
            for (Kolcsonzesek kolcsonzesek : kolcsonzesekLista) {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setInt(1, kolcsonzesek.kolcsonzoId);
                preparedStatement.setString(2, kolcsonzesek.iro);
                preparedStatement.setString(3, kolcsonzesek.mufaj);
                preparedStatement.setString(4, kolcsonzesek.cim);
                preparedStatement.execute();
                szamlalo++;
            }
            connection.close();
            System.out.println(szamlalo + " adat rögzítve a kolcsonzesek tablaban");
        } catch (SQLException e) {
            System.err.println("Hiba: " + e.getMessage());
        }
    }

}

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Service {
    ArrayList<Kolcsonzok> kolcsonzokLista = new ArrayList<>();
    ArrayList<Kolcsonzesek> kolcsonzesekLista = new ArrayList<>();
    int kolcsonzoId;

    public Service() {

    }

    private Connection getConnection() throws SQLException {
        String user = "root";
        String password = "";
        String url = "jdbc:mariadb://localhost:3306/konyvtar";
        return DriverManager.getConnection(url, user, password);
    }

    public ArrayList<Kolcsonzok> getKolcsonzok() throws SQLException {
        Connection conn = getConnection();// a kapcsolatlétrehozása

        String sql = "select * from kolcsonzok";// sql lekérdezés
        PreparedStatement stmt = conn.prepareStatement(sql);// előkészítjük a lekérdezést
        ResultSet rs = stmt.executeQuery();// eltároljuk a kapott adatokat az rs változóban
        while (rs.next()) {// végigmegyünk a kapott sorokon
            Kolcsonzok kolcsonzok = new Kolcsonzok(
                    rs.getInt("id"), // beállítjuk az adatokat
                    rs.getString("nev"),
                    rs.getString("szulIdo"));
            kolcsonzokLista.add(kolcsonzok);// és hozzáadjuk a listához
        }
        return kolcsonzokLista;
    }

    public ArrayList<Kolcsonzesek> getKolcsonzesek(int id) throws SQLException {
        Connection conn = getConnection();
        String sql = "select * from kolcsonzesek where kolcsonzoId = " + id;
        PreparedStatement stmt = conn.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Kolcsonzesek kolcsonzesek = new Kolcsonzesek(
                    rs.getInt("id"),
                    rs.getInt("kolcsonzoId"),
                    rs.getString("cim"),
                    rs.getString("iro"),
                    rs.getString("mufaj"));
            kolcsonzesekLista.add(kolcsonzesek);
        }
        return kolcsonzesekLista;
    }

    public void torol(int id) throws SQLException {
        Connection conn = getConnection();
        String sql = "delete from kolcsonzesek where id = " + id;
        PreparedStatement stmt = conn.prepareStatement(sql);
        stmt.executeUpdate();
    }
}

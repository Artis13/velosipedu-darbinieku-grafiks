package dbconnect;

import Dati.Darbinieks;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbDarbinieki extends connect {

    List<Darbinieks> darbinieks;

    public dbDarbinieki(){
        darbinieks = new ArrayList<>();
    }

    public void pievienotDarbiniekuSarakstam(int Id, String vards, String uzvards){
        this.darbinieks.add(new Darbinieks(Id,vards,uzvards));
    }
    public static void atjaunotDarbinieku(int id, String vards, String uzvards) {
        String sql = "UPDATE Darbinieki SET Vards = ? , "
                + "Uzvards = ? "
                + "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, vards);
            pstmt.setString(2, uzvards);
            pstmt.setInt(3, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Darbinieks dabutDarbinieku(int id){
        String sql = "SELECT Id,Vards , Uzvards FROM Darbinieki where Id = ?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            int Id = rs.getInt("Id");
            String vards = rs.getString("Vards");
            String uzvards = rs.getString("Uzvards");
            Darbinieks darbinieks = new Darbinieks(Id,vards,uzvards);
            conn.close();
            return darbinieks;
        }
        catch (SQLException e) {
         System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Darbinieks> darbiniekuSaraksts() {
        String sql = "SELECT Id, vards, Uzvards FROM Darbinieki";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("Id");
                String vards = rs.getString("Vards");
                String uzvards = rs.getString("Uzvards");
                pievienotDarbiniekuSarakstam(id,vards,uzvards);

            }
            conn.close();
            return this.darbinieks;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return this.darbinieks;
    }
    public static boolean darbiniekaEksistence(int id){
        String sql = "SELECT Id FROM Darbinieki";
        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){
                if (rs.getInt("Id")==id){
                    conn.close();
                    return true;
                }
            }
            conn.close();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

        return false;
    }
    public static int nakosaDarbinirkaId(){
        String sql = "SELECT Id FROM Darbinieki";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int x = 0;

            while (rs.next()){
                x = rs.getInt("Id");
            }
            x = x+1;
            return x;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public static void pievienotDarbinieku(int Id, String vards, String uzvards) {
        String sql = "INSERT INTO Darbinieki(Id, Vards, Uzvards) VALUES(?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,Id);
            pstmt.setString(2, vards);
            pstmt.setString(3, uzvards);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public static void izdzestDarbinieku(int id) {
        String sql = "DELETE FROM Darbinieki WHERE Id = ?";

        try {

            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

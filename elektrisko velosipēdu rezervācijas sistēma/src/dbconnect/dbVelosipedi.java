package dbconnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Dati.Velosipeds;

public class dbVelosipedi extends connect {
    List<Velosipeds> velosipeds;

    public dbVelosipedi(){
        velosipeds = new ArrayList<>();
    }


    public void pievienotVelosipeduSarakstam(int id, String razotajs,String krasa, String gads){
        velosipeds.add( new Velosipeds(id,razotajs,krasa,gads));
    }

    public static void atjaunotVelosipedu(int id,String razotajs, String krasa, String gads){
        String sql = "UPDATE Velosipedi SET Razotajs = ? , "
                + "Krasa = ? ,"
                + "Gads = ?"
                + "WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, razotajs);
            pstmt.setString(2, krasa);
            pstmt.setString(3,gads);
            pstmt.setInt(4, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static Velosipeds dabutvelosipedu(int id){
        String sql = "SELECT Id, Gads , Krasa, Razotajs FROM Velosipedi where Id = ?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            int Id = rs.getInt("Id");
            String gads = rs.getString("Gads");
            String krasa = rs.getString("Krasa");
            String razotajs = rs.getString("Razotajs");
            Velosipeds velosipeds = new Velosipeds(Id,gads,krasa,razotajs);
            conn.close();
            return velosipeds;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public List<Velosipeds> velosipeduSaraksts (){
        String sql = "SELECT ID, Razotajs, Krasa, Gads FROM Velosipedi";


        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()){
                int id = rs.getInt("Id");
                String razotajs = rs.getString("Razotajs");
                String krasa = rs.getString("Krasa");
                String gads = rs.getString("Gads");
                pievienotVelosipeduSarakstam(id,razotajs,krasa,gads);
            }
            conn.close();
            return this.velosipeds;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return this.velosipeds;
    }
    public static boolean velosipedaEksistence(int id){
        String sql = "SELECT Id FROM Velosipedi";
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
    public static int nakosaVelosipedaId(){
        String sql = "SELECT Id FROM Velosipedi";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int x = 999;

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
    public static void pievienotVelosipedu(int id,String razotajs, String krasa, String gads){
        String sql = "INSERT INTO Velosipedi(Id, Razotajs, Krasa, Gads) VALUES(?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,id);
            pstmt.setString(2,razotajs);
            pstmt.setString(3,krasa);
            pstmt.setString(4,gads);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void izdzestVelosipedu(int id){
        String sql = "DELETE FROM Velosipedi WHERE Id = ?";

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

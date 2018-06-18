package dbconnect;

import Dati.Grafiks;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class dbGrafiks extends connect {

    List<Grafiks> grafiks;

    public dbGrafiks(){
        grafiks = new ArrayList<>();
    }
    public void pievienotGrafikaSarakstam(int id,int velosipeds, int darbinieks, String datums,String no, String lidz){
        grafiks.add( new Grafiks (id,velosipeds,darbinieks,datums,no,lidz));
    }
    public List<Grafiks> grafiksSaraksts (){
        String sql = "SELECT ID, Velosipeds, Darbinieks, Datums, No, Lidz FROM Grafiks ORDER BY Datums DESC";


        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            while (rs.next()){
                int id = rs.getInt("Id");
                int velosipeds = rs.getInt("Velosipeds");
                int darbinieks = rs.getInt("Darbinieks");
                String datums = rs.getString("Datums");
                String no = rs.getString("No");
                String lidz = rs.getString("Lidz");
                pievienotGrafikaSarakstam(id,velosipeds,darbinieks,datums, no,lidz);
            }
            conn.close();
            return this.grafiks;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return this.grafiks;
    }
    public Grafiks dabutIerakstu(int id){
        String sql = "SELECT id, Velosipeds, Darbinieks, Datums, No,Lidz FROM Grafiks where Id = ?";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            int Id = rs.getInt("Id");
            int velosipeds = rs.getInt("Velosipeds");
            int darbinieks = rs.getInt("Darbinieks");
            String datums = rs.getString("Datums");
            String no = rs.getString("No");
            String lidz = rs.getString("Lidz");
            Grafiks grafikIeraksts = new Grafiks(Id,velosipeds,darbinieks,datums,no,lidz);

            conn.close();
            return grafikIeraksts;
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void pievienotIerakstuGrafikam(int Id, int velosipeds, int darbinieks,String datums,String no, String lidz) {
        String sql = "INSERT INTO Grafiks(Id, Velosipeds, Darbinieks, Datums, No, Lidz) VALUES(?,?,?,?,?,?)";

        try {
            Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(sql);

            pstmt.setInt(1,Id);
            pstmt.setInt(2, velosipeds);
            pstmt.setInt(3, darbinieks);
            pstmt.setString(4,datums);
            pstmt.setString(5,no);
            pstmt.setString(6,lidz);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    public static void dzestIerakstuNoGrafika(int id){
        String sql = "DELETE FROM Grafiks WHERE id = ?";

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
    public static int nakosaIerakstaId(){
        String sql = "SELECT id FROM Grafiks";

        try {
            Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            int x = 0;

            while (rs.next()){
                x = rs.getInt("id");
            }
            x = x+1;
            return x;
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return 0;
    }
    public static boolean ierakstaEksistenci(int id){
        String sql = "SELECT id FROM Grafiks";
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
    public static boolean darbinieksPieejams(int darbinieks,  Date datums, Time no, Time lidz) {
        String sql = "SELECT Darbinieks, Datums ,No ,Lidz  FROM Grafiks";

        Connection conn = connect();

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                String dat = rs.getString("Datums");
                Date dat1 = Date.valueOf(dat);

                String no1 = rs.getString("No");
                Time no2 = Time.valueOf(no1);

                String lidz1 = rs.getString("Lidz");
                Time lidz2 = Time.valueOf(lidz1);

                if(darbinieks == rs.getInt("Darbinieks") && datums.equals(dat1)){
                    if (no.after(lidz2)||lidz.before(no2)){
                        conn.close();
                        return true;
                    }
                    else {
                        conn.close();
                        return false;
                    }
                }
            }
            conn.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
    public static boolean velosipedssPieejams(int velosipeds,  Date datums, Time no, Time lidz){
        String sql = "SELECT Velosipeds, Datums ,No ,Lidz  FROM Grafiks";

        Connection conn = connect();

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()){

                String dat = rs.getString("Datums");
                Date dat1 = Date.valueOf(dat);

                String no1 = rs.getString("No");
                Time no2 = Time.valueOf(no1);

                String lidz1 = rs.getString("Lidz");
                Time lidz2 = Time.valueOf(lidz1);

                if(velosipeds == rs.getInt("Velosipeds") && datums.equals(dat1)){
                    if (no.after(lidz2)||lidz.before(no2)){
                        conn.close();
                        return true;
                    }
                    else {
                        conn.close();
                        return false;
                    }
                }
            }
            conn.close();
            return true;
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return true;
    }
}

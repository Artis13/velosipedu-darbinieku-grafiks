package Aplikacija;


import Dati.Velosipeds;
import dbconnect.dbVelosipedi;
import java.util.List;
import java.util.Scanner;

public class VelosipediFunkcijas {

    Scanner scanner = new Scanner(System.in);

    public static void printetVelosipeduSarakstu(){
        dbVelosipedi dbVelosipedi = new dbVelosipedi();

       List<Velosipeds> x = dbVelosipedi.velosipeduSaraksts();

        for (int i = 0; i<x.size();i++){
            System.out.println(x.get(i).toString());
        }
    }

    public void atjaunotVelosipedaDatus(){
        try {
            System.out.println("Ievadiet velosipeda id?");
            int id = scanner.nextInt();

            if (dbVelosipedi.velosipedaEksistence(id)){
                System.out.println("Lai nemainītu vērtības nospiediet enter ");
                System.out.println("Ievadiet ražotāju ");
                System.out.println(scanner.nextLine());
                String razotajs = scanner.nextLine();
                if (razotajs.equals("")){
                    razotajs = dbVelosipedi.dabutvelosipedu(id).razotajs;
                }
                System.out.println("Ievadiet velosipēda krāsu ");
                String krasa = scanner.nextLine();
                if (krasa.equals("")){
                    krasa = dbVelosipedi.dabutvelosipedu(id).krasa;
                }
                System.out.println("Ievadiet velosipēda ražošanas gadu ");
                String gads = scanner.nextLine();
                if (gads.equals("")){
                    gads = dbVelosipedi.dabutvelosipedu(id).gads;
                }
                System.out.println("Velosipēds atjaunots ");
                dbVelosipedi.atjaunotVelosipedu(id,razotajs,krasa,gads);
            }
            else {
                System.out.println("Velosipēds netika atrasts ");
            }
        }
        catch (Exception e){
            System.out.println("Ievadiet tikai skaitļus ");
        }
    }
    public void izdzēstVelosipedu(){
        try {
            System.out.println("Ievadiet velosipēda id?");
            int id = scanner.nextInt();

            if (dbVelosipedi.velosipedaEksistence(id)){
                System.out.println("Vai tiešām vēlaties izdzēst velosipēdu?(j/n)");
                String apstiprinajums = scanner.next();
                if (apstiprinajums.equals("j")){
                    System.out.println("Velosipēds tika izdzests");
                    dbVelosipedi.izdzestVelosipedu(id);
                }
                else {
                    System.out.println("Velosipēds netika izdzēsts");
                }

            }
            else {
                System.out.println("Velosipēds netika atrasts");
            }
        }
        catch (Exception e){
            System.out.println("Ievadiet tikai skaitli");
        }
    }
    public void pievienotVelosipedu(){
        System.out.println("Ievadiet velsipēda ražotāju?");
        String razotajs = scanner.next();
        System.out.println("Ievadiet velosipēda krāsu?");
        String krasa = scanner.next();
        System.out.println("Ievadiet velosipēda ražošanas gadu?");
        String gads = scanner.next();

        int id = dbVelosipedi.nakosaVelosipedaId();

        dbVelosipedi.pievienotVelosipedu(id,razotajs,krasa,gads);
    }
}

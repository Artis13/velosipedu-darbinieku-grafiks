package Aplikacija;

import Dati.Darbinieks;
import dbconnect.dbDarbinieki;
import java.util.List;
import java.util.Scanner;

public class DarbiniekiFunkcijas {

    Scanner scanner = new Scanner(System.in);

    public static void printetDarbiniekuSarakstu(){
        dbDarbinieki darbinieki = new dbDarbinieki();

        List<Darbinieks> darbinieksSaraksts = darbinieki.darbiniekuSaraksts();

        for (int i = 0;i<darbinieksSaraksts.size();i++){
            System.out.println(darbinieksSaraksts.get(i).toString());
        }
    }

    public void atjaunotDarbiniekaDatus(){

        try {
            System.out.println("Ievadiet darbinieka id?");
            int id = scanner.nextInt();

            if (dbDarbinieki.darbiniekaEksistence(id)){
                System.out.println("Lai nemainītu vērtības nospiediet enter");
                System.out.println("Ievadiet darbinieka vārdu?");
                System.out.println(scanner.nextLine());
                String vards = scanner.nextLine();
                if (vards.equals("")){
                    vards = dbDarbinieki.dabutDarbinieku(id).vards;
                }
                System.out.println("Ievadiet darbnieka uzvārdu?");
                String uzvards = scanner.nextLine();
                if (uzvards.equals("")){
                    uzvards = dbDarbinieki.dabutDarbinieku(id).uzvards;
                }
                System.out.println("Darbinieks atjaunots");
                dbDarbinieki.atjaunotDarbinieku(id,vards,uzvards);
            }
            else {
                System.out.println("Darbinieks netika atrasts");
            }
        }
        catch (Exception e){
            System.out.println("Ievadiet tikai skaitļus");
        }
    }
    public void pievienotDarbinieku(){

        System.out.println("Ievadiet darbinieka vārdu? ");
        String vards = scanner.nextLine();
        System.out.println("Ievadiet darbinieka uzvārdu? ");
        String uzvards = scanner.nextLine();

        int id = dbDarbinieki.nakosaDarbinirkaId();

        dbDarbinieki.pievienotDarbinieku(id,vards,uzvards);

    }
    public void izdzestDarbinieku(){
        try {
            System.out.println("Ievadiet darbinieka id?");
            int id = scanner.nextInt();

            if (dbDarbinieki.darbiniekaEksistence(id)){
                System.out.println("Vai tiešām vēlaties izdzēst darbinieku?(j/n)");
                String apstiprinajums = scanner.next();
                if (apstiprinajums.equals("j")){
                    System.out.println("Darbinieks izdzests");
                    dbDarbinieki.izdzestDarbinieku(id);
                }
                else {
                    System.out.println("Darbinieks netika izdzēsts");
                }

            }
            else {
                System.out.println("Darbinieks netika atrasts");
            }
        }
        catch (Exception e){
            System.out.println("Ievadiet tikai skaitli");
        }
    }
}

package Aplikacija;

import Dati.Grafiks;
import dbconnect.*;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Scanner;

public class Funkcijas {
    Scanner scanner = new Scanner(System.in);

   public void izvelne(){
       System.out.println("Izvēlieties iespēju ievadot funkcijas numuru ");
       System.out.println("0. Beigt darbu");
       System.out.println("1. Apskatīt darbinieku sarakstu ");
       System.out.println("2. Pievienot jaunu darbinieku ");
       System.out.println("3. Dzēst darbinieku no saraksta ");
       System.out.println("4. Atjaunot darbinieka datus ");
       System.out.println("5. Apskatīt velosipēdu sarakstu ");
       System.out.println("6. Pievienot jaunu velosipēdu ");
       System.out.println("7. Dzēst velosipēdu no saraksta ");
       System.out.println("8. Atjaunot velosipēda datus ");
       System.out.println("9. Apskatit grafiku");
       System.out.println("10. Pievienot jaunu ierakstu grafika ");
       System.out.println("11. Dzēst ierakstu no grafika");

   }
   public void printetGrafiku(){
       dbGrafiks grafiks = new dbGrafiks();
       dbVelosipedi velosipedi = new dbVelosipedi();
       dbDarbinieki darbinieki = new dbDarbinieki();

       List<Grafiks> grafiksSaraksts = grafiks.grafiksSaraksts();

       for (int i = 0;i<grafiksSaraksts.size();i++){
           System.out.println(grafiksSaraksts.get(i).id + "\t" +
                   velosipedi.dabutvelosipedu(grafiksSaraksts.get(i).velosipeds).id + "\t" +
                   velosipedi.dabutvelosipedu(grafiksSaraksts.get(i).velosipeds).razotajs + "\t" +
                   darbinieki.dabutDarbinieku(grafiksSaraksts.get(i).darbinieks).vards + "\t" +
                   darbinieki.dabutDarbinieku(grafiksSaraksts.get(i).darbinieks).uzvards + "\t" +
                   grafiksSaraksts.get(i).datums + "\t" +
                   grafiksSaraksts.get(i).no + "\t" + grafiksSaraksts.get(i).lidz);

       }
   }
    public void pievienotIerakstuGrafika() {

           System.out.println("Izvēlieties velosipēdu ierakstot tā id");
            VelosipediFunkcijas.printetVelosipeduSarakstu();
           int velosipedsId = scanner.nextInt();
           if (!dbVelosipedi.velosipedaEksistence(velosipedsId)) {
               System.out.println("Velosipēds neeksistē datubāzē");
               return;
           }

           System.out.println("Izvēlieties darbinieku ierakstot viņa id");
           DarbiniekiFunkcijas.printetDarbiniekuSarakstu();
           int darbinieksID = scanner.nextInt();
           if (!dbDarbinieki.darbiniekaEksistence(darbinieksID)){
               System.out.println("Darbinieks neeksistē datubāzē");
               return;
           }

           Date datums;

           while (true){
               try {
                   System.out.println("Ievadiet datumu (YYYY-MM-DD)");
                   String datumsIevade = scanner.next();
                   datums = Date.valueOf(datumsIevade);
                   break;
               }
               catch (Exception e){
                System.out.println("Nepareizs datuma formats");
               }
           }

           Time no;

            while (true){
               try {
                   System.out.println("Ievadiet laiku no cikiem sāks strādāt (HH:MM:SS)");
                   String noIevade = scanner.next();
                   no = Time.valueOf(noIevade);
                   break;
               }
               catch (Exception e){
                   System.out.println("Nepareizs laika formāts ");
               }
            }

            Time lidz;

            while (true){
                try {
                    System.out.println("Ievadiet laiku lidz cikiem strādās (HH:MM:SS)");
                    String lidzIevade = scanner.next();
                    lidz = Time.valueOf(lidzIevade);
                    break;
                }
                catch (Exception e){
                    System.out.println("Nepareizs laika formāts ");
                }
            }


           if (!dbGrafiks.darbinieksPieejams(darbinieksID,datums,no,lidz)){
               System.out.println("Darbinieks šajā lakā jau tiek izmantots ");
               return;
           }
           if (!dbGrafiks.velosipedssPieejams(velosipedsId,datums,no,lidz)){
               System.out.println("Velosipeds saja laika jau tiek izmantots ");
               return;
           }

           int Id = dbGrafiks.nakosaIerakstaId();
           dbGrafiks.pievienotIerakstuGrafikam(Id,velosipedsId,darbinieksID,datums.toString(),
                   no.toString(),lidz.toString());

    }
    public void dzestIerakstuNoGrafika(){
        try {
            System.out.println("Ievadiet ieraksta id?");
            int id = scanner.nextInt();

            if (dbGrafiks.ierakstaEksistenci(id)){
                System.out.println("Vai tiešām vēlaties izdzēst ierakstu?(j/n)");
                String apstiprinajums = scanner.next();
                if (apstiprinajums.equals("j")){
                    System.out.println("Ieraksts izdzests");
                    dbGrafiks.dzestIerakstuNoGrafika(id);
                }
                else {
                    System.out.println("Ieraksts netika izdzēsts");
                }

            }
            else {
                System.out.println("Ieraksts netika atrasts");
            }
        }
        catch (Exception e){
            System.out.println("Ievadiet tikai skaitli");
        }
    }
}

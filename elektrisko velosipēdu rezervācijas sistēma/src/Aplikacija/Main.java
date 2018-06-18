package Aplikacija;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Funkcijas gf = new Funkcijas();
        VelosipediFunkcijas vf = new VelosipediFunkcijas();
        DarbiniekiFunkcijas df = new DarbiniekiFunkcijas();


        String at;

        while (true){
            System.out.println();
            System.out.println("Izvēlieties kādu darbību vēlaties veikt?");
            gf.izvelne();
            at = scanner.next();
            if (at.equals("0")) {
                break;
            }

            switch (at){
                case "1":
                    df.printetDarbiniekuSarakstu();
                    break;
                case "2":
                    df.pievienotDarbinieku();
                    break;
                case "3":
                    df.printetDarbiniekuSarakstu();
                    df.izdzestDarbinieku();
                    break;
                case "4":
                    df.printetDarbiniekuSarakstu();
                    df.atjaunotDarbiniekaDatus();
                    break;
                case "5":
                    vf.printetVelosipeduSarakstu();
                    break;
                case "6":
                    vf.pievienotVelosipedu();
                    break;
                case "7":
                    vf.printetVelosipeduSarakstu();
                    vf.izdzēstVelosipedu();
                    break;
                case "8":
                    vf.printetVelosipeduSarakstu();
                    vf.atjaunotVelosipedaDatus();
                    break;
                case "9":
                    gf.printetGrafiku();
                    break;
                case "10":
                    gf.printetGrafiku();
                    gf.pievienotIerakstuGrafika();
                    break;
                case "11":
                    gf.printetGrafiku();
                    gf.dzestIerakstuNoGrafika();
                    break;
                default:
                    System.out.println("Neatpazīta darbība");
                    break;
            }
        }
    }
}

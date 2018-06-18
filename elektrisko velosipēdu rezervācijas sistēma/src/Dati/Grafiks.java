package Dati;

public class Grafiks {

    public int id;
    public int velosipeds;
    public int darbinieks;
    public String datums;
    public String no;
    public String lidz;

    public Grafiks(int id, int velosipeds,int darbinieks , String datums,String no, String lidz){
        this.id = id;
        this.velosipeds = velosipeds;
        this.darbinieks = darbinieks;
        this.datums = datums;
        this.no = no;
        this.lidz = lidz;
    }
    public String toString(){
        return id + "\t" + velosipeds + "\t" + darbinieks + "\t" + datums + "\t" + no + "\t" + lidz;
    }
}

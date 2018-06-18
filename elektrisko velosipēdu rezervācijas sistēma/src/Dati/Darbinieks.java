package Dati;

public class Darbinieks {
    public int Id;
    public String vards;
    public String uzvards;

    public Darbinieks(int Id, String vards, String uzvards){
        this.Id = Id;
        this.vards = vards;
        this.uzvards = uzvards;
    }
    public String toString(){
        return Id + "\t" + vards + "\t" + uzvards;
    }
}

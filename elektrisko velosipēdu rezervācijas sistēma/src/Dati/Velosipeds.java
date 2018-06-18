package Dati;

public class Velosipeds {
    public int id;
    public String razotajs;
    public String krasa;
    public String gads;

    public Velosipeds(int id, String razotajs,String krasa, String gads){
        this.id = id;
        this.razotajs = razotajs;
        this.krasa = krasa;
        this.gads = gads;
    }
    public String toString(){
        return id + "\t" + razotajs + "\t" + krasa + "\t" + gads;
    }

}

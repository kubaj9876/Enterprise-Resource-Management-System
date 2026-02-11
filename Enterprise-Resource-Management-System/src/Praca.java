import java.io.Serializable;

public class Praca implements Serializable {
    private rodzajPracy rodzaj;
    private int czasPracy;
    private boolean zrealizowane;
    private String opis;
    private static int counter = 1;
    private int id;

    public Praca(rodzajPracy rodzaj, int czasPracy, String opis){
        this.id = counter++;
        this.rodzaj = rodzaj;
        this.czasPracy = czasPracy;
        this.opis = opis;
    }

    public int getId() { return id; }
    public rodzajPracy getRodzaj() { return rodzaj; }
    public void setRodzaj(rodzajPracy rodzaj) { this.rodzaj = rodzaj; }
    public int getCzasPracy() { return czasPracy; }
    public void setCzasPracy(int czasPracy) { this.czasPracy = czasPracy; }
    public boolean czyZrealizowane() { return zrealizowane; }
    public void setZrealizowane(boolean zrealizowane) { this.zrealizowane = zrealizowane; }
    public String getOpis() { return opis; }
    public void setOpis(String opis) { this.opis = opis; }
}

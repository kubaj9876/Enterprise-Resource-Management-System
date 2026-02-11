import java.io.Serializable;
import java.time.LocalDateTime;

public class Pracownik implements Serializable {
    private String imie;
    private String nazwisko;
    private LocalDateTime dataUrodzenia;
    private DzialPracownikow dzial;

    public Pracownik(String imie, String nazwisko, LocalDateTime dataUrodzenia, DzialPracownikow dzial){
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = dataUrodzenia;
        this.dzial = dzial;
    }

    public String getImie() { return imie; }
    public void setImie(String imie) { this.imie = imie; }
    public String getNazwisko() { return nazwisko; }
    public void setNazwisko(String nazwisko) { this.nazwisko = nazwisko; }
    public LocalDateTime getDataUrodzenia() { return dataUrodzenia; }
    public void setDataUrodzenia(LocalDateTime dataUrodzenia) { this.dataUrodzenia = dataUrodzenia; }
    public DzialPracownikow getDzial() { return dzial; }
    public void setDzial(DzialPracownikow dzial) { this.dzial = dzial; }
}

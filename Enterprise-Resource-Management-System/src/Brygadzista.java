import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Brygadzista extends Uzytkownik implements Serializable {
    private List<Brygada> listaBrygad = new ArrayList<>();

    public Brygadzista(String imie, String nazwisko, LocalDateTime dataUrodzenia, DzialPracownikow dzialPracownikow, String login, String haslo){
        super(imie, nazwisko, dataUrodzenia, dzialPracownikow, login, haslo);
    }

    public List<Brygada> getListaBrygad() { return listaBrygad; }
    public Brygadzista getBrygadzista(){ return this; }
    public void dodajBrygade(Brygada brygada) { listaBrygad.add(brygada); }
}

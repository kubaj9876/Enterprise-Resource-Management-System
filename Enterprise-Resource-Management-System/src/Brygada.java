import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Brygada implements Serializable {
    private String nazwa;
    private Brygadzista brygadzista;
    private List<Pracownik> listaPracownikow = new ArrayList<>();

    public Brygada(String nazwa, Brygadzista brygadzista){
        this.nazwa = nazwa;
        this.brygadzista = brygadzista;
    }

    public void addPracownik(Pracownik pracownik){
        if(pracownik instanceof Uzytkownik){
            throw new IllegalStateException("Nie można dodać " + pracownik.getClass().getName() + " do brygady.");
        }
        listaPracownikow.add(pracownik);
    }

    public String getNazwa() { return nazwa; }
    public void setNazwa(String nazwa) { this.nazwa = nazwa; }
    public Brygadzista getBrygadzista() { return brygadzista; }
    public void setBrygadzista(Brygadzista brygadzista) { this.brygadzista = brygadzista; }
    public List<Pracownik> getListaPracownikow() { return listaPracownikow; }

    @Override
    public String toString() {
        return nazwa;
    }
}

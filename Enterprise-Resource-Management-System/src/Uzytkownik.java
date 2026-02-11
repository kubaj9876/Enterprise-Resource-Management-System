import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Uzytkownik extends Pracownik implements Serializable {
    String login;
    String haslo;
    String inicial;

    public Uzytkownik(String imie, String nazwisko, LocalDateTime dataUrodzenia, DzialPracownikow dzialPracownikow, String login, String haslo){
        super(imie, nazwisko, dataUrodzenia, dzialPracownikow);
        this.login = login;
        this.haslo = haslo;
        this.inicial = updateInicial(imie, nazwisko);
    }

    private String updateInicial(String imie, String nazwisko){
        return (imie.charAt(0) + "" + nazwisko.charAt(0)).toUpperCase();
    }

    public String getLogin(){
        return login;
    }
    public void setLogin(String login){
        this.login = login;
    }
    public String getHaslo(){
        return haslo;
    }
    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public void setImie(String imie){
        super.setImie(imie);
        this.inicial = updateInicial(imie, getNazwisko());
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Uzytkownik)) return false;
        Uzytkownik u = (Uzytkownik) o;
        return Objects.equals(login, u.login);
    }
    @Override
    public void setNazwisko(String nazwisko){
        super.setNazwisko(nazwisko);
        this.inicial = updateInicial(getImie(), nazwisko);
    }
}

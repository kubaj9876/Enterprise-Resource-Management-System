import java.io.Serializable;
import java.util.Objects;

public class DzialPracownikow implements Serializable {
    private String nazwa;
    private Storage storage;

    public DzialPracownikow(Storage storage, String nazwa) throws NotUniqueNameException{
        if(!isUnique(storage, nazwa)){
            throw new NotUniqueNameException("Nazwa " + nazwa + " już istnieje!");
        }
        this.nazwa = nazwa;
        this.storage = storage;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        if (!isUnique(storage, nazwa)) {
            throw new IllegalArgumentException("Nazwa działu musi być unikalna");
        }
        this.nazwa = nazwa;
    }

    public static boolean isUnique(Storage storage, String nazwa){
        return storage.getDzialy().stream().noneMatch(dp -> dp.getNazwa().equals(nazwa));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DzialPracownikow)) return false;
        DzialPracownikow d = (DzialPracownikow) o;
        return Objects.equals(nazwa, d.nazwa);
    }

    @Override
    public String toString() {
        return nazwa;
    }
}

import java.io.IOException;
import java.time.LocalDateTime;

public class TD {
    public static void test() {
        Storage storage = new Storage();

        DzialPracownikow produkcja = new DzialPracownikow(storage,"Produkcja");
        DzialPracownikow logistyka = new DzialPracownikow(storage,"Logistyka");
        storage.getDzialy().add(produkcja);
        storage.getDzialy().add(logistyka);

        Pracownik jan = new Pracownik("Jan", "Kowalski", LocalDateTime.of(1980, 5, 15, 10, 10), produkcja);
        Uzytkownik annaNowak = new Uzytkownik("Anna", "Rosa", LocalDateTime.of(1985, 8, 22, 10, 10), logistyka, "anna", "123");
        Brygadzista piotr = new Brygadzista("Piotr", "Bamu", LocalDateTime.of(1975, 3, 10, 10, 10), produkcja, "", "");
        storage.getPracownicy().add(jan);
        storage.getPracownicy().add(annaNowak);
        storage.getPracownicy().add(piotr);

        Brygada brygada = new Brygada("Brygada A", piotr);
        brygada.addPracownik(jan);
        storage.getBrygady().add(brygada);
        piotr.dodajBrygade(brygada);

        Zlecenie zlecenie1 = new Zlecenie(brygada, true);
        zlecenie1.setDataRealizacji(LocalDateTime.now().plusDays(5));
        storage.getZlecenia().add(zlecenie1);

        Praca praca1 = new Praca(rodzajPracy.Montaz, 120, "Montaż maszyny");
        Praca praca2 = new Praca(rodzajPracy.Ogolna, 60, "Przygotowanie terenu");
        zlecenie1.getListaPrac().add(praca1);
        zlecenie1.getListaPrac().add(praca2);

        try {
            storage.save();
        } catch (IOException e) {
            System.err.println("Błąd: " + e.getMessage());
        }

        Storage loaded = new Storage();
        try {
            loaded.load();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Błąd: " + e.getMessage());
        }
    }
}

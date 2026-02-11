import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage implements Serializable {
    private List<DzialPracownikow> dzialy = new ArrayList<>();
    private List<Pracownik> pracownicy = new ArrayList<>();
    private List<Brygada> brygady = new ArrayList<>();
    private List<Zlecenie> zlecenia = new ArrayList<>();

    public void save() throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("dzialy.ser"))) {
            oos.writeObject(dzialy);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("pracownicy.ser"))) {
            oos.writeObject(pracownicy);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("brygady.ser"))) {
            oos.writeObject(brygady);
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("zlecenia.ser"))) {
            oos.writeObject(zlecenia);
        }
    }

    public void load() throws IOException, ClassNotFoundException{
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("dzialy.ser"))) {
            dzialy = (List<DzialPracownikow>) ois.readObject();
        } catch (FileNotFoundException e) {
            dzialy = new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("pracownicy.ser"))) {
            pracownicy = (List<Pracownik>) ois.readObject();
        } catch (FileNotFoundException e) {
            pracownicy = new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("brygady.ser"))) {
            brygady = (List<Brygada>) ois.readObject();
        } catch (FileNotFoundException e) {
            brygady = new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("zlecenia.ser"))) {
            zlecenia = (List<Zlecenie>) ois.readObject();
        } catch (FileNotFoundException e) {
            zlecenia = new ArrayList<>();
        }
    }

    public List<DzialPracownikow> getDzialy() { return dzialy; }
    public List<Pracownik> getPracownicy() { return pracownicy; }
    public List<Brygada> getBrygady() { return brygady; }
    public List<Zlecenie> getZlecenia() { return zlecenia; }
}

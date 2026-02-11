import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Zlecenie implements Serializable {
    private List<Praca> listaPrac = new ArrayList<>();
    private Brygada brygada;
    private stanZlecenia stan;
    private LocalDateTime dataUtworzenia;
    private LocalDateTime dataRealizacji;
    private LocalDateTime dataZakonczenia;

    public Zlecenie(Brygada brygada, boolean planowane){
        this.brygada = brygada;
        this.stan = planowane ? stanZlecenia.Planowane : stanZlecenia.Nieplanowane;
        this.dataUtworzenia = LocalDateTime.now();
    }

    public void zakonczZlecenie(){
        this.stan = stanZlecenia.Zakonczone;
        this.dataZakonczenia = LocalDateTime.now();
        for(Praca praca : listaPrac){
            praca.setZrealizowane(true);
        }
    }

    public List<Praca> getListaPrac() { return listaPrac; }
    public Brygada getBrygada() { return brygada; }
    public void setBrygada(Brygada brygada) { this.brygada = brygada; }
    public stanZlecenia getStan() { return stan; }
    public void setStan(stanZlecenia stan) { this.stan = stan; }
    public LocalDateTime getDataUtworzenia() { return dataUtworzenia; }
    public LocalDateTime getDataRealizacji() { return dataRealizacji; }
    public void setDataRealizacji(LocalDateTime dataRealizacji) { this.dataRealizacji = dataRealizacji; }
    public LocalDateTime getDataZakonczenia() { return dataZakonczenia; }
}

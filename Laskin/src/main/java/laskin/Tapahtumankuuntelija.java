package laskin;

import java.util.HashMap;
import java.util.Map;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventTarget;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Tapahtumankuuntelija implements EventHandler {
    private final Map<EventTarget, Komento> nappienKomennot;
    private final Button undo;
    private Komento edellinen = null;

    public Tapahtumankuuntelija(
        TextField tuloskentta,
        TextField syotekentta,
        Button plus,
        Button miinus,
        Button nollaa,
        Button undo
    ) {
        this.undo = undo;
        this.nappienKomennot = luoKomennot(
            tuloskentta,
            syotekentta,
            plus,
            miinus,
            nollaa,
            undo,
            new Sovelluslogiikka()
        );
    }

    private Map<EventTarget, Komento> luoKomennot(
        TextField tuloskentta,
        TextField syotekentta,
        Button plus,
        Button miinus,
        Button nollaa,
        Button undo,
        Sovelluslogiikka sovellus
    ) {
        HashMap<EventTarget, Komento> komennot = new HashMap<>();
        komennot.put(plus, new Summa(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(miinus, new Erotus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        komennot.put(nollaa, new Nollaus(tuloskentta, syotekentta, nollaa, undo, sovellus));
        return komennot;
    }

    @Override
    public void handle(Event event) {
        if (event.getTarget() == undo) {
            peruKomento();
            return;
        }

        Komento komento = haeKomento(event.getTarget());
        suoritaKomento(komento);
    }

    private void peruKomento() {
        if (edellinen != null) {
            edellinen.peru();
            edellinen = null;
        }
    }

    private Komento haeKomento(EventTarget eventTarget) {
        return nappienKomennot.getOrDefault(
            eventTarget,
            new Tuntematon()
        );
    }

    private void suoritaKomento(Komento komento) {
        komento.suorita();
        edellinen = komento;
    }
}

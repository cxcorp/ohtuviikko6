package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Nollaus extends LaskuKomento {

    public Nollaus(
        TextField tuloskentta,
        TextField syotekentta,
        Button nollaa,
        Button undo,
        Sovelluslogiikka sovellus
    ) {
        super(tuloskentta, syotekentta, nollaa, undo, sovellus);
    }

    @Override
    protected void laske() {
        sovellus.nollaa();
    }

    @Override
    protected void laskeUndo() {
        sovellus.asetaArvo(edellinenArvo);
    }
}

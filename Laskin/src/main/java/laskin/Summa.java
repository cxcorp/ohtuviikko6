package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class Summa extends LaskuKomento {

    public Summa(
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
        sovellus.plus(syotettyArvo);
    }

    @Override
    protected void laskeUndo() {
        sovellus.miinus(syotettyArvo);
    }
}

package laskin;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public abstract class LaskuKomento implements Komento {

    protected Sovelluslogiikka sovellus;
    protected int syotettyArvo;
    private TextField tuloskentta;
    private TextField syotekentta;
    private Button nollaa;
    private Button undo;

    public LaskuKomento(TextField tuloskentta, TextField syotekentta, Button nollaa, Button undo, Sovelluslogiikka sovellus) {
        this.tuloskentta = tuloskentta;
        this.syotekentta = syotekentta;
        this.nollaa = nollaa;
        this.undo = undo;
        this.sovellus = sovellus;
    }

    @Override
    public void suorita() {
        syotettyArvo = safeParseInt(syotekentta.getText());

        laske();
        int tulos = sovellus.tulos();

        asetaTulos(tulos);
        enabloiNollaus(tulos != 0);
        enabloiPeruminen();
    }

    @Override
    public void peru() {
        System.out.println("peru");
    }

    private void asetaTulos(int tulos) {
        syotekentta.setText("");
        tuloskentta.setText("" + tulos);
    }

    private void enabloiNollaus(boolean enabled) {
        nollaa.disableProperty().set(!enabled);
    }

    private void enabloiPeruminen() {
        undo.disableProperty().set(false);
    }

    private static int safeParseInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (Exception e) {
            return 0;
        }
    }

    protected abstract void laske();
}

package Verschiebungschiffre;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Die Klasse Leser dient zum Lesen von Inhalten aus einer Datei unter Verwendung einer bestimmten Zeichenkodierung.
 */
public class Leser {
    private BufferedReader leser;
    private String header;

    /**
     * Konstruktor für die Klasse Leser.
     *
     * @param pfad Der Pfad zur Datei, die gelesen werden soll.
     */
    public Leser(String pfad) {
        File file = new File(pfad);
        InputStreamReader isr;
        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            isr = new InputStreamReader(bis, StandardCharsets.ISO_8859_1.name());

            {
                this.leser = new BufferedReader(isr);
            }
        } catch (FileNotFoundException | UnsupportedEncodingException fnf) {
            fnf.printStackTrace(System.err);
        }

    }

    /**
     * Gibt den Header der gelesenen Datei zurück.
     *
     * @return Der Header der Datei.
     */
    public String getHeader() {

        return header;
    }

    /**
     * Lies den Inhalt der Datei Zeile für Zeile ein und wandelt Umlaute in ihre entsprechenden Buchstaben um.
     *
     * @return Eine Liste von Zeilen aus der Datei.
     * @throws IOException Falls ein Fehler beim Lesen der Datei auftritt.
     */
    public List<String> liesInhalt() throws IOException {
        List<String> inhalt = new ArrayList<>();
        String zeile = leser.readLine();
        while (zeile != null) {
            zeile = zeile.toLowerCase();
            zeile = zeile.replaceAll("ß", "ss").replaceAll("ä", "ae")
                    .replaceAll("ü", "ue").replaceAll("ö", "oe");

            inhalt.add(zeile.toString());
            zeile = leser.readLine();
        }
        leser.close();
        return inhalt;
    }

}

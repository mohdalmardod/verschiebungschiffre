package Verschiebungschiffre;

import java.io.*;
import java.util.*;

/**
 * Die Klasse Verschluesselung bietet Methoden zur Verschlüsselung und Entschlüsselung von Text mithilfe der Verschiebungschiffre.
 */
public class Verschluesselung {

    private Leser leser;
    private List<String> text;

    /**
     * Konstruktor für die Klasse Verschluesselung. Liest den Text aus einer Datei ein.
     */
    public Verschluesselung() {
        this.leser = new Leser("D:\\Uni\\Mup1\\Verschiebungschiffre\\Gedicht.txt");
        try {
            this.text = leser.liesInhalt();
        } catch (IOException e) {
            e.printStackTrace(System.err);
        }
    }

    /**
     * Verschlüsselt den gelesenen Text mit dem angegebenen Schlüssel.
     *
     * @param schluessel Der Verschlüsselungsschlüssel.
     */
    public void verschluesselnmit(int schluessel) {
        StringBuilder alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        StringBuilder schlusselalphabet = new StringBuilder();
        if (schluessel >= 0) {
            alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
            schlusselalphabet = new StringBuilder(alphabet.substring(schluessel, alphabet.length())
                    + alphabet.substring(0, schluessel));
            System.out.println("Dechiffrierungtabelle:");
            drucktabelle(alphabet);
            System.out.println("Chiffrierungtabelle:");
            drucktabelle2(schlusselalphabet, schluessel);
        }
        if (schluessel < 0) {
            schluessel = -schluessel;
            schlusselalphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
            alphabet = new StringBuilder(schlusselalphabet.substring(schluessel, schlusselalphabet.length())
                    + schlusselalphabet.substring(0, schluessel));
            System.out.println("Dechiffrierungtabelle:");
            drucktabelle(schlusselalphabet);
            System.out.println("Chiffrierungtabelle:");
            drucktabelle2(alphabet, schluessel);
        }
        StringBuilder sb = new StringBuilder();
        schluessel = schluessel % 26;
        for (int z = 0; z < text.size(); z++) {
            sb = new StringBuilder(text.get(z));
            for (int i = 0; i < sb.length(); i++) {
                char s = sb.charAt(i);
                int a = alphabet.indexOf(String.valueOf(s));
                if ((a >= 0) && (a < 27)) {
                    sb.replace(i, i + 1, String.valueOf(schlusselalphabet.charAt(a)));
                }
            }
            text.set(z, sb.toString());
        }
        speichern(text);
    }

    /**
     * Entschlüsselt den gelesenen Text mit dem angegebenen Schlüssel.
     *
     * @param schlussel Der Entschlüsselungsschlüssel.
     */
    public void entschluesselnmit(int schlussel) {
        schlussel = -schlussel;
        verschluesselnmit(schlussel);
    }

    /**
     * Verschlüsselt den gelesenen Text mit einem zufälligen Schlüssel.
     */
    public void verschluesseln() {
        int random = (int) (Math.random() * (26));
        verschluesselnmit(random);
    }

    /**
     * Entschlüsselt den gelesenen Text ohne Schlüssel.
     */
    public void entschluesseln() {
        StringBuilder alphabet = new StringBuilder("abcdefghijklmnopqrstuvwxyz");
        StringBuilder haufigkeit = new StringBuilder("enisratdhulcgmobwfkzpvjyxq");
        ArrayList<Integer> a = new ArrayList<>();
        int schlussel = 0;
        for (int i = 0; i < 26; i++) {
            a.add(0);
        }
        for (int z = 0; z < text.size(); z++) {
            StringBuilder sb = new StringBuilder(text.get(z));
            for (int i = 0; i < sb.length(); i++) {
                int j = alphabet.indexOf(String.valueOf(sb.charAt(i)));
                if ((j >= 0) && (j < 27)) {
                    a.set(j, (a.get(j) + 1));
                }
            }
        }
        while (true) {
            schlussel = a.indexOf(Collections.max(a)) - alphabet.indexOf(haufigkeit.substring(0, 1));
            if (text.get(0).charAt(0) == alphabet.charAt(3 + schlussel)) {
                break;
            } else {
                haufigkeit.delete(0, 1);
            }
        }

        entschluesselnmit(schlussel);
    }

    /**
     * Speichert den Text in einer Ausgabedatei.
     *
     * @param text Der zu speichernde Text.
     */
    private void speichern(List<String> text) {
        System.out.println("Ausgangtext:");
        try {
            File file = new File("D:\\Uni\\Mup1\\Verschiebungschiffre\\Gedicht_Aus.txt");

            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            for (String i : text) {
                System.out.println(i);
                pw.println(i);
            }
            bw.close();
        } catch (IOException fnfEX) {
            fnfEX.printStackTrace(System.err);
        }
    }

    /**
     * Druckt eine Chiffrierungstabelle.
     *
     * @param sb Das Alphabet.
     */
    private void drucktabelle(StringBuilder sb) {
        for (int i = 0; i < sb.length(); i++) {
            System.out.print(sb.charAt(i));
            System.out.print("  ");
            if (i > 8) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int i = 0; i < sb.length(); i++) {
            System.out.print(i);
            System.out.print("  ");
        }
        System.out.println();
    }

    /**
     * Druckt eine Chiffrierungstabelle mit Schlüsselverschiebung.
     *
     * @param sb        Das verschlüsselte Alphabet.
     * @param schlussel Die Verschiebung des Schlüssels.
     */
    private void drucktabelle2(StringBuilder sb, int schlussel) {
        for (int i = 0; i < sb.length(); i++) {
            System.out.print(sb.charAt(i));
            System.out.print("  ");
            if ((i + schlussel) % 25 > 9) {
                System.out.print(" ");
            }
        }
        System.out.println();
        for (int i = 0; i < sb.length(); i++) {
            System.out.print((i + schlussel) % 26);
            System.out.print("  ");
        }
        System.out.println();
    }

}

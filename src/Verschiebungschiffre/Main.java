package Verschiebungschiffre;

/**
 * Die Klasse Main ist die Einstiegspunkt-Anwendung für die Verschiebungschiffre-Anwendung.
 */
public class Main {

    /**
     * Der Einstiegspunkt der Anwendung.
     *
     * @param args Die Befehlszeilenargumente (nicht verwendet in dieser Anwendung).
     */
    public static void main(String[] args) {
        Verschluesselung verschluesslung = new Verschluesselung();

         // verschluesslung.verschluesselnmit(3);
         //   verschluesslung.entschluesselnmit(3);
              verschluesslung.verschluesseln();
           // verschluesslung.entschluesseln();

    }
}
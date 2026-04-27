/**
 * Fahrkartenautomat Übungsprojekt
 * @author Jan Albrecht (SE-C 51)
 * @version * A7.4: Das Fahrkartenarray
 */

import java.util.Scanner;

class Fahrkartenautomat {
	private static final double[] FAHRKARTEN_PREISE = { 3.0, 3.5, 3.8, 2.0, 8.6, 9.2, 10.0, 9.4, 12.6, 13.8, 25.5, 26.0, 26.5 };
	private static final String[] FAHRKARTEN_BEZEICHNUNGEN = {
		"Einzelfahrschein AB",
		"Einzelfahrschein BC",
		"Einzelfahrschein ABC",
		"Kurzstrecke AB",
		"Tageskarte AB",
		"Tageskarte BC",
		"Tageskarte ABC",
		"4-Fahrten-Karte AB",
		"4-Fahrten-Karte BC",
		"4-Fahrten-Karte ABC",
		"Kleingruppen-Tageskarte AB",
		"Kleingruppen-Tageskarte BC",
		"Kleingruppen-Tageskarte ABC"
	};

	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in);

		int gesamtbetragCent = fahrkartenbestellungErfassen(tastatur);
		int eingezahlterGesamtbetragCent;
		int eingeworfeneMuenzeCent;
		int rueckgabebetragCent;
		int nochZuZahlenCent;

		// 2 Geldeinwurf
		eingezahlterGesamtbetragCent = 0;
		nochZuZahlenCent = gesamtbetragCent;
		while (eingezahlterGesamtbetragCent < gesamtbetragCent) {
			nochZuZahlenCent = gesamtbetragCent - eingezahlterGesamtbetragCent;
			System.out.printf("Noch zu zahlen: %.2f Euro%n", nochZuZahlenCent / 100.0);
			System.out.print("Eingabe (Münzen: 0.05, 0.10, 0.20, 0.50, 1, 2 | Scheine: 5, 10, 20): ");
			double muenze = tastatur.nextDouble();
			eingeworfeneMuenzeCent = (int) Math.round(muenze * 100);
			
			// Validierung: Prüfe ob der eingeworfene Betrag gültig ist
			if (!istGueltigerBetrag(eingeworfeneMuenzeCent)) {
				System.out.println("FEHLER: Ungültiger Betrag! Bitte nur folgende Werte verwenden:");
				System.out.println("Münzen: 5 Cent, 10 Cent, 20 Cent, 50 Cent, 1 Euro, 2 Euro");
				System.out.println("Scheine: 5 Euro, 10 Euro, 20 Euro");
				continue; // Springe zum nächsten Schleifendurchlauf
			}
			
			eingezahlterGesamtbetragCent = eingezahlterGesamtbetragCent + eingeworfeneMuenzeCent;
		}
		
		// 3 Fahrscheinausgabe
		System.out.println("\nFahrschein wird ausgegeben");
		for (int i = 0; i < 8; i++) {
			System.out.print("=");
			try {
				Thread.sleep(200);
			} 
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("\n\n");
		
		// 4 Rückgeldberechnung und -ausgabe
		rueckgabebetragCent = eingezahlterGesamtbetragCent - gesamtbetragCent;
		if (rueckgabebetragCent > 0) {
			System.out.printf("Der Rückgabebetrag in Höhe von %.2f Euro%n", rueckgabebetragCent / 100.0);
			System.out.println("wird in folgenden Münzen ausgezahlt:");

			while (rueckgabebetragCent >= 200) { // 2-Euro-Münzen
				System.out.println("2 Euro");
				rueckgabebetragCent = rueckgabebetragCent - 200;
			}
			while (rueckgabebetragCent >= 100) { // 1-Euro-Münzen
				System.out.println("1 Euro");
				rueckgabebetragCent = rueckgabebetragCent - 100;
			}
			while (rueckgabebetragCent >= 50) { // 50-Cent-Münzen
				System.out.println("50 Cent");
				rueckgabebetragCent = rueckgabebetragCent - 50;
			}
			while (rueckgabebetragCent >= 20) { // 20-Cent-Münzen
				System.out.println("20 Cent");
				rueckgabebetragCent = rueckgabebetragCent - 20;
			}
			while (rueckgabebetragCent >= 10) { // 10-Cent-Münzen
				System.out.println("10 Cent");
				rueckgabebetragCent = rueckgabebetragCent - 10;
			}
			while (rueckgabebetragCent >= 5) { // 5-Cent-Münzen
				System.out.println("5 Cent");
				rueckgabebetragCent = rueckgabebetragCent - 5;
			}
		}

		System.out.println("\nVergessen Sie nicht, den Fahrschein\n" + "vor Fahrtantritt entwerten zu lassen!\n"
				+ "Wir wünschen Ihnen eine gute Fahrt.");

		tastatur.close();
	}

	public static int fahrkartenbestellungErfassen(Scanner tastatur) {
		int ticketAuswahl;
		do {
			System.out.println("Wählen Sie eine Fahrkarte aus:");
			for (int i = 0; i < FAHRKARTEN_BEZEICHNUNGEN.length; i++) {
				System.out.printf("%s (%.2f Euro)%n", FAHRKARTEN_BEZEICHNUNGEN[i], FAHRKARTEN_PREISE[i]);
			}
			System.out.print("Ihre Auswahl (1-" + FAHRKARTEN_BEZEICHNUNGEN.length + "): ");
			ticketAuswahl = tastatur.nextInt();

			if (ticketAuswahl < 1 || ticketAuswahl > FAHRKARTEN_BEZEICHNUNGEN.length) {
				System.out.println("  << Bitte wählen Sie eine gültige Fahrkartenart. >>");
			}
		} while (ticketAuswahl < 1 || ticketAuswahl > FAHRKARTEN_BEZEICHNUNGEN.length);

		int anzahlTickets;
		do {
			System.out.print("Anzahl der Tickets: ");
			anzahlTickets = tastatur.nextInt();

			if (anzahlTickets < 1 || anzahlTickets > 10) {
				System.out.println("  << Wählen Sie bitte eine Anzahl von 1 bis 10 Tickets aus. >>");
			}
		} while (anzahlTickets < 1 || anzahlTickets > 10);

		double einzelpreisEuro = FAHRKARTEN_PREISE[ticketAuswahl - 1];
		int gesamtbetragCent = (int) Math.round(einzelpreisEuro * 100) * anzahlTickets;
		System.out.printf("Der Gesamtbetrag für %d x %s beträgt: %.2f Euro%n",
			anzahlTickets,
			FAHRKARTEN_BEZEICHNUNGEN[ticketAuswahl - 1],
			gesamtbetragCent / 100.0);
		return gesamtbetragCent;
	}
	
	/**
	 * Prüft, ob der eingeworfene Betrag ein gültiges Zahlungsmittel ist
	 * @param centBetrag Betrag in Cent
	 * @return true wenn gültig, false wenn ungültig
	 */
	private static boolean istGueltigerBetrag(int centBetrag) {
		// Gültige Beträge in Cent
		int[] gueltigeBetraege = {5, 10, 20, 50, 100, 200, 500, 1000, 2000};
		
		for (int betrag : gueltigeBetraege) {
			if (centBetrag == betrag) {
				return true;
			}
		}
		return false;
	}
}

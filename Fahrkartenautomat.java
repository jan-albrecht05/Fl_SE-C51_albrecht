/**
 * Fahrkartenautomat Übungsprojekt
 * @author Jan Albrecht (SE-C 51)
 * @version * A4.3: Ticketgrenzen im Fahrkartenautomat
 */

import java.util.Scanner;

class Fahrkartenautomat {
	public static void main(String[] args) {

		Scanner tastatur = new Scanner(System.in);

		int zuZahlenderBetragCent;      // in Cent gespeichert
		int eingezahlterGesamtbetragCent;
		int eingeworfeneMuenzeCent;
		int rueckgabebetragCent;
		int nochZuZahlenCent;

		// 1 Geldbetrag eingeben
		System.out.print("Ticketpreis (Euro - Cent): ");
		double preis = tastatur.nextDouble();
		
		// Validierung: Ticketpreis muss größer als 0 sein
		if (preis <= 0) {
			System.out.println("FEHLER: Ticketpreis muss größer als 0 sein!");
			System.out.println("Programm wird beendet.");
			tastatur.close();
			return;
		}
		
		zuZahlenderBetragCent = (int) Math.round(preis * 100);  // Umwandlung in Cent
		
		System.out.print("Anzahl der Tickets: ");
		int anzahlTickets = tastatur.nextInt();
		
		// Validierung: Anzahl muss zwischen 1 und 10 liegen
		if (anzahlTickets < 1 || anzahlTickets > 10) {
			System.out.println("FEHLER: Ungültige Ticketanzahl (erlaubt: 1-10).");
			System.out.println("Es wird mit der Standardanzahl von 1 Ticket fortgefahren.");
			anzahlTickets = 1;
		}
		
		System.out.printf("Der Gesamtbetrag für %d Ticket(s) beträgt: %.2f Euro%n", anzahlTickets, (zuZahlenderBetragCent * anzahlTickets) / 100.0);

		// 2 Geldeinwurf
		eingezahlterGesamtbetragCent = 0;
		nochZuZahlenCent = zuZahlenderBetragCent * anzahlTickets;
		while (eingezahlterGesamtbetragCent < zuZahlenderBetragCent * anzahlTickets) {
			nochZuZahlenCent = zuZahlenderBetragCent * anzahlTickets - eingezahlterGesamtbetragCent;
			System.out.printf("Noch zu zahlen: %.2f Euro%n", nochZuZahlenCent / 100.0);
			System.out.print("Eingabe (mind. 5 Cent, höchstens 2 Euro): ");
			double muenze = tastatur.nextDouble();
			eingeworfeneMuenzeCent = (int) Math.round(muenze * 100);
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
		rueckgabebetragCent = eingezahlterGesamtbetragCent - zuZahlenderBetragCent * anzahlTickets;
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
}

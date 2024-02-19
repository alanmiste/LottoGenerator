# Lotto-Tippgenerator

[Anforderungen](#Anforderungen) | [Kurze Dokumentation](#Kurze-Dokumentation-für-die-Lottozahlengenerierungsanwendung) | [Bedienungsanleitung des Benutzers](#Bedienungsanleitung-des-Benutzers)

---

Dieses Projekt bietet eine Java-Applikation zum Generieren von Lotto-Tippreihen an. Herr Keller (57) hat uns gebeten, ihm bei der Entwicklung einer solchen Anwendung zu helfen, um sicherzustellen, dass seine Unglückszahlen bei der Generierung ausgeschlossen werden.

## Anforderungen

### User Stories

1. Als Lottospieler möchte ich über eine kleine Java-Applikation Tippreihen generieren.
    - **Akzeptanzkriterium:** Die Kommandozeile bzw. Terminalzeile wird als Aufruf- und Präsentationsmittel verwendet.

2. Als Lottospieler möchte ich bestimmen, ob eine spezielle Tippreihe für Lotto 6aus49 oder eine für Eurojackpot generiert wird.
    - **Akzeptanzkriterien:**
        - Die Auswahl des Spiels erfolgt über feste Namensparameter.
        - Wenn dieser Parameter nicht definiert ist, wird automatisch eine Tippreihe für Lotto 6aus49 gewählt.
        - Bei Angabe eines ungültigen Parameters wird eine Fehlermeldung mit den gültigen Namensparametern ausgegeben.

3. Als Lottospieler möchte ich bis zu sechs Unglückszahlen eingeben, die bei der Generierung der Tippreihe ausgeschlossen werden.
    - **Akzeptanzkriterien:**
        - Die Übergabe der Unglückszahlen erfolgt als Aufrufparameter.
        - Die eingegebenen Unglückszahlen werden auf Gültigkeit geprüft.
        - Bei Angabe einer ungültigen Unglückszahl wird eine Fehlermeldung mit dem gültigen Zahlenraum ausgegeben.
        - Die Unglückszahlen werden gespeichert und bleiben auch nach dem Schließen der Anwendung erhalten.
        - Die Unglückszahlen werden unabhängig von der Lotterie berücksichtigt.

4. Als Lottospieler möchte ich neue Unglückszahlen festlegen können.
5. Als Lottospieler möchte ich die Unglückszahlen löschen können.
6. Als Lottospieler möchte ich, dass die zufällig ermittelte Tippreihe so ausgegeben wird, dass sie einfach übertragen werden kann.
    - **Akzeptanzkriterium:** Sortierte, aufsteigende Reihenfolge der Zahlen der Tippreihe, erkennbare Trennung für die Eurojackpot-Tippreihe (erst 5aus50, dann 2aus10).

### Optionale Anforderung

- Als Lottospieler möchte ich weitere Tippreihen generieren können, bis ich das System stoppe.

## Hinweise zur Umsetzung

Bei der Umsetzung der Aufgabe sind folgende Punkte zu beachten:

- Verwendung von Vererbung.
- Verwendung eines Interfaces für allgemeine Funktionen zur Generierung von Tippreihen.
- Implementierung von Exception-Handling.
- Schreiben von Logdateien zur Fehleranalyse.
- Nutzung von Unittests zur Überprüfung der Funktionalität.
- Konsequente Versionierung des Fortschritts mit GitHub.
- Kurze Dokumentation der Lösung für den Anwender und andere Programmierer.

---


## Kurze Dokumentation für die Lottozahlengenerierungsanwendung

### Main.java:
- Dies ist die Startdatei, um die Anwendung zu starten.
- Der Benutzer verwendet diese Datei, um den Spieltyp (Lotto 6aus49 oder Eurojackpot) beim Start der Anwendung festzulegen.
- Nach der Auswahl des Spieltyps kann der Benutzer unglückliche Zahlen hinzufügen, entfernen und anzeigen lassen.
- Es generiert auch Lottozahlen und zeigt sie dem Benutzer an.

### LotteryGenerator.java:
- Diese Schnittstelle definiert die öffentlichen Funktionen für alle Lotteriegeneratoren.
- Es enthält nur eine Funktion, generateNumbers, die zum Erstellen von Lotteriezahlen verwendet werden kann.

### EurojackpotGenerator.java:
- Diese Datei generiert Eurojackpot-Zahlen und schließt unglückliche Zahlen aus.
- Es implementiert das LotteryGenerator-Interface.

### LottoGenerator.java:
- Diese Datei generiert Lotto 6aus49-Zahlen und schließt unglückliche Zahlen aus.
- Es implementiert das LotteryGenerator-Interface.

### UnluckyNumbersManager.java:
- Es verwaltet unglückliche Zahlen und bietet Funktionen zum Hinzufügen, Entfernen und Anzeigen von Zahlen.
- Es speichert die Zahlen in einer Textdatei für zukünftige Verwendung.
- Es enthält Funktionen zum Überprüfen, Formatieren, Speichern und Abrufen von Zahlen.

### CommandLineParser.java:
- Es analysiert die Befehlszeile und konvertiert sie in ein Array von Parametern.
- Es wird verwendet, um dem Benutzer das Übergeben von Parametern beim Start der Anwendung zu ermöglichen, um den Spieltyp zu bestimmen und unglückliche Zahlen zu verwalten.

### Logger.java:
- Es protokolliert Ereignisse und Fehler, die während des Betriebs der Anwendung auftreten.
- Es speichert Protokolle in einer Textdatei zur späteren Analyse.
- Es enthält Funktionen zum ordnungsgemäßen Protokollieren von Ereignissen und Fehlern mit Zeiterfassung und Speicherung in einer Textdatei.

**Eine ausführliche Erklärung der Codes gibt es im LottoGenerator-Wiki** : [Hier klicken](https://github.com/alanmiste/LottoGenerator/wiki) 

---
## Bedienungsanleitung des Benutzers.

**Wie kann der Benutzer das Programm ausprobieren?**

Als **Benutzer**, der das Programm zum Generieren von Lottozahlen testen möchte, müssen Sie lediglich das **LG-User**-Ordner klonen. 
Öffnen Sie dann die Befehlszeile oder das Terminal, wechseln Sie zum geklonten Verzeichnis und verwenden Sie die folgenden Befehle:

* **Befehle zum Generieren von Zahlen:**
   - `java Main lotto`:
     Um zufällige Zahlen für *Lotto 6aus49* zu generieren.
   - `java Main eurojackpot`:
     Um zufällige Zahlen für *EuroJackPot* zu generieren.
   - Wenn der **Spieltyp** nicht wie folgt angegeben wird java Main:
     Werden standardmäßig zufällige Zahlen für *Lotto 6aus49* generiert.

        <img width="800" src="https://raw.githubusercontent.com/alanmiste/photos/master/LottoGenerator/LottoGenerator-run.png" alt="Lotto Generator- run- screenshot" />


* **Befehl zum Hinzufügen von Unglückszahlen:**

  Durch Hinzufügen des Parameters `-add` nach dem Spieltyp gefolgt von den Zahlen können Sie Zahlen hinzufügen, die Sie nicht in den zukünftig generierten Zahlen haben möchten. Diese Zahlen werden in einer Datei gespeichert, um sie bei jedem zukünftigen Spiel zu vermeiden, unabhängig vom Spieltyp. Sie können eine oder mehrere Zahlen hinzufügen, die im erlaubten Bereich von 1-49 liegen müssen.

  Beispiel-1: `java Main lotto -add 3`

  Beispiel-2: `java Main lotto -add 35 22 5`

    <img width="800" src="https://raw.githubusercontent.com/alanmiste/photos/master/LottoGenerator/LottoGenerator-add.png" alt="Lotto Generator- add- screenshot" />

* **Befehl zum Entfernen von Unglückszahlen:**
  
   Durch Hinzufügen des Parameters `-remove` nach dem Spieltyp gefolgt von den zu löschenden Zahlen aus der Liste der gespeicherten Unglückszahlen werden diese Zahlen aus der Datei gelöscht, sofern sie vorhanden sind. Andernfalls wird eine Benachrichtigungsmeldung angezeigt.

  Beispiel-1: `java Main lotto -remove 35`

  Beispiel-2: `java Main eurojackpot -remove 22 5`

    <img width="800" src="https://raw.githubusercontent.com/alanmiste/photos/master/LottoGenerator/LottoGenerator-remove.png" alt="Lotto Generator- remove- screenshot" />

* **Befehl zum Anzeigen der gespeicherten Unglückszahlen:**

  Durch Hinzufügen des Parameters `-display` nach dem Spieltyp werden die gespeicherten Zahlen angezeigt.

  Beispiel-1: `java Main lotto -display`

  Beispiel-2: `java Main eurojackpot -display`

    <img width="800" src="https://raw.githubusercontent.com/alanmiste/photos/master/LottoGenerator/LottoGenerator-display.png" alt="Lotto Generator- display- screenshot" />

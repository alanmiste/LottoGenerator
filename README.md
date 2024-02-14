# Lotto-Tippgenerator

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

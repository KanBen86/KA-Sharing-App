# KA-Sharing-App
## Einrichtung
Programcode for the KA-Sharing Application

Projektteam: Christopher Pschibila, Orlando Jähde, Dominik Kunzmann, Vincent Neuhoff, Benjamin Kanzler

Um die User-Experience uneingeschränkt erleben zu können, wird vom Entwicklerteam der Google Browser Chrome in der Version 72.0.3626.121 oder höher empfohlen.

FÜr das Ausführen der Lösung innerhalb der IDE wird benötigt:
  - JDK >= 1.8
  - Git
  - Maven
  
Das JDK muss zuerst installiert sein und die Standard-Umgebungsvariablen müssen gesetzt werden. Danach kann in beliebiger Reihenfolge mit der Installation von Git und Maven fortgefahren werden.

  - Netbeans IDE Version 10
  - Apache TomEE-Server
  - Apache Derby Datenbank

Nach der Installation der IDE muss im Installationsverzeichnis in der Konfigurationsdatei der Pfad zum JDK eingetragen werden. Nachdem das JDK bekannt ist, wird in Der IDE der Server und die Datenbank konfiguriert.
  
  1. Konfiguration des Servers:
      - Im Projektrepository im Verzeichnis Tools liegt ein zip-file mit dem vorkonfigurierten Server
      - Das Zipfile entpacken und in einem Verzeichnis ablegen, wo es jederzeit wiedergefunden wird.
      - Im Reiter Services in Netbeans einen Rechtsklick auf den Eintrag Server ausführen und "add Server" auswählen
      - Das Folgefenster sollte die Auswahl "Apache Tomcat or TomEE" bereitstellen
      - Als nächsten Schritt nach dem Weiter geklickt wurde, muss das Verzeichnis in dem sch der TomEE befinden ausgewählt werden
      - Als User und Passwort jeweils "admin" eintragen. -> finish klicken
      
  2. Konfiguration der DB:
      - Im Service-Fenster den Datenbankeintrag mit dem + aufklappen
      - Rechtsklick auf JAVA-DB und dann auf Properties
      - Jetzt im aufgepoppten Fenster das Verzeichnis in dem sich die derby.jar befindet und das Verzeichnis für den Datenbankinhalt auswählen
      - mit OK bestätigen
      - Rechtsklick auf den neu entstandenen jdbc Reiter und dann connect auswählen
      
Der letzte Schritt der DB-Konfiguration muss nach jedem Neustart der IDE wiederholt werden, da ansonsten keine DB zur Verfügung steht.

Das Starten der App funktioniert via Rechtsklick auf das von Git importierte Projekt und der Auswahl des Punkts Run... evtl. muss der TomEE-Server ausgewählt werden.

## Verwendung:

Die App kann nun sofern die DB und der Server gestartet sind verwendet werden. Alle Interaktionen mit der Datenbank und dem Server können direkt aus der GUI getätigt werden. Für einige Funktionen ist es wichtig als eine spezieller Typ Nutzer eingeloggt zu sein, siehe Klammernotation:

  - User Registrieren
  - User anmelden
  - Fahzeuge anzeigen
  - Fahrzeuge nach Datum filtern
  - Fahrzeug anlegen (Mitarbeiter)
  - Fahrzeug buchen (Kunde)
  - Alle aktuell vergebenen Fahrzeuge anzeigen, Button Buchungen (Mitarbeiter)
  - Fahrzeug zurückgeben (Mitarbeiter)
  - Meine Buchungen anzeigen (Kunde)
  - Nutzerprofil bearbeiten (Kunde und Mitarbeiter)

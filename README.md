# Enterprise-Resource-Management-System
Kompleksowa aplikacja desktopowa do zarządzania strukturą organizacyjną przedsiębiorstwa, kadrą pracowniczą oraz procesami produkcyjnymi (zleceniami). System pozwala na pełną kontrolę nad obiegiem informacji o działach, brygadach i konkretnych zadaniach wykonywanych przez pracowników.

Kluczowe funkcjonalności
  * Zaawansowana hierarchia pracowników: Implementacja ról z wykorzystaniem dziedziczenia i polimorfizmu (Pracownik -> Użytkownik -> Brygadzista). Każda rola posiada unikalne uprawnienia i widoki (np. dedykowany panel "Moje zlecenia" dla brygadzisty).
  * System Zarządzania Zleceniami: Pełny cykl życia zlecenia (Planowane, Realizowane, Zakończone) wraz z listą konkretnych prac do wykonania.
  * Trwałość danych (Persistence): Wykorzystanie mechanizmu Serializacji Java do zapisu i odczytu stanu całej aplikacji (działy, pracownicy, brygady, zlecenia) do plików .ser.
  * Dynamiczny interfejs Swing: Modułowa budowa oparta na JTabbedPane.
    * Autorskie komponenty (np. DateTimePicker).
    * Interaktywne tabele (JTable) z obsługą zdarzeń myszy (double-click do podglądu szczegółów).
  * Bezpieczeństwo i walidacja: System logowania z weryfikacją poświadczeń.
    * Własne wyjątki (np. NotUniqueNameException) oraz mechanizmy zapobiegające dublowaniu nazw działów.
    * Walidacja logiki biznesowej (np. blokada dodawania użytkowników z uprawnieniami administracyjnymi do zwykłych brygad roboczych).

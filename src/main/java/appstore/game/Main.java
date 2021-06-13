package appstore.game;

import appstore.enums.Technology;

import java.text.ParseException;
import java.util.*;

import static java.lang.Thread.sleep;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    private static Company company;
    private static GameInfo game;

    public static void main(String[] args) throws ParseException, InterruptedException {
        game = new GameInfo();
        game.setStartParameters();

        startGameMenu();

        while (game.isGameState()) {
            String action = printMenu();
            performAction(action);
        }
    }

    private static void startGameMenu() throws InterruptedException {
        Player player = new Player();
        company = new Company();

        System.out.println("Witaj w grze Appstore!\n");
        sleep(500);
        System.out.println("Na początku podaj kilka parametrów.");
        sleep(100);
        System.out.println("Nazwa gracza: ");
        player.setName(scanner.next());
        sleep(100);
        System.out.println("Nazwa firmy: ");
        company.setName(scanner.next());
        sleep(500);
        System.out.println("Przydzielanie puli pieniędzy dla firmy");
        sleep(500);
        System.out.println(".");
        sleep(500);
        System.out.println(".");
        int money = new Random().nextInt(1000) + 2000;
        company.setMoney(money);
        System.out.println("Firma dostała " + money + " złotych na start.");
        sleep(500);
        System.out.println("\nStartuję grę");
        sleep(500);
        System.out.println(".");
        sleep(500);
        System.out.println(".");
        sleep(500);
        System.out.println(".");
        sleep(500);

        company.setPlayer(player);
    }

    private static String printMenu() {
        System.out.println("\n[ - - - - - - - - - - - MENU - - - - - - - - - - - ]");
        System.out.println("Dostępne akcje:");
        System.out.println("1. Sprawdź stan realizacji projektów.");
        System.out.println("2. Przejrzyj dostępne projekty.");
        System.out.println("3. Przejrzyj dostępnych pracowników.");
        System.out.println("4. Sprawdź stan konta.");
        System.out.println("5. Wykonaj akcję dnia");
        System.out.println("0. Wyjdź z gry.");
        System.out.println("Podaj numer akcji: ");

        return scanner.next();
    }
    private static void performAction(String action) throws InterruptedException {
        switch (action) {
            case "1":
                checkProjectsRealization();
                break;
            case "2":
                showAvailableProjects();
                break;
            case "3":
                showAvailableEmployees();
                break;
            case "4":
                checkAccountBalance();
                break;
            case "5":
                printDailyActionsMenu();
                break;
            case "0":
                System.exit(0);
            default:
                System.out.println("Podana opcja nie istnieje!");

        }
        sleep(1500);
    }

    private static void checkAccountBalance() {
        System.out.println("\n[ - - - - - - - - - - - STAN KONTA - - - - - - - - - - - ]");
        System.out.println("Firma na dzień " + game.getDate().toString() + " posiada " + company.getMoney() + " złotych.");
    }

    private static void showAvailableProjects() {
        System.out.println("\n[ - - - - - - - - - - - DOSTĘPNE PROJEKTY - - - - - - - - - - - ]");
        for(Project p : game.getAvailableProjects()) {
            System.out.println(p);
        }
    }

    private static void showAvailableEmployees() {
        System.out.println("\n[ - - - - - - - - - - - DOSTĘPNI PRACOWNICY - - - - - - - - - - - ]");
        if (game.getAvailableEmployees().isEmpty()) {
            System.out.println("Brak dostępnych pracowników na rynku.");
        } else {
            System.out.println("Dostępni pracownicy: ");
            for (Employee e : game.getAvailableEmployees()) {
                System.out.println(e);
            }
        }
    }

    private static void checkProjectsRealization() {
        System.out.println("\n[ - - - - - - - - - - - STATUS PROJEKTOW - - - - - - - - - - - ]");
        if (company.getProjectsProgress().isEmpty()) {
            System.out.println("Nie pracujesz nad żadnym projektem.");
        } else {
            System.out.println("Projekty i prace, które zostały do wykonania: ");
            for (Project p : company.getProjectsProgress()) {
                System.out.println(p);
            }
        }
    }

    private static void printDailyActionsMenu() throws InterruptedException {
        System.out.println("\n[ - - - - - - - - - - - AKCJE DO WYKONANIA - - - - - - - - - - - ]");
        System.out.println("1. Podpisz umowę na realizację jednego z dostępnych projektów.");
        System.out.println("2. Przeznacz dzień na szukanie klientów (każde 5 dni to jeden nowy dostępny projekt).");
        System.out.println("3. Przeznacz dzień na programowanie.");
        System.out.println("4. Przeznacz dzień na testowanie (możesz testować własny kod, kod podwykonawców i kod pracowników).");
        System.out.println("5. Oddaj gotowy projekt klientowi.");
        System.out.println("6. Zatrudnij nowego pracownika.");
        System.out.println("7. Zwolnij pracownika.");
        System.out.println("8. Przeznacz dzień na rozliczenia z urzędami (jeśli nie poświęcisz na to 2 dni w miesiącu ZUS wjeżdża z taką kontrolą, że zamykasz firmę z długami).");
        System.out.println("0. Powrót.");
        System.out.println("Podaj numer akcji: ");
        Scanner scanner = new Scanner(System.in);
        performDailyAction(scanner.next());
    }

    private static void performDailyAction(String action) throws InterruptedException {
        switch (action) {
            case "1":
                signContractForTheProject();
                break;
            case "2":
                searchForClients();
                break;
            case "3":
                workOnTheProject();
                break;
            case "4":
                testTheProject();
                break;
            case "5":
                returnTheProject();
                break;
            case "6":
                hireNewEmployee();
                break;
            case "7":
                dismissTheEmployee();
                break;
            case "8":
                settlementWithOffices();
                break;
            case "0":
                break;
            default:
                System.out.println("Podana opcja nie istnieje!");

        }
        sleep(1000);
    }

    private static void settlementWithOffices() {
    }

    private static void dismissTheEmployee() {
        System.out.println("\n[ - - - - - - - - - - - ZWOLNIJ PRACOWNIKA - - - - - - - - - - - ]");
        if (company.getEmployees().isEmpty()) {
            System.out.println("Nie zatrudniasz żadnego pracownika!");
        } else {
            int iterator = 1;
            System.out.println("Wybierz pracowanika, którego chcesz zwolnić:");
            for (Employee e : company.getEmployees()) {
                System.out.println(iterator + ". " + e);
                iterator++;
            }
            System.out.println("0. Anuluj.");
            String action = scanner.next();

            if (!action.equals("0")) {
                try {
                    int toDelete = Integer.parseInt(action);
                    if (toDelete > 0 && toDelete <= iterator) {
                        company.getEmployees().remove(toDelete - 1);
                        System.out.println("Pomyślnie zwolniono pracownika.");
                        incrementDayCounter();
                    } else {
                        System.out.println("Podana opcja nie istnieje!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Podana opcja nie istnieje!");
                }
            }
        }
    }

    private static void hireNewEmployee() {
        System.out.println("\n[ - - - - - - - - - - - ZATRUDNIJ PRACOWNIKA - - - - - - - - - - - ]");
        int iterator = 1;
        System.out.println("Wybierz pracowanika, którego chcesz zatrudnić:");
        for (Employee e : game.getAvailableEmployees()) {
            System.out.println(iterator + ". " + e);
            iterator++;
        }
        System.out.println("0. Anuluj.");
        String action = scanner.next();

        if (!action.equals("0")) {
            try {
                int toHire = Integer.parseInt(action);
                if (toHire > 0 && toHire <= iterator) {
                    Employee e = game.getAvailableEmployees().get(toHire - 1);
                    company.getEmployees().add(e);
                    game.getAvailableEmployees().remove(e);
                    System.out.println("Pomyślnie zatrudniono pracownika " + e);
                    incrementDayCounter();
                } else {
                    System.out.println("Podana opcja nie istnieje!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Podana opcja nie istnieje!");
            }
        }
    }

    private static void returnTheProject() {
        System.out.println("\n[ - - - - - - - - - - - ODDAJ PROJEKT - - - - - - - - - - - ]");
        if (company.getProjectsToReturn().isEmpty()) {
            System.out.println("Najpierw musisz przetestować jakiś projekt!");
        } else {
            int iterator = 1;
            System.out.println("Wybierz projekt, który chcesz oddać:");
            for (Project p : company.getProjectsToReturn()) {
                System.out.println(iterator + ". " + p);
                iterator++;
            }
            System.out.println("0. Anuluj.");
            String action = scanner.next();

            if (!action.equals("0")) {
                try {
                    int toReturn = Integer.parseInt(action);
                    if (toReturn > 0 && toReturn <= iterator) {
                        Project p = company.getProjectsToReturn().get(toReturn - 1);
                        company.getProjectsToReturn().remove(p);
                        System.out.println("Pomyślnie oddano projekt klientowi!");

                        incrementDayCounter();
                    } else {
                        System.out.println("Podana opcja nie istnieje!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Podana opcja nie istnieje!");
                }
            }
        }
    }

    private static void testTheProject() {
        System.out.println("\n[ - - - - - - - - - - - TESTUJ PROJEKT - - - - - - - - - - - ]");
        if (company.getProjectsToTest().isEmpty()) {
            System.out.println("Najpierw musisz zaprogramować jakiś projekt!");
        } else {
            int iterator = 1;
            System.out.println("Wybierz projekt, który chcesz przetestować:");
            for (Project p : company.getProjectsToTest()) {
                System.out.println(iterator + ". " + p);
                iterator++;
            }
            System.out.println("0. Anuluj.");
            String action = scanner.next();

            if (!action.equals("0")) {
                try {
                    int toTest = Integer.parseInt(action);
                    if (toTest > 0 && toTest <= iterator) {
                        Project p = company.getProjectsToTest().get(toTest - 1);
                        company.getProjectsToReturn().add(p);
                        company.getProjectsToTest().remove(p);
                        System.out.println("Pomyślnie zakończono testowanie projektu!");
                        incrementDayCounter();
                    } else {
                        System.out.println("Podana opcja nie istnieje!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Podana opcja nie istnieje!");
                }
            }
        }
    }
    private static void workOnTheProject() {
        System.out.println("\n[ - - - - - - - - - - - PROGRAMUJ - - - - - - - - - - - ]");
        if (company.getProjectsProgress().isEmpty()) {
            System.out.println("Najpierw musisz podpisać kontrakt na jakiś projekt!");
        } else {
            int iterator = 1;
            System.out.println("Wybierz projekt, który chcesz programować:");
            for (Project p : company.getProjectsProgress()) {
                System.out.println(iterator + ". " + p);
                iterator++;
            }
            System.out.println("0. Anuluj.");
            String action = scanner.next();

            if (!action.equals("0")) {
                try {
                    int toWork = Integer.parseInt(action);
                    if (toWork > 0 && toWork <= iterator) {
                        Project p = company.getProjectsProgress().get(toWork - 1);
                        showAvailableModulesToWorkInProject(p);
                        incrementDayCounter();
                    } else {
                        System.out.println("Podana opcja nie istnieje!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Podana opcja nie istnieje!");
                }
            }
        }
    }

    private static void showAvailableModulesToWorkInProject(Project p) {
        Map<Technology, Integer> modules = p.getDaysPerTechnology();
        System.out.println("Wybierz moduł, który chcesz zaprogramować: ");
        int iterator = 1;
        List<Technology> availableModules = new ArrayList<>();
        for (Technology t : modules.keySet()) {
            System.out.println(iterator + ". Moduł:" + t + "; pozostała praca: " + modules.get(t));
            availableModules.add(t);
            iterator++;
        }
        System.out.println("0. Anuluj.");
        String action = scanner.next();

        if (!action.equals("0")) {
            try {
                int toWork = Integer.parseInt(action);
                if (toWork > 0 && toWork <= iterator) {
                    int index = company.getProjectsProgress().indexOf(p);
                    int value = company.getProjectsProgress().get(index).getDaysPerTechnology().get(availableModules.get(toWork - 1)) - 1;
                    company.getProjectsProgress().get(index).getDaysPerTechnology().put(availableModules.get(toWork - 1), value);
                    System.out.println("Pomyślnie zakończono prace programistyczne!");
                    if (value == 0) {
                        company.getProjectsProgress().get(index).getDaysPerTechnology().remove(availableModules.get(toWork - 1));
                        System.out.println("Cały moduł został zaprogramowany.");
                        if (company.getProjectsProgress().get(index).getDaysPerTechnology().isEmpty()) {
                            company.getProjectsToTest().add(p);
                            company.getProjectsProgress().remove(index);
                            System.out.println("Prace programistyczne nad projektem zostały zakończone! Możesz przejść do testowania.");
                        }
                    }
                } else {
                    System.out.println("Podana opcja nie istnieje!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Podana opcja nie istnieje!");
            }
        }
    }

    private static void searchForClients() {
        System.out.println("\n[ - - - - - - - - - - - SZUKAJ KLIENTÓW - - - - - - - - - - - ]");
        game.incrementDaysSpentLookingForClients();
        if (game.getDaysSpentLookingForClients() == 5) {
            game.getAvailableProjects().add(ProjectGenerator.generateProject(game.getDate()));
            game.setDaysSpentLookingForClients(0);
            System.out.println("Znaleziono nowego klienta!");
        } else {
            System.out.println("Niestety poszukiwania nie powiodły się! (Liczba dni spędzonych na szukaniu = " + game.getDaysSpentLookingForClients() + ")");
        }
        incrementDayCounter();
    }
    private static void signContractForTheProject() {
        System.out.println("\n[ - - - - - - - - - - - WYBIERZ PROJEKT - - - - - - - - - - - ]");
        int iterator = 1;
        System.out.println("Wybierz projekt, który chcesz podpisać:");
        for (Project p : game.getAvailableProjects()) {
            System.out.println(iterator + ". " + p);
            iterator++;
        }
        System.out.println("0. Anuluj.");
        String action = scanner.next();

        if (!action.equals("0")) {
            try {
                int toSign = Integer.parseInt(action);
                if (toSign > 0 && toSign <= iterator) {
                    Project p = game.getAvailableProjects().get(toSign - 1);
                    company.getProjects().add(p);
                    company.getProjectsProgress().add(p);
                    game.getAvailableProjects().remove(p);
                    System.out.println("Pomyślnie podpisano projekt " + p);
                    incrementDayCounter();
                } else {
                    System.out.println("Podana opcja nie istnieje!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Podana opcja nie istnieje!");
            }
        }
    }

    private static void incrementDayCounter() {
        Calendar c = Calendar.getInstance();
        c.setTime(game.getDate());
        c.add(Calendar.DATE, 1);
        game.setDate(c.getTime());
    }
}

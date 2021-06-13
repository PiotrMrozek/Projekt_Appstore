package appstore.game;

import appstore.enums.ClientType;
import appstore.enums.Complexity;
import appstore.enums.Technology;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class ProjectGenerator {

    private static int numberOfProjects = 0;

    public static Project generateProject(Date currentDate) {
        int projectType = (numberOfProjects % 3 + 1); // 1 - łatwy; 2 - średni; 3 - złożony

        String name = generateProjectName();

        Map<Technology, Integer> daysPerTechnology = generateDaysForEachTechnology(projectType);

        Client client = new Client();
        client.setClientType(ClientType.getClientType(new Random().nextInt(3) + 1));

        int numberOfDaysForProject = (new Random().nextInt(4) + 3) * projectType;
        Calendar c = Calendar.getInstance();
        c.setTime(currentDate);
        c.add(Calendar.DATE, numberOfDaysForProject);
        Date deadline = c.getTime();

        int penaltyForExceedingTheDeadline = (new Random().nextInt(300) + 300) * projectType;

        int price = (new Random().nextInt(500) + 1000 + penaltyForExceedingTheDeadline) * projectType;

        int paymentDate = new Random().nextInt(8);

        Complexity complexity = Complexity.getComplexity(projectType);

        numberOfProjects++;
        return new Project(name, daysPerTechnology, client, deadline, penaltyForExceedingTheDeadline, price, paymentDate, complexity);
    }

    private static String generateProjectName() {
        int line1 = new Random().nextInt(37);
        int line2 = new Random().nextInt(6);
        try {
            String first = Files.readAllLines(Paths.get("src/main/resources/names.txt")).get(line1);
            String second = Files.readAllLines(Paths.get("src/main/resources/names2.txt")).get(line2);

            return first + " of " + second;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static Map<Technology, Integer> generateDaysForEachTechnology(int projectType) {
        Map<Technology, Integer> daysPerTechnology = new HashMap<>();
        int numberNeeded = 0;
        if (projectType == Complexity.EASY.getNumber()) {
            numberNeeded = new Random().nextInt(2) + 1;
        } else if (projectType == Complexity.MEDIUM.getNumber()) {
            numberNeeded = new Random().nextInt(2) + 2;
        } else if (projectType == Complexity.COMPLEX.getNumber()) {
            numberNeeded = new Random().nextInt(2) + 3;
        }
        List<Integer> possibleTechnologies = new ArrayList<>();
        for (int i = 1; i < 7; i++) {
            possibleTechnologies.add(i);
        }
        int iterator = 0;
        while (iterator < numberNeeded) {
            int random = new Random().nextInt(7);
            if (possibleTechnologies.contains(random)) {
                int numberOfDays = new Random().nextInt(3) + 1;
                daysPerTechnology.put(Technology.getTechnology(random), numberOfDays);
                possibleTechnologies.remove(new Integer(random));
                iterator++;
            }
        }

        return daysPerTechnology;
    }
}

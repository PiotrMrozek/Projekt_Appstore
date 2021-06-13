package appstore.game;

import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class GameInfo {
    private Date date;
    private boolean gameState;
    private List<Project> availableProjects;
    private List<Employee> availableEmployees;
    private int daysSpentLookingForClients = 0;

    public void setStartParameters() throws ParseException {
        date = new SimpleDateFormat("yyyyMMdd").parse("20200101");
        gameState = true;

        availableProjects = new ArrayList<>();
        availableProjects.add(ProjectGenerator.generateProject(date));
        availableProjects.add(ProjectGenerator.generateProject(date));
        availableProjects.add(ProjectGenerator.generateProject(date));

        availableEmployees = new ArrayList<>();
    }

    public void incrementDaysSpentLookingForClients() {
        this.daysSpentLookingForClients++;
    }
}

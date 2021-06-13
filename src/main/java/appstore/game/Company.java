package appstore.game;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Company {
    private String name;
    private Player player;
    private int money;
    private List<Employee> employees = new ArrayList<>();
    private List<Project> projects = new ArrayList<>();
    private List<Project> projectsProgress = new ArrayList<>();
    private List<Project> projectsToTest = new ArrayList<>();
    private List<Project> projectsToReturn = new ArrayList<>();
}

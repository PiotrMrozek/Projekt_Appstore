package appstore.game;

import appstore.enums.Complexity;
import appstore.enums.Technology;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
@AllArgsConstructor
public class Project {
    private String name;
    private Map<Technology, Integer> daysPerTechnology;
    private Client client;
    private Date deadline;
    private int penaltyForExceedingTheDeadline;
    private int price;
    private int paymentDate;
    private Complexity complexity;
}

import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString
class Machine {

    private List<Task> tasks;
    private int sumOfProcessingTime;
    private double numeratorF;

    Machine() {
        tasks = new ArrayList<>();
    }

    void countProcessingTime() {
        double previousPrc = 0;
        for (Task task : tasks) {
            previousPrc = previousPrc + task.getProcessingTime();
            numeratorF = numeratorF + previousPrc;
        }
    }
}

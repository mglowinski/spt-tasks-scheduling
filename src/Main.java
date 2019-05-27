import java.util.*;

public class Main {

    public static void main(String[] args) {
        List<Task> tasks = new ArrayList<>();
        Random random = new Random();

        int numberOfTasks = 18;
        int numberOfMachines = 3;

        //create tasks
        for (int i = 0; i < numberOfTasks; i++) {
            tasks.add(new Task(i + 1, random.nextInt(10) + 1));
        }

        //create machines
        List<Machine> machines = new ArrayList<>();
        for (int i = 0; i < numberOfMachines; i++) {
            machines.add(new Machine());
        }

        tasks.sort(Comparator.comparingInt(Task::getProcessingTime));

        while (tasks.size() != 0) {
            for (Machine machine : machines) {
                machine.getTasks().add(tasks.get(0));
                tasks.remove(0);
            }
        }

        double fNumerator = 0.0;

        for (int i = 0; i < machines.size(); i++) {
            System.out.println("Maszyna " + (i + 1) + ":");

            machines.get(i)
                    .setSumOfProcessingTime(machines.get(i)
                            .getTasks()
                            .stream()
                            .mapToInt(Task::getProcessingTime)
                            .sum());

            machines.get(i).countProcessingTime();

            fNumerator += machines.get(i).getNumeratorF();

            machines.get(i)
                    .getTasks()
                    .forEach(task -> System.out.println("Zadanie " + task.getNumber() +
                            ", proccessing time: " + task.getProcessingTime()));

            System.out.println("Suma czasu dla maszyny " + (i + 1) + ": " +
                    machines.get(i).getSumOfProcessingTime());

            System.out.println();
        }

        int maxFlowTime = machines.stream()
                .max(Comparator.comparingInt(Machine::getSumOfProcessingTime))
                .map(Machine::getSumOfProcessingTime)
                .orElseThrow(NoSuchElementException::new);

        System.out.println("Maksymalny czas przepływu: " + maxFlowTime);
        System.out.println("Średni czas przepływu: " + round(fNumerator / numberOfTasks));
    }

    private static double round(double value) {
        long factor = (long) Math.pow(10, 2);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}

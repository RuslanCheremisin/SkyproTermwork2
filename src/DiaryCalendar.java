
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class DiaryCalendar {
    private static Map<LocalDate, List<Task>> activeTasks = new TreeMap<>();
    private static Map<LocalDate, List<Task>> deletedTasks = new TreeMap<>();

    private static int numberOfActiveTasks = 0;
    private static int numberOfDeletedTasks = 0;

    private static void sortTasks() {
        int countActive = 0;
        int countDeleted = 0;
        for (Map.Entry<LocalDate, List<Task>> entry : activeTasks.entrySet()) {
            entry.getValue().sort((Comparator.comparing(Task::getDateTimeOfExecution)));
            for (Task task : entry.getValue()) {
                if (!task.isDeleted()) {
                    countActive++;
                    task.setId(countActive);
                } else {
                    countDeleted++;
                    task.setId(countDeleted);
                }
            }
        }
    }

    public static void addTask(String name, LocalDateTime dateTimeOfExecution, String description, TaskType type, TaskPeriodicity periodicity) {
        switch (periodicity) {
            case SINGLE:
                if (!activeTasks.containsKey(dateTimeOfExecution.toLocalDate())) {
                    List<Task> tasks = new ArrayList<>();
                    tasks.add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate(), tasks);
                } else {
                    activeTasks.get(dateTimeOfExecution.toLocalDate()).add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                }
                numberOfActiveTasks++;
                break;
            case DAILY:
                if (!activeTasks.containsKey(dateTimeOfExecution.toLocalDate())) {
                    List<Task> tasks = new ArrayList<>();
                    tasks.add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate(), tasks);
                    List<Task> tasksRepeat = new ArrayList<>();
                    tasksRepeat.add(new Task(name, dateTimeOfExecution.plusDays(1), description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate().plusDays(1), tasksRepeat);
                } else {
                    activeTasks.get(dateTimeOfExecution.toLocalDate()).add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    List<Task> tasksRepeat = new ArrayList<>();
                    tasksRepeat.add(new Task(name, dateTimeOfExecution.plusDays(1), description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate().plusDays(1), tasksRepeat);
                }
                numberOfActiveTasks += 2;
                break;
            case WEEKLY:
                if (!activeTasks.containsKey(dateTimeOfExecution.toLocalDate())) {
                    List<Task> tasks = new ArrayList<>();
                    tasks.add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate(), tasks);
                    List<Task> tasksRepeat = new ArrayList<>();
                    tasksRepeat.add(new Task(name, dateTimeOfExecution.plusDays(7), description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate().plusDays(7), tasksRepeat);
                } else {
                    activeTasks.get(dateTimeOfExecution.toLocalDate()).add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    List<Task> tasksRepeat = new ArrayList<>();
                    tasksRepeat.add(new Task(name, dateTimeOfExecution.plusDays(7), description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate().plusDays(7), tasksRepeat);
                }
                numberOfActiveTasks += 2;
                break;
            case MONTHLY:
                if (!activeTasks.containsKey(dateTimeOfExecution.toLocalDate())) {
                    List<Task> tasks = new ArrayList<>();
                    tasks.add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate(), tasks);
                    List<Task> tasksRepeat = new ArrayList<>();
                    tasksRepeat.add(new Task(name, dateTimeOfExecution.plusMonths(1), description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate().plusMonths(1), tasksRepeat);
                } else {
                    activeTasks.get(dateTimeOfExecution.toLocalDate()).add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    List<Task> tasksRepeat = new ArrayList<>();
                    tasksRepeat.add(new Task(name, dateTimeOfExecution.plusMonths(1), description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate().plusMonths(1), tasksRepeat);
                }
                numberOfActiveTasks += 2;
                break;
            case ANNUAL:
                if (!activeTasks.containsKey(dateTimeOfExecution.toLocalDate())) {
                    List<Task> tasks = new ArrayList<>();
                    tasks.add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate(), tasks);
                    List<Task> tasksRepeat = new ArrayList<>();
                    tasksRepeat.add(new Task(name, dateTimeOfExecution.plusYears(1), description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate().plusYears(1), tasksRepeat);
                } else {
                    activeTasks.get(dateTimeOfExecution.toLocalDate()).add(new Task(name, dateTimeOfExecution, description, type, periodicity, 0));
                    List<Task> tasksRepeat = new ArrayList<>();
                    tasksRepeat.add(new Task(name, dateTimeOfExecution.plusYears(1), description, type, periodicity, 0));
                    activeTasks.put(dateTimeOfExecution.toLocalDate().plusYears(1), tasksRepeat);
                }
                numberOfActiveTasks += 2;
                break;
        }
        sortTasks();
    }

    public static void removeTaskById(int id) {
        if (diaryContainsTaskById(id)) {
            for (Map.Entry<LocalDate, List<Task>> entry : activeTasks.entrySet()) {
                for (Task task : entry.getValue()) {
                    if (task.getId() == id && !task.isDeleted()) {
                        task.setDeleted();
                        System.out.println("Задача " + task.getId() + " удалена!");
                        numberOfActiveTasks--;
                        numberOfDeletedTasks++;
                    }
                }
            }
            sortTasks();
        } else {
            System.out.println("Нет такого задания в списке");
        }
    }

    public static void editTaskById(int id, String newName, String newDescription) {
        if (diaryContainsTaskById(id)) {
            for (Map.Entry<LocalDate, List<Task>> entry : activeTasks.entrySet()) {
                for (Task task : entry.getValue()) {
                    if (task.getId() == id && !task.isDeleted()) {
                        task.setName(newName);
                        task.setDescription(newDescription);
                    }
                }
            }
            sortTasks();
        } else {
            System.out.println("Нет такого задания в списке");
        }
    }

    private static boolean diaryContainsTaskById(int id) {
        boolean contains = false;
        for (Map.Entry<LocalDate, List<Task>> entry : activeTasks.entrySet()) {
            for (Task task : entry.getValue()) {
                if (task.getId() == id && !task.isDeleted()) {
                    contains = true;
                }
            }
        }
        return contains;
    }

    public static void getTasksByDate(String date) {
        if (activeTasks.containsKey(ValidateUtil.convertStringToDate(date))) {
            for (Task task : activeTasks.get(ValidateUtil.convertStringToDate(date))) {
                if (!task.isDeleted()) {
                    System.out.println(task);
                } else if (activeTasks.containsKey(ValidateUtil.convertStringToDate(date)) && task.isDeleted()) {
                    System.out.println("На " + date + " нет активных задач");
                }
            }
        } else {
            System.out.println("На " + date + " нет активных задач");
        }
    }

    public static void printActiveTasks() {
        for (Map.Entry<LocalDate, List<Task>> entry : activeTasks.entrySet()) {
            for (Task task : entry.getValue()) {
                if (!task.isDeleted()) {
                    System.out.println(task);
                }
            }
        }
        System.out.println("В расписании " + numberOfActiveTasks + " задач");
    }

    public static void printDeletedTasks() {
        for (Map.Entry<LocalDate, List<Task>> entry : activeTasks.entrySet()) {
            for (Task task : entry.getValue()) {
                if (task.isDeleted()) {
                    System.out.println(task);
                }
            }
        }
        System.out.println("В архиве " + numberOfDeletedTasks + " задач");
    }
}


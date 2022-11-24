public interface CalendarUtil {
    void addTask(String name, String description, TaskType type, TaskPeriodicity periodicity);

    Task getTask(int id);

    void removeTaskById(int id);

    void printAllTasks();

    void rearrangeId();
}

package DiaryCalendar;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;

public class Task {
    private int id;
    private String name;
    private String description;
    private TaskType type;
    private TaskPeriodicity periodicity;
    private LocalDateTime dateTimeOfRecord;
    private LocalDateTime dateTimeOfExecution;
    private LocalDate dateOfExecution;

    private boolean isDeleted;
    private boolean isDone;

    private DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);

    public Task(String name, LocalDateTime dateTimeOfExecution, String description, TaskType taskType, TaskPeriodicity taskPeriodicity, int id) {
        this.name = ValidateUtil.validateString(name);
        this.description = ValidateUtil.validateString(description);
        this.type = taskType;
        this.periodicity = taskPeriodicity;
        this.id = id;
        this.dateTimeOfRecord = LocalDateTime.now();
        this.dateTimeOfExecution = dateTimeOfExecution;
        this.dateOfExecution = this.dateTimeOfExecution.toLocalDate();
    }


    String getName() {
        return name;
    }

    LocalDate getDateOfExecution() {
        return dateOfExecution;
    }

    LocalDateTime getDateTimeOfExecution() {
        return dateTimeOfExecution;
    }

    String getDescription() {
        return description;
    }

    int getId() {
        return id;
    }

    boolean isDeleted() {
        return isDeleted;
    }

    void setDateTimeOfRecord(LocalDateTime dateTimeOfRecord) {
        this.dateTimeOfRecord = dateTimeOfRecord;
    }

    void setName(String name) {
        this.name = ValidateUtil.validateString(name);
    }

    void setDescription(String description) {
        this.description = ValidateUtil.validateString(description);
    }

    void setId(int id) {
        this.id = id;
    }

    void setDateTimeOfExecution(LocalDateTime dateTime) {
        this.dateTimeOfExecution = dateTime;
    }

    void setDone() {
        isDone = true;
    }

    void setNotDone() {
        isDone = false;
    }

    void setDeleted() {
        isDeleted = true;
    }

    public String toString() {
        String taskTypeStr = "";
        switch (type) {
            case PERSONAL:
                taskTypeStr = "Личная";
                break;
            case WORK:
                taskTypeStr = "Рабочая";
                break;
        }
        String taskPeriodicityStr = "";
        switch (periodicity) {
            case SINGLE:
                taskPeriodicityStr = "Разовая";
                break;
            case DAILY:
                taskPeriodicityStr = "Ежедневная";
                break;
            case WEEKLY:
                taskPeriodicityStr = "Еженедельная";
                break;
            case MONTHLY:
                taskPeriodicityStr = "Ежемесячная";
                break;
            case ANNUAL:
                taskPeriodicityStr = "Ежегодная";
                break;
        }
        return id + ": " + name + "\n" + description + "\n" + taskTypeStr + "\n" + taskPeriodicityStr + "\n" + dtf.format(dateTimeOfExecution) + "\n----------------";
    }

    public int hashCode() {
        return Objects.hash(id, name, description, type, periodicity, dateOfExecution, dateTimeOfExecution);
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        Task task = (Task) o;
        return Objects.equals(task.name, name) &&
                Objects.equals(task.description, description) &&
                Objects.equals(task.type, type) &&
                Objects.equals(task.periodicity, periodicity) &&
                Objects.equals(task.dateOfExecution, dateOfExecution) &&
                Objects.equals(task.dateTimeOfExecution, dateTimeOfExecution);
    }

}

package task_trecker.models;

public class EmployeeReport {
    int day;
    Employee employee;

    public EmployeeReport(int day, Employee employee) {
        this.day = day;
        this.employee = employee;
    }

    public int getDay() {
        return day;
    }


    public Employee getEmployee() {
        return employee;
    }

    public float getEfficiency(){
        return (float) employee.getTimeBusy()/ employee.getTimeAtWork() * 100;
    }

    @Override
    public String toString() {
        return String.format(
                "\nОтчёт за день %d \t| Сотрудник: %d - %s \t| " +
                "Рабочее время: %d ч \t| Занятое время: %d ч \t| " +
                "Эффективность: %.1f%% \t| Выполнено задач: %d",
                day,
                employee.getId(),
                employee.getName(),
                employee.getTimeAtWork(),
                employee.getTimeBusy(),
                getEfficiency(),
                employee.getCompletedTasksValue()
        );
    }
}

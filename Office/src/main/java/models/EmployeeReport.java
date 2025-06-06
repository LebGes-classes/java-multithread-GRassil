package models;

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
                "Отчёт за день %d | Сотрудник: %d - %s | " +
                "Рабочее время: %d ч | Занятое время: %d ч | " +
                "Эффективность: %.1f%% | Выполнено задач: %d \n",
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

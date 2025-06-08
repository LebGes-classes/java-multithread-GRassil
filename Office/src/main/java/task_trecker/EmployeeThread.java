package task_trecker;

import task_trecker.models.Employee;
import task_trecker.models.Task;

import java.util.Random;

import static task_trecker.services.TaskService.getTask;

public class EmployeeThread extends Thread{
    private Employee  employee;

    EmployeeThread(Employee employee) {
        if (employee!=null) this.employee = employee;
    }

    @Override
    public void run() {
        Random random = new Random();
        int workday = 8 + random.nextInt(-2,3); // Рабочий день с учетом опоздания/задержки после работы
        employee.setTimeAtWork(workday);

        while (workday>0) {
            if (employee.getCurrentTask()==null){ /// Если заданий нет, то даем задание
                employee.setCurrentTask(getTask());
                if (employee.getCurrentTask()== null) break; /// Если задания всё ещё нет - завершаем процесс
            }

            Task task = employee.getCurrentTask();

            int timeTask = task.makeTask(workday); // Выполняем задание, получаем реально затраченное за день время

            employee.addTimeBusy(timeTask);

            if (task.isCompleted()) {
                employee.addCompletedTasksValue(1);// увеличиваем счетчик выполненных задач
                employee.setCurrentTask(null); // обнуляем задание
            }
            workday-=timeTask;
        }
    }
}

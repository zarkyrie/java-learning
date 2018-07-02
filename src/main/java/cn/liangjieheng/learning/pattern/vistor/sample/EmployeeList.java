package cn.liangjieheng.learning.pattern.vistor.sample;

import java.util.ArrayList;
import java.util.List;


public class EmployeeList {

    private List<Employee> employeeList = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void accept(Department handler) {
        for (Employee anEmployeeList : employeeList) {
            anEmployeeList.accept(handler);
        }
    }
}

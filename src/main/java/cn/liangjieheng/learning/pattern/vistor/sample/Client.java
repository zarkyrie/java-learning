package cn.liangjieheng.learning.pattern.vistor.sample;

public class Client {

    public static void main(String[] args) {
        EmployeeList list = new EmployeeList();
        Employee fte1, pte1;
        fte1 = new FulltimeEmployee("张无忌", 3200.00, 45);
        pte1 = new ParttimeEmployee("洪七公", 80.00, 20);
        list.addEmployee(fte1);
        list.addEmployee(pte1);
        FADepartment faDepartment = new FADepartment();
        list.accept(faDepartment);
    }
}

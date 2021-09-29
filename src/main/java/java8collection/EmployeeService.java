package java8collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeService {

    public List<Employee> removeWhereSalaryIsLowerThan(List<Employee> employees, int maxSalary) {
        return employees.stream()
                .filter(e -> e.getSalary() > maxSalary)
                .collect(Collectors.toList());
//        or: employees.removeIf(e -> e.getSalary() < maxSalary);
    }

    public List<String> trimmedNames(List<String> names) {
        return names.stream()
                .map(name -> name.trim())
                .collect(Collectors.toList());
//        or: names.replaceAll(String::trim);
    }

    public List<Employee> sortByName(List<Employee> employees) {
        return employees.stream()
                .sorted( (e1, e2) -> e1.getName().compareTo(e2.getName()))
                .collect(Collectors.toList());
//        or: employees.sort(Comparator.comparing(Employee::getName));
    }

    public List<Employee> convertNamesToLowerCase(List<Employee> employees) {
        return employees.stream()
                .peek(e -> e.setName(e.getName().toLowerCase()))
                .collect(Collectors.toList());
//        or: employees.forEach(e -> e.setName(e.getName().toLowerCase()));
    }

    public Map<String, Integer> countByFirstName(List<Employee> employees) {
        Map<String, Integer> counts = new HashMap<>();
        for (Employee e : employees){
            String firstName = e.getName().substring(0, e.getName().indexOf(" "));
            counts.merge(firstName, 1, (k,v) -> v + 1);
        }
        return counts;
    }

    public Map<String, Employee> firstEmployeeWithFirstName(List<Employee> employees) {
        Map<String, Employee> firstEmployees = new HashMap<>();
        for (Employee e : employees){
            String firstName = e.getName().substring(0, e.getName().indexOf(" "));
//     or:  String firstName = employee.getName().split(" ")[0];
            firstEmployees.putIfAbsent(firstName, e);
        }
        return firstEmployees;
    }

    public Map<Long, Employee> updateEmployees(Map<Long, Employee> base, List<Employee> changedEmployees) {
        for(Employee e : changedEmployees){
            base.replace(e.getId(), e);
        }
        return base;
    }

    public Map<Long, Integer> salariesChanged(Map<Long, Integer> base, List<Employee> changedEmployees) {
        for (Employee e : changedEmployees){
            base.remove(e.getId(), e.getSalary());      //remove if did not change
            base.replace(e.getId(), e.getSalary());     //replace if still exist
        }
        return base;
    }     // OR:
//            if( base.get( e.getId() ) ==  e.getSalary()){
//                base.remove(e.getId());
//            } else {
//                base.put(e.getId(), e.getSalary());
//            }

}

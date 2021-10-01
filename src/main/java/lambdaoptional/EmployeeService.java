package lambdaoptional;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class EmployeeService {

    public Optional<Employee> findFirst(List<Employee> list, Predicate<Employee> predicate){
        for(Employee e : list){
            if(predicate.test(e)){
                return Optional.of(e);
            }
        }
        return Optional.empty();
    }

}

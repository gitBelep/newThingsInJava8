package lambdastreams;

import org.junit.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.Assert.assertEquals;

public class OwnTest {

    List<Employee> employees = Arrays.asList(
            new Employee("John Doe"),
            new Employee("Jane Doe"),
            new Employee("John John Smith2"),
            new Employee("Joe Doe"),
            new Employee("John John Smith")
    );

    @Test   //for unmodifiable Object
    public void countEmpWith3And2NamePartsWithReduce(){
            UnmodifiableNamePartCounter result = employees.stream()
                    .reduce(new UnmodifiableNamePartCounter(),       //0,0
                            (unpc, employee) -> unpc.add(employee),  //0,0  + employee(two or three)
                            (npc1, npc2) -> npc1.add(npc2)
                    );

        assertEquals(2, result.getThreeNames());
        assertEquals(3, result.getTwoNames());
    }

    @Test   //for modifiable Object
    public void testOwnNamePartCounterWithReduce(){
        ModifiableNamePartCounter result = employees.stream()
        .collect(ModifiableNamePartCounter::new,
                (npc, e) -> npc.add(e),
                (npc1, npc2) -> npc1.addAll(npc2) );

        assertEquals(2, result.getThreeNames());
        assertEquals(3, result.getTwoNames());
    }

}

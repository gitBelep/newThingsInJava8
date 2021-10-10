package lambdaintermediate;

import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class IntermediateTest {

    List<Employee> employees = Arrays.asList(
            new Employee("John Doe"),
            new Employee("Jane Doe"),
            new Employee("Joe Doe"),
            new Employee("John Smith")
    );

    @Test
    public void testFilter() {
        List<Employee> result = employees.stream()
                .filter(e -> e.getName().startsWith("John"))
                .collect(Collectors.toList());
        assertEquals(2, result.size());
    }

    @Test
    public void testDistinct() {
        List<String> result = Stream.of("John", "John", "Jane", "John")
                .distinct().collect(Collectors.toList());
        assertEquals(2, result.size());
        assertEquals("Jane", result.get(1));
    }

    @Test
    public void testLimitSkip() {
        List<Employee> filtered = employees.stream()
                .skip(1L)
                .limit(2L)
                .collect(Collectors.toList());
        assertEquals("Jane Doe", filtered.get(0).getName());
        assertEquals("Joe Doe", filtered.get(1).getName());
    }

    @Test
    public void testFlatMap() {
        List<String> s1 = Arrays.asList("John Doe", "Jane Doe");
        List<String> s2 = Arrays.asList("Jack Doe", "Joe Doe");
        List<String> s3 = Arrays.asList("John Doex", "Jane Doex");
        List<String> s4 = Arrays.asList("Jack Doex", "Joe Doex");
        List<List<String>> ss1 = new ArrayList<>(List.of(s1, s2));
        List<List<String>> ss2 = new ArrayList<>(List.of(s4, s3));

        List<String> names = Stream.of(ss1, ss2)    //Stream List<List<String>>
                .flatMap( l -> l.stream() )         //Stream List<String>
                .flatMap( l -> l.stream() )         //Stream String
                .collect(Collectors.toList());

        assertEquals("John Doe", names.get(0));
        assertEquals("Jane Doex", names.get(7));
    }

    @Test
    public void testSorted() {
        List<String> sorted = employees.stream()
            .sorted(Comparator.comparing( Employee::getName,
                    (n1, n2) -> Integer.compare((n1.length()), n2.length()) )
                    .thenComparing(Employee::getName)
            )
            .map(Employee::getName)
            .collect(Collectors.toList());

        assertEquals(sorted, List.of("Joe Doe", "Jane Doe", "John Doe", "John Smith"));
    }

    @Test
    public void testInfinite() {
        List<Integer> d = new ArrayList<>(10);
        Random rnd = new Random();
        Set<Boolean> booleans = Stream.generate( Math::random)
                .limit(10)
                .map(e -> d.add((int) Math.pow((int) (e * 20), 2)))  //.add ~ booleanStream
                .collect(Collectors.toSet());

        System.out.println(d);                      //squareNr
        assertEquals(1, booleans.size());   //true, true, true, true..
    }

    @Test
    public void testPeek() {
        List<Integer> squares = new ArrayList<>(10);
        Random rnd = new Random();
        List<Integer> integers = Stream.generate( Math::random)
                .limit(10)
                .peek(e -> squares.add((int) Math.pow((int) (e * 20), 2)))    //stream numbers go along
                .map(e -> Integer.parseInt(
                        Double.toString(e * 20).substring(0, Double.toString(e * 20).indexOf("."))) )
                .collect(Collectors.toList());

        assertEquals(10, integers.size());
        int square = squares.get(5);
        int i = integers.get(5);
        assertEquals(square, i * i);
    }

}

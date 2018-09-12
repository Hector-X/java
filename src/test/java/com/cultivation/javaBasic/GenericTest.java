package com.cultivation.javaBasic;

import com.cultivation.javaBasic.util.*;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericTest {
    @SuppressWarnings("unused")
    @Test
    void should_auto_resolve_generic_method() {
        final String[] words = {"Hello", "Good", "Morning"};

        // TODO: please call getMiddle method for string
        // <--start
        final String middle = getLast(words);
        // --end-->

        assertEquals("Morning", middle);
    }

    @Test
    void should_specify_a_type_restriction_on_typed_parameters() {
        int minimumInteger = min(new Integer[]{1, 2, 3});
        double minimumReal = min(new Double[]{1.2, 2.2, -1d});
        String miniString = min(new String[]{"z", "s", "sz"});

        assertEquals(1, minimumInteger);
        assertEquals(-1d, minimumReal, 1.0E-05);
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void should_not_know_generic_type_parameters_at_runtime() {
        KeyValuePair<String, Integer> pair = new KeyValuePair<>("name", 2);
        KeyValuePair<Integer, String> pairWithDifferentTypeParameter = new KeyValuePair<>(2, "name");

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), pair.getClass().equals(pairWithDifferentTypeParameter.getClass()));
    }

    @SuppressWarnings({"UnnecessaryLocalVariable", "unchecked", "unused", "ConstantConditions"})
    @Test
    void should_be_careful_of_raw_type_generic() {
        Pair<Manager> managerPair = new Pair<>();
        Pair rawPair = managerPair;
        rawPair.setFirst(new Employee());

        boolean willThrow = false;
        try {
            Manager first = (Manager) rawPair.getFirst();
        } catch (ClassCastException error) {
            willThrow = true;
        }

        // TODO: please modify the following code to pass the test
        // <--start
        final Optional<Boolean> expected = Optional.of(true);
        // --end-->

        assertEquals(expected.get(), willThrow);
    }

    @Test
    void should_swap() {
        Pair<String> pair = new Pair<>("Hello", "World");

        swap(pair);

        assertEquals("World", pair.getFirst());
        assertEquals("Hello", pair.getSecond());
    }

    @Test
    void should_success_when_generic_is_sub_of_class_implements_interface() {
        MySubInteger mySubInteger1 = new MySubInteger(2);
        MySubInteger mySubInteger2 = new MySubInteger(1);
        MySubInteger[] subIntegers = new MySubInteger[]{mySubInteger1, mySubInteger2};
        assertEquals(mySubInteger2, getMin(subIntegers));
    }

    private static <T extends MySubInteger> T getMin(T[] array) {
        if (array == null && array.length == 0) return null;
        T result = array[0];
        for (int index = 1; index < array.length; index++) {
            if (result.compareTo(array[index]) > 0) {
                result = array[index];
            }
        }
        return result;
    }

    private <T> T getLast(T[] array) {
        if (array == null || array.length == 0) {
            return null;
        }
        return array[array.length - 1];
    }

    // TODO: please implement the following code to pass the test. It should be generic after all.
    // The method should only accept `Number` and the number should implement `Comparable<T>`
    // <--start
    private static <T extends Comparable<T>> T min(T[] array) {
        if (array == null && array.length == 0) return null;
        T result = array[0];
        for (int index = 1; index < array.length; index++) {
            if (result.compareTo(array[index]) > 0) {
                result = array[index];
            }
        }
        return result;
    }
    // --end-->

    // TODO: please implement following method to pass the test. But you cannot change the signature
    // <--start
    @SuppressWarnings("unused")
    private static void swap(Pair<?> pair) {
        swapHelper(pair);
    }

    // TODO: You can add additional method within the range if you like
    // <--start

    private static <T> void swapHelper(Pair<T> tPair) {
        T first = tPair.getFirst();
        tPair.setFirst(tPair.getSecond());
        tPair.setSecond(first);
    }
    // --end-->
}

/*
 * - Can you give an example when you have to specify a typed parameter in generic method call?
 * - Can type parameter have more than one bounds? Can type parameter bounds to class? Can type parameter bounds to both
 *   class and interface?
 * - What if you have 2 class that one is generic called `KeyValuePair<K, V>` and the second is a non-generic method
 *   called `KeyValuePair` in the same package?
 * - Can you use primitive types as type parameter?
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   ```
 * - What is the result of the following code
 *   ```
 *   KeyValuePair[] keyValuePairs = new KeyValuePair[10];
 *   keyValuePairs[0] = new KeyValuePair<>("Name", 10);
 *   keyValuePairs[1] = new KeyValuePair<>(10, "Name");
 *   KeyValuePair<String, Integer> value = keyValuePairs[1];
 *   String key = value.getKey();
 *   ```
 * - Please describe the wildcard generic type.
 */
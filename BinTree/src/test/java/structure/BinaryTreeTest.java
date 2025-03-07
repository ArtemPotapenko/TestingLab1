package structure;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

@DisplayName("Тестирование методов бинарного дерева")
public class BinaryTreeTest {

    @Test
    @DisplayName("Тест добавления чисел в дерево")
    public void testInsertAndSearchInteger() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);
        tree.insert(3);
        tree.insert(7);

        assertTrue(tree.search(10));
        assertTrue(tree.search(5));
        assertTrue(tree.search(15));
        assertTrue(tree.search(3));
        assertTrue(tree.search(7));

        assertFalse(tree.search(100));
        assertFalse(tree.search(20));

        List<Integer> result = new ArrayList<>();
        for (Integer value : tree) {
            result.add(value);
        }

        Integer[] expected = {3, 5, 7, 10, 15};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    @DisplayName("Тест добавления строк в дерево")
    public void testInsertAndSearchString() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.insert("apple");
        tree.insert("banana");
        tree.insert("cherry");
        tree.insert("date");

        assertTrue(tree.search("apple"));
        assertTrue(tree.search("banana"));
        assertTrue(tree.search("cherry"));
        assertTrue(tree.search("date"));

        assertFalse(tree.search("orange"));
        assertFalse(tree.search("grape"));

        List<String> result = new ArrayList<>();
        for (String value : tree) {
            result.add(value);
        }

        String[] expected = {"apple", "banana", "cherry", "date"};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    @DisplayName("Тест добавления дробных чисел")
    public void testInsertAndSearchDouble() {
        BinaryTree<Double> tree = new BinaryTree<>();
        tree.insert(1.1);
        tree.insert(2.2);
        tree.insert(0.5);
        tree.insert(3.3);

        assertTrue(tree.search(1.1));
        assertTrue(tree.search(2.2));
        assertTrue(tree.search(0.5));
        assertTrue(tree.search(3.3));

        assertFalse(tree.search(0.0));
        assertFalse(tree.search(4.4));

        List<Double> result = new ArrayList<>();
        for (Double value : tree) {
            result.add(value);
        }

        Double[] expected = {0.5, 1.1, 2.2, 3.3};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    @DisplayName("Тест пустого дерева")
    public void testEmptyTree() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        assertFalse(tree.search(10));

        List<Integer> result = new ArrayList<>();
        for (Integer value : tree) {
            result.add(value);
        }

        Integer[] expected = {};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    @DisplayName("Проверка не нахождения строки")
    public void testSearchNonExistentValue() {
        BinaryTree<String> tree = new BinaryTree<>();
        tree.insert("apple");
        tree.insert("banana");

        assertFalse(tree.search("orange"));
    }

    @Test
    @DisplayName("Проверка не нахождения числа")
    public void testDeleteInteger() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Integer[] values = {10, 5, 15, 3, 7, 12, 17};

        for (Integer value : values) {
            tree.insert(value);
        }

        tree.delete(15);
        List<Integer> result = new ArrayList<>();
        for (Integer value : tree) {
            result.add(value);
        }

        Integer[] expectedAfterDelete = {3, 5, 7, 10, 12, 17};
        assertArrayEquals(expectedAfterDelete, result.toArray());
    }

    @Test
    @DisplayName("Проверка удаления строки")
    public void testDeleteString() {
        BinaryTree<String> tree = new BinaryTree<>();
        String[] values = {"apple", "banana", "cherry", "date", "elderberry"};

        for (String value : values) {
            tree.insert(value);
        }

        tree.delete("banana");
        List<String> result = new ArrayList<>();
        for (String value : tree) {
            result.add(value);
        }

        String[] expectedAfterDelete = {"apple", "cherry", "date", "elderberry"};
        assertArrayEquals(expectedAfterDelete, result.toArray());
    }

    @Test
    @DisplayName("Проверка удаления дробного числа")
    public void testDeleteDouble() {
        BinaryTree<Double> tree = new BinaryTree<>();
        Double[] values = {1.1, 2.2, 0.5, 3.3};

        for (Double value : values) {
            tree.insert(value);
        }

        tree.delete(2.2);
        List<Double> result = new ArrayList<>();
        for (Double value : tree) {
            result.add(value);
        }

        Double[] expectedAfterDelete = {0.5, 1.1, 3.3};
        assertArrayEquals(expectedAfterDelete, result.toArray());
    }

    @Test
    @DisplayName("Удаление не существующего числа")
    public void testDeleteNonExistent() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Integer[] values = {10, 20, 30, 40, 50};

        for (Integer value : values) {
            tree.insert(value);
        }

        tree.delete(60);
        List<Integer> result = new ArrayList<>();
        for (Integer value : tree) {
            result.add(value);
        }

        Integer[] expected = {10, 20, 30, 40, 50};
        assertArrayEquals(expected, result.toArray());
    }

    @Test
    @DisplayName("Удаление корня")
    public void testDeleteRoot() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        tree.insert(10);
        tree.insert(5);
        tree.insert(15);

        tree.delete(10);
        List<Integer> result = new ArrayList<>();
        for (Integer value : tree) {
            result.add(value);
        }

        Integer[] expectedAfterDeleteRoot = {5, 15};
        assertArrayEquals(expectedAfterDeleteRoot, result.toArray());
    }

    @Test
    @DisplayName("Тест добавления большого числа данных")
    public void randomDataTest() {
        List<Double> list = new ArrayList<>();
        BinaryTree<Double> tree = new BinaryTree<>();
        for (int i = 0; i < 10000; i++) {
            double random = Math.random();
            list.add(random);
            tree.insert(random);
        }
        list.sort(Double::compareTo);
        List<Double> actualList = new ArrayList<>();
        for (Double value : tree) {
            actualList.add(value);
        }
        assertArrayEquals(list.toArray(), actualList.toArray());
    }
}

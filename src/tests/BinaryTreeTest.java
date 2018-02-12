package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import tree.BinaryTree;
import org.junit.jupiter.api.Test;

class BinaryTreeTest {

    private Integer[] values;
    private BinaryTree<Integer> tree;

    public BinaryTreeTest() {
        int size = 1000;
        values = new Integer[size];
        for (int i = 0; i < size; i++)
            values[i] = i;
    }

    @Test
    void test__size() {
        // Arrange
        tree = new BinaryTree<Integer>(values);

        // Act
        int size = tree.getSize();

        // Assert
        Assert.assertEquals(values.length, size);
    }

    @Test
    void test__add() {
        // Arrange
        tree = new BinaryTree<Integer>();

        // Act
        tree.add(1);
        tree.add(2);

        // Assert
        Assert.assertTrue(tree.toList().contains(1));
    }

    @Test
    void test__toList() {
        // Arrange
        tree = new BinaryTree<Integer>(values);

        // Act
        Integer[] list = (Integer[]) tree.toList().toArray(new Integer[tree.getSize()]);

        // Assert
        Assert.assertArrayEquals(values, list);
    }

    @Test
    void test__balance() {
        // Arrange
        tree = new BinaryTree<Integer>(values);

        // Act
        tree.naivelyBalanceTree();
        Integer[] list = (Integer[]) tree.toList().toArray(new Integer[tree.getSize()]);

        // Assert
        Assert.assertArrayEquals(values, list);
    }

    @Test
    void test__sort() {
        // Arrange
        List<Integer> clonedValuesAsList = new ArrayList<Integer>(Arrays.asList(values));

        // multiple adds, hence "values" is not sorted
        clonedValuesAsList.addAll(Arrays.asList(values));
        clonedValuesAsList.addAll(Arrays.asList(values));
        clonedValuesAsList.addAll(Arrays.asList(values));

        Integer[] clonedValues = clonedValuesAsList.toArray(new Integer[clonedValuesAsList.size()]);
        tree = new BinaryTree<Integer>(clonedValues);

        // manually sort the array
        Arrays.sort(clonedValues);

        // Act
        tree.naivelyBalanceTree();
        Integer[] list = (Integer[]) tree.toList().toArray(new Integer[tree.getSize()]);

        // Assert
        Assert.assertArrayEquals(clonedValues, list);
    }

    @Test
    void test__delete() {
        // Arrange
        tree = new BinaryTree<Integer>(values);

        // Act
        tree.delete(1);
        List<Integer> list = tree.toList();

        // Assert
        Assert.assertFalse(list.contains(1));
    }

    @Test
    void test__deleteEvery() {
        // Arrange
        tree = new BinaryTree<Integer>(values);

        // Act
        List<Integer> list = tree.toList();
        list.forEach(x -> tree.delete(x));

        // Assert
        Assert.assertEquals(0, tree.getSize());
    }

    @Test
    void test__deleteAll() {
        // Arrange
        tree = new BinaryTree<Integer>(values);

        // Act
        tree.deleteAll();

        // Assert
        Assert.assertEquals(0, tree.getSize());
    }
}

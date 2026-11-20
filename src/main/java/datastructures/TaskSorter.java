package datastructures;

import java.util.ArrayList;
import model.Task;

// Sorts tasks from HIGH to LOW priority using Bubble Sort (written by hand)
public class TaskSorter {

    public ArrayList<Task> sortByPriority(ArrayList<Task> tasks) {
        // Make a copy so the original list order is not changed
        ArrayList<Task> sorted = new ArrayList<>(tasks);

        // Outer loop: each round pushes one task to its correct place
        for (int i = 0; i < sorted.size() - 1; i++) {

            // Inner loop: compare two neighbours at a time
            for (int j = 0; j < sorted.size() - i - 1; j++) {
                Task left = sorted.get(j);
                Task right = sorted.get(j + 1);

                // If the left task has LOWER priority than the right one, swap them
                if (left.getPriority().getValue() < right.getPriority().getValue()) {
                    sorted.set(j, right);
                    sorted.set(j + 1, left);
                }
            }
        }

        return sorted;
    }
}

package org.lionbo.practice.java.forkjoinpool.countarray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class CountArrayTask extends RecursiveTask<Long> {

    private long[] inputArray;

    public CountArrayTask(long[] inputArray) {
        this.inputArray = inputArray;
    }

    /**
     * 
     */
    private static final long serialVersionUID = -7074946799506287326L;

    @Override
    protected Long compute() {
        if (inputArray == null) {
            return 0L;
        }
        if (inputArray.length > 3) {
            List<RecursiveTask<Long>> tasks = this.divideTask(inputArray);
            if (tasks != null && tasks.size() > 0) {
                Collection<RecursiveTask<Long>> resultList = this.invokeAll(tasks);
                long result = 0;
                for (RecursiveTask<Long> task : resultList) {
                    result += task.join();
                }
                return result;
            }
        } else {
            long result = 0;
            for (int i = 0; i < inputArray.length; i++) {
                result += inputArray[i];
            }
            return result;
        }
        return null;
    }

    private List<RecursiveTask<Long>> divideTask(long[] inputArray) {
        List<RecursiveTask<Long>> tasks = new ArrayList<>();
        if (inputArray != null) {
            int group = inputArray.length / 3;
            for (int i = 0; i <= group; i++) {
                int start = i * 3;
                int end = (i + 1) * 3 > inputArray.length ? inputArray.length : (i + 1) * 3;
                tasks.add(new CountArrayTask(Arrays.copyOfRange(inputArray, start, end)));
            }
        }
        return tasks;
    }

}

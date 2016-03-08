package org.lionbo.practice.java;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.concurrent.RecursiveTask;

public class CountFileTask extends RecursiveTask<Long> {

    private Path dir;

    public CountFileTask(String dir) {
        this.dir = new File(dir).toPath();
    }

    /**
     * 
     */
    private static final long serialVersionUID = 2891237076448965739L;

    @Override
    protected Long compute() {
        Long count = 0L;
        try {
            DirectoryStream<Path> ds = Files.newDirectoryStream(dir);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}

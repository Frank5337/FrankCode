package com.zbl.wwj.concurrent.step2.p68;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * @Author: zbl
 * @Date: Created in 2021/3/28
 * @Description:
 * @Version: $
 */
public class BalkingData {

    private final String fileName;

    private String content;

    private boolean changed;

    public BalkingData(String fileName, String content) {
        this.fileName = fileName;
        this.content = content;
        this.changed = true;
    }

    //主动查看变化
    public synchronized void change(String newContent) {
        this.content = newContent;
        this.changed = true;
    }

    //巡视的服务生
    public synchronized void save() throws IOException {
        if (!changed) {
            return;
        }

        doSave();
        //已经被受理了
        this.changed = false;

    }

    private void doSave() throws IOException {
        System.out.println(Thread.currentThread().getName() + " calls do save, content = " + content);
        try (Writer writer = new FileWriter(fileName, true);) {
            writer.write(content);
            writer.write("\n");
            writer.flush();
        }

    }
}


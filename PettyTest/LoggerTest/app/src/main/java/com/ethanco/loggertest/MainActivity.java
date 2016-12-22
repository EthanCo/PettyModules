package com.ethanco.loggertest;

import android.app.Activity;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

//SEVERE（最高值）> WARNING > INFO > CONFIG > FINE > FINER > FINEST（最低值）
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Logger log = Logger.getLogger("hello");
        log.setLevel(Level.INFO);
        Logger log1 = Logger.getLogger("hello");

        //log和log1是同一个
        System.out.println(log == log1);

        Logger log2 = Logger.getLogger("hello world");
        log2.setLevel(Level.INFO);

        ConsoleHandler consoleHandler = new ConsoleHandler();
        consoleHandler.setLevel(Level.ALL);
        consoleHandler.setFormatter(new MyLogHandler());

        log.addHandler(consoleHandler);
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler(getExternalCacheDir().getPath() + File.separator + "log4j1.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        fileHandler.setLevel(Level.ALL);
        log2.addHandler(fileHandler);
        fileHandler.setFormatter(new MyLogHandler());

        log.info("aaa");
        log1.info("bbb");
        log2.warning("fine");
    }

    class MyLogHandler extends Formatter {
        public String format(LogRecord logRecord) {
            return logRecord.getLevel() + ":" + logRecord.getMessage() + "\n";
        }
    }
}

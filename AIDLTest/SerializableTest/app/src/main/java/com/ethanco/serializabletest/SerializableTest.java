package com.ethanco.serializabletest;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * Created by EthanCo on 2016/12/16.
 */

public class SerializableTest {
    public static void main(String[] args) {
        serializableTest();
    }

    public static void serializableTest() {
        //序列化过程
        User user = new User(0, "ethanco", true);
        try {
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cache.txt"));
            out.writeObject(user);
            out.close();

            System.out.println("user:" + user.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        //反序列化过程
        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("cache.txt"));
            User newUser = (User) in.readObject();
            in.close();
            System.out.println("id:" + newUser.userId + " userName:"
                    + newUser.userName + " isMale:" + newUser.isMale);
            System.out.println("newUser:" + newUser.toString());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}

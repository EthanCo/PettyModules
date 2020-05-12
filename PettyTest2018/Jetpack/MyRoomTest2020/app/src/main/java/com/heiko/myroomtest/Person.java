package com.heiko.myroomtest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import java.util.Map;

/**
 * Person
 *
 * 注意事项:
 * 1.Entity类需要使用 @Entity注解标注，并且其中的 tableName即数据表名，可以改为自己想取的名字
 * 2.构造函数：构造函数可以允许多个，但只允许只有一个不加 @Ignore注解，其它的都得加上 @Ignore注解，实际运行发现，若多个构造函数不加 @Ignore，将无法通过编译。至于多个构造函数的存在是因为CRUD的特性决定的，例如删除，只需要id主键就行了。因此提供了多个构造函数
 * 3.Getter和Setter方法：这些方法是必须要的，否则无法对对象属性进行读取或修改。
 *
 * @author Heiko
 * @date 2020/3/18 0018
 */
@Entity(tableName = "person")
@TypeConverters(MapConverter.class)
public class Person {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "age")
    private int age;

    private String lastName;

    private Map<String,Integer> myMap;

    public Map<String, Integer> getMyMap() {
        return myMap;
    }

    public void setMyMap(Map<String, Integer> myMap) {
        this.myMap = myMap;
    }

    /**
     * 必须指定一个构造方法，room框架需要。并且只能指定一个
     * 如果有其他构造方法，则其他的构造方法必须添加@Ignore注解
     */
    @Ignore
    public Person() {
    }

    /**
     * 为了更新数据时使用
     *
     * @param id
     * @param name
     */
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 为了删除对象时使用
     *
     * @param id
     */
    @Ignore
    public Person(int id) {
        this.id = id;
    }

    /**
     * 为了方便直接构造对象使用
     *
     * @param name
     * @param age
     */
    @Ignore
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", lastName='" + lastName + '\'' +
                ", myMap=" + myMap +
                '}';
    }

    //@Embedded内嵌对象
    //https://www.baidu.com/link?url=rCNBgvA70pt551u1qOXlYzHzKSOU9hV7F84Zr_ZPMhARz66eO7qvVMcubtwxTjj3ia64sg7v7EPksff1LY48QK&wd=&eqid=b4ac8dbd000b89ed000000055eba4967
}

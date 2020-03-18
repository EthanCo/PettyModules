package com.heiko.myroomtest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * DAO层接口,需要添加@Dao注解声明
 * 所有的操作都以主键为依托进行
 *
 * 注意点:
 * PersonDao接口需要使用 @Dao注解进行标注
 * 注意！传递参数的集合，Room通过参数名称进行匹配，若不匹配，则编译出现错误。即当方法中存在参数时，参数名与数据库查询语句的变量名要保持一致，例如 getPersonById这个方法，如果参数名不一致，编译会报错。
 * 注意删除、修改和增添的返回值的含义。增添的返回值主键索引，而删除和修改的返回值时影响的行数，例如修改一行，那么返回的是1，代表着一行受到影响
 * 注意各个方法均需要对方法的意图进行注解标注。
 *
 *
 * @author Heiko
 * @date 2020/3/18 0018
 */
@Dao
public interface PersonDao {
    /**
     * 查询所有的数据，返回List集合
     *
     * @return
     */
    @Query("SELECT * FROM person")
    List<Person> getAllPersonList();

    @Query("SELECT * FROM person WHERE id == :personId")
    Person getPersonById(int personId);

    /**
     * 传递参数的集合，注意 Room通过参数名称进行匹配，若不匹配，则编译出现错误
     *
     * @param personId
     * @return
     */
    @Query("SELECT * FROM person WHERE id in (:personId)")
    List<Person> getPersonById(int[] personId);

    /**
     * 返回一定条件约束下的数据，注意参数在查询语句中的写法
     *
     * @param minAge
     * @param maxAge
     * @return
     */
    @Query("SELECT * FROM person WHERE age between :minAge and :maxAge")
    List<Person> getPersonByChosen(int minAge, int maxAge);

    /**
     * 插入数据，onConflict = OnConflictStrategy.REPLACE表明若存在主键相同的情况则直接覆盖
     * 返回的long表示的是插入项新的id
     *
     * @param person
     * @return
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertPerson(Person person);

    /**
     * 更新数据，这意味着可以指定id然后传入新的person对象进行更新
     * 返回的long表示更新的行数
     *
     * @param person
     * @return
     */
    @Update
    int updatePerson(Person person);

    /**
     * 删除数据，根据传入实体的主键进行数据的删除。
     * 也可以返回long型数据，表明从数据库中删除的行数
     *
     * @param person
     * @return
     */
    @Delete
    int deletePerson(Person person);
}

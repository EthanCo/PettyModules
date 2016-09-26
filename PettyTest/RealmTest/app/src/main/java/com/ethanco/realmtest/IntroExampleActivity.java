/*
 * Copyright 2014 Realm Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ethanco.realmtest;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ethanco.realmtest.model.Cat;
import com.ethanco.realmtest.model.Dog;
import com.ethanco.realmtest.model.Person;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class IntroExampleActivity extends Activity {

    public static final String TAG = IntroExampleActivity.class.getName();
    private LinearLayout rootLayout = null;

    private Realm realm;
    private RealmConfiguration realmConfig;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realm_basic_example);
        rootLayout = ((LinearLayout) findViewById(R.id.container));
        rootLayout.removeAllViews();

        //这些操作通常都足够小,我们可以安全地在UI线程上运行它们。

        //创建 Realm 配置
        realmConfig = new RealmConfiguration.Builder(this).build();
        //在UI线程打开Realm
        realm = Realm.getInstance(realmConfig);

        //基本的 增删改查
        basicCRUD(realm);
        //基本的 查询
        basicQuery(realm);
        //基本的 关联查询
        basicLinkQuery(realm);

        // 在另一个线程可以执行更复杂的操作。
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                String info;
                info = complexReadWrite();
                info += complexQuery();
                return info;
            }

            @Override
            protected void onPostExecute(String result) {
                showStatus(result);
            }
        }.execute();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        //记得在Destory时关闭Realm
        realm.close();
    }

    private void showStatus(String txt) {
        Log.i(TAG, txt);
        TextView tv = new TextView(this);
        tv.setText(txt);
        rootLayout.addView(tv);
    }

    private void basicCRUD(Realm realm) {
        showStatus("Perform basic Create/Read/Update/Delete (CRUD) operations...");

        //所有的写必须在Transaction内，从而保障多线程情况下的安全
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                //添加一个 Person
                Person person = realm.createObject(Person.class);
                person.setId(1);
                person.setName("Young Person");
                person.setAge(14);

            }
        });

        //找到第一个Person (没有查询条件) 并且读取 一个 字段
        final Person person = realm.where(Person.class).findFirst();
        showStatus(person.getName() + ":" + person.getAge());

        //在 transaction 中 更新 Person
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                person.setName("Senior Person");
                person.setAge(99);
                showStatus(person.getName() + " got older: " + person.getAge());
            }
        });

        //删除所有的Person
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(Person.class);
            }
        });
    }

    private void basicQuery(Realm realm) {
        showStatus("\nPerforming basic Query operation...");

        //查询Person的个数
        showStatus("Number of persons: " + realm.where(Person.class).count());

        //查询到所有age为99的Person
        RealmResults<Person> results = realm.where(Person.class).equalTo("age", 99).findAll();

        showStatus("Size of result set: " + results.size());
    }

    private void basicLinkQuery(Realm realm) {
        showStatus("\nPerforming basic Link Query operation...");

        //查询Person的个数
        showStatus("Number of persons: " + realm.where(Person.class).count());

        //查询到所有 Cat 的 Name 为 Tiger 的 Person
        RealmResults<Person> results = realm.where(Person.class).equalTo("cats.name", "Tiger").findAll();

        showStatus("Size of result set: " + results.size());
    }

    private String complexReadWrite() {
        String status = "\nPerforming complex Read/Write operation...";

        //打开默认的Realm。 所有的线程 必须 使用 自己的Realm引用，不能跨线程使用
        Realm realm = Realm.getInstance(realmConfig);

        //在transaction中添加 10个 person
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Dog fido = realm.createObject(Dog.class);
                fido.name = "fido";
                for (int i = 0; i < 10; i++) {
                    Person person = realm.createObject(Person.class);
                    person.setId(i);
                    person.setName("Person no. " + i);
                    person.setAge(i);
                    person.setDog(fido);

                    /*tempReference字段有@Ignore注解
                    注解 @Ignore 意味着一个字段不应该被保存到 Realm。
                    某些时候输入的信息包含比模型更多的字段，而你不希望处理这些未使用的数据字段，
                    你可以用 @Ignore 来标识这些你希望被 Realm 忽略的字段。*/
                    person.setTempReference(42);

                    for (int j = 0; j < i; j++) {
                        Cat cat = realm.createObject(Cat.class);
                        cat.name = "Cat_" + j;
                        person.getCats().add(cat);
                    }
                }
            }
        });

        // 查询Person的个数
        status += "\nNumber of persons: " + realm.where(Person.class).count();

        // 遍历所有的对象
        for (Person pers : realm.where(Person.class).findAll()) {
            String dogName;
            if (pers.getDog() == null) {
                dogName = "None";
            } else {
                dogName = pers.getDog().name;
            }
            status += "\n" + pers.getName() + ":" + pers.getAge() + " : " + dogName + " : " + pers.getCats().size();
        }

        // 排序
        RealmResults<Person> sortedPersons = realm.where(Person.class).findAllSorted("age", Sort.DESCENDING);
        status += "\nSorting " + sortedPersons.last().getName() + " == " + realm.where(Person.class).findFirst().getName();

        //关闭realm
        realm.close();

        return status;
    }

    private String complexQuery() {
        String status = "\n\nPerforming complex Query operation...";

        Realm realm = Realm.getInstance(realmConfig);

        // 查询Person的个数
        status += "\nNumber of persons: " + realm.where(Person.class).count();

        // 查询 所有 age 在 7-9 之间的Person
        RealmResults<Person> results = realm.where(Person.class)
                .between("age", 7, 9)       // Notice implicit "and" operation
                .beginsWith("name", "Person").findAll();
        status += "\nSize of result set: " + results.size();

        //关闭realm
        realm.close();

        return status;
    }
}

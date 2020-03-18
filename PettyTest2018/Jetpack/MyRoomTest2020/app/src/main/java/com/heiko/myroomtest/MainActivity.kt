package com.heiko.myroomtest

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    private lateinit var mPersonDao: PersonDao

    companion object {
        const val TAG = "Z-Main"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var personDataBase = PersonDatabase.getInstance(application)
        mPersonDao = personDataBase.getPersonDao()

        val btnAdd = findViewById<Button>(R.id.btn_add)
        btnAdd.setOnClickListener {
            Thread() {
                kotlin.run {
                    val person1 = Person("a" + System.currentTimeMillis(), 18)
                    person1.lastName = "lastName hhh"
                    var id = mPersonDao.insertPerson(person1)
                    Log.i(TAG, "添加数据:$id")
                    val person = mPersonDao.getPersonById(id.toInt())
                    Log.i(TAG, "person:$person")
                }
            }.start()
        }

        var btnUpdate = findViewById<Button>(R.id.btn_update)
        btnUpdate.setOnClickListener {
            thread {
                val person = mPersonDao.getPersonById(1)
                Log.i(TAG, "person:$person")
                person.name = "b" + System.currentTimeMillis()
                val result = mPersonDao.updatePerson(person)
                Log.i(TAG, "result:$result")
                val person2 = mPersonDao.getPersonById(1)
                Log.i(TAG, "person2:$person2")
            }
        }

        val btnQuery = findViewById<Button>(R.id.btn_query)
        btnQuery.setOnClickListener {
            thread {
                val person = mPersonDao.getPersonById(1)
                Log.i(TAG, "person:$person")
            }
        }

        val btnDelete = findViewById<Button>(R.id.btn_delete)
        btnDelete.setOnClickListener {
            thread {
                val person = mPersonDao.getPersonById(1)
               val result = mPersonDao.deletePerson(person)
                Log.i(TAG, "result:$result")
            }
        }
    }
}

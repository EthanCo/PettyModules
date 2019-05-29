package com.heiko.roomtest

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //操作数据库增删查
        var mAppDatabase = AppDatabase.getInstance(this.getApplication());
        var user1 = User()
        user1.firstName = "name1"
        user1.lastName = "last1"
        var user2 = User()
        user2.firstName = "name2"
        user2.lastName = "last2"
        Thread {
            kotlin.run {
                mAppDatabase.userDao().delete(user1)
                mAppDatabase.userDao().insert(user2)
                val all = mAppDatabase.userDao().getAll();
                runOnUiThread {
                    Toast.makeText(this, "length:" + all.size, Toast.LENGTH_LONG).show()
                    Log.i("Z-Main", "all[0]:" + all[0].firstName+" "+all[0].lastName)
                }
            }
        }.start()

        mAppDatabase.userDao().usersLiveData.observe(this, Observer {
            Log.i("Z-Main", "usersLiveData:" + it?.size)
        })

        mAppDatabase.userDao().usersFlowable.subscribe {
            Log.i("Z-Main", "subscribe:" + it.size)
        }
    }
}

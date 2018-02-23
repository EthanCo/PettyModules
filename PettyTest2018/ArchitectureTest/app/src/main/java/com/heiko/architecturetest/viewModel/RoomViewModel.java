package com.heiko.architecturetest.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.heiko.architecturetest.lifecycle.User;
import com.heiko.architecturetest.room.AppDatabase;

import java.util.Date;
import java.util.List;

/**
 * 基于Room数据库的ViewModel
 *
 * @author EthanCo
 * @since 2018/2/23
 */

public class RoomViewModel extends AndroidViewModel {//ViewModel无法获取到Context，AndroidViewModel 可以获取到Context
    private final AppDatabase _appDatabase;
    //对于LiveData而言，我们一般使用它的实现类MutableLiveData
    private MutableLiveData<List<User>> users;

    public RoomViewModel(@NonNull Application application) {
        super(application);
        _appDatabase = AppDatabase.getInMemoryDatabase(application);
    }

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asyncronous operation to fetch users.
       /* List<User> users1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users1.add(new User(String.valueOf(i)));
        }
        users.postValue(users1);*/


        //SystemClock.sleep(1000);
        List<User> usersRoom = _appDatabase.userModal().loadAllUsers();
        Log.i("Z-Room", "usersRoom:"+usersRoom.size());
        if (usersRoom == null || usersRoom.size() == 0) {
            _appDatabase.userModal().insertUser(new User(new Date().toString()));
            _appDatabase.userModal().insertUser(new User(new Date().toString()));
            _appDatabase.userModal().insertUser(new User(new Date().toString()));
            _appDatabase.userModal().insertUser(new User(new Date().toString()));
            _appDatabase.userModal().insertUser(new User(new Date().toString()));
            _appDatabase.userModal().insertUser(new User(new Date().toString()));
            _appDatabase.userModal().insertUser(new User(new Date().toString()));
            _appDatabase.userModal().insertUser(new User(new Date().toString()));
            //_appDatabase.userModal().insertUser(new User(new Date().toString()));
            //_appDatabase.userModal().insertUser(new User(new Date().toString()));
            usersRoom = _appDatabase.userModal().loadAllUsers();
        }

        users.postValue(usersRoom);
    }

    public void addUser(User user) {
        List<User> users1 = users.getValue();
        users1.add(user);
        users.postValue(users1);
    }

    public void addUserByRoom(User user) {
        AppDatabase _appDatabase = AppDatabase.getInMemoryDatabase(getApplication());
        _appDatabase.userModal().insertUser(user);
        if (users != null) {
            users.postValue(_appDatabase.userModal().loadAllUsers());
        }
    }
}

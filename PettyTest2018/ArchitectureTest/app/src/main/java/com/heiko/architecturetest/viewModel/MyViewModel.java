package com.heiko.architecturetest.viewModel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.heiko.architecturetest.lifecycle.User;

import java.util.ArrayList;
import java.util.List;

/**
 * 普通ViewModel
 *
 * @author EthanCo
 * @since 2018/2/23
 */

public class MyViewModel extends ViewModel { //ViewModel无法获取到Context，AndroidViewModel 可以获取到Context
    //对于LiveData而言，我们一般使用它的实现类MutableLiveData
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asyncronous operation to fetch users.
        List<User> users1 = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            users1.add(new User(String.valueOf(i)));
        }
        users.postValue(users1);
    }

    public void addUser(User user) {
        List<User> users1 = users.getValue();
        users1.add(user);
        users.postValue(users1);
    }
}

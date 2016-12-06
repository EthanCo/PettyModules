package com.ethanco.aidlservice;

import com.ethanco.aidlservice.bean.Book;

interface IOnNewBookArrivedListener {
    void onNewBookArrived(in Book newBook);
}

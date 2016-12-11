package com.ethanco.aidlservice;

import com.ethanco.aidlservice.bean.Book;
import com.ethanco.aidlservice.IOnNewBookArrivedListener;

interface IBookManager {
     List<Book> getBookList();
     void addBook(in Book book);
     void registerListener(IOnNewBookArrivedListener listener);
     void unregisterListener(IOnNewBookArrivedListener listener);
}
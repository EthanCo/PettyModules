package com.example.fengh.recyclerviewtest;

import android.view.View;
import android.widget.Toast;

import java.util.Date;

/**
 * Created by luoxiongwen on 16/10/24.
 */

public class MoviePresenter {
    public void buyTicket(View view, Movie movie) {
        Toast.makeText(view.getContext(), "buy ticket: " + movie.name, Toast.LENGTH_SHORT).show();
        movie.setName("Hello"+new Date());
    }
}

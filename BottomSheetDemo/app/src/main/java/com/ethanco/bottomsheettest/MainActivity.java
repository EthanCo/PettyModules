package com.ethanco.bottomsheettest;

import android.os.Bundle;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

/**
 * BottomSheet
 * 详情见
 * http://blog.csdn.net/qibin0506/article/details/51002241
 * https://github.com/android-cjj/BottomSheets
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_show).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                select(view);
            }
        });
    }

    public void select(View view) {
        //RecyclerView recyclerView = (RecyclerView) LayoutInflater.from(this).inflate(R.layout.list, null);
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        Adapter adapter = new Adapter();
        recyclerView.setAdapter(adapter);

        final BottomSheetDialog dialog = new BottomSheetDialog(this);
        dialog.setContentView(recyclerView);
        dialog.show();
        adapter.setOnItemClickListener(new Adapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String text) {
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    static class Adapter extends RecyclerView.Adapter<Adapter.Holder> {

        private OnItemClickListener mItemClickListener;

        public void setOnItemClickListener(OnItemClickListener li) {
            mItemClickListener = li;
        }

        @Override
        public Adapter.Holder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
            return new Holder(item);
        }

        @Override
        public void onBindViewHolder(final Adapter.Holder holder, int position) {
            holder.tv.setText("item " + position);
            if (mItemClickListener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mItemClickListener.onItemClick(holder.getLayoutPosition(),
                                holder.tv.getText().toString());
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return 50;
        }

        class Holder extends RecyclerView.ViewHolder {
            TextView tv;

            public Holder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text);
            }
        }

        interface OnItemClickListener {
            void onItemClick(int position, String text);
        }
    }
}

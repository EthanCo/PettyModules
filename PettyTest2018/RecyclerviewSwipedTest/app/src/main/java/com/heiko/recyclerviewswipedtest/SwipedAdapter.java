package com.heiko.recyclerviewswipedtest;

import android.content.Context;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * SwipedAdapter
 *
 * @author EthanCo
 * @since 2018/5/10
 */

public class SwipedAdapter extends RecyclerView.Adapter<SwipedAdapter.SwipedViewHolder> {

    // touch 点击开始时间
    private long startTime;
    // touch 间隔时间  用于分辨是否是 "点击"
    private static final long SPACE_TIME = 100;
    private ItemTouchHelper mItemTouchHelper;
    private boolean isEditMode;

    private List<Bean> datas = new ArrayList<>();
    private Context context;

    public SwipedAdapter(List<Bean> datas, Context context,ItemTouchHelper itemTouchHelper) {
        this.datas = datas;
        this.context = context;
        this.mItemTouchHelper = itemTouchHelper;
    }

    @Override
    public SwipedViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        LayoutInflater mInflater = LayoutInflater.from(context);
        View itemView = mInflater.inflate(R.layout.item_swiped, parent, false);
        final SwipedViewHolder holder = new SwipedViewHolder(itemView);

        holder.tvItemInfo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!isEditMode) {
                    RecyclerView recyclerView = ((RecyclerView) parent);
                    startEditMode(recyclerView);
                }
                mItemTouchHelper.startDrag(holder);
                return true;
            }
        });

        holder.tvItemInfo.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (MotionEventCompat.getActionMasked(event)) {
                    case MotionEvent.ACTION_DOWN:
                        startTime = System.currentTimeMillis();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (System.currentTimeMillis() - startTime > SPACE_TIME) {
                            /*if (!isEditMode) {
                                RecyclerView recyclerView = ((RecyclerView) parent);
                                startEditMode(recyclerView);
                            }*/
                            mItemTouchHelper.startDrag(holder);
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                    case MotionEvent.ACTION_UP:
                        startTime = 0;
                        break;
                }
                return false;
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(final SwipedViewHolder holder, int position) {
        holder.tvItemInfo.setText(datas.get(position).getName());
        int visible = datas.get(position).isChecked() ? View.VISIBLE : View.GONE;
        holder.tvLeft.setVisibility(visible);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    /**
     * 开启编辑模式
     *
     * @param parent
     */
    private void startEditMode(RecyclerView parent) {
        isEditMode = true;

        int visibleChildCount = parent.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = parent.getChildAt(i);
            View viewEdit = view.findViewById(R.id.tv_left);
            if (viewEdit != null) {
                viewEdit.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * 完成编辑模式
     *
     * @param parent
     */
    private void cancelEditMode(RecyclerView parent) {
        isEditMode = false;

        int visibleChildCount = parent.getChildCount();
        for (int i = 0; i < visibleChildCount; i++) {
            View view = parent.getChildAt(i);
            View viewEdit = view.findViewById(R.id.tv_left);
            if (viewEdit != null) {
                viewEdit.setVisibility(View.INVISIBLE);
            }
        }
    }

    public static class SwipedViewHolder extends RecyclerView.ViewHolder {
        public final TextView tvItemInfo;
        private final TextView tvLeft;

        public SwipedViewHolder(View itemView) {
            super(itemView);

            tvItemInfo = itemView.findViewById(R.id.tv_item_info);
            tvLeft = itemView.findViewById(R.id.tv_left);
        }
    }
}

package com.example.fengh.recyclerviewtest;

import android.graphics.Color;
import android.text.TextPaint;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

/**
 * TODO
 *
 * @author EthanCo
 * @since 2017/10/24
 */

public class PullToRefreshAdapter extends BaseQuickAdapter<Status, BaseViewHolder> {
    public PullToRefreshAdapter() {
        super(R.layout.layout_animation, null);
    }

    @Override
    protected void convert(BaseViewHolder helper, Status item) {
        switch (helper.getLayoutPosition() % 3) {
            case 0:
                helper.setImageResource(R.id.img, R.mipmap.animation_img1);
                break;
            case 1:
                helper.setImageResource(R.id.img, R.mipmap.animation_img2);
                break;
            case 2:
                helper.setImageResource(R.id.img, R.mipmap.animation_img3);
                break;
        }
        helper.setText(R.id.tweetText, "Hoteis in Rio de Janeiro");
        ((TextView) helper.getView(R.id.tweetText)).setText("position:" + helper.getLayoutPosition());
    }

    ClickableSpan clickSpan = new ClickableSpan() {
        @Override
        public void onClick(View widget) {
            Toast.makeText(widget.getContext(), "时间触发了 nedes", Toast.LENGTH_LONG).show();
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(Color.BLUE);
            ds.setUnderlineText(true);
        }
    };
}

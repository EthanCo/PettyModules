package com.ethanco.androidiconicstest;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;
import android.widget.TextView;

import com.mikepenz.google_material_typeface_library.GoogleMaterial;
import com.mikepenz.iconics.IconicsDrawable;

public class MainActivity extends BaseActivity {

    private TextView tv2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv2 = (TextView) findViewById(R.id.test2);
        SpannableString sb = new SpannableString(tv2.getText());
        IconicsDrawable star = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_star).sizeDp(48).paddingDp(4);
        IconicsDrawable _android = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_android).sizeDp(48).paddingDp(4).color(getResources().getColor(R.color.green));
        IconicsDrawable favourite1 = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_favorite).sizeDp(48).paddingDp(4).color(getResources().getColor(R.color.colorAccent));
        IconicsDrawable favourite2 = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_favorite).sizeDp(36).paddingDp(4).color(getResources().getColor(R.color.colorAccent));
        IconicsDrawable favourite3 = new IconicsDrawable(this, GoogleMaterial.Icon.gmd_favorite).sizeDp(24).paddingDp(4).color(getResources().getColor(R.color.colorAccent));
        sb.setSpan(new ImageSpan(star, DynamicDrawableSpan.ALIGN_BOTTOM), 7, 8, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new ImageSpan(_android, DynamicDrawableSpan.ALIGN_BOTTOM), 11, 12, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        sb.setSpan(new ImageSpan(favourite1, DynamicDrawableSpan.ALIGN_BOTTOM), sb.length() - 3, sb.length(), Spannable.SPAN_MARK_MARK);
        sb.setSpan(new ImageSpan(favourite2, DynamicDrawableSpan.ALIGN_BOTTOM), sb.length() - 2, sb.length(), Spannable.SPAN_MARK_MARK);
        sb.setSpan(new ImageSpan(favourite3, DynamicDrawableSpan.ALIGN_BOTTOM), sb.length() - 1, sb.length(), Spannable.SPAN_MARK_MARK);
        tv2.setText(sb);
    }
}

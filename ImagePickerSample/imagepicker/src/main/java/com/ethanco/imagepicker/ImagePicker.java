package com.ethanco.imagepicker;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description 上传图片View ：对recycleview的封装
 * Created by EthanCo on 2016/3/9.
 */
public class ImagePicker extends FrameLayout {
    private List<Uri> data = new ArrayList<>();
    //列数
    private int spanCount = 3;

    public RecyclerView recyclerViewUpload;
    public ImagePickerAdapter uploadingImgAdapter;
    public GridLayoutManager gridLayoutManager;

    public ImagePicker(Context context) {
        super(context);
        initView(context);
    }

    public ImagePicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public ImagePicker(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initData(List<Uri> data) {
        this.data = data;
        uploadingImgAdapter = new ImagePickerAdapter(data) {
            @Override
            protected void displayImage(Uri uri, Object imgUpload) {
                if (disPlayListener != null) {
                    disPlayListener.disPlayImage(uri, imgUpload);
                }
            }

            @Override
            protected void onUploadButtonClick(View v) {
                if (uploadButtonClickListener != null) {
                    uploadButtonClickListener.OnUploadButtonClick(v);
                }
            }
        };
        recyclerViewUpload.setAdapter(uploadingImgAdapter);
        gridLayoutManager = new GridLayoutManager(getContext(), spanCount);
        recyclerViewUpload.setLayoutManager(gridLayoutManager);
    }

    public void addData(Uri uri) {
        this.data.add(uri);
        uploadingImgAdapter.notifyItemInserted(data.size() - 1);
    }

    private void initView(Context context) {
        View view = View.inflate(context, R.layout.layout_uploading_img, this);
        recyclerViewUpload = (RecyclerView) view.findViewById(R.id.recyclerview_uploading_img);
        initData(new ArrayList<Uri>());
    }

    public interface UploadButtonClickListener {
        void OnUploadButtonClick(View v);
    }

    public interface DisPlayListener {
        void disPlayImage(Uri uri, Object imgUpload);
    }

    public void setUploadButtonClickListener(UploadButtonClickListener uploadButtonClickListener) {
        this.uploadButtonClickListener = uploadButtonClickListener;
    }

    private UploadButtonClickListener uploadButtonClickListener;

    public void setDisPlayListener(DisPlayListener disPlayListener) {
        this.disPlayListener = disPlayListener;
    }

    private DisPlayListener disPlayListener;
}

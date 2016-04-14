package com.ethanco.imagepicker;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.List;

/**
 * @Description 上传图片Adapter
 * Created by EthanCo on 2016/3/9.
 */
public abstract class ImagePickerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_BOTTOM = 219;
    private static final int TYPE_ITEM = 951;
    private final List<Uri> data;

    public ImagePickerAdapter(List<Uri> data) {
        this.data = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_BOTTOM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uploading_bottom, parent, false);
            return new BottomViewHolder(view);
        }
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_uploading_img, parent, false);
        return new ItemViewHodler(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof BottomViewHolder) {
            Log.i("Z-UploadingImgAdapter", "onBindViewHolder BottomViewHolder: ");
            BottomViewHolder bottomViewHolder = (BottomViewHolder) holder;
            bottomViewHolder.llUploading.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onUploadButtonClick(v);
                }
            });
        } else {
            Log.i("Z-UploadingImgAdapter", "onBindViewHolder Item: ");
            final ItemViewHodler itemViewHodler = (ItemViewHodler) holder;
            Log.i("Z-UploadingImgAdapter", "onBindViewHolder : " + data.get(position));
            //itemViewHodler.imgUpload.setImageURI(data.get(position));
            //displayImage(data.get(position), itemViewHodler.imgUpload);
            itemViewHodler.imgDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delData(position);
                }
            });
        }
    }

    protected abstract void displayImage(Uri uri, Object imgUpload);

    public void delData(Uri url) {
        int index = data.indexOf(url);
        if (index > -1) {
            data.remove(url);
            notifyItemRemoved(index);
        }
    }

    public void delData(int position) {
        data.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(0, data.size() + 1);
    }

    protected abstract void onUploadButtonClick(View v);

    @Override
    public int getItemCount() {
        return data.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return TYPE_BOTTOM;
        }
        return TYPE_ITEM;
    }

    class ItemViewHodler extends RecyclerView.ViewHolder {

        private final Object imgUpload;
        private final ImageView imgDel;

        public ItemViewHodler(View itemView) {
            super(itemView);
            imgUpload = itemView.findViewById(R.id.img_upload);
            imgDel = (ImageView) itemView.findViewById(R.id.img_del);
        }
    }

    class BottomViewHolder extends RecyclerView.ViewHolder {

        private final FrameLayout llUploading;

        public BottomViewHolder(View itemView) {
            super(itemView);
            llUploading = (FrameLayout) itemView.findViewById(R.id.ll_uploading);
        }
    }
}

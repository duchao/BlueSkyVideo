package com.bluesky.video.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.bluesky.video.R;
import com.bluesky.video.component.ImageLoader;
import com.bluesky.video.ui.activity.SplashActivity;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public class PicGridAdapter extends BaseAdapter {

    private List<String> mImageList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public PicGridAdapter(Context context, List<String> imageList) {
        mImageList = imageList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return mImageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_search_pic_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ImageLoader.load(mContext, mImageList.get(position), viewHolder.mContentImage);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SplashActivity.class);
                intent.putExtra("type", "1");
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView mContentImage;
        public ViewHolder(View convertView) {
            mContentImage = (ImageView) convertView.findViewById(R.id.iv_item_search_pic_big_image);
        }
    }
}

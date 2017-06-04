package com.bluesky.video.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.component.ImageLoader;
import com.bluesky.video.model.bean.VideoBean;
import com.bluesky.video.ui.activity.PlayVideoActivity;

import java.util.List;

/**
 * Created by duchao on 2017/5/10.
 */

public class TabOneGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<VideoBean> mVideoList;
    public TabOneGridAdapter(Context context, List<VideoBean> videoList) {
        mContext = context;
        mVideoList = videoList;
    }

    @Override
    public int getCount() {
        return mVideoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mVideoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_one_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final VideoBean videoBean = mVideoList.get(position);
        viewHolder.videNameText.setText(videoBean.getVideoTitle());
        ImageLoader.load(mContext, videoBean.getVideoImageUrl(), viewHolder.videImage);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, PlayVideoActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url", videoBean.getVideoUrl());
                intent.putExtra("time", videoBean.getVideoLength());
                intent.putExtra("ly", "1");
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView videImage;
        TextView videNameText;
        public ViewHolder(View convertView) {
            videImage = (ImageView) convertView.findViewById(R.id.iv_fragment_tab_one_item);
            videNameText = (TextView) convertView.findViewById(R.id.tv_fragment_tab_one_item);
        }
    }
}

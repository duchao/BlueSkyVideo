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
import com.bluesky.video.ui.activity.SplashActivity;

import java.util.ArrayList;

/**
 * Created by duchao on 2017/5/10.
 */

public class VideoListGridAdapter extends BaseAdapter {

    private Context mContext;
    private ArrayList<VideoBean> mVideoList;

    public VideoListGridAdapter(Context context, ArrayList<VideoBean> videoList) {
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_video_list_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final VideoBean videoBean = mVideoList.get(position);
        viewHolder.videNameText.setText(videoBean.getVideoTitle());
        viewHolder.videTimeLength.setText(videoBean.getVideoLength());
        ImageLoader.load(mContext, videoBean.getVideoImageUrl(), viewHolder.videImage);
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

    class ViewHolder {
        ImageView videImage;
        TextView videNameText;
        TextView videTimeLength;

        public ViewHolder(View convertView) {
            videImage = (ImageView) convertView.findViewById(R.id.iv_activity_video_list_big_image);
            videNameText = (TextView) convertView.findViewById(R.id.tv_activity_video_list_grid_name);
            videTimeLength = (TextView) convertView.findViewById(R.id.tv_activity_video_list_time_length);
        }
    }
}

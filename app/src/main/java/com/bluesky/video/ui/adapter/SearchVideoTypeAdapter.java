package com.bluesky.video.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.model.bean.SearchVideoTypeBean;
import com.bluesky.video.ui.activity.SplashActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public class SearchVideoTypeAdapter extends BaseAdapter {
    private Context mContext;
    private List<SearchVideoTypeBean> mSearchVideoTypeList;
    private LayoutInflater mLayoutInflater;
    private String mKey;
    public SearchVideoTypeAdapter(Context context, List<SearchVideoTypeBean> searchVideoTypeList, String key) {
        mContext = context;
        mSearchVideoTypeList = searchVideoTypeList;
        mLayoutInflater = LayoutInflater.from(mContext);
        mKey = key;
    }
    @Override
    public int getCount() {
        return mSearchVideoTypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSearchVideoTypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_search_video_type_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SearchVideoTypeBean searchVideoTypeBean = mSearchVideoTypeList.get(position);

        String videoTitle = searchVideoTypeBean.getVideoTitle();
        if (videoTitle.contains(mKey)) {
            int start = videoTitle.indexOf(this.mKey);
            int end =  start + mKey.length();
            SpannableString newVideoTitle = new SpannableString(videoTitle);
            newVideoTitle.setSpan(new ForegroundColorSpan(Color.RED),start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            viewHolder.title.setText(newVideoTitle);
        } else {
            viewHolder.title.setText(videoTitle);
        }
        viewHolder.hot.setText("视频热度:" + searchVideoTypeBean.getPlayCount() );
        viewHolder.length.setText("视频长度:" + searchVideoTypeBean.getVideoLength());
        String[] imageUrls = searchVideoTypeBean.getVideoImageUrl().split(",");
        List<String> imageList = new ArrayList<>();
        for (String imageUrl: imageList) {
            imageList.add(imageUrl);
        }
        PicGridAdapter picGridAdapter = new PicGridAdapter(mContext, imageList);
        viewHolder.recycleserch.setAdapter(picGridAdapter);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SplashActivity.class);
                intent.putExtra("type", "1");
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }
    private class ViewHolder {
        TextView copy;
        TextView hot;
        TextView length;
        TextView play;
        GridView recycleserch;
        TextView title;
        public ViewHolder(View convertView) {
            copy = (TextView)convertView.findViewById(R.id.tv_item_search_video_type_copy);
            hot = (TextView)convertView.findViewById(R.id.tv_item_search_video_type_hot);
            length = (TextView)convertView.findViewById(R.id.tv_item_search_video_type_timelenth);
            play = (TextView)convertView.findViewById(R.id.tv_item_search_video_type_play);
            title = (TextView)convertView.findViewById(R.id.tv_item_search_video_type_title);
            recycleserch = (GridView) convertView.findViewById(R.id.gv_item_search_video_type_recycleserch);
        }
    }
}

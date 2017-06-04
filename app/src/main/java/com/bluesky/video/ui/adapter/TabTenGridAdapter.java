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
import com.bluesky.video.model.bean.ForumBean;
import com.bluesky.video.ui.activity.SplashActivity;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public class TabTenGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<ForumBean> mForumList;
    private LayoutInflater mLayoutInflater;

    public TabTenGridAdapter(Context context, List<ForumBean> forumList) {
        mContext = context;
        mForumList = forumList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mForumList.size();
    }

    @Override
    public Object getItem(int position) {
        return mForumList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_tab_ten_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ForumBean forumBean = mForumList.get(position);
        viewHolder.forumTitle.setText(forumBean.getTitle());
        viewHolder.forumNum.setText("发帖数：" + forumBean.getBbbSendNum() + "   回帖数:" + forumBean.getBbsReturnNum());
        viewHolder.formContent.setText(forumBean.getContent());
        ImageLoader.load(mContext, forumBean.getImageURL(), viewHolder.forumImage);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, SplashActivity.class);
                intent.putExtra("type", 1);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    private class ViewHolder {
        ImageView forumImage;
        TextView forumTitle;
        TextView forumNum;
        TextView formContent;
        public ViewHolder(View convertView) {
            forumImage = (ImageView) convertView.findViewById(R.id.iv_item_tab_ten_forum_image);
            forumTitle = (TextView) convertView.findViewById(R.id.tv_item_tab_ten_forum_title);
            forumNum = (TextView) convertView.findViewById(R.id.tv_item_tab_ten_forum_num);
            formContent = (TextView) convertView.findViewById(R.id.tv_item_tab_ten_forum_content);
        }
    }
}

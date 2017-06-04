package com.bluesky.video.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bluesky.video.R;
import com.bluesky.video.model.bean.SearchKeyBean;
import com.bluesky.video.ui.activity.SplashActivity;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public class SearchKeyGridAdapter extends BaseAdapter {
    private Context mContext;
    private List<SearchKeyBean> mSearchKeyList;
    private LayoutInflater mLayoutInflater;
    public SearchKeyGridAdapter(Context context, List<SearchKeyBean> searchKeyList) {
        mContext = context;
        mSearchKeyList = searchKeyList;
        mLayoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return mSearchKeyList.size();
    }

    @Override
    public Object getItem(int position) {
        return mSearchKeyList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_search_key_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        SearchKeyBean searchKeyBean = mSearchKeyList.get(position);
        viewHolder.title.setText(searchKeyBean.getVideoTitle());
        viewHolder.content.setText("磁力链接" + "magnet:?xt=urn:btih:" + searchKeyBean.getMagLink() + "&dn=" + searchKeyBean.getVideoTitle());
        viewHolder.file.setText("文件大小" + Long.valueOf(searchKeyBean.getVideoSize()) /100000L + "MB");
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
        TextView content;
        TextView copy;
        TextView file;
        TextView play;
        TextView title;
        public ViewHolder(View convertView) {
            content = (TextView) convertView.findViewById(R.id.tv_item_search_key_content);
            copy = (TextView) convertView.findViewById(R.id.tv_item_search_key_copy);
            file = (TextView) convertView.findViewById(R.id.tv_item_search_key_file);
            play = (TextView) convertView.findViewById(R.id.tv_item_search_key_play);
            title = (TextView) convertView.findViewById(R.id.tv_item_search_key_title);
        }
    }
}

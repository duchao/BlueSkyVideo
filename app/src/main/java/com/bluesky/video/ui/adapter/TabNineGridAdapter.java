package com.bluesky.video.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.bluesky.video.R;
import com.bluesky.video.model.bean.PinDaoBean;
import com.bluesky.video.ui.activity.SearchListActivity;

import java.util.List;

/**
 * Created by duchao on 2017/6/2.
 */

public class TabNineGridAdapter extends BaseAdapter {

    private List<PinDaoBean> mPinDaoList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    public TabNineGridAdapter(Context context, List<PinDaoBean> pinDaoList) {
        mPinDaoList = pinDaoList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mPinDaoList.size();
    }

    @Override
    public Object getItem(int position) {
        return mPinDaoList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.item_tab_nine_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final PinDaoBean pinDaoBean = mPinDaoList.get(position);
        viewHolder.searchButton.setText(pinDaoBean.getVideoTypeName());
        viewHolder.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 打开搜索activity
                String videoName = pinDaoBean.getVideoTypeName();
                String videoId = pinDaoBean.getVideTypeId();
                Intent intent = new Intent(mContext, SearchListActivity.class);
                intent.putExtra("key", videoName);
                intent.putExtra("id", videoId);
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        Button searchButton;
        public ViewHolder(View convertView) {
            searchButton = (Button) convertView.findViewById(R.id.btn_item_tab_nine_search_key);
        }

    }
}

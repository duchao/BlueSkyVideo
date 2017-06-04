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
import com.bluesky.video.model.bean.PinDaoBean;
import com.bluesky.video.ui.activity.VideoListActivity;

import java.util.List;

/**
 * Created by duchao on 2017/5/10.
 */

public class TabEightGridAdapter extends BaseAdapter {

    private Context mContext;
    private List<PinDaoBean> mPinDaoList;
    public TabEightGridAdapter(Context context, List<PinDaoBean> pinDaoList) {
        mContext = context;
        mPinDaoList = pinDaoList;
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
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_tab_eight_gridview, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final PinDaoBean pinDaoBean = mPinDaoList.get(position);
        ImageLoader.load(mContext, pinDaoBean.getImageUrl(), viewHolder.icon);
        viewHolder.name.setText(pinDaoBean.getVideoTypeName());
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, VideoListActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", pinDaoBean.getVideTypeId());
                intent.putExtra("name", pinDaoBean.getVideoTypeName());
                mContext.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        ImageView icon;
        TextView name;
        public ViewHolder(View convertView) {
            icon = (ImageView) convertView.findViewById(R.id.iv_item_tab_eight_grid_icon);
            name = (TextView) convertView.findViewById(R.id.tv_item_tab_eight_grid_name);
        }
    }
}

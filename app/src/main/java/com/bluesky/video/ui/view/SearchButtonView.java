package com.bluesky.video.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bluesky.video.R;

/**
 * Created by duchao on 2017/6/1.
 */

public class SearchButtonView extends RelativeLayout {

    private LinearLayout mSearchButon;
    private Context mContext;

    public SearchButtonView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.view_search_button, this, true);
        mSearchButon = (LinearLayout) findViewById(R.id.view_search_btn);
    }
}

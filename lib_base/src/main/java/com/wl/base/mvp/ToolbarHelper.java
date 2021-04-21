
package com.wl.base.mvp;

import android.graphics.Paint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

/**
 * Created by Moodd on 2017/3/3.
 */

public class ToolbarHelper {

    private AppCompatActivity mActivity;
    private Toolbar mToolbar;

    private ToolbarHelper() {
    }

    public ToolbarHelper(AppCompatActivity activity, Toolbar toolbar) {
        this.mToolbar = toolbar;
        this.mActivity = activity;
    }

    public static ToolbarHelper get(AppCompatActivity activity, Toolbar toolbar) {

        return new ToolbarHelper(activity,toolbar);
    }

    /**
     * 初始化 Toolbar,按返回关闭界面，标题居中
     *
     * @param resTitle
     */
    public void init(@StringRes int resTitle) {
        init(mActivity.getString(resTitle));
    }

    /**
     * 初始化 Toolbar,按返回关闭界面，标题居中
     *
     * @param title
     */
    public void init(CharSequence title) {
        init(title, true, true);
    }

    /**
     * 初始化 Toolbar
     *
     * @param title
     * @param homeAsUpEnabled
     */
    public void init(CharSequence title, boolean homeAsUpEnabled) {
        init(title, homeAsUpEnabled, false);
    }

    /**
     * 初始化 Toolbar
     *
     * @param resTitle
     * @param homeAsUpEnabled
     */
    public void init(@StringRes int resTitle, boolean homeAsUpEnabled) {
        init(mActivity.getString(resTitle), homeAsUpEnabled, false);
    }

    /**
     * 初始化 Toolbar
     *
     * @param resTitle
     * @param homeAsUpEnabled
     * @param isTitleCenter
     */
    public void init(@StringRes int resTitle, boolean homeAsUpEnabled, boolean isTitleCenter) {
        init(mActivity.getString(resTitle), homeAsUpEnabled, isTitleCenter);
    }

    /**
     * 初始化 Toolbar
     *
     * @param title
     * @param homeAsUpEnabled
     * @param isTitleCenter
     */
    public void init(CharSequence title, boolean homeAsUpEnabled, boolean isTitleCenter) {
        init(title, homeAsUpEnabled, isTitleCenter, false);
    }

    /**
     * 初始化 Toolbar
     *
     * @param resTitle
     * @param homeAsUpEnabled
     * @param isTitleCenter
     */
    public void init(@StringRes int resTitle, boolean homeAsUpEnabled, boolean isTitleCenter, boolean isSetSupportActionBar) {
        init(mActivity.getString(resTitle), homeAsUpEnabled, isTitleCenter, isSetSupportActionBar);
    }

    /**
     * 初始化 Toolbar
     *
     * @param title
     * @param homeAsUpEnabled
     * @param isTitleCenter
     */
    public void init(CharSequence title, boolean homeAsUpEnabled, boolean isTitleCenter, boolean isSetSupportActionBar) {
        mToolbar.setTitle(title);
        if (isTitleCenter) {
            setTitleCenter(mToolbar);
        }
        if (isSetSupportActionBar) {
            mActivity.setSupportActionBar(mToolbar);
            ActionBar actionBar = mActivity.getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(homeAsUpEnabled);
            }
        } else {
            if (homeAsUpEnabled) {
                setBackFinish(mToolbar);
            }
        }
    }

    /**
     * 设置Toolbar按返回关闭界面
     *
     * @param toolbar
     */
    public void setBackFinish(final Toolbar toolbar) {
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mActivity.onBackPressed();
            }
        });

    }

    /**
     * 设置Toolbar标题居中
     *
     * @param toolbar
     */
    public void setTitleCenter(Toolbar toolbar) {
        int childCount = toolbar.getChildCount();
        int left = toolbar.getContentInsetStartWithNavigation();
        //int left1 = toolbar.getContentInsetLeft();
        int deviceWidth = toolbar.getContext().getResources().getDisplayMetrics().widthPixels;
        String toolbarText = toolbar.getTitle().toString();

        for (int i = 0; i < childCount; i++) {
            View child = toolbar.getChildAt(i);
            if (child instanceof TextView) {
                TextView childTitle = (TextView) child;
                String childText = childTitle.getText().toString();
                if (TextUtils.equals(childText, toolbarText)) {
                    Paint p = childTitle.getPaint();
                    float textWidth = p.measureText(childText);
                    float tx = (deviceWidth - textWidth) / 2.0f - left;
                    childTitle.setTranslationX(tx);
                    break;
                }
            }
        }
    }

}

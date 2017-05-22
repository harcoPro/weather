package com.example.kirill.weather.ui.misc;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.kirill.weather.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageView extends LinearLayout {

    @BindView(R.id.message_view_text)           TextView message;
    @BindView(R.id.message_view_refresh)        AppCompatButton refresh;

    @Nullable
    private Callback callback;

    public MessageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        ButterKnife.bind(this);
    }

    public void bind(String msg) {
        bind(msg, null);
    }

    public void bind(@StringRes int msgResId) {
        bind(getResources().getString(msgResId), null);
    }

    public void bind(String msg, @Nullable Callback callback) {
        this.message.setText(msg);
        this.callback = callback;
        refresh.setVisibility(callback == null ? GONE : VISIBLE);
    }

    public void bind(@StringRes int stringResId, @Nullable Callback callback) {
        bind(getResources().getString(stringResId), callback);
    }

    public void bind(@StringRes int stringResId, @Nullable Callback callback, @ColorRes int color){
        this.message.setTextColor(getResources().getColor(color));
        bind(getResources().getString(stringResId), callback);
    }

    public interface Callback {
        void refresh();
    }

    @OnClick(R.id.message_view_refresh)
    void clickRefresh() {
        if (callback != null) {
            callback.refresh();
        }
    }

}

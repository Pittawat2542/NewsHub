package com.example.newshub.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

import com.example.newshub.activity.WebViewActivity;
import com.example.newshub.chrometab.CustomTabActivityHelper;

/**
 * Created by pitta on 22/3/2559.
 */
public class WebViewFallBack implements CustomTabActivityHelper.CustomTabFallback {
    @Override
    public void openUri(Activity activity, Uri uri) {
        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("Link", uri.toString());
        activity.startActivity(intent);
    }
}

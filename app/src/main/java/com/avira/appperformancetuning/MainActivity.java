package com.avira.appperformancetuning;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

public class MainActivity extends AppCompatActivity {
    private ViewStub mViewStub;
    private ImageView imageView_show_glide;
    private String URL_DOWNLOAD_IMAGE1 = "http://img.61gequ.com/allimg/2013-02/2167-130201101H7.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView_show_glide = (ImageView) findViewById(R.id.imageView_show_glide);

        mViewStub = (ViewStub) findViewById(R.id.viewStubId);
    }


    public void viewStubButton(View view) {
        //Glide监听加载图片进度
        Glide.with(this)
                .load(URL_DOWNLOAD_IMAGE1)
                .dontAnimate()
                //.placeholder(R.mipmap.ic_launcher)由于用了监听加载进度条，故不用站位图片
                .error(R.mipmap.ic_launcher)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(new GlideDrawableImageViewTarget(imageView_show_glide) {
                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        mViewStub.setVisibility(View.GONE);
                    }
                    //点击两次就会出现异常ViewStub must have a non-null ViewGroup viewParent
                    @Override
                    public void onLoadStarted(Drawable placeholder) {
                        super.onLoadStarted(placeholder);
                        if (mViewStub != null) {
                            mViewStub.inflate();
                        }
                    }

                    @Override
                    public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> animation) {
                        super.onResourceReady(resource, animation);
                        mViewStub.setVisibility(View.GONE);
                    }
                });
    }

    public void mergeButton(View view) {
        startActivity(new Intent(this, MergeActivity.class));
    }

    public void spaceButton(View view) {
        startActivity(new Intent(this, SpaceActivity.class));
    }
}

package com.scu.miomin.keeperplus.splash;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jakewharton.rxbinding.view.RxView;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.constants.APPStatu;
import com.scu.miomin.keeperplus.constants.APPString;
import com.scu.miomin.keeperplus.core.AppStatusTracker;
import com.scu.miomin.keeperplus.core.BaseActivity;
import com.scu.miomin.keeperplus.core.KeepPlusApp;
import com.scu.miomin.keeperplus.ui.CirclePageIndicator;
import com.scu.miomin.keeperplus.util.SharedPreferenceUtil;
import com.scu.miomin.keeperplus.util.UIHelper;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import rx.Subscriber;

/**
 * Created by 莫绪旻 on 15/7/29.
 */
public class SplashActivity extends BaseActivity {

    private static String FIRSTTIMEUSE = "first-time-use";

    @Bind(R.id.btnHome)
    Button btnHome;
    @Bind(R.id.indicator)
    CirclePageIndicator indicator;
    @Bind(R.id.guideImage)
    SimpleDraweeView guideImage;
    @Bind(R.id.pager)
    ViewPager pager;

    private GalleryPagerAdapter adapter;
    private int[] images = {
            R.drawable.newer01,
            R.drawable.newer02,
            R.drawable.newer03,
            R.drawable.newer04
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 将APP状态置为已正常启动
        AppStatusTracker.getInstance(getApplication()).setAppStatus(APPStatu.STATUS_ONLINE);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void getContentView() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
    }

    @Override
    protected void setUpView() {
        RxView.clicks(btnHome)
                .throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribe(new Subscriber<Void>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(Void aVoid) {
                        SharedPreferenceUtil.getInstance().putBoolean(FIRSTTIMEUSE, false);
                        UIHelper.showLogin(SplashActivity.this);
                    }
                });
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        Uri uri = Uri.parse("res://" + APPString.PACKAGE_NAME + "/" + R.drawable.img_welcome);
        guideImage.setImageURI(uri);

        final boolean firstTimeUse = SharedPreferenceUtil.getInstance().getBoolean(FIRSTTIMEUSE, true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firstTimeUse) {
                    Animation fadeOut = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.fadeout);
                    fadeOut.setFillAfter(true);
                    findViewById(R.id.guideImage).startAnimation(fadeOut);
                    initGuideGallery();
                } else {
                    if (KeepPlusApp.getInstance().AUTOLOGINSUCCEED)
                        UIHelper.showPatientHome(SplashActivity.this);
                    else
                        UIHelper.showLogin(SplashActivity.this);
                    finish();
                }
            }
        }, 2000);
    }

    private void initGuideGallery() {
        final Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fadein);

        indicator.setVisibility(View.VISIBLE);
        pager.setVisibility(View.VISIBLE);

        adapter = new GalleryPagerAdapter();
        pager.setAdapter(adapter);
        indicator.setViewPager(pager);

        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position == images.length - 1) {
                    btnHome.setVisibility(View.VISIBLE);
                    btnHome.startAnimation(fadeIn);
                } else {
                    btnHome.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    public class GalleryPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return images.length;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            SimpleDraweeView item = new SimpleDraweeView(SplashActivity.this);
            item.setScaleType(ImageView.ScaleType.CENTER_CROP);
            Uri uri = Uri.parse("res://" + APPString.PACKAGE_NAME + "/" + images[position]);
            item.setImageURI(uri);
            container.addView(item);
            return item;
        }

        @Override
        public void destroyItem(ViewGroup collection, int position, Object view) {
            collection.removeView((View) view);
        }
    }
}

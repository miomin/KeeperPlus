package com.scu.miomin.keeperplus.mvp.view.impl.fragment;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jph.takephoto.model.TImage;
import com.jph.takephoto.model.TResult;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.core.BaseFragment;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.mvp.view.impl.activity.FindUserActivity;
import com.scu.miomin.keeperplus.mvp.view.impl.activity.SettingActivity;
import com.scu.miomin.keeperplus.mvpcore.BaseToolbarMvpActivity;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;
import com.scu.miomin.keeperplus.util.TakePhotoHelper;

import java.io.File;
import java.util.ArrayList;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;
import me.drakeet.materialdialog.MaterialDialog;

/**
 * Created by 莫绪旻 on 16/11/29.
 */
public class HomeMeFragment extends BaseFragment {

    private final static String TITLE = "title";

    private TextView tvPhonenumber_top;
    private TextView tvPhonenumber;
    private TextView tvPatientname;
    private SimpleDraweeView ivHead;

    public static HomeMeFragment newInstance(String title) {
        HomeMeFragment fragment = new HomeMeFragment();
        Bundle bundle = new Bundle();
        bundle.putString(TITLE, title);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getArguments().getString(TITLE);
    }

    @Override
    protected View getContentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_home_me, container, false);
    }

    @Override
    protected void setUpView() {

        tvPatientname = (TextView) fragmentView.findViewById(R.id.tvUsername);
        tvPhonenumber_top = (TextView) fragmentView.findViewById(R.id.tvUserphone_top);
        tvPhonenumber = (TextView) fragmentView.findViewById(R.id.tvUserphone);
        ivHead = (SimpleDraweeView) fragmentView.findViewById(R.id.ivHead);

        String phonenumber = KeeperPlusCache.getInstance().getCurrentUser().getMobilePhoneNumber().substring(0, 3)
                + "****"
                + KeeperPlusCache.getInstance().getCurrentUser().getMobilePhoneNumber().substring(7, 11);

        tvPatientname.setText(KeeperPlusCache.getInstance().getCurrentUser().getUsername());
        tvPhonenumber_top.setText(phonenumber);
        tvPhonenumber.setText(phonenumber);

        fragmentView.findViewById(R.id.layout_setting).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SettingActivity.startActivity(getActivity());
            }
        });

        fragmentView.findViewById(R.id.layout_bind_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog mMaterialDialog = new MaterialDialog(getActivity());
                mMaterialDialog.setTitle("提示")
                        .setMessage("您绑定的手机号码是" + tvPhonenumber.getText() + ",鉴于安全，暂不支持绑定后的手机号修改，请悉知！")
                        .setPositiveButton("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();
            }
        });

        fragmentView.findViewById(R.id.layout_about_me).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog mMaterialDialog = new MaterialDialog(getActivity());
                mMaterialDialog.setTitle("关于开发者")
                        .setMessage("开发者：莫绪旻，阿里巴巴手机淘宝团队，客户端开发工程师" + "\n"
                                + "Github：https://github.com/miomin" + "\n"
                                + "Email：miomin_93@foxmail.com" + "\n")
                        .setPositiveButton("好的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();
            }
        });

        fragmentView.findViewById(R.id.layout_find_doctor).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FindUserActivity.startActivity(getActivity());
            }
        });

        fragmentView.findViewById(R.id.layout_quit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog mMaterialDialog = new MaterialDialog(getActivity());
                mMaterialDialog.setTitle("暂时不要退出哟")
                        .setMessage("再给其它两位专家看看，您先别退出哟~")
                        .setPositiveButton("好的", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();
            }
        });

        ivHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final MaterialDialog mMaterialDialog = new MaterialDialog(getActivity());
                mMaterialDialog.setTitle("提示")
                        .setMessage("是否打开相册，选择照片上传作为你的头像")
                        .setPositiveButton("确定", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                                TakePhotoHelper.of().selectPhoto(getTakePhoto());
                            }
                        })
                        .setNegativeButton("取消", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mMaterialDialog.dismiss();
                            }
                        });

                mMaterialDialog.show();
            }
        });
    }

    @Override
    protected void setUpData() {
        Uri uri = Uri.parse(KeeperPlusCache.getInstance().getCurrentUser().getHeadUrl());
        ivHead.setImageURI(uri);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getActivity() instanceof ToolbarActivity) {
            ((ToolbarActivity) getActivity()).hideToolbar();
        } else if (getActivity() instanceof BaseToolbarMvpActivity) {
            ((BaseToolbarMvpActivity) getActivity()).hideToolbar();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (getActivity() instanceof ToolbarActivity || getActivity() instanceof BaseToolbarMvpActivity) {
            ((ToolbarActivity) getActivity()).showToolbar();
        } else if (getActivity() instanceof BaseToolbarMvpActivity) {
            ((BaseToolbarMvpActivity) getActivity()).hideToolbar();
        }
    }

    @Override
    public void takeSuccess(TResult result) {
        super.takeSuccess(result);
        uploadHeader(result.getImages());
    }

    private void uploadHeader(ArrayList<TImage> images) {
        File file = new File(images.get(0).getCompressPath());
        final BmobFile bmobFile = new BmobFile(file);

        if (bmobFile == null) {
            Toast.makeText(getContext(), "头像获取失败", Toast.LENGTH_SHORT).show();
            return;
        }

        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    updateUserHeader(bmobFile.getFileUrl());
                } else {
                    Toast.makeText(getContext(), "头像上传失败", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onProgress(Integer value) {

            }
        });
    }

    private void updateUserHeader(final String headerUrl) {
        KeeperPlusCache.getInstance().getCurrentUser().setHeadUrl(headerUrl);

        Userbean userbean = new Userbean();
        userbean.setHeadUrl(headerUrl);
        userbean.update(KeeperPlusCache.getInstance().getCurrentUser().getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e == null) {
                    ivHead.setImageURI(Uri.parse(headerUrl));
                    Toast.makeText(getContext(), "头像上传成功", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "头像上传失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}

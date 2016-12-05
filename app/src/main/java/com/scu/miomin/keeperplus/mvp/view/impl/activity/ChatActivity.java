package com.scu.miomin.keeperplus.mvp.view.impl.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.Observer;
import com.netease.nimlib.sdk.msg.MessageBuilder;
import com.netease.nimlib.sdk.msg.MsgService;
import com.netease.nimlib.sdk.msg.MsgServiceObserve;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.scu.miomin.keeperplus.R;
import com.scu.miomin.keeperplus.adapter.ChatListAdapter;
import com.scu.miomin.keeperplus.constants.ActivityType;
import com.scu.miomin.keeperplus.mvp.cache.KeeperPlusCache;
import com.scu.miomin.keeperplus.mvp.model.ChatMessageBean;
import com.scu.miomin.keeperplus.mvp.model.Enum.ChatMsgTypeEnum;
import com.scu.miomin.keeperplus.mvp.model.Userbean;
import com.scu.miomin.keeperplus.toolbar.ToolbarActivity;
import com.scu.miomin.keeperplus.util.DateUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;

public class ChatActivity extends ToolbarActivity {

    @Bind(R.id.list_chat)
    ListView list_chat;

    @Bind(R.id.btn_showVoiceBtn)
    ImageView btn_showVoiceBtn;
    @Bind(R.id.btn_recordVoice)
    Button btn_recordVoice;
    @Bind(R.id.btn_sendText)
    ImageView btn_sendText;

    @Bind(R.id.edit_msg)
    EditText edit_msg;

    @Bind(R.id.layout_showEdit)
    LinearLayout layout_showEdit;
    @Bind(R.id.layout_send_img)
    LinearLayout layout_send_img;

    // 消息接受的观察者
    Observer<List<IMMessage>> incomingMessageObserver;

    private ChatListAdapter chatListAdapter;

    private String friendphone;
    private String textContent;
    private Userbean chatFriend;

    public static void startActivity(Context context, String phonenumber) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra("phonenumber", phonenumber);
        context.startActivity(intent);
    }

    @Override
    protected void getContentView() {
        setContentView(R.layout.activity_chat, ActivityType.MODE_TOOLBAR_BACK);
        //进入Activity时不打开软件盘
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        friendphone = getIntent().getStringExtra("phonenumber");
        chatFriend = KeeperPlusCache.getInstance().getFriendByID(friendphone);

        if (friendphone == null || chatFriend == null) {
            showToast("数据加载失败");
            finish();
            return;
        }
    }

    @Override
    protected void setUpView() {

        setUpTitle(chatFriend.getUsername());

        edit_msg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                if (edit_msg.getText().length() > 0) {
                    btn_showVoiceBtn.setVisibility(View.INVISIBLE);
                    btn_showVoiceBtn.setEnabled(false);
                    btn_sendText.setEnabled(true);
                    btn_sendText.setVisibility(View.VISIBLE);
                } else {
                    btn_showVoiceBtn.setVisibility(View.VISIBLE);
                    btn_sendText.setVisibility(View.INVISIBLE);
                    btn_showVoiceBtn.setEnabled(true);
                    btn_sendText.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edit_msg.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    //打开软键盘
                    pushInputView();
                    //将显示输入文本框的按钮变化为显示其他菜单的按钮
                    layout_send_img.setVisibility(View.VISIBLE);
                    layout_send_img.setEnabled(true);
                    layout_showEdit.setVisibility(View.INVISIBLE);
                    layout_showEdit.setEnabled(false);
                }
            }
        });

        layout_showEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //将显示输入文本框的按钮变换为显示其他菜单的按钮
                layout_send_img.setVisibility(View.VISIBLE);
                layout_send_img.setEnabled(true);
                layout_showEdit.setVisibility(View.INVISIBLE);
                layout_showEdit.setEnabled(false);

                //隐藏录制语音的按钮
                btn_recordVoice.setVisibility(View.INVISIBLE);
                btn_recordVoice.setEnabled(false);

                //显示输入文本框
                edit_msg.setVisibility(View.VISIBLE);

                //弹出软键盘
                pushInputView();
            }
        });

        btn_sendText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textContent = edit_msg.getText().toString();
                if (!TextUtils.isEmpty(textContent)) {
                    SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                    Date currentData = new Date(System.currentTimeMillis());
                    String time = format.format(currentData);
                    ChatMessageBean textMsg = new ChatMessageBean(KeeperPlusCache.getInstance().getCurrentUser().getMobilePhoneNumber(),
                            friendphone, textContent, time, ChatMsgTypeEnum.SEND_MSG);
                    //把terxtMsg发送到服务器
                    addMsg(textMsg);
                    edit_msg.setText("");
                }

                IMMessage message = MessageBuilder.createTextMessage(
                        chatFriend.getMobilePhoneNumber(), // 聊天对象的 ID，如果是单聊，为用户帐号，如果是群聊，为群组 ID
                        SessionTypeEnum.P2P, // 聊天类型，单聊或群组
                        textContent // 文本内容
                );

                // 发送消息。如果需要关心发送结果，可设置回调函数。发送完成时，会收到回调。如果失败，会有具体的错误码。
                NIMClient.getService(MsgService.class).sendMessage(message, true);
            }
        });

        btn_showVoiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //收起软键盘
                hideSoftInputView();

                //清空输入文本框的焦点
                edit_msg.clearFocus();

                //将显示其他菜单的按钮变换为显示输入文本框的按钮
                layout_send_img.setVisibility(View.INVISIBLE);
                layout_send_img.setEnabled(false);
                layout_showEdit.setVisibility(View.VISIBLE);
                layout_showEdit.setEnabled(true);

                //隐藏输入文本框
                edit_msg.setVisibility(View.GONE);
                //显示录音按键
                btn_recordVoice.setVisibility(View.VISIBLE);
                btn_recordVoice.setEnabled(true);
            }
        });

        layout_send_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void addMsg(ChatMessageBean msg) {
        chatListAdapter.addMsg(msg);
        list_chat.setSelection(chatListAdapter.getCount());
    }

    public void addHistoryMsg(ChatMessageBean msg) {
        chatListAdapter.addHistoryMsg(msg);
    }

    @Override
    protected void setUpData(Bundle savedInstanceState) {
        chatListAdapter = new ChatListAdapter(this,
                new ArrayList<ChatMessageBean>(), chatFriend.getMobilePhoneNumber());
        list_chat.setAdapter(chatListAdapter);

        incomingMessageObserver =
                new Observer<List<IMMessage>>() {
                    @Override
                    public void onEvent(List<IMMessage> messages) {
                        // 处理新收到的消息，为了上传处理方便，SDK 保证参数 messages 全部来自同一个聊天对象。
                        for (int i = 0; i < messages.size(); i++) {
                            ChatMessageBean textMsg = new ChatMessageBean(messages.get(i).getSessionId(),
                                    KeeperPlusCache.getInstance().getCurrentUser().getMobilePhoneNumber(),
                                    messages.get(i).getContent(), DateUtil.transferLongToDate("HH:mm", messages.get(i).getTime()),
                                    ChatMsgTypeEnum.RECIVE_MSG);
                            addMsg(textMsg);
                        }
                    }
                };

        // 注册观察者对象
        NIMClient.getService(MsgServiceObserve.class)
                .observeReceiveMessage(incomingMessageObserver, true);


//        NIMClient.getService(MsgService.class).queryMessageListEx(messages.get(messages.size() - 1), QueryDirectionEnum.QUERY_OLD, 50, false)
//                .setCallback(new RequestCallback<List<IMMessage>>() {
//                    @Override
//                    public void onSuccess(List<IMMessage> imMessages) {
//                        for (IMMessage message : imMessages) {
//                            ChatMessageBean textMsg = new ChatMessageBean(message.getSessionId(),
//                                    KeeperPlusCache.getInstance().getCurrentUser().getMobilePhoneNumber(),
//                                    message.getContent(), DateUtil.transferLongToDate("HH:mm", message.getTime()),
//                                    ChatMsgTypeEnum.RECIVE_MSG);
//                            addHistoryMsg(textMsg);
//                        }
//                    }
//
//                    @Override
//                    public void onFailed(int i) {
//                    }
//
//                    @Override
//                    public void onException(Throwable throwable) {
//                    }
//                });
    }

    // 隐藏软键盘
    public void hideSoftInputView() {
        InputMethodManager manager = ((InputMethodManager) this
                .getSystemService(Activity.INPUT_METHOD_SERVICE));
        if (getWindow().getAttributes().softInputMode != WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN) {
            if (getCurrentFocus() != null)
                manager.hideSoftInputFromWindow(getCurrentFocus()
                        .getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    // 打开软键盘
    private void pushInputView() {
        edit_msg.setFocusable(true);
        edit_msg.setFocusableInTouchMode(true);
        edit_msg.requestFocus();
        InputMethodManager inputManager =
                (InputMethodManager) edit_msg.getContext().getSystemService(
                        Context.INPUT_METHOD_SERVICE);

        inputManager.showSoftInput(edit_msg, 0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_show_info) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

package com.scu.miomin.keeperplus.mvp.view.interf;


import android.app.Activity;

import com.scu.miomin.keeperplus.adapter.ConversationAdapter;
import com.scu.miomin.keeperplus.mvpcore.IBaseView;

/**
 * Created by miomin on 16/11/21.
 */

public interface IHomeMsgView extends IBaseView {

    void setConversationAdapter(ConversationAdapter conversationAdapter);

    Activity getViewActivity();
}

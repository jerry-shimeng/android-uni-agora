package vip.flcloud.fenglin_agora.rtm;

import android.content.Context;
import android.nfc.Tag;
import android.util.Log;
import android.webkit.WebView;

import com.alibaba.fastjson.JSON;
import com.taobao.weex.bridge.JSCallback;

import java.util.Map;

import io.agora.rtm.ErrorInfo;
import io.agora.rtm.ResultCallback;
import io.agora.rtm.RtmClient;
import io.agora.rtm.RtmClientListener;
import io.agora.rtm.RtmFileMessage;
import io.agora.rtm.RtmImageMessage;
import io.agora.rtm.RtmMediaOperationProgress;
import io.agora.rtm.RtmMessage;
import io.agora.rtm.SendMessageOptions;
import vip.flcloud.fenglin_agora.R;
import vip.flcloud.fenglin_agora.model.CallbackModel;

import static io.dcloud.common.adapter.ui.RefreshView.TAG;

public class RTMClient {
    private RtmClient rtmClient = null;
    private static RTMClient mRTMClient = null;


    private RTMClient() {
    }

    public static RTMClient getInstance() {
        return mRTMClient;
    }

    public static RTMClient createInstance(String appId, final JSCallback callback) {

        if (mRTMClient != null) return mRTMClient;

        mRTMClient = new RTMClient();

        try {
            mRTMClient.rtmClient = RtmClient.createInstance(MyApplication.getApplication(), appId,
                    new RtmClientListener() {
                        @Override
                        public void onConnectionStateChanged(int state, int reason) {
                            Log.d(TAG, "Connection state changes to "
                                    + state + " reason: " + reason);

                            callback.invokeAndKeepAlive(new CallbackModel("onConnectionStateChanged", "Connection state changes to "
                                    + state + " reason: " + reason, null));
                        }

                        @Override
                        public void onMessageReceived(RtmMessage rtmMessage, String peerId) {
                            String msg = rtmMessage.getText();
                            Log.d(TAG, "Message received " + " from " + peerId + msg
                            );

                            Log.d(TAG, JSON.toJSONString(callback));
                            callback.invokeAndKeepAlive(new CallbackModel("onMessageReceived", rtmMessage, peerId));
                        }

                        @Override
                        public void onImageMessageReceivedFromPeer(RtmImageMessage rtmImageMessage, String peerId) {
                            callback.invokeAndKeepAlive(new CallbackModel("onImageMessageReceivedFromPeer", rtmImageMessage, peerId));
                        }

                        @Override
                        public void onFileMessageReceivedFromPeer(RtmFileMessage rtmFileMessage, String peerId) {
                            callback.invokeAndKeepAlive(new CallbackModel("onFileMessageReceivedFromPeer", rtmFileMessage, peerId));
                        }

                        @Override
                        public void onMediaUploadingProgress(RtmMediaOperationProgress rtmMediaOperationProgress, long l) {

                        }

                        @Override
                        public void onMediaDownloadingProgress(RtmMediaOperationProgress rtmMediaOperationProgress, long l) {

                        }

                        @Override
                        public void onTokenExpired() {

                        }

                        @Override
                        public void onPeersOnlineStatusChanged(Map<String, Integer> map) {

                        }
                    });
        } catch (Exception ex) {
            mRTMClient = null;
            return null;
        }

        return mRTMClient;
    }

    public void login(String userId) {
        rtmClient.login(null, userId, new ResultCallback<Void>() {
            @Override
            public void onSuccess(Void aVoid) {

            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {

            }
        });
    }

    public void logout() {
        rtmClient.logout(null);
    }


    public void sendPeerMessage(String receiverId, String content, final JSCallback callback) {

        final RtmMessage message = rtmClient.createMessage();
        message.setText(content);

        SendMessageOptions option = new SendMessageOptions();
        option.enableOfflineMessaging = true;

        rtmClient.sendMessageToPeer(receiverId, message, option, new ResultCallback<Void>() {

            @Override
            public void onSuccess(Void aVoid) {
                callback.invokeAndKeepAlive(new CallbackModel("sendPeerMessage", true, null));
            }

            @Override
            public void onFailure(ErrorInfo errorInfo) {
                callback.invokeAndKeepAlive(new CallbackModel("sendPeerMessage", errorInfo));
            }
        });
    }

}

package vip.flcloud.fenglin_agora.rtm;

import android.util.Log;

import java.util.Map;

import io.agora.rtm.RtmClient;
import io.agora.rtm.RtmClientListener;
import io.agora.rtm.RtmFileMessage;
import io.agora.rtm.RtmImageMessage;
import io.agora.rtm.RtmMediaOperationProgress;
import io.agora.rtm.RtmMessage;

import static io.dcloud.common.adapter.ui.RefreshView.TAG;

public class RTMClient {
    private RTMClient rtmClient = null;


    private RTMClient(){}


    public RTMClient createInstance(String appId){


        try {
            rtmClient = RtmClient.createInstance(this, appId,
                    new RtmClientListener() {
                        @Override
                        public void onConnectionStateChanged(int state, int reason) {
                            Log.d(TAG, "Connection state changes to "
                                    + state + " reason: " + reason);
                        }

                        @Override
                        public void onMessageReceived(RtmMessage rtmMessage, String peerId) {
                            String msg = rtmMessage.getText();
                            Log.d(TAG, "Message received " + " from " + peerId + msg
                            );
                        }

                        @Override
                        public void onImageMessageReceivedFromPeer(RtmImageMessage rtmImageMessage, String s) {

                        }

                        @Override
                        public void onFileMessageReceivedFromPeer(RtmFileMessage rtmFileMessage, String s) {

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
        }catch (Exception ex){

        }


        return rtmClient;
    }
}

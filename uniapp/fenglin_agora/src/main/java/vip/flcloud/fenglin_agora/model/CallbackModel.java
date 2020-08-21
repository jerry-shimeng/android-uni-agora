package vip.flcloud.fenglin_agora.model;

public class CallbackModel {

    private String callbackKey = "";

    private Object data;

    private String peerId;


    public CallbackModel(String callbackKey, Object data,String peerId) {
        this.callbackKey = callbackKey;
        this.data = data;
        this.peerId = peerId;
    }

    public CallbackModel(String callbackKey, Object data) {
        this.callbackKey = callbackKey;
        this.data = data;
    }

    public String getCallbackKey() {
        return callbackKey;
    }

    public void setCallbackKey(String callbackKey) {
        this.callbackKey = callbackKey;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getPeerId() {
        return peerId;
    }

    public void setPeerId(String peerId) {
        this.peerId = peerId;
    }
}

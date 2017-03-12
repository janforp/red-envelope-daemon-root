package com.hongbao.api.model.dto;

import org.craigq.common.logger.LogMgr;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuqiang on 15-9-1.
 *
 * @author wuqiang
 */
public class ServiceResult {
    private boolean success; // 表明此次业务操作是否成功(有时才会用到它)
    private String json;
    private List<Runnable> callbacks;
    private boolean isCallbackInvoked = false;  // callbacks是否已经被执行过了

    public ServiceResult() {
    }

    public ServiceResult(String json) {
        this.json = json;
    }

    public ServiceResult(String json, List<Runnable> callbacks) {
        this(json);
        this.callbacks = callbacks;
    }

    public ServiceResult(String json, Runnable callback) {
        this(json);
        if (callback != null) {
            List<Runnable> _callbacks = new ArrayList<Runnable>(1);
            _callbacks.add(callback);
            this.callbacks = _callbacks;
        }
    }

    public static ServiceResult build(String json) {
        return new ServiceResult(json);
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean isCallbackInvoked() {
        return isCallbackInvoked;
    }

    public void setIsCallbackInvoked(boolean isCallbackInvoked) {
        this.isCallbackInvoked = isCallbackInvoked;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public List<Runnable> getCallbacks() {
        return callbacks;
    }

    public void setCallbacks(List<Runnable> callbacks) {
        this.callbacks = callbacks;
    }

    public void addCallback(Runnable callback) {
        if (callback == null) {
            return;
        }
        if (this.callbacks == null) {
            this.callbacks = new ArrayList<Runnable>(1);
        }
        this.callbacks.add(callback);
    }

    /**
     * 执行所有回调代码(只能执行一次, 后续多次调用, 将不会执行callback)
     * <p/>
     * 并同时返回JSON字符串
     */
    public String invokeCallbackGetJson() {
        if (!isCallbackInvoked) {
            if (callbacks != null && callbacks.size() > 0) {
                int size = callbacks.size();
                for (int i = 0; i < size; i++) {
                    Runnable runnable = callbacks.get(i);
                    try {
                        runnable.run();
                    } catch (Exception e) {
                        LogMgr.getLogger().error("ServiceResult callbacks[" + i + "].run() error", e);
                    }
                }
            }
            isCallbackInvoked = true;
        }
        return this.json;
    }
}

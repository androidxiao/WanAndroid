package com.commmonlibrary.cn.progress;

/**
 * Created by chawei on 2018/8/22.
 */

public interface ProgressListener {

    void update(long readBytes, long totalBytes, boolean isEnd, boolean b);
}

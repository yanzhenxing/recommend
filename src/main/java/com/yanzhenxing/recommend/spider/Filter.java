package com.yanzhenxing.recommend.spider;

/**
 * �������ӿ�
 *
 * @author yanzhenxing
 */
public interface Filter {

    public boolean accept(String url);
}

package com.yanzhenxing.recommend.spider;

import java.util.Set;

/**
 *
 * ������
 *
 * Created by yanzhenxing on 16/5/20.
 */
public class Crawler {

    /**
     * ץȡ����
     *
     * @return
     * @param seeds
     */
    public void crawling(String url) { // ���������

        Filter filter = new Filter() {
            public boolean accept(String url) {
                //������˹�������Ҫ������վ�Ĺ�����иı䣬�Ƽ�ʹ������ʵ�֣���������������վ
                if(url.indexOf("douban.com/group/topic") != -1 || url.indexOf("douban.com/group/haixiuzu/discussion?start") != -1 )
                    return true;
                else
                    return false;
            }
        };
        // ��ʼ�� URL ����
        LinkQueue.addUnvisitedUrl(url);

        // ѭ����������ץȡ�����Ӳ���
        while (!LinkQueue.unVisitedUrlsEmpty()) {
            // ��ͷURL������
            String visitUrl = (String) LinkQueue.unVisitedUrlDeQueue();
            if (visitUrl == null)
                continue;

            DownLoadPic.downloadPic(visitUrl);

            // ��ȡ��������ҳ�е� URL
            Set<String> links = ParserHttpUrl.extracLinks(visitUrl, filter);
            // �µ�δ���ʵ� URL ���
            for (String link : links) {
                LinkQueue.addUnvisitedUrl(link);
            }
        }
    }

    // main �������
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.crawling("http://www.douban.com/group/haixiuzu/discussion?start=0");
    }

}

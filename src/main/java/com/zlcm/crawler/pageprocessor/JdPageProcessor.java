package com.zlcm.crawler.pageprocessor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class JdPageProcessor implements PageProcessor {

    Site site = Site.me().setRetryTimes(3).setSleepTime(100);


    @Override
    public void process(Page page) {
        String html = page.getHtml().toString();
        System.out.println(html);
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] arg){
        String url = "http://search.jd.com/Search?keyword=Python&enc=utf-8&book=y&wq=Python&pvid=33xo9lni.p4a1qb";
        Spider.create(new JdPageProcessor()).addUrl(url).thread(5).run();
    }
}

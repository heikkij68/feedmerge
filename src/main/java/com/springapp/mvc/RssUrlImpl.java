package com.springapp.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RssUrlImpl implements RssUrl {

    private final static transient Logger logger = LoggerFactory.getLogger(RssUrlImpl.class);

    @Override
    public List<URL> get() {
        List<URL> urls = new ArrayList<>();
        try {
            urls.add(new URL("http://rss.kauppalehti.fi/rss/etusivun_uutiset.jsp"));
            urls.add(new URL("http://rss.kauppalehti.fi/rss/yritysuutiset.jsp"));
            urls.add(new URL("http://rss.kauppalehti.fi/rss/omaraha.jsp"));
            urls.add(new URL("http://rss.kauppalehti.fi/rss/auto.jsp"));
        } catch (MalformedURLException e) {
            logger.error("invalid url", e);
        }
        return urls;
    }
}

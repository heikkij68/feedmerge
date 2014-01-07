package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;

public class RssUrlProvider {

    @Autowired
    private RssUrl rssUrl;

    public RssUrlProvider(RssUrl rssUrl) {
        this.rssUrl = rssUrl;
    }

    public RssUrlProvider() {
    }

    public List<URL> get() {
        return rssUrl.get();
    }

}

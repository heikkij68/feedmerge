package com.springapp.mvc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
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
            urls.add(new File("feed1.xhtml").toURI().toURL());
            urls.add(new File("feed2.xhtml").toURI().toURL());
        } catch (MalformedURLException e) {
            logger.error("invalid url", e);
        }
        return urls;
    }
}

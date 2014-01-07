package com.springapp.mvc;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class RssFeedImpl implements RssFeed {

    private final static transient Logger logger = LoggerFactory.getLogger(RssFeedImpl.class);

    @Override
    public List<SyndEntry> get(URL feedURL) {
        List<SyndEntry> feedEntries = new ArrayList<>() ;
        try {
            try (XmlReader reader = new XmlReader(feedURL)) {
                SyndFeed feed = new SyndFeedInput().build(reader);
                for (SyndEntry syndEntry : (Iterable<SyndEntry>) feed.getEntries()) {
                    feedEntries.add(syndEntry);
                }
            } catch (FeedException e) {
                logger.error("feed reading failed", e);
            }
        } catch (MalformedURLException e) {
            logger.error("invalid url", e);
        } catch (IOException e) {
            logger.error("url reading failed", e);
        }
        return feedEntries;
    }

}

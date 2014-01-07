package com.springapp.mvc;

import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;
import java.util.List;

public class RssFeedService {

    @Autowired
    private RssFeed rssFeed;

    public RssFeedService(RssFeed rssFeed) {
        this.rssFeed = rssFeed;
    }

    public RssFeedService() {
    }

    public List<SyndEntry> get(URL feedURL) {
        return rssFeed.get(feedURL);
    }

}

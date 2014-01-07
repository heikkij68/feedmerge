package com.springapp.mvc;

import com.sun.syndication.feed.synd.SyndEntry;

import java.net.URL;
import java.util.List;

public interface RssFeed {

    public List<SyndEntry> get(URL feedURL);

}

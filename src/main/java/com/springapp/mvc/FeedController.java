package com.springapp.mvc;

import com.sun.syndication.feed.synd.SyndEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Controller
@RequestMapping("/")
public class FeedController {

    private final static transient Logger logger = LoggerFactory.getLogger(FeedController.class);

    private final static long cachePeriodInMilliseconds = 1000 * 30; // 30 seconds

    private long cached = 0;

    private final AtomicReference<List<SyndEntry>> cache = new AtomicReference<>();

    @Autowired(required = true)
    RssUrlProvider rssUrlProvider;

    @Autowired(required = true)
    RssFeedService rssFeedService;

    public FeedController(RssFeedService rssFeedService) {
        this.rssFeedService = rssFeedService;
    }

    public FeedController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showFeedPage(ModelMap model) {

        if (cacheIsValid(cached)) {
            model.addAttribute("entries", cache.get());
            logger.info("return cached entries");
            return "index";
        }

        List<SyndEntry> feedEntries = new ArrayList<>();
        List<URL> urls = rssUrlProvider.get();
        for (URL url : urls) {
            feedEntries.addAll(rssFeedService.get(url));
        }
        Collections.sort(feedEntries, new Comparator<SyndEntry>() {
            @Override
            public int compare(SyndEntry e1, SyndEntry e2) {
                return e2.getPublishedDate().compareTo(e1.getPublishedDate());
            }
        });
        model.addAttribute("entries", feedEntries);
        cache.set(feedEntries);
        cached = System.currentTimeMillis();
        logger.info("return new entries");
        return "index";
    }

    private static boolean cacheIsValid(long cached) {
        return cached > 0 && System.currentTimeMillis() < cached + cachePeriodInMilliseconds;
    }

}
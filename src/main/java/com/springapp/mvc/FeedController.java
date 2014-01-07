package com.springapp.mvc;

import com.sun.syndication.feed.synd.SyndEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.File;
import java.net.MalformedURLException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/")
public class FeedController {

    //final static transient Logger logger = LoggerFactory.getLogger(FeedController.class);

    @Autowired(required = true)
    RssFeedService rssFeedService;

    public FeedController(RssFeedService rssFeedService) {
        this.rssFeedService = rssFeedService;
    }

    public FeedController() {
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showFeedPage(ModelMap model) throws MalformedURLException {

        List<SyndEntry> feedEntries = rssFeedService.get(new File("feed1.xhtml").toURI().toURL());
        List<SyndEntry> feedEntries2 = rssFeedService.get(new File("feed2.xhtml").toURI().toURL());
        feedEntries.addAll(feedEntries2);
        Collections.sort(feedEntries, new Comparator<SyndEntry>() {
            @Override
            public int compare(SyndEntry e1, SyndEntry e2) {
                return e2.getPublishedDate().compareTo(e1.getPublishedDate());
            }
        });
        model.addAttribute("entries", feedEntries);
        return "index";
    }

}
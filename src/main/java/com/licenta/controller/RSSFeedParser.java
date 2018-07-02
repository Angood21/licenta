package com.licenta.controller;

import com.licenta.model.News;
import org.springframework.beans.factory.annotation.Autowired;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.Locale;

public class RSSFeedParser {


    static final String TITLE = "title";
    static final String CATEGORY = "category";
    static final String DESCRIPTION = "description";
    static final String LINK = "link";
    static final String AUTHOR = "author";
    static final String ITEM = "item";
    static final String PUB_DATE = "pubDate";
    static final String SOURCE = "pubDate";

    final URL url;

    public RSSFeedParser(String feedUrl) {
        try {
            this.url = new URL(feedUrl);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Feed readFeed() {
        Feed feed = null;
        try {
            boolean isFeedHeader = true;
            // Set header values intial to the empty string
            String description = "";
            String title = "";
            String link = "";
            String authorName = "";
            String pubdate = "";

            // First create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = read();
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();
                if (event.isStartElement()) {
                    String localPart = event.asStartElement().getName()
                            .getLocalPart();
                    switch (localPart) {
                        case ITEM:
                            if (isFeedHeader) {
                                isFeedHeader = false;
                                feed = new Feed(title, link, description, pubdate);
                            }
                            event = eventReader.nextEvent();
                            break;
                        case TITLE:
                            title = getCharacterData(event, eventReader);
                            break;
                        case DESCRIPTION:
                            description = getCharacterData(event, eventReader);
                            break;
                        case LINK:
                            link = getCharacterData(event, eventReader);
                            break;
                        case AUTHOR:
                            authorName = getCharacterData(event, eventReader);
                            break;
                        case PUB_DATE:
                            pubdate = getCharacterData(event, eventReader);
                            break;
                    }
                } else if (event.isEndElement()) {
                    if (event.asEndElement().getName().getLocalPart() == (ITEM)) {

                        News message = new News();
                        message.setDescription(description);
                        message.setTitle(title);
                        message.setPubDate(getDateFromString(pubdate));
                        message.setUrl(link);
                        feed.getMessages().add(message);
                        eventReader.nextEvent();
                        continue;

                    }
                }

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return feed;
    }

    private Date getDateFromString(String pubdate) {
        LocalDateTime date = null;
        try {
            date = LocalDateTime.from(DateTimeFormatter.RFC_1123_DATE_TIME.parse(pubdate));
            return Date.from(date.atZone(ZoneId.systemDefault()).toInstant());
        } catch (DateTimeParseException  dtpe) {
            try {
                DateFormat dateFormatterRssPubDate = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
                return dateFormatterRssPubDate.parse(pubdate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private String getCharacterData(XMLEvent event, XMLEventReader eventReader)
            throws XMLStreamException {
        String result = "";
        event = eventReader.nextEvent();
        if (event instanceof Characters) {
            result = event.asCharacters().getData();
        }
        return result;
    }

    private InputStream read() {
        try {
            return url.openStream();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // public static void main(String[] args) {
//       RSSFeedParser parser = new RSSFeedParser(
//                "http://rss.cnn.com/rss/edition_us.rss");
//        Feed feed = parser.readFeed();
//        System.out.println(feed);
//        for (News message : feed.getMessages()) {
//            System.out.println(message);
//    }
//
//    }
}

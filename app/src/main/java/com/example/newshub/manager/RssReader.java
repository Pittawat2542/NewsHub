package com.example.newshub.manager;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.newshub.adapter.NewsItemListAdapter;
import com.example.newshub.utils.FeedItem;
import com.example.newshub.utils.VerticalSpace;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

/**
 * Created by pitta on 21/3/2559.
 */
public class RssReader extends AsyncTask<Void, Void, Void> {
    Context context;
    Activity activity;
    String website;
    ArrayList<FeedItem> feedItems;
    RecyclerView recyclerView;
    URL url;
    NewsItemListAdapter adapter;
    boolean isFirstRun;
    LinearLayoutManager mLayoutManager;
    int index;

    public RssReader(Activity activity, Context context, RecyclerView recyclerView, boolean isFirstRun, int index) {
        this.recyclerView = recyclerView;
        this.context = context;
        this.activity = activity;
        this.isFirstRun = isFirstRun;
        this.index = index;
    }

    public String setWebSite(String website) {
        this.website = website;
        return website;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        mLayoutManager = new LinearLayoutManager(context);

        adapter = new NewsItemListAdapter(activity, context, feedItems, index);
        recyclerView.setLayoutManager(mLayoutManager);

        if (!isFirstRun) {
            recyclerView.addItemDecoration(new VerticalSpace(50));
        }
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected Void doInBackground(Void... params) {
        processXml(getData());
        return null;
    }

    public void processXml(Document data) {
        getRssData(data);
    }

    public void getRssData(Document data) {
        if (data != null) {
            feedItems = new ArrayList<>();
            Element root = data.getDocumentElement();
            Node channel = root.getChildNodes().item(1);
            NodeList items = channel.getChildNodes();
            for (int i = 0; i < items.getLength(); i++) {
                Node currentChild = items.item(i);
                if (currentChild.getNodeName().equalsIgnoreCase("item")) {
                    FeedItem item = new FeedItem();
                    NodeList itemChild = currentChild.getChildNodes();
                    for (int j = 0; j < itemChild.getLength(); j++) {
                        Node current = itemChild.item(j);
                        if (current.getNodeName().equalsIgnoreCase("title")) {
                            switch (index) {
                                case 3:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "Tiếng Việt":
                                            item.setTitle(android.text.Html.fromHtml(toRealTitleFirstFormat(current.getTextContent())).toString());
                                            break;
                                        default:
                                            item.setTitle(android.text.Html.fromHtml(current.getTextContent()).toString());
                                    }
                                    break;
                                default:
                                    item.setTitle(android.text.Html.fromHtml(current.getTextContent()).toString());
                            }
                        } else if (current.getNodeName().equalsIgnoreCase("link")) {
                            switch (index) {
                                case 0:
                                    item.setLink(toRealSanookLink(current.getTextContent()));
                                    break;
                                default:
                                    item.setLink(current.getTextContent());
                                    break;
                            }
                        } else if (current.getNodeName().equalsIgnoreCase("pubDate")) {
                            switch (index) {
                                case 0:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "English":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        case "ไทย":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        case "Tiếng Việt":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        default:
                                            item.setPubDate(current.getTextContent());
                                    }
                                    break;
                                case 1:
                                    item.setPubDate(current.getTextContent());
                                    break;
                                case 2:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "English":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        case "ไทย":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        case "Tiếng Việt":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        default:
                                            item.setPubDate(current.getTextContent());
                                    }
                                    break;
                                case 3:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "English":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        case "ไทย":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        case "Tiếng Việt":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        default:
                                            item.setPubDate(current.getTextContent());
                                    }
                                    break;
                                case 4:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "English":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        case "ไทย":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        case "Tiếng Việt":
                                            item.setPubDate(current.getTextContent());
                                            break;
                                        default:
                                            item.setPubDate(current.getTextContent());
                                    }
                                    break;
                                case 5:
                                    item.setPubDate(current.getTextContent());
                                    break;
                                case 6:
                                    item.setPubDate(current.getTextContent());
                                    break;
                                case 7:
                                    item.setPubDate(current.getTextContent());
                                    break;
                                default:
                                    item.setPubDate(current.getTextContent());
                                    break;
                            }
                        } else if (current.getNodeName().equalsIgnoreCase("description")) {
                            switch (index) {
                                case 0:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "English":
                                            item.setDescription(android.text.Html.fromHtml(current.getTextContent()).toString());
                                            break;
                                        case "ไทย":
                                            item.setDescription(android.text.Html.fromHtml(toRealDescriptionFirstFormat(current.getTextContent())).toString());
                                            break;
                                        case "Tiếng Việt":
                                            item.setEnclosure(toRealEnclosureFirstFormat(current.getTextContent()));
                                            item.setDescription(toRealDescriptionSecondFormat(current.getTextContent()));
                                            break;
                                        default:
                                            item.setDescription(android.text.Html.fromHtml(toRealDescriptionFirstFormat(current.getTextContent())).toString());
                                    }
                                    break;
                                case 1:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "English":
                                            item.setDescription(android.text.Html.fromHtml(current.getTextContent()).toString());
                                            break;
                                        case "ไทย":
                                            item.setDescription(android.text.Html.fromHtml(current.getTextContent()).toString());
                                            break;
                                        case "Tiếng Việt":
                                            item.setDescription(android.text.Html.fromHtml(toRealDescriptionThirdFormat(current.getTextContent())).toString());
                                            break;
                                        default:
                                            item.setDescription(android.text.Html.fromHtml(current.getTextContent()).toString());
                                    }
                                    break;
                                case 2:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "English":
                                            item.setEnclosure(toRealEnclosureThirdFormat(current.getTextContent()));
                                            item.setDescription(android.text.Html.fromHtml(toRealDescriptionForthFormat(current.getTextContent())).toString());
                                            break;
                                        case "ไทย":
                                            item.setDescription(android.text.Html.fromHtml(current.getTextContent()).toString());
                                            break;
                                        case "Tiếng Việt":
                                            item.setEnclosure(toRealEnclosureForthFormat(current.getTextContent()));
                                            item.setDescription(android.text.Html.fromHtml(toRealDescriptionFifthFormat(current.getTextContent())).toString());
                                            break;
                                        default:
                                            item.setDescription(android.text.Html.fromHtml(current.getTextContent()).toString());
                                    }
                                    break;
                                default:
                                    item.setDescription(android.text.Html.fromHtml(current.getTextContent()).toString());
                                    break;
                            }
                        } else if (current.getNodeName().equalsIgnoreCase("enclosure")) {
                            switch (index) {
                                case 0:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "Tiếng Việt":
                                            break;
                                        default:
                                            item.setEnclosure(current.getAttributes().getNamedItem("url").getNodeValue());
                                    }
                                    break;
                                case 2:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "Tiếng Việt":
                                            break;
                                        default:
                                            item.setEnclosure(current.getAttributes().getNamedItem("url").getNodeValue());
                                    }
                                    break;
                                case 6:
                                    item.setEnclosure("http://ptcdn.info/pantip/pantip_logo.png");
                                    break;
                                case 7:
                                    item.setEnclosure("https://www.blognone.com/sites/default/files/blognone-thumb.png");
                                    break;
                                default:
                                    item.setEnclosure(current.getAttributes().getNamedItem("url").getNodeValue());
                            }
                        } else if (current.getNodeName().equalsIgnoreCase("media:thumbnail")) {
                            switch (index) {
                                default:
                                    item.setEnclosure(current.getAttributes().getNamedItem("url").getNodeValue());
                            }
                        } else if (current.getNodeName().equalsIgnoreCase("guid")) {
                            switch (index) {
                                case 3:
                                    switch (Locale.getDefault().getDisplayLanguage()) {
                                        case "ไทย":
                                            item.setLink(current.getTextContent());
                                    }
                            }
                        } else {

                        }
                    }
                    feedItems.add(item);
                }
            }
        }

    }

    public Document getData() {
        try {
            url = new URL(website);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream inputStream = connection.getInputStream();
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document xmlDocument = documentBuilder.parse(inputStream);
            return xmlDocument;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<FeedItem> getFeedItems() {
        return feedItems;
    }

    public NewsItemListAdapter getAdapter() {
        return adapter;
    }


    /***********************************************************
     * Utils
     ***********************************************************/

    /*********************************
     * PubDate
     *******************************/

    private String toRealPubDateFirstFormat(String pubDate) {
        String realPubDate = pubDate.substring(0, pubDate.indexOf("T")) + ", "
                + pubDate.substring(pubDate.indexOf("T") + 1, pubDate.indexOf("+"));

        return realPubDate;
    }

    private String toRealPubDateSecondFormat(String pubDate) {
        String realPubDate = pubDate.substring(0, pubDate.indexOf(" +"));
        return realPubDate;
    }

    private String toRealPubDateThirdFormat(String pubDate) {
        String realPubDate = pubDate.substring(0, pubDate.indexOf(" GMT"));
        return realPubDate;
    }

    private String toRealPubDateForthFormat(String pubDate) {
        String realPubDate = pubDate.substring(0, pubDate.indexOf(" EDT"));
        return realPubDate;
    }

    private String toRealPubDateFifthFormat(String pubDate) {
        String realPubDate = pubDate.substring(0, pubDate.indexOf(" EST"));
        return realPubDate;
    }

    private String toRealPubDateSixthFormat(String pubDate) {
        String realPubDate = pubDate.substring(0, pubDate.indexOf(" -0400"));
        return realPubDate;
    }

    /*********************************
     * Description
     *******************************/

    private String toRealDescriptionFirstFormat(String description) {
        if (description != null) {
            String realDescription = description.substring(description.indexOf("&nbsp;") + 12);
            return realDescription;
        } else {
            return "";
        }
    }

    private String toRealDescriptionSecondFormat(String description) {
        if (description != null) {
            String realDescription = description.substring(description.indexOf("<br />") + 6);
            return realDescription;
        } else {
            return "";
        }
    }

    private String toRealDescriptionThirdFormat(String description) {
        if (description != null) {
            String realDescription = description.substring(description.indexOf("</br>") + 5);
            return realDescription;
        } else {
            return "";
        }
    }

    private String toRealDescriptionForthFormat(String description) {
        if (description != null) {
            String realDescription = description.substring(description.indexOf("' /> <p>") + 5);
            return realDescription;
        } else {
            return "";
        }
    }

    private String toRealDescriptionFifthFormat(String description) {
        if (description != null) {
            String realDescription = description.substring(description.indexOf("</a>") + 4);
            return realDescription;
        } else {
            return "";
        }
    }

    /*********************************
     * Enclosure
     *******************************/

    public String toRealEnclosureFirstFormat(String url) {
        String realUrl = url.substring(url.indexOf("src") + 5, url.indexOf("' alt"));
        return realUrl;
    }

    public String toRealEnclosureSecondFormat(String url) {
        String realUrl = url.substring(url.indexOf("src") + 5, url.indexOf("\" ></a></br>"));
        return realUrl;
    }

    public String toRealEnclosureThirdFormat(String url) {
        String realUrl = url.substring(url.indexOf("src") + 5, url.indexOf("' />"));
        return realUrl;
    }

    public String toRealEnclosureForthFormat(String url) {
        String realUrl = url.substring(url.indexOf("src") + 5, url.indexOf("\" align"));
        return realUrl;
    }

    /*********************************
     * Link
     *******************************/

    private String toRealSanookLink(String link) {
        String realLink = link.substring(link.indexOf("|") + 1);
        return realLink;
    }

    /*********************************
     * Title
     *******************************/

    private String toRealTitleFirstFormat(String title) {
        String realTitle = title.substring(0, title.indexOf(" ( Cập"));
        return realTitle;
    }
}
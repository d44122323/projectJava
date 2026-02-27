package com.pricetracker.proekt.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class PriceParserService {

    public static double fetchPrice(String url) {
        try {
            Document doc = Jsoup.connect(url)
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 " +
                            "(KHTML, like Gecko) Chrome/112.0.0.0 Safari/537.36")
                    .timeout(10000)
                    .get();

            if (url.contains("wildberries.ru")) {
                Element price = doc.selectFirst("span.final-cost");
                if (price != null) {
                    String text = price.text().replaceAll("[^\\d]", "");
                    return Double.parseDouble(text);
                }
            }

            if (url.contains("ozon.ru")) {
                Element price = doc.selectFirst("[data-test-id='price']");
                if (price != null) {
                    String text = price.text().replaceAll("[^\\d]", "");
                    return Double.parseDouble(text);
                }
            }

        } catch (Exception e) {
            System.out.println("Ошибка при получении цены: " + e.getMessage());
        }
        return -1;
    }
}
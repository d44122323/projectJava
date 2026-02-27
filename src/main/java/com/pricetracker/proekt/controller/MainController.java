package com.pricetracker.proekt.controller;

import com.pricetracker.proekt.service.PriceParserService;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MainController {

    @FXML
    private TextField urlField;

    @FXML
    private ProgressIndicator loadingIndicator;

    @FXML
    private VBox productContainer;

    @FXML
    private void onAddClick() {
        String url = urlField.getText().trim();
        if (url.isEmpty()) return;

        loadingIndicator.setVisible(true);

        new Thread(() -> {
            double price = PriceParserService.fetchPrice(url);

            Platform.runLater(() -> {
                loadingIndicator.setVisible(false);
                if (price != -1) {
                    addProductCard(url, price);
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR, "Не удалось получить цену.");
                    alert.showAndWait();
                }
            });
        }).start();
    }

    private void addProductCard(String url, double price) {
        Label title = new Label(url);
        Label priceLabel = new Label("Цена: " + price + " ₽");
        Label updateTime = new Label("Последнее обновление: " +
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss")));

        VBox card = new VBox(5, title, priceLabel, updateTime);
        card.setStyle("-fx-background-color: #2a2a2a; -fx-padding: 10; -fx-background-radius: 12;");

        productContainer.getChildren().add(card);
    }
}
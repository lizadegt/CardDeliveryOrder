package ru.netology;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TestingCardDeliveryOrder {
    @Test
    void shouldRegisterByApplication() {
        open("http://localhost:9999/");
        $("[placeholder='Город']").setValue("Барнаул");
        $("[data-test-id=date] input").doubleClick().sendKeys(Keys.BACK_SPACE);
        String deliveryDate = LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        $("[data-test-id=date] input").setValue(deliveryDate);
        $("[name='name']").setValue("Иванов Иван");
        $("[name='phone']").setValue("+70000000000");
        $("[class=checkbox__box]").click();
        $(withText("Забронировать")).click();
        $(withText("Успешно!")).shouldBe(visible, Duration.ofSeconds(15));
        $("[data-test-id=notification] .notification__content").shouldHave(exactText("Встреча успешно забронирована на " + deliveryDate));
    }
}

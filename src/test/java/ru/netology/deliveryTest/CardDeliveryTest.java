package ru.netology.deliveryTest ;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selectors;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class CardDeliveryTest {

    private String dateGenerator(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldCheckFormSending() {
        Selenide.open("http://localhost:9999");
        String deliveryDate = dateGenerator(7, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue("Майкоп");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE);
        $("[data-test-id='date'] input").setValue(deliveryDate);
        $("[data-test-id='name'] input").setValue("Иван Иванов");
        $("[data-test-id='phone'] input").setValue("+79454552187");
        $("[data-test-id='agreement'] .checkbox__box").click();
        $$("button").findBy(Condition.text("Забронировать")).click();
        $$("[data-test-id='notification']").findBy(Condition.text("Встреча успешно забронирована на " + deliveryDate)).shouldBe(Condition.exist, Duration.ofSeconds(15));
    }

//    @Test
//    void shouldCheckPopupsMenu() {
//        Selenide.open("http://localhost:9999");
//        String deliveryDate = dateGenerator(7, "dd.MM.yyyy");
//        $("[data-test-id='city'] input").setValue("Ма");
//        $$(".menu-item").findBy(Condition.text("Майкоп")).click();
//    }
}

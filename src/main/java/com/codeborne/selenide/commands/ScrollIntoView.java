package com.codeborne.selenide.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.codeborne.selenide.commands.Util.firstOf;

@ParametersAreNonnullByDefault
public class ScrollIntoView implements Command<SelenideElement> {
  @Override
  @Nonnull
  public SelenideElement execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
    Object param = firstOf(args);
    WebElement webElement = locator.getWebElement();
    locator.driver().executeJavaScript("arguments[0].scrollIntoView(" + param + ")", webElement);
    return proxy;
  }
}

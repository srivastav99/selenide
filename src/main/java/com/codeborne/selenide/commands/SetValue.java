package com.codeborne.selenide.commands;

import com.codeborne.selenide.Command;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.InvalidStateException;
import com.codeborne.selenide.impl.JavaScript;
import com.codeborne.selenide.impl.WebElementSource;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import static com.codeborne.selenide.commands.Util.firstOf;
import static com.codeborne.selenide.impl.Events.events;

@ParametersAreNonnullByDefault
public class SetValue implements Command<SelenideElement> {
  private final JavaScript js = new JavaScript("set-value.js");

  @Override
  @Nonnull
  public SelenideElement execute(SelenideElement proxy, WebElementSource locator, @Nullable Object[] args) {
    String text = firstOf(args);
    WebElement element = locator.findAndAssertElementIsInteractable();
    Driver driver = locator.driver();

    setValueForTextInput(driver, element, text);
    return proxy;
  }

  private void setValueForTextInput(Driver driver, WebElement element, @Nullable String text) {
    if (text == null || text.isEmpty()) {
      element.clear();
    }
    else if (driver.config().fastSetValue()) {
      String error = setValueByJs(driver, element, text);
      if (error != null) throw new InvalidStateException(error);
      else {
        events.fireEvent(driver, element, "keydown", "keypress", "input", "keyup", "change");
      }
    }
    else {
      element.clear();
      element.sendKeys(text);
    }
  }

  private String setValueByJs(Driver driver, WebElement element, String text) {
    return js.execute(driver, element, text);
  }
}

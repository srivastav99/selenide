package com.codeborne.selenide.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import com.codeborne.selenide.impl.JavaScript;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Animated extends WebElementCondition {
  private static final JavaScript js = new JavaScript("animation.js");

  public Animated() {
    super("animated");
  }

  @Nonnull
  @Override
  public CheckResult check(Driver driver, WebElement element) {
    boolean animated = js.execute(driver, element);
    return new CheckResult(animated, animated ? "animated" : "not animated");
  }
}

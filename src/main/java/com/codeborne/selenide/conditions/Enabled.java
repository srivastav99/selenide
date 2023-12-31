package com.codeborne.selenide.conditions;

import com.codeborne.selenide.CheckResult;
import com.codeborne.selenide.Driver;
import com.codeborne.selenide.WebElementCondition;
import org.openqa.selenium.WebElement;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class Enabled extends WebElementCondition {

  public Enabled() {
    super("enabled");
  }

  @Nonnull
  @Override
  public CheckResult check(Driver driver, WebElement element) {
    boolean enabled = element.isEnabled();
    return new CheckResult(enabled, enabled ? "enabled" : "disabled");
  }
}

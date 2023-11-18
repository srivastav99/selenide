package integration;

import com.codeborne.selenide.ex.ElementShould;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.animated;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

final class AnimationTest extends ITest {
  @BeforeEach
  void openAnimationTestPage() {
    openFile("animations.html");
  }

  @Test
  void shouldCheckNotAnimatedBox() {
    $(".magic-box").shouldNotBe(animated);
  }

  @Test
  void shouldCheckAnimatedBox() {
    $("#move-box").click();
    $(".magic-box").shouldBe(animated);
  }

  @Test
  void shouldNotAllowClickToAnimatedBox() {
    $("#move-box").click();
    assertThatThrownBy(() -> $(".magic-box").click())
      .isInstanceOf(ElementShould.class)
      .hasMessageStartingWith("Element should be clickable: interactable and enabled and not animated {.magic-box}")
      .hasMessageContaining("Element: '<div class=\"magic-box move-ltr\"></div>'")
      .hasMessageContaining("Actual value: animated");

  }
}

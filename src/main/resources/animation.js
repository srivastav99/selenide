(function (element) {
  function isEqual(rect1, rect2) {
    return rect1.x === rect2.x &&
      rect1.y === rect2.y &&
      rect1.width === rect2.width &&
      rect1.height === rect2.height;
  }

  // if current browser window or tab is background, then immediately return false
  // because for an inactive window, the browser will not call window.requestAnimationFrame callback
  if (document.visibilityState === 'hidden') {
      return false;
  }

  return new Promise((resolve) => {
    window.requestAnimationFrame(() => {
      const rect1 = element.getBoundingClientRect();
      window.requestAnimationFrame(() => {
        const rect2 = element.getBoundingClientRect();
        if (isEqual(rect1, rect2)) {
          resolve(false);
        } else {
          resolve(true);
        }
      });
    });
  });
})(arguments[0]);

(function () {
  function setFontSize() {
    document.documentElement.style.fontSize = window.innerWidth / 19.2 + 'px';
  }
  setFontSize();
  window.onresize = function () {
    setTimeout(setFontSize(), 200)
  }
})();
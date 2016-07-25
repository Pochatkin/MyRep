// Generated by CoffeeScript 1.10.0
(function() {
  define(function(require, exports, module) {
    require("ace/ext/jquery");
    return $(document).ready(function() {
      var cssDemoPath, cssKatexPath, formulaString, katex, linkDemo, linkKatex, span;
      cssKatexPath = require.toUrl("../katex/katex.min.css");
      linkKatex = $("<link>").attr("rel", "stylesheet").attr("href", cssKatexPath);
      $("head").append(linkKatex);
      cssDemoPath = require.toUrl("./katex-demo.css");
      linkDemo = $("<link>").attr("rel", "stylesheet").attr("href", cssDemoPath);
      $("head").append(linkDemo);
      span = $("<span>").attr("id", "formula");
      $("body").append(span);
      katex = require("ace/ext/katex/katex");
      formulaString = "e^{i \\pi} + 1 = 0";
      return katex.render(formulaString, span[0]);
    });
  });

}).call(this);
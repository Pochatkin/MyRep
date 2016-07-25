// Generated by CoffeeScript 1.10.0
(function() {
  var bind = function(fn, me){ return function(){ return fn.apply(me, arguments); }; };

  define(function(require, exports, module) {
    var SpellChecker, TEST_JSON_TYPOS;
    TEST_JSON_TYPOS = [
      {
        word: "blablabla",
        corrections: ["blah-blah", "blabber", "blabbed", "salable"]
      }
    ];
    SpellChecker = (function() {
      function SpellChecker() {
        this.check = bind(this.check, this);
        this.getJson = bind(this.getJson, this);
      }

      SpellChecker.prototype.getJson = function() {
        return TEST_JSON_TYPOS;
      };

      SpellChecker.prototype.check = function(token) {
        var i, item, len, wordsList;
        wordsList = this.getJson();
        for (i = 0, len = wordsList.length; i < len; i++) {
          item = wordsList[i];
          if (token === item.word) {
            console.log(token, item.corrections);
            return false;
          }
        }
        return true;
      };

      return SpellChecker;

    })();
    exports.SpellChecker = SpellChecker;
  });

}).call(this);

//# sourceMappingURL=spellchecker.js.map

ace.define("ace/mode/latex_parser",["require","exports","module"], function(require, exports, module) {
"use strict";

var getDiscussionCommentRegexp = function(lineCommentSymbol) {
    return new RegExp(
        "^" +
        "\\s*" + lineCommentSymbol +
        "\\s+" + "([\\*\\^])" + // type of discussion: * or ^
        "\\s+" + "<(.+)>" +     // author: email or display name in angle brackets
        "\\s*(.*?)\\s*" +       // datetime without leading/trailing spaces
        ":\\s*$"
    );
};
var getDiscussionTextRowRegexp = function(lineCommentSymbol) {
    return new RegExp(
        "^" +
        "\\s*" + lineCommentSymbol +
        "\\s*" + "(.*)" + // row with text without leading spaces
        "$"
    );
};

var LatexParser = exports.LatexParser = function(bgTokenizer) {
    this.myErrors = [];
    this.myDiscussions = [];
    this.myTokenizer = bgTokenizer;
};

(function(){
    this.setSpellingCheckDictionary = function(dictionary, alphabet) {
        this.myDictionary = dictionary;
        this.myAlphabet = alphabet;
    };
    this.go = function(doc, options) {
        if (!options) {
            throw Error("IllegalArgumentException: options can't be null");
        }
        var doSpellcheck = options.spellcheck;
        var doGetDiscussions = options.parseDiscussions;
        var lineCommentSymbol = options.lineCommentSymbol;

        if (doSpellcheck && this.myDictionary === null) {
            doSpellcheck = false;
        }
        if (!doSpellcheck && !doGetDiscussions) {
            return;
        }

        var lines = doc.getAllLines();

        this.myErrors = [];
        this.myDiscussions = [];
        this.myLastReadDiscussionComment = null;

        for (var row = 0, linesLength = lines.length; row < linesLength; row++) {
            var line = lines[row];
            var tokens = this.myTokenizer.getTokens(row);

            var column = 0;
            var tokenLength = tokens.length;
            if (tokenLength === 0) {
              doGetDiscussions && saveLastReadDiscussionComment.call(this);
            }
            for (var j = 0; j < tokenLength; j++) {
                var token = tokens[j];

                var tokenColumn = line.slice(column).indexOf(token.value);
                if (tokenColumn != -1) {
                    column += tokenColumn;
                }

                switch(token.type) {
                    case "comment":
                        if (tokenLength === 1 && doGetDiscussions) {
                            parseRowForDiscussions.call(this, token.value, row);
                        }
                        doSpellcheck && parseRowForSpellcheck.call(this, token.value, row, column);
                        break;
                    case "text":
                        doGetDiscussions && saveLastReadDiscussionComment.call(this);
                        doSpellcheck && parseRowForSpellcheck.call(this, token.value, row, column);
                        break;
                    default:
                        doGetDiscussions && saveLastReadDiscussionComment.call(this);
                        break;
                }
            }
        }
        doGetDiscussions && saveLastReadDiscussionComment.call(this);
        function parseRowForSpellcheck(tokenValue, row, column) {
            var words = this.splitTextToWords(tokenValue, row, column);
            for (var i = 0, wordsLength = words.length; i < wordsLength; i++) {
                if (!this.myDictionary.check(words[i].value)) {
                    this.myErrors.push({
                        row: words[i].row,
                        column: words[i].column,
                        text: "grammar error",
                        type: "error",
                        raw: words[i].value
                    });
                }
            }
        }
        function parseRowForDiscussions(line, row) {
            var matchComment = line.match(getDiscussionCommentRegexp(lineCommentSymbol));
            if (matchComment) {
                saveLastReadDiscussionComment.call(this);
                this.myLastReadDiscussionComment = {
                    type: matchComment[1],
                    author: matchComment[2],
                    datetime: matchComment[3],
                    row: row,
                    textLines: [],
                    replies: []
                };
            } else if (this.myLastReadDiscussionComment !== null) {
                var matchTextRow = line.match(getDiscussionTextRowRegexp(lineCommentSymbol));
                if (matchTextRow) {
                    this.myLastReadDiscussionComment.textLines.push(matchTextRow[1]);
                }
            }
        }

        function saveLastReadDiscussionComment() {
            if (this.myLastReadDiscussionComment === null) {
                return;
            }
            switch (this.myLastReadDiscussionComment.type) {
                case '*':
                    this.myDiscussions.push(this.myLastReadDiscussionComment);
                    break;
                case '^':
                    var lastIdx = this.myDiscussions.length - 1;
                    if (this.myDiscussions[lastIdx] !== undefined) {
                        this.myDiscussions[lastIdx].replies.push(this.myLastReadDiscussionComment);
                    }
                    break;
                default:
                    throw Error("Unsupported discussion type: " + this.myLastReadDiscussionComment.type);
            }
            this.myLastReadDiscussionComment = null;
        }
    };

    this.splitTextToWords = function(text, row, column) {
        var regex = new RegExp('^[' + this.myAlphabet + ']+', 'i');
        var words = [];
        var pos = 0;
        while (pos < text.length) {
            var match = regex.exec(text.substr(pos));
            if (match) {
                words.push({
                    value: match[0],
                    row: row,
                    column: column + pos
                });
                pos += match[0].length;
            } else {
                pos++;
            }
        }
        return words;
    };

    this.getErrors = function() {
        return this.myErrors;
    };

    this.getDiscussions = function() {
        return this.myDiscussions;
    };

}).call(LatexParser.prototype);

});

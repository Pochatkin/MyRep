ace.define("ace/mode/latex_highlight_rules",["require","exports","module","ace/lib/oop","ace/mode/text_highlight_rules"], function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextHighlightRules = require("./text_highlight_rules").TextHighlightRules;

var LatexHighlightRules = function() {  

    this.$rules = {
        "start" : [{
            token : "comment",
            regex : "%.*$"
        }, {
            token : ["keyword", "lparen", "variable.parameter", "rparen", "lparen", "storage.type", "rparen"],
            regex : "(\\\\(?:documentclass|usepackage|input))(?:(\\[)([^\\]]*)(\\]))?({)([^}]*)(})"
        }, {
            token : ["keyword","lparen", "variable.parameter", "rparen"],
            regex : "(\\\\(?:label|v?ref|cite(?:[^{]*)))(?:({)([^}]*)(}))?"
        }, {
            token : ["storage.type", "lparen", "variable.parameter", "rparen"],
            regex : "(\\\\(?:begin|end))({)(\\w*)(})"
        }, {
            token : "storage.type",
            regex : "\\\\[a-zA-Z]+"
        }, {
            token : "lparen",
            regex : "[[({]"
        }, {
            token : "rparen",
            regex : "[\\])}]"
        }, {
            token : "constant.character.escape",
            regex : "\\\\[^a-zA-Z]?"
        }, {
            token : "string",
            regex : "\\${1,2}",
            next  : "equation"
        }],
        "equation" : [{
            token : "comment",
            regex : "%.*$"
        }, {
            token : "string",
            regex : "\\${1,2}",
            next  : "start"
        }, {
            token : "constant.character.escape",
            regex : "\\\\(?:[^a-zA-Z]|[a-zA-Z]+)"
        }, {
            token : "error", 
            regex : "^\\s*$", 
            next : "start" 
        }, {
            defaultToken : "string"
        }]

    };
};
oop.inherits(LatexHighlightRules, TextHighlightRules);

exports.LatexHighlightRules = LatexHighlightRules;

});

ace.define("ace/mode/folding/latex",["require","exports","module","ace/lib/oop","ace/mode/folding/fold_mode","ace/range","ace/token_iterator"], function(require, exports, module) {
"use strict";

var oop = require("../../lib/oop");
var BaseFoldMode = require("./fold_mode").FoldMode;
var Range = require("../../range").Range;
var TokenIterator = require("../../token_iterator").TokenIterator;

var FoldMode = exports.FoldMode = function() {};

oop.inherits(FoldMode, BaseFoldMode);

(function() {

    this.foldingStartMarker = /^\s*\\(begin)|(section|subsection|paragraph)\b|{\s*$/;
    this.foldingStopMarker = /^\s*\\(end)\b|^\s*}/;

    this.getFoldWidgetRange = function(session, foldStyle, row) {
        var line = session.doc.getLine(row);
        var match = this.foldingStartMarker.exec(line);
        if (match) {
            if (match[1])
                return this.latexBlock(session, row, match[0].length - 1);
            if (match[2])
                return this.latexSection(session, row, match[0].length - 1);

            return this.openingBracketBlock(session, "{", row, match.index);
        }

        var match = this.foldingStopMarker.exec(line);
        if (match) {
            if (match[1])
                return this.latexBlock(session, row, match[0].length - 1);

            return this.closingBracketBlock(session, "}", row, match.index + match[0].length);
        }
    };

    this.latexBlock = function(session, row, column) {
        var keywords = {
            "\\begin": 1,
            "\\end": -1
        };

        var stream = new TokenIterator(session, row, column);
        var token = stream.getCurrentToken();
        if (!token || !(token.type == "storage.type" || token.type == "constant.character.escape"))
            return;

        var val = token.value;
        var dir = keywords[val];

        var getType = function() {
            var token = stream.stepForward();
            var type = token.type == "lparen" ?stream.stepForward().value : "";
            if (dir === -1) {
                stream.stepBackward();
                if (type)
                    stream.stepBackward();
            }
            return type;
        };
        var stack = [getType()];
        var startColumn = dir === -1 ? stream.getCurrentTokenColumn() : session.getLine(row).length;
        var startRow = row;

        stream.step = dir === -1 ? stream.stepBackward : stream.stepForward;
        while(token = stream.step()) {
            if (!token || !(token.type == "storage.type" || token.type == "constant.character.escape"))
                continue;
            var level = keywords[token.value];
            if (!level)
                continue;
            var type = getType();
            if (level === dir)
                stack.unshift(type);
            else if (stack.shift() !== type || !stack.length)
                break;
        }

        if (stack.length)
            return;

        var row = stream.getCurrentTokenRow();
        if (dir === -1)
            return new Range(row, session.getLine(row).length, startRow, startColumn);
        stream.stepBackward();
        return new Range(startRow, startColumn, row, stream.getCurrentTokenColumn());
    };

    this.latexSection = function(session, row, column) {
        var keywords = ["\\subsection", "\\section", "\\begin", "\\end", "\\paragraph"];

        var stream = new TokenIterator(session, row, column);
        var token = stream.getCurrentToken();
        if (!token || token.type != "storage.type")
            return;

        var startLevel = keywords.indexOf(token.value);
        var stackDepth = 0
        var endRow = row;

        while(token = stream.stepForward()) {
            if (token.type !== "storage.type")
                continue;
            var level = keywords.indexOf(token.value);

            if (level >= 2) {
                if (!stackDepth)
                    endRow = stream.getCurrentTokenRow() - 1;
                stackDepth += level == 2 ? 1 : - 1;
                if (stackDepth < 0)
                    break
            } else if (level >= startLevel)
                break;
        }

        if (!stackDepth)
            endRow = stream.getCurrentTokenRow() - 1;

        while (endRow > row && !/\S/.test(session.getLine(endRow)))
            endRow--;

        return new Range(
            row, session.getLine(row).length,
            endRow, session.getLine(endRow).length
        );
    };

}).call(FoldMode.prototype);

});

ace.define("ace/mode/papeeria_latex",["require","exports","module","ace/lib/oop","ace/mode/text","ace/tokenizer","ace/mode/latex_highlight_rules","ace/mode/folding/latex","ace/range","ace/worker/worker_client","ace/edit_session"], function(require, exports, module) {
"use strict";

var oop = require("../lib/oop");
var TextMode = require("./text").Mode;
var Tokenizer = require("../tokenizer").Tokenizer;
var LatexHighlightRules = require("./latex_highlight_rules").LatexHighlightRules;
var LatexFoldMode = require("./folding/latex").FoldMode;
var Range = require("../range").Range;
var WorkerClient = require("../worker/worker_client").WorkerClient;
var EditSession = require("../edit_session").EditSession;

var Mode = function() {
    this.HighlightRules = LatexHighlightRules;
    this.foldingRules = new LatexFoldMode();
};
oop.inherits(Mode, TextMode);

EditSession.prototype.removeAllMarkersByType = function(type) {
    var markers = this.getMarkers(true);
    for (var i in markers) {
        if (markers[i].type === type) {
            this.removeMarker(markers[i].id);
        }
    }
};

(function() {
    this.lineCommentStart = "%";

    var mySettingsUpdateCallbacks = {
        onSuccess: null,
        onError: null
    };
    var myDiscussionsCallbacks = {
        onSuccess: null
    };

    this.createWorker = function(session) {
        var worker = new WorkerClient(["ace"], "ace/mode/latex_worker", "LatexWorker");
        worker.attachToDocument(session.getDocument());

        worker.on("spellcheck", function(results) {
            session.removeAllMarkersByType("typo");
            for (var i = 0; i < results.data.length; ++i) {
                var word = results.data[i];
                session.addMarker(new Range(word.row, word.column, word.row, word.column + word.raw.length), "typo", "typo", true);
            }
        });

        worker.on("settingsUpdateSuccess", function(result) {
            mySettingsUpdateCallbacks.onSuccess();
        });

        worker.on("settingsUpdateError", function(result) {
            mySettingsUpdateCallbacks.onError(result.data);
        });

        worker.on("discussionsSettingsUpdateSuccess", function(result) {
            myDiscussionsCallbacks.onSuccess();
        });

        worker.on("discussions", function(discussions) {
            if (myDiscussionsCallbacks.onSuccess != null) {
                myDiscussionsCallbacks.onSuccess({
                  discussions: discussions,
                  lineCommentSymbol: this.lineCommentStart
                });
            }
        }.bind(this));

        var onChangeSpellchecker = function(data) {
            if (!data.enabled) {
                session.removeAllMarkersByType("typo");
            }
            mySettingsUpdateCallbacks = {
                onSuccess: data.onSettingsUpdateSuccess,
                onError: data.onSettingsUpdateError
            };
            worker.call("changeSpellingCheckOptions", [{
                language: data.language,
                dicPath: data.dicPath,
                affPath: data.affPath,
                enabled: data.enabled,
                alphabet: data.alphabet
            }]);
        };
        var onChangeDiscussions = function(data) {
            myDiscussionsCallbacks = {
                onSuccess: data.onSuccess
            };
            worker.call("changeDiscussionsOptions", [{
                enabled: data.enabled,
                lineCommentSymbol: this.lineCommentStart
            }]);
        }.bind(this);

        worker.on("terminate", function() {
            session.removeAllMarkersByType("typo");
            session.off("changeSpellingCheckSettings", onChangeSpellchecker);
            session.off("changeDiscussionsSettings", onChangeDiscussions);
        });
        session.on("changeSpellingCheckSettings", onChangeSpellchecker);

        session.on("changeDiscussionsSettings", onChangeDiscussions);

        return worker;
    };

}).call(Mode.prototype);

exports.Mode = Mode;

});

ace.define("ace/snippets/papeeria_latex",["require","exports","module"], function(require, exports, module) {
"use strict";

exports.snippetText = "#DOCUMENT\n\
# \\begin{}...\\end{}\n\
snippet \\begin{}...\n\
	\\begin{${1:env}}\n\
		${2}\n\
	\\end{$1}\n\
snippet \\begin{equation}...\n\
	\\begin{equation}\n\
		${1}\n\
	\\end{equation}\n\
# Equation\n\
snippet \\begin{equation*}...\n\
	\\begin{equation*}\n\
		${1}\n\
	\\end{equation*}\n\
snippet \\begin{enumerate}...\n\
	\\begin{enumerate}\n\
		\\item ${1}\n\
	\\end{enumerate}\n\
# Itemize\n\
snippet \\begin{itemize}...\n\
	\\begin{itemize}\n\
		\\item ${1}\n\
	\\end{itemize}\n\
# Description\n\
snippet \\begin{description}...\n\
	\\begin{description}\n\
		\\item[${1}] ${2}\n\
	\\end{description}\n\
snippet \\begin{split}...\n\
	\\begin{split}\n\
		${1}\n\
	\\end{split}\n\
\n\
";
exports.scope = "papeeria_latex";

});

package com.homework.models

import java.util.*

/**
 * Created by mikhail on 13.09.16.
 */
class InvertIndex {

    private val baseBooks: HashMap<String, String>
    private val baseIndex: HashMap<String, LinkedHashSet<String>>


    init {
        val base = Bases.getInstance()
        baseBooks = base.getBaseBooks()
        baseIndex = base.getBaseIndex()
    }

    fun getDocNames(searchText: String): Array<String> {
        var searchText = searchText
        searchText = searchText.toLowerCase()
        val searchWords = searchText.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()

        var currentDocs = HashSet(baseBooks.keys)
        for (word in searchWords) {
            if (baseIndex[word] != null) {
                currentDocs = intersection(currentDocs, baseIndex[word])
            } else {
                currentDocs = HashSet<String>()
            }
        }
        return currentDocs.toTypedArray()
    }

    fun getTextInDocs(searchText: String, docsName: Array<String>): Array<String> {
        val result = ArrayList<Array<String>>()
        for (doc in docsName) {
            val text = baseBooks[doc]
            val searchWords = searchText.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            val elementText = ArrayList<String>()
            for (word in searchWords)
                try {
                    elementText.add(text.substring(prevIndexWord(text, word)!!, nextIndexWord(text, word)!!))
                } catch (e: Exception) {
                    println("prev: " + prevIndexWord(text, word)!!)
                    println("next: " + nextIndexWord(text, word)!!)
                    e.printStackTrace()
                }

            result.add(elementText.toTypedArray())
        }
        return makeFinalString(result)

    }

    private fun nextIndexWord(text: String, word: String): Int? {
        var result: Int?
        result = text.indexOf(" $word ")
        if (result === -1)
            result = text.indexOf(" $word,")
        if (result === -1)
            result = text.indexOf(" $word.")
        if (result === -1)
            result = text.indexOf(word)
        var k = 0
        while (k < 2) {
            if (text[result] == ' ')
                k++
            result++
        }
        return --result

    }

    private fun prevIndexWord(text: String, word: String): Int? {
        var result: Int?
        result = text.indexOf(" $word ")
        if (result === -1)
            result = text.indexOf(" $word,")
        if (result === -1)
            result = text.indexOf(" $word.")
        if (result === -1)
            result = text.indexOf(word)
        var k = 0
        while (k < 2) {
            if (text[result] == ' ')
                k++
            result--
            if (result <= 0)
                return 0
        }
        return ++result
    }

    private fun makeFinalString(result: List<Array<String>>): Array<String> {
        val returns = ArrayList<String>()
        for (arrText in result) {
            var s = "..."
            for (text in arrText) {
                s += text + "... "
            }
            returns.add(s)
        }
        return returns.toTypedArray()
    }

    private fun intersection(a: HashSet<String>, b: LinkedHashSet<String>): HashSet<String> {

        val temp = HashSet<String>()
        for (s in b) {
            if (a.contains(s)) {
                temp.add(s)
            }
        }
        return temp
    }

}

package com.homework.models


import java.io.File
import java.io.IOException
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.util.*
import java.util.logging.Logger
import java.util.regex.Pattern

/**
 * Created by mikhail on 15.09.16.
 */
internal class Bases private constructor() {
    val baseBooks: HashMap<String, String>
    val baseIndex: HashMap<String, LinkedHashSet<String>>

    @Volatile var instance: Bases? = null


    init {
        baseBooks = HashMap<String, String>()
        baseIndex = HashMap<String, LinkedHashSet<String>>()
        try {
            getBase()
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }


    @Throws(IOException::class)
    private fun getBase() {
        if (baseIndex.isEmpty()) {
            for (i in 1..10) {
                initBases(i.toString() + ".txt")
            }
        }
    }

    private fun split(list: List<String>): List<String> {
        val p = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS)
        val temp = LinkedList<String>()
        for (a in list) {
            Collections.addAll(temp, *p.split(a))
        }
        val iterator = temp.listIterator()
        while (iterator.hasNext()) {
            iterator.set(iterator.next().toLowerCase())
        }
        return temp
    }

    @Throws(IOException::class)
    private fun initBases(indexDocument: String) {
        val f = File("1.txt")
        val logger = Logger.getLogger(Bases::class.java.name)
        logger.info(f.absolutePath)
        var arrWord = Files.readAllLines(Paths.get("res/" + indexDocument), StandardCharsets.UTF_8)
        var s = String()
        for (words in arrWord) {
            s += words + " "
        }
        arrWord = split(arrWord)
        for (word in arrWord) {
            baseBooks.put(indexDocument, s)
            if (!baseIndex.containsKey(word)) {
                val temp = LinkedHashSet<String>()
                temp.add(indexDocument)
                baseIndex.put(word, temp)
            } else {
                baseIndex[word]?.add(indexDocument)
            }
        }
    }

    companion object {
        @Volatile var instance: Bases? = null

        fun getInstance(): Bases? {
            if (instance == null)
                synchronized(Bases::class.java) {
                    if (instance == null)
                        instance = Bases()
                }
            return instance
        }
    }

}
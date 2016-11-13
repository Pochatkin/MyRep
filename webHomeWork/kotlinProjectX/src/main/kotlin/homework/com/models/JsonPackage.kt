package com.homework.models

/**
 * Created by mikhail on 13.09.16.
 */
class JsonPackage  {
    private val action:String
    private val docsName:Array<String>
    private val textInDocs:Array<String>
    constructor(action:String, docsName:Array<String>, textInDocs:Array<String>) {
        this.action = action
        this.docsName = docsName
        this.textInDocs = textInDocs
    }
    constructor(action:String) {
        this.action = action
    }
}
}

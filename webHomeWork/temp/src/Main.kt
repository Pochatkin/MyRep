/**
 * Created by mikhail on 12.11.16.
 */
fun main(args: Array<String>) {

    val arr = arrayOf(
            "asfa",
            "asda"
    )
    val temp1 = JsonPackage("kek1")
    val temp3 = JsonPackage("kek3", arr);
    val temp2 = JsonPackage("kek2", arr, arr)

    readLine();
}

class JsonPackage(action: String, docsName: Array<String> = arrayOf(), textInDocs: Array<String> = arrayOf()) {
    val action : String =
    init {
        print("class init with parameter $action")
    }

}
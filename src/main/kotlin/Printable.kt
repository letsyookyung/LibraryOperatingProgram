package library

interface Printable {
    fun <T> print(task: T) : T
}

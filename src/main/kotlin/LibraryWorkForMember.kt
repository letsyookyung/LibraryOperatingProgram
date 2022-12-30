package library

import CheckOutReturnBook
import CommonErrorCheck

open class LibraryWorkForMember(val id: String, val pwd: String) {

    private val checkOutBook = CheckOutReturnBook("member", id)

    fun printMyCheckOutStatus() {
        println("\n📚나의 도서 대여/반납 현황: ")
        LibraryDataBase.memberList
            .filter { it.id == id }[0].checkOutHistory
            .forEach { item -> println("➡️ ${item.date} | ${item.book} | ${item.lastStatus} ")}
        }

    fun searchBook(task: String) {
        when (task) {
            "1" -> {
                val bookName = MemberPrintFormat.SEARCHBOOK.print("askBookName")

                CommonErrorCheck.checkIfExists("bookName", bookName)

                val filteredBookList = LibraryDataBase.bookList.filter { it.name == bookName }

                MemberPrintFormat.SEARCHBOOK.print("bookList")
                filteredBookList.forEach { println("➡️ ${it.name} | ${it.author} | ${it.checkOut}") }
            }
            "2" -> {
                val author = MemberPrintFormat.SEARCHBOOK.print("askAuthor")

                CommonErrorCheck.checkIfExists("author", author)

                val filteredBookList = LibraryDataBase.bookList.filter { it.author == author }

                MemberPrintFormat.SEARCHBOOK.print("bookList")
                filteredBookList.forEach { println("➡️ ${it.name} | ${it.author} | ${it.checkOut}") }
            }

            "3" -> {
                MemberPrintFormat.SEARCHBOOK.print("bookList")
                LibraryDataBase.bookList.forEach { println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")}
            }
        }
    }

    fun borrowBook(task: String) {
        when (task) {
            "1" -> checkOutBook.byName("checkOut")
            "2" -> checkOutBook.byAuthor()
        }
    }

    fun returnBook() {
        checkOutBook.byName("return")
    }
}





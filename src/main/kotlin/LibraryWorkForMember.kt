package library


import java.time.LocalDateTime
import java.util.*

open class LibraryWorkForMember(var id:String, var pwd:String) {

    private val sc = Scanner(System.`in`)

    fun printMyCheckOutStatus() {
        println("\n📚나의 도서 대여/반납 현황: ")
        LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.forEach { item ->
            println("➡️ ${item.date} | ${item.book} | ${item.lastStatus} ")
        }
    }

    fun searchBook(task: String) {
        when (task) {
            //책이름
            "1" -> {
                val bookName = MemberPrintFormat.SEARCHBOOK.print("askBookName")
                if (LibraryDataBase.bookList.none { it.name == bookName }) {
                    MemberPrintFormat.RETURNBOOK.print("noBook")
                    return
                }
                MemberPrintFormat.SEARCHBOOK.print("bookList")
                LibraryDataBase.bookList.filter { it.name == bookName }.forEach {
                    println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
                }
            }

            "2" -> {
                val author = MemberPrintFormat.SEARCHBOOK.print("askAuthor")

                if (LibraryDataBase.bookList.none { it.author == author }) {
                    MemberPrintFormat.RETURNBOOK.print("noBook")
                    return
                }
                MemberPrintFormat.SEARCHBOOK.print("bookList")
                LibraryDataBase.bookList.filter { it.author == author }.forEach {
                    println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
                }
            }

            "3" -> {
                MemberPrintFormat.SEARCHBOOK.print("bookList")
                LibraryDataBase.bookList.forEach {
                    println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
                }
            }
        }
    }

    fun borrowBook(task: String) {
        when (task) {
            //책이름
            "1" -> {
                val bookName = MemberPrintFormat.BORROWBOOK.print("askBookName")

                val filteredBookList = LibraryDataBase.bookList.filter { it.name == bookName }

                checkIfExists("bookName", bookName)
                checkIfAvailable("checkOut", filteredBookList)

                if (filteredBookList.size == 1) oneBookSearched(filteredBookList)


            }
            //저자명
            "2" -> {
                val author = MemberPrintFormat.BORROWBOOK.print("askAuthor")

                val filteredBookList = LibraryDataBase.bookList.filter { it.author == author }

                checkIfExists("author", author)
                checkIfAvailable("checkOut", filteredBookList)

                if (filteredBookList.size == 1) oneBookSearched(filteredBookList)
                else moreThanOneBookSearched(filteredBookList)
            }
        }
    }

    fun returnBook(bookName: String) {

        val filteredBookList = LibraryDataBase.bookList.filter { it.name == bookName }

        checkIfExists("bookName", bookName)
        checkIfAvailable("return", filteredBookList)

        val returnBookListById =
            LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.groupBy { it.book == bookName }[true]
        if (returnBookListById != null) {
            if (returnBookListById.last().lastStatus == "대여중") {
                LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.add(
                    LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "반납 완료")
                )
                LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 가능"
                println("반납 완료 ^^")
            } else println("해당 도서를 이미 반납하셨습니다.")
        } else println("반납 요청하신 도서를 대여중이지 않습니다.")
    }

    private fun checkIfExists(field:String, value:String) {
        when (field) {
            "bookName" -> {
                if (LibraryDataBase.bookList.none { it.name == value }) {
                    MemberPrintFormat.RETURNBOOK.print("noBook")
                    return
                }
            }
            "author" -> {
                if (LibraryDataBase.bookList.none { it.author == value }) {
                    MemberPrintFormat.RETURNBOOK.print("noBook")
                    return
                }
            }
        }
    }

    private fun checkIfAvailable(field:String, filteredBookList:List<LibraryDataBase.BookInfo>) {
        when (field) {
            "checkOut" -> {
                if (filteredBookList.none { it.checkOut == "대여 가능" }) {
                    println("대여 가능한 도서가 없는 상태입니다. 혹시 모르니 도서명을 확인 해보세요. \n ")
                    return
                }
            }
            "return" -> {
                if (filteredBookList.none { it.checkOut == "대여 불가능" }) {
                    println("해당 책은 대여 상태가 아닙니다. 혹시 모르니 도서명을 확인 해보세요. \n ")
                    return
                }
            }
        }
    }
    private fun oneBookSearched(filteredBookList: List<LibraryDataBase.BookInfo>) {
        MemberPrintFormat.BORROWBOOK.print("selectBook")
        filteredBookList.forEach {
            println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
        }

        println("대여하시겠습니까? Y/N")
        val input = sc.nextLine()
        when (input.toUpperCase()) {
            "Y" -> {
                LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.add(
                    LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), filteredBookList[0].name, "대여중")
                )
                LibraryDataBase.bookList.filter { it.name == filteredBookList[0].name }[0].checkOut = "대여 불가능"
                println("\n\uD83D\uDCDA대여 완료 ^^")
            }
        }
    }

    private fun moreThanOneBookSearched(filteredBookList: List<LibraryDataBase.BookInfo>) {
        MemberPrintFormat.BORROWBOOK.print("selectBook2")
        filteredBookList.forEach {
            println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
        }

        println("도서명:")
        val bookName = sc.nextLine()

        LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.add(
            LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "대여중")
        )
        LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 불가능"
        println("\n\uD83D\uDCDA대여 완료 ^^")
    }
}





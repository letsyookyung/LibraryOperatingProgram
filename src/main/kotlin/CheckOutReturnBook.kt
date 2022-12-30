
import library.LibraryDataBase
import library.LibraryWorkForManager
import library.MemberPrintFormat
import java.time.LocalDateTime
import java.util.*

class CheckOutReturnBook(private val mode: String, private var id: String) {
    private val sc = Scanner(System.`in`)

    fun byName(task: String) {
        val bookName = MemberPrintFormat.BORROWBOOK.print("askBookName")
        val filteredBookList = LibraryDataBase.bookList.filter { it.name == bookName }

        if (!CommonErrorCheck.checkIfExists("bookName", bookName)) return

        when (task) {
            "checkOut" -> {
                if (!CommonErrorCheck.checkIfAvailable("checkOut", filteredBookList)) return

                if (filteredBookList.size == 1) oneBookSearched(mode, filteredBookList)
                else moreThanOneBookSearched(mode, filteredBookList)
            }
            "return" -> {
                if (!CommonErrorCheck.checkIfAvailable("return", filteredBookList)) return

                when (mode) {
                    "manager" -> id = LibraryWorkForManager.askId()
                    }

                if (id == "") return

                val returnBookListById =
                    LibraryDataBase.memberList
                        .filter { it.id == id }[0].checkOutHistory
                        .groupBy { it.book == bookName }[true]
                if (returnBookListById != null) {
                    if (returnBookListById.last().lastStatus == "대여중") {
                        LibraryDataBase.memberList
                            .filter { it.id == id }[0].checkOutHistory
                            .add(LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "반납 완료"))

                        LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 가능"

                        println("\uD83D\uDCDA반납 완료 ^^")
                    } else println("해당 도서를 이미 반납하셨습니다.")
                } else println("반납 요청하신 도서를 대여중이지 않습니다.")
            }
     }

}

    fun byAuthor() {
        val author = MemberPrintFormat.BORROWBOOK.print("askAuthor")
        val filteredBookList = LibraryDataBase.bookList.filter { it.author == author }

        if (!CommonErrorCheck.checkIfExists("author", author)) return
        if (!CommonErrorCheck.checkIfAvailable("checkOut", filteredBookList)) return

        if (filteredBookList.size == 1) oneBookSearched(mode, filteredBookList)
        else moreThanOneBookSearched(mode, filteredBookList)
    }

    fun oneBookSearched(mode: String, filteredBookList: List<LibraryDataBase.BookInfo>) {
        MemberPrintFormat.BORROWBOOK.print("selectBook")
        filteredBookList.forEach { println("➡️ ${it.name} | ${it.author} | ${it.checkOut}") }

        println("대여하시겠습니까? Y/N")
        val input = sc.nextLine()

        when (input.toUpperCase()) {
            "Y" -> {
                when (mode) {
                    "manager" -> id = LibraryWorkForManager.askId()
                    }

                if (id == "") return

                LibraryDataBase.memberList
                    .filter { it.id == id }[0].checkOutHistory
                    .add(LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), filteredBookList[0].name, "대여중"))

                LibraryDataBase.bookList.filter { it.name == filteredBookList[0].name }[0].checkOut = "대여 불가능"

                println("\n\uD83D\uDCDA대여 완료 ^^")
                return
            }
        }
        return
    }

    fun moreThanOneBookSearched(mode: String, filteredBookList: List<LibraryDataBase.BookInfo>) {
        MemberPrintFormat.BORROWBOOK.print("selectBook2")
        filteredBookList.forEach { println("➡️ ${it.name} | ${it.author} | ${it.checkOut}") }

        println("도서명:")
        val bookName = sc.nextLine()

        when (mode) {
            "manager" -> id = LibraryWorkForManager.askId()
            }

        if (id == "") return

        LibraryDataBase.memberList
            .filter { it.id == id }[0].checkOutHistory
            .add(LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "대여중"))

        LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 불가능"

        println("\n\uD83D\uDCDA대여 완료 ^^")
    }
}
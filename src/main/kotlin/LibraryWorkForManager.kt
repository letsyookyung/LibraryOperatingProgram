package library
import java.time.LocalDateTime
import java.util.*

open class LibraryWorkForManager(var id:String, var pwd:String) : PurchaseBook() {

    private var sc = Scanner(System.`in`)

    fun checkBookList() {
        println("\n📚전체 도서 목록: ")
        LibraryDataBase.bookList.forEach { item ->
            println("➡️ ${item.name} | ${item.author} | ${item.checkOut} ")
        }
    }

    fun updateBookListStatus(task: String) {

        when (task) {
            //대여
            "1" -> {
                when (MemberPrintFormat.BORROWBOOK.print("menu")) {
                    //책이름
                    "1" -> {
                        val bookName = MemberPrintFormat.BORROWBOOK.print("askBookName")

                        val filteredBookList = LibraryDataBase.bookList.filter { it.name == bookName }

                        checkIfExist("bookName", bookName, filteredBookList)

                        if (filteredBookList.size == 1) oneBookSearched(filteredBookList)
                    }
                    //저자명
                    "2" -> {
                        val author = MemberPrintFormat.BORROWBOOK.print("askAuthor")
                        val filteredBookList = LibraryDataBase.bookList.filter { it.author == author }

                        if (filteredBookList.size == 1) oneBookSearched(filteredBookList)
                        else moreThanOneBookSearched(filteredBookList)

                    }
                }
            }

            //반납
            "2" -> {
                val bookName = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askBookName")

                val filteredBookList = LibraryDataBase.bookList.filter { it.name == bookName }
                checkIfExist("bookName", bookName,filteredBookList )

                val memberID = askId()
                if (memberID == "") return

                val returnBookListById =
                    LibraryDataBase.memberList.filter { it.id == memberID }[0].checkOutHistory.groupBy { it.book == bookName }[true]
                if (returnBookListById != null) {
                    if (returnBookListById.last().lastStatus == "대여중") {
                        LibraryDataBase.memberList.filter { it.id == memberID }[0].checkOutHistory.add(
                            LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "반납 완료")
                        )
                        LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 가능"
                        println("반납 완료 ^^")
                    } else println("해당 도서를 이미 반납하셨습니다.")
                } else println("반납 요청하신 도서를 대여중이지 않습니다.")
            }
        }

    }


    private fun askId(): String {

        var checkId = 0
        while (checkId <= 2) {
            val memberID = ManagerPrintFormat.UPDATEBOOKLISTSTATUS.print("askID")
            if (LibraryDataBase.memberList.none { it.id == memberID }) {
                println("입력하신 멤버의 ID는 존재하지 않습니다. 다시 확인하여 입력해주세요.")
                checkId += 1
                continue
            }
            return memberID
        }
        return ""
    }

    private fun checkIfExist(field:String, value:String, filteredBookList:List<LibraryDataBase.BookInfo>) {
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

        if (filteredBookList.none { it.checkOut == "대여 가능" }) {
            println("대여 가능한 도서가 없는 상태입니다. 혹시 모르니 도서명을 확인 해보세요. \n ")
            return
        }
    }

    private fun oneBookSearched(filteredBookList:List<LibraryDataBase.BookInfo>) {
        MemberPrintFormat.BORROWBOOK.print("selectBook")
        filteredBookList.forEach {
            println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
        }

        println("대여하시겠습니까? Y/N")
        val input = sc.nextLine()
        when (input.toUpperCase()) {
            "Y" -> {
                var memberID = askId()
                if (memberID == "") return

                LibraryDataBase.memberList.filter { it.id == memberID }[0].checkOutHistory.add(
                    LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), filteredBookList[0].name, "대여중"))
                LibraryDataBase.bookList.filter { it.name == filteredBookList[0].name }[0].checkOut = "대여 불가능"
                println("\n\uD83D\uDCDA대여 완료 ^^")
                return
            }
        }
        return

    }

    private fun moreThanOneBookSearched(filteredBookList:List<LibraryDataBase.BookInfo>) {
        MemberPrintFormat.BORROWBOOK.print("selectBook2")
        filteredBookList.forEach {
            println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
        }

        println("도서명:")
        val bookName = sc.nextLine()

        val memberID = askId()
        if (memberID == "") return

        LibraryDataBase.memberList.filter { it.id == memberID }[0].checkOutHistory.add(
            LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "대여중")
        )
        LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 불가능"
        println("\n\uD83D\uDCDA대여 완료 ^^")
    }

}








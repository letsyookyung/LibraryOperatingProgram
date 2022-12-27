package library


import java.time.LocalDateTime
import java.util.*

open class LibraryWorkForMember(var id:String, var pwd:String) {

    var sc = Scanner(System.`in`)

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
                var bookName = MemberPrintFormat.SEARCHBOOK.print("askBookName")
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
                var author = MemberPrintFormat.SEARCHBOOK.print("askAuthor")

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
                var bookName = MemberPrintFormat.BORROWBOOK.print("askBookName")

                if (LibraryDataBase.bookList.none { it.name == bookName }) {
                    MemberPrintFormat.RETURNBOOK.print("noBook")
                    return
                }
                if (LibraryDataBase.bookList.filter { it.name == bookName }.none { it.checkOut == "대여 가능" }) {
                    println("대여 가능한 도서가 없는 상태입니다. 혹시 모르니 도서명을 확인 해보세요. \n ")
                    return
                }

                MemberPrintFormat.BORROWBOOK.print("selectBook1")
                LibraryDataBase.bookList.filter { it.name == bookName }.forEach {
                    println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
                }
                println("대여하시겠습니까? Y/N")
                var input = sc.nextLine()
                when (input.toUpperCase()) {
                    "Y" -> {
                        LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.add(
                            LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "대여중")
                        )
                        LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 불가능"
                        println("\n\uD83D\uDCDA대여 완료 ^^")
                    }
                }
            }
            //저자명
            "2" -> {
                var author = MemberPrintFormat.BORROWBOOK.print("askAuthor")

                if (LibraryDataBase.bookList.none { it.author == author }) {
                    MemberPrintFormat.RETURNBOOK.print("noBook")
                    return
                }
                if (LibraryDataBase.bookList.filter { it.author == author }.none { it.checkOut == "대여 가능" }) {
                    println("대여 가능한 도서가 없는 상태입니다. 혹시 모르니 도서명을 확인 해보세요. \n ")
                    return
                }

                MemberPrintFormat.BORROWBOOK.print("selectBook")
                if (LibraryDataBase.bookList.filter { it.author == author }.size == 1) {
                    LibraryDataBase.bookList.filter { it.author == author }.forEach {
                        println("➡️ ${it.name} | ${it.author} | ${it.checkOut}")
                    }
                    println("대여하시겠습니까? Y/N")
                    var input = sc.nextLine()
                    when (input.toUpperCase()) {
                        "Y" -> {
                            LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.add(
                                LibraryDataBase.HistoryByPersonInfo(
                                    LocalDateTime.now(),
                                    LibraryDataBase.bookList.filter { it.author == author }[0].name, "대여중"
                                )
                            )
                            LibraryDataBase.bookList.filter { it.name == LibraryDataBase.bookList.filter { it.author == author }[0].name }[0].checkOut =
                                "대여 불가능"
                            println("\n\uD83D\uDCDA대여 완료 ^^")
                            return
                        }
                    }
                    return
                }

                MemberPrintFormat.BORROWBOOK.print("selectBook2")
                LibraryDataBase.bookList.filter { it.author == author }.forEach{println("➡️ ${it.name} | ${it.author} | ${it.name}")}
                println("도서명 : ")
                var bookName = sc.nextLine()
                if (LibraryDataBase.bookList.none { it.name == bookName }) {
                    MemberPrintFormat.RETURNBOOK.print("noBook")
                    return
                }

                LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.add(
                    LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "대여중"))
                LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 불가능"
                println("\n\uD83D\uDCDA대여 완료 ^^")

            }
        }
    }

    fun returnBook(bookName: String) {

        if (LibraryDataBase.bookList.none { it.name == bookName }) {
            MemberPrintFormat.RETURNBOOK.print("noBook")
            return
        }
        if (LibraryDataBase.bookList.filter { it.name == bookName }.none { it.checkOut == "대여 불가능" }) {
            println("이 도서는 대여 가능 상태입니다. 도서명을 확인 해보세요. \n ")
            return
        }

        var returnBookListById =
            LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.groupBy { it.book == bookName }[true]
        if (returnBookListById != null) {
            if (returnBookListById.last().lastStatus == "대여중") {
                LibraryDataBase.memberList.filter { it.id == id }[0].checkOutHistory.add(
                    LibraryDataBase.HistoryByPersonInfo(LocalDateTime.now(), bookName, "반납 완료")
                )
                LibraryDataBase.bookList.filter { it.name == bookName }[0].checkOut = "대여 가능"
                println("반납 완료 ^^")
            } else {
                println("해당 도서를 이미 반납하셨습니다.")
            }
        } else {
            println("반납 요청하신 도서를 대여중이지 않습니다.")


        }
    }

}



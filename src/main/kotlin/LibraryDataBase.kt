package library

import java.time.LocalDate

class LibraryDataBase {

    companion object {

        var managerList = mutableListOf<PeopleInfo>()

        var memberList = mutableListOf<PeopleInfo>()

        var purchaseHistoryTable = mutableListOf(
            BookInfo("kotlin바보","이유경",8000, ""),
            BookInfo("바보가바보에게","박명수",12000, ""),
            BookInfo("오늘은목요일","ivy",20000, ""))

            var bookList = mutableListOf(
                BookInfo("kotlin바보","이유경",8000, "대여 가능"),
                BookInfo("바보가바보에게","박명수",12000, "대여 가능"),
                BookInfo("오늘은목요일","ivy",20000, "대여 가능"))

    }

    data class BookInfo(val name: String, val author: String, var price: Int, var checkOut: String = "대여 가능") {
        var remainBalance:Int = PurchaseBook.totalBalance
    }
    data class PeopleInfo(val id: String, val pwd: String, val type: String) {
        var checkOutHistory = mutableListOf<HistoryByPersonInfo>()
    }

    data class HistoryByPersonInfo(var date: LocalDate, val book:String, val lastStatus:String)

}

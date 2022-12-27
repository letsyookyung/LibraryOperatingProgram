package library

import java.time.LocalDateTime


class LibraryDataBase {

    companion object {

        var managerList = mutableListOf(
           PeopleInfo("ron", "1234", "관리자")
        )

        var memberList = mutableListOf(
            PeopleInfo("ivy", "1234", "멤버"),
            PeopleInfo("hailey", "1234", "멤버"),
            PeopleInfo("betty", "1234", "멤버"),

        )

        var purchaseHistoryTable = mutableListOf(
            BookInfo("kotlin","이유경",8000, "대여 가능"),
            BookInfo("frombabo","박명수",12000, "대여 가능"),
            BookInfo("hello","ivy",20000, "대여 불가능"),
            BookInfo("tobabo","anderson",20000, "대여 가능"),
            BookInfo("carrot","oscar",20000, "대여 가능"),
            BookInfo("insurance","wannee",20000, "대여 가능"),
            BookInfo("monday","maeve",20000, "대여 불가능"),
            BookInfo("tuesday","adele",20000, "대여 가능"),
            BookInfo("wednesday","rex",20000, "대여 가능"),
            BookInfo("thursday","pete",20000, "대여 불가능"),
            BookInfo("friday","정기주",20000, "대여 가능"))

        var bookList = purchaseHistoryTable

    }

    data class BookInfo(val name: String, val author: String, var price: Int, var checkOut: String = "대여 가능") {
        var remainBalance:Int = PurchaseBook.totalBalance
    }
    data class PeopleInfo(val id: String, val pwd: String, val type: String) {
        var checkOutHistory = mutableListOf<HistoryByPersonInfo>()
    }

    data class HistoryByPersonInfo(var date: LocalDateTime, val book:String, val lastStatus:String)

}

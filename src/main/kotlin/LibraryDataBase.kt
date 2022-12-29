package library

import java.time.LocalDateTime


class LibraryDataBase {

    companion object {

        val managerList = mutableListOf(
            PeopleInfo("ron", "1234", "관리자")
        )

        val memberList = mutableListOf(
            PeopleInfo("ivy", "1234", "멤버"),
            PeopleInfo("hailey", "1234", "멤버"),
            PeopleInfo("betty", "1234", "멤버"),

            )

        val purchaseHistoryTable = mutableListOf(
            BookInfo("kotlin", "이유경", 8000, "대여 가능"),
            BookInfo("frombabo", "박명수", 12000, "대여 가능"),
            BookInfo("hello", "ivy", 20000, "대여 가능"),
            BookInfo("tobabo", "anderson", 20000, "대여 가능"),
            BookInfo("carrot", "oscar", 20000, "대여 가능"),
            BookInfo("insurance", "wanee", 20000, "대여 가능"),
            BookInfo("monday", "maeve", 20000, "대여 가능"),
            BookInfo("tuesday", "adele", 20000, "대여 가능"),
            BookInfo("wednesday", "rex", 20000, "대여 가능"),
            BookInfo("thursday", "pete", 20000, "대여 가능"),
            BookInfo("friday", "ivy", 20000, "대여 가능")
        )

        val bookList = purchaseHistoryTable

        var totalBalance = 100000


    }


    data class BookInfo(val name: String, val author: String, var price: Int, var checkOut: String = "대여 가능") {
        var remainBalance = totalBalance
    }

    data class PeopleInfo(val id: String, val pwd: String, val type: String) {
        var checkOutHistory = mutableListOf<HistoryByPersonInfo>()
    }

    data class HistoryByPersonInfo(var date: LocalDateTime, val book: String, val lastStatus: String)


}

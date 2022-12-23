package library
import java.util.*


enum class PrintFormatManager(
    val task : String,
    val specificTask : String,
): Printable {

    MANAGER("start", "menu") {

        override fun <T> print(task: T, specificTask: T): T {
            when (task) {
                "start" -> {
                    when (specificTask) {
                        "menu" -> {
                            println(
                                "\n\uD83D\uDE4F원하는 업무 번호를 선택하세요. " + "\n1.전체 도서 목록 조회 " + "\n2.대여/반납 업데이트" +
                                        "\n3.도서 구매 및 도서 목록 업데이트" + "\n4.로그아웃" + "\n5.프로그램 종료"
                            )
                            var input = sc.nextLine()
                            return input as T
                        }
                    }
                }

                "checkBookList" -> {
                    println("\n📚전체 도서 목록: ")
                    LibraryDataBase.bookList.forEach { item ->
                        println("➡️ ${item.name} | ${item.author} | ${item.checkOut} ")
                     }

                }

                "updateBookListStatus" -> {
                    when(specificTask){
                        "menu" -> {
                            println("\n\uD83D\uDE4F원하는 상세 업무를 선택하세요." + "\n1.도서 대여 처리" + "\n2.도서 반납 처리")
                            var input = sc.nextLine()
                            return input as T
                        }
                        "askBookName" -> {
                            println("\n\uD83D\uDCDA도서명을 입력하세요.")
                            var input = sc.nextLine()
                            return input as T
                        }
                        "checkOutDeny" -> println("\n\uD83D\uDE4F해당 도서는 대여 불가능합니다. 죄송합니당")
                        "checkOutAccept" -> println("\n📙대여 완료. 대여 기간 7일~")
                        "noBookName" -> println("\n🙏입력하신 도서명을 찾지 못합니다. 도서명을 다시 확인해주세요.")
                        "return" -> println("\n🧤반납 완료. 잘하셨어요^^")
                    }
                }
                "purchase" -> {
                    when(specificTask) {
                        "menu" -> {
                            println("\n\uD83D\uDE4F원하는 상세 업무를 선택하세요." + "\n1.도서 구매" + "\n2.도서 구매 및 잔액 내역 확인")
                            var input = sc.nextLine()
                            return input as T
                        }

                        "askBookInfo" -> {
                            println("\n\uD83D\uDE4F구매 할 도서명/저자/가격 입력해주세요. (ex. 비하인드 더 도어/ivy/16000)")
                            var input = sc.nextLine()
                            return input as T
                        }
                        "addInBookList" -> println("\uD83D\uDCD9새로 구매한 도서 추가 완료.")
                        "printPurchaseHistory" -> {
                            println("\n\uD83D\uDCB6도서 구매 및 잔액 내역 : ")
                            LibraryDataBase.purchaseHistoryTable.forEach { item ->
                                println("➡️ ${item.name} | ${item.author} | ${item.price} | ${item.remainBalance}")
                            }
                        }
                        "noMoney" -> println("\uD83D\uDCB6도서 구매 예산을 초과합니다. 충전 후 이용하시죠? ")
                    }
                }
            }
            return "" as T
        }
    },

    MEMBER("start", "menu"){
        override fun <T> print(task: T, specificTask: T): T{
            return "" as T
        }
    };


    var sc = Scanner(System.`in`)



}


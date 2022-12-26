package library
import java.util.*


enum class MemberPrintFormat(val task: String) : Printable {

    START("task") {
        override fun <T> print(task: T): T {
            when(task) {
                "menu" -> {
                    println(
                        "\n\uD83D\uDE4F번호를 선택하세요. " + "\n1.나의 도서 대여/반납 현황 보기 " + "\n2.도서 검색" + "\n3.도서 대여" +
                                "\n4.도서 반납" + "\n5.로그아웃" + "\n6.프로그램 종료"
                    )
                    var input = sc.nextLine()
                    return input as T
                }
            }
            return "" as T
        }
    },

    PRINTMYCHECKOUTSTATUS("task") {
        override fun <T> print(task: T): T {
            var idx = task.toString()
            println("\n📚나의 도서 대여/반납 현황: ")
            LibraryDataBase.memberList[idx.toInt()].checkOutHistory.forEach { item ->
                println("➡️ ${item.date} | ${item.book} | ${item.lastStatus} ")
            }
            return "" as T
        }
    },

    SEARCHBOOK("task") {
        override fun <T> print(task: T): T {
            when(task) {

            }
            return "" as T
        }
    },

    BORROWBOOK("task") {
        override fun <T> print(task: T): T {
            when(task) {
                "menu" -> {
                    println(
                        "\n어떤 항목으로 검색하시겠습니까? \n1.도서명 \n2.저자명"
                    )
                    var input = sc.nextLine()
                    return input as T
                }
                "askBookName" -> {
                    println("\n\uD83D\uDCDA도서명을 입력하세요.")
                    var input = sc.nextLine()
                    return input as T
                }
                "askAuthor" -> {
                    println("\n\uD83D\uDCDA저자명을 입력하세요.")
                    var input = sc.nextLine()
                    return input as T
                }
                "selectBook" -> {
                    println("📚대여 가능한 목록 중 해당하는 도서의 번호를 입력해주세요. q를 누르면 뒤로 돌아갑니다.)")
                }
                "noBook" -> println("🙏입력하신 도서명 혹은 저자명을 찾지 못합니다. 다시 확인해주세요.")


            }
            return "" as T
        }
    },

    RETURNBOOK("task") {
        override fun <T> print(task: T): T {
            when(task) {

            }
            return "" as T
        }
    };


    var sc = Scanner(System.`in`)

}


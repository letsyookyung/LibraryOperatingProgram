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

    SEARCHBOOK("task") {
        override fun <T> print(task: T): T {
            when(task) {
                "byWhichField" -> {
                    println("\n\uD83D\uDE4F어떤 필드로 검색하시겠습니까?" + "\n1.도서명" + "\n2.저자명" + "\n3.전체 도서 목록")
                    var input = sc.nextLine()
                    return input as T
                }
                "askBookName" -> {
                    println("\n\uD83D\uDCDA도서명을 입력하세요.")
                    var input = sc.nextLine()
                    return input as T
                }
                "askAuthor" -> {
                    println("\n\uD83D\uDCDA저장명을 입력하세요.")
                    var input = sc.nextLine()
                    return input as T
                }
                "bookList" -> {
                    println("\n📚도서 목록 : ")
                }


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
                "selectBook1" -> {
                    println("\n📚대여 가능한 도서 목록 : ")
                }
                "selectBook2" -> {
                    println("\n두개 이상의 도서가 검색 되었습니다. ✓도서명✓으로 다시 입력해주세요!\n📚대여 가능한 도서 목록 :")
                    }
                "noBook" -> println("🙏입력하신 도서명 혹은 저자명을 찾지 못합니다. 다시 확인해주세요.")


            }
            return "" as T
        }
    },

    RETURNBOOK("task") {
        override fun <T> print(task: T): T {
            when(task) {
                "askBookName" -> {
                    println("\n\uD83D\uDCDA도서명을 입력하세요.")
                    var input = sc.nextLine()
                    return input as T
                }
                "noBook" -> println("🙏입력하신 도서명 혹은 저자명을 찾지 못합니다. 다시 확인해주세요.")


            }
            return "" as T
        }
    };


    var sc = Scanner(System.`in`)

}


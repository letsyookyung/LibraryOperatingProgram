package library
import java.util.*
enum class ProgramPrintFormat(val task: String) : Printable {

    START("task") {
        override fun <T> print(task: T): T {
            when (task) {
                "menu" -> {
                    println("\n🙏원하는 모드를 선택하세요. \n 1. 도서관 관리자 모드 \n 2. 도서관 멤버 모드 \n 3. 사용자 등록 \n 4. 프로그램 종료")
                    val input = sc.nextLine()
                    return input as T
                }

                "login" -> {
                    println("\n\uD83D\uDE4F로그인 id 및 비밀번호를 입력해주세요.\nid❔ ")
                    val id = sc.nextLine()
                    println("pwd❔ ")
                    val pwd = sc.nextLine()
                    val loginSet = "${id},${pwd}"
                    return loginSet as T
                }

                "loginFail" -> println("\n\uD83D\uDE4F로그인 id 혹은 비밀번호를 확인해주세요.\n")
            }

                return "" as T
            }
        },

    REGISTER("task") {
        override fun <T> print(task: T) : T {
            when (task) {
                "askPersonInfo" -> {
                    println("\n\uD83D\uDE4F등록할 모드, 로그인 id, 비밀번호를 입력해주세요.\n관리자 or 멤버❔ ")
                    val mode = sc.nextLine()
                    println("id❔ ")
                    val id = sc.nextLine()
                    println("pwd❔ ")
                    val pwd = sc.nextLine()
                    val loginSet = "${mode},${id},${pwd}"
                    return loginSet as T
                }
            }
            return "" as T
        }
    };

    val sc = Scanner(System.`in`)

}


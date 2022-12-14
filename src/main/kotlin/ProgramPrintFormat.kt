package library

import java.util.*
enum class ProgramPrintFormat(val task: String) : Printable {
    START("task") {
        override fun <T> print(task: T) : T {
            when (task) {
                "menu" -> {
                    println("\n๐์ํ๋ ๋ชจ๋๋ฅผ ์ ํํ์ธ์. \n 1. ๋์๊ด ๊ด๋ฆฌ์ ๋ชจ๋ \n 2. ๋์๊ด ๋ฉค๋ฒ ๋ชจ๋ \n 3. ์ฌ์ฉ์ ๋ฑ๋ก \n 4. ํ๋ก๊ทธ๋จ ์ข๋ฃ")
                    val input = sc.nextLine()
                    return input as T
                }
                "login" -> {
                    println("\n\uD83D\uDE4F๋ก๊ทธ์ธ id ๋ฐ ๋น๋ฐ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์.\nidโ ")
                    val id = sc.nextLine()
                    println("pwdโ ")
                    val pwd = sc.nextLine()
                    val loginSet = "${id},${pwd}"
                    return loginSet as T
                }
                "loginFail" -> println("\n\uD83D\uDE4F๋ก๊ทธ์ธ id ํน์ ๋น๋ฐ๋ฒํธ๋ฅผ ํ์ธํด์ฃผ์ธ์.\n")
            }
            return "" as T
        }
    },

    REGISTER("task") {
        override fun <T> print(task: T) : T {
            when (task) {
                "askPersonInfo" -> {
                    println("\n\uD83D\uDE4F๋ฑ๋กํ  ๋ชจ๋, ๋ก๊ทธ์ธ id, ๋น๋ฐ๋ฒํธ๋ฅผ ์๋ ฅํด์ฃผ์ธ์.\n๊ด๋ฆฌ์ or ๋ฉค๋ฒโ ")
                    val mode = sc.nextLine()
                    println("idโ ")
                    val id = sc.nextLine()
                    println("pwdโ ")
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


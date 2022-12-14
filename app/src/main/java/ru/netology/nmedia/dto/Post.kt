package ru.netology.nmedia.dto


data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    val likes: Int = 0,
    val likedByMe: Boolean = false,
    val shares: Int,
    val shared: Boolean = false,
    val video: String? = null
) {

    fun viewFormat(likes: Int): String {
        if (likes in 1000..9999) {
            if (likes % 1000 == 0) {
                return ("${likes / 1000}K")
            }
            return ("%.1f".format(likes / 1000.0) + "K")
        }

        if (likes in 10000..999999) return ("${likes / 1000}K")

        if (likes >= 1000000) {
            if (likes % 1000000 == 0) {
                return ("${likes / 1000000}M")
            }
            return ("%.1f".format(likes / 1000000.0) + "M")
        }
        return likes.toString()
    }
}


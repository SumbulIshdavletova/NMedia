package ru.netology.nmedia.dto


data class Post(
    val id: Long,
    val author: String,
    val content: String,
    val published: String,
    var likes: Int = 3400,
    var likedByMe: Boolean = false,
    var shares: Int = 5300,
    var shared: Boolean = false
) {

    fun viewFormat(likes: Int): String {
        if (likes in 1000..9999) {
            if (likes % 1000 == 0) {
                return ("${likes / 1000}K")
            } else {
                var likes = likes / 1000.0
                return ("%.1f".format(likes) + "K")
            }
        }

        if (likes in 10000..999999) {
            return ("${likes / 1000}K")
        }

        if (likes >= 1000000) {
            if (likes % 1000000 == 0) {
                return ("${likes / 1000000}M")
            } else {
                var likes = likes / 1000000.0
                return ("%.1f".format(likes) + "M")
            }
        }
        return likes.toString()
    }
}


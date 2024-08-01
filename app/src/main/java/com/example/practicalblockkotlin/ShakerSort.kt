package com.example.practicalblockkotlin

fun shakerSort(list: MutableList<Int?>?): List<Int?>? {
    if (list == null) {
        return null
    }

    if (list.isEmpty()) {
        return list
    }

    fun swap(i: Int, j: Int) {
        val temp = list[i]
        list[i] = list[j]
        list[j] = temp
    }

    var right = list.size - 1
    var left = 0

    do {
        var swapped = false

        for (i in 0 until right)
            if ((list[i] == null && list[i + 1] != null) ||
                ((list[i] != null && list[i + 1] != null) && list[i]!! > list[i + 1]!!)
            ) {
                swap(i, i + 1)
                swapped = true
            }
        right--

        if (!swapped) break
        swapped = false

        for (i in right - 1 downTo left)
            if ((list[i] == null && list[i + 1] != null) ||
                ((list[i] != null && list[i + 1] != null) && list[i]!! > list[i + 1]!!)
            ) {
                swap(i, i + 1)
                swapped = true
            }
        left++
    } while (swapped)

    return list
}
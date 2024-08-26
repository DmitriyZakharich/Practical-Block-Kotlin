package com.example.practicalblockkotlin.topic_1_kotlin

/**
 * Могут ли возникнуть какие-то проблемы, если мы будем использовать подобный класс
 * в качестве ключа для HashMap?
 *
 * Ответ:
 * HashCode будет вычисляться на основе параметров конструктора
 * без учета field3. Это может привести к ложному равенству ключей, если
 * не учтем этот момент.
 */


data class Key(
val field1: Int,
var field2: String,
) {
    var field3: String? = null
}

package com.example.practicalblockkotlin

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ShakerSortTest {

    @Test
    fun shakerSortSuccessTest() {
        val list = mutableListOf(12, null, 15, 9, 3, null, 6)
        val result = shakerSort(list)
        val expected = listOf(3, 6, 9, 12, 15, null, null)
        assertEquals(expected, result)
    }

    @Test
    fun shakerSortFailureTest() {
        val list = mutableListOf(12, null, 15, 9, 3, null, 6)
        val result = shakerSort(list)
        val expected = listOf(12, null, 15, 9, 3, null, 6)
        assertNotEquals(expected, result)
    }

    @Test
    fun nullListTest() {
        val result = shakerSort(null)
        val expected = null
        assertEquals(expected, result)
    }

    @Test
    fun emptyListTest() {
        val result = shakerSort(mutableListOf())
        val expected = mutableListOf<Int>()
        assertEquals(expected, result)
    }
}
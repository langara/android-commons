package com.elpassion.android.commons.recycler.basic

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class UtilsTest {

    @Test
    fun shouldCreateBasicMapWithAsBasicMap() {
        val source = mapOf(1 to "A")
        val basicMap = source.asBasicMap()

        assertEquals(basicMap[1], "A")
    }

    @Test
    fun shouldCreateBasicMapWithBasicMapOf() {
        val basicMap = basicMapOf(1 to "A", 2 to "B")

        assertEquals(basicMap[1], "A")
        assertEquals(basicMap[2], "B")
    }

    @Test
    fun shouldCreateBasicMutableMapWithAsBasicMutableMap() {
        val source = mutableMapOf(1 to "A")
        val basicMutableMap = source.asBasicMutableMap()

        assertEquals(basicMutableMap[1], "A")

        basicMutableMap[1] = "AAA"

        assertEquals(basicMutableMap[1], "AAA")
    }

    @Test
    fun shouldCreateBasicMutableMapWithBasicMutableMapOf() {
        val basicMutableMap = basicMutableMapOf(1 to "A", 2 to "B")

        assertEquals(basicMutableMap[1], "A")
        assertEquals(basicMutableMap[2], "B")

        basicMutableMap[1] = "AAA"

        assertEquals(basicMutableMap[1], "AAA")
    }

    @Test
    fun shouldCreateBasicListWithAsBasicList() {
        val source = listOf("A")
        val basicList = source.asBasicList()

        assertEquals(basicList[0], "A")
    }

    @Test
    fun shouldCreateBasicListWithBasicListOf() {
        val basicList = basicListOf("A", "B")

        assertEquals(basicList[0], "A")
        assertEquals(basicList[1], "B")
    }

    @Test
    fun shouldCreateBasicMutableListWithAsBasicMutableList() {
        val source = mutableListOf("A")
        val basicMutableList = source.asBasicMutableList()

        assertEquals(basicMutableList[0], "A")

        basicMutableList.insert(0, "X")

        assertEquals(basicMutableList[0], "X")
        assertEquals(basicMutableList[1], "A")
    }

    @Test
    fun shouldCreateBasicMutableListWithBasicMutableListOf() {
        val basicMutableList = basicMutableListOf("A", "B")

        assertEquals(basicMutableList[0], "A")
        assertEquals(basicMutableList[1], "B")

        basicMutableList[1] = "AAA"

        assertEquals(basicMutableList[1], "AAA")
    }

    @Test
    fun shouldCreateBasicMapOfBasicListsWithAsBasicMapOfBasicLists() {
        val source = mapOf("A" to listOf("AA", "AB"), "B" to listOf("BA", "BB"))
        val basicMapOfBasicLists = source.asBasicMapOfBasicLists()

        assertEquals(basicMapOfBasicLists["A"]!![0], "AA")
        assertEquals(basicMapOfBasicLists["A"]!![1], "AB")
        assertEquals(basicMapOfBasicLists["B"]!![0], "BA")
        assertEquals(basicMapOfBasicLists["B"]!![1], "BB")
        assertNull(basicMapOfBasicLists["C"])
    }

    @Test
    fun shouldAddAllGivenItemsToBasicMutableList() {
        val basicMutableList = basicMutableListOf("A")
        basicMutableList.addAll(listOf("B", "C"))

        assertEquals(basicMutableList.size, 3)
        assertEquals(basicMutableList[0], "A")
        assertEquals(basicMutableList[1], "B")
        assertEquals(basicMutableList[2], "C")
    }
}
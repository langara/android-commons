package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicMutableList
import org.junit.Assert.assertEquals
import org.junit.Test

class BasicMutableListImplTest {

    @Test
    fun shouldCreateBasicMutableListFromRegularList() {
        val basicMutableList = BasicMutableListImpl<String>(mutableListOf())

        assert(basicMutableList is BasicMutableList<String>)
    }

    @Test
    fun shouldReturnCorrectValuesForGivenPositions() {
        val basicMutableList = BasicMutableListImpl(mutableListOf("A", "B", "ABC"))

        assertEquals(basicMutableList[0], "A")
        assertEquals(basicMutableList[1], "B")
        assertEquals(basicMutableList[2], "ABC")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun shouldThrowOutOfBoundsExceptionForNonExistingValue() {
        val basicMutableList = BasicMutableListImpl<String>(mutableListOf())

        basicMutableList[0]
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun shouldContainCorrectValuesAndThrowForWrongPosition() {
        val basicMutableList = BasicMutableListImpl(mutableListOf("A", "B", "ABC"))

        assertEquals(basicMutableList[0], "A")
        assertEquals(basicMutableList[1], "B")
        assertEquals(basicMutableList[2], "ABC")

        basicMutableList[3]
    }

    @Test
    fun shouldReturnValidValuesAfterSourceChange() {
        val source = mutableListOf("A", "B", "C")
        val basicMutableList = BasicMutableListImpl(source)

        source[1] = "E"
        source[2] = "P"

        assertEquals(basicMutableList[0], "A")
        assertEquals(basicMutableList[1], "E")
        assertEquals(basicMutableList[2], "P")
    }

    @Test
    fun shouldReturnCorrectSizeWhenSourceChanges() {
        val source = mutableListOf("A", "B", "C")
        val basicMutableList = BasicMutableListImpl(source)
        assertEquals(basicMutableList.size, 3)
        source.add("D")
        assertEquals(basicMutableList.size, 4)
    }

    @Test
    fun shouldClearSourceWhenClearMethodCalled() {
        val source = mutableListOf("A", "B", "C")
        val basicMutableList = BasicMutableListImpl(source)
        assertEquals(basicMutableList.size, 3)
        basicMutableList.clear()
        assertEquals(source.size, 0)
        assertEquals(basicMutableList.size, 0)
    }

    @Test
    fun shouldReturnValidValuesAfterChange() {
        val basicMutableList = BasicMutableListImpl(mutableListOf("A", "B", "C"))

        basicMutableList[1] = "E"
        basicMutableList[2] = "P"

        assertEquals(basicMutableList[0], "A")
        assertEquals(basicMutableList[1], "E")
        assertEquals(basicMutableList[2], "P")
    }

    @Test
    fun shouldReturnValidValuesAfterInsert() {
        val basicMutableList = BasicMutableListImpl(mutableListOf("A", "B", "C"))

        basicMutableList.insert(1, "E")
        basicMutableList.insert(4, "P")

        assertEquals(basicMutableList[0], "A")
        assertEquals(basicMutableList[1], "E")
        assertEquals(basicMutableList[2], "B")
        assertEquals(basicMutableList[3], "C")
        assertEquals(basicMutableList[4], "P")
        assertEquals(basicMutableList.size, 5)
    }

    @Test
    fun shouldReturnValidValuesAfterRemove() {
        val basicMutableList = BasicMutableListImpl(mutableListOf("A", "B", "C"))

        basicMutableList.remove(1)

        assertEquals(basicMutableList[0], "A")
        assertEquals(basicMutableList[1], "C")
        assertEquals(basicMutableList.size, 2)
    }
}
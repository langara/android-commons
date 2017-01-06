package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicList
import org.junit.Assert.assertEquals
import org.junit.Test

class BasicListImplTest {

    @Test
    fun shouldCreateBasicListFromRegularList() {
        val basicList = BasicListImpl<String>(listOf())

        assert(basicList is BasicList<String>)
    }

    @Test
    fun shouldReturnCorrectValuesForGivenPositions() {
        val basicList = BasicListImpl(listOf("A", "B", "ABC"))

        assertEquals(basicList[0], "A")
        assertEquals(basicList[1], "B")
        assertEquals(basicList[2], "ABC")
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun shouldThrowOutOfBoundsExceptionForNonExistingValue() {
        val basicList = BasicListImpl<String>(listOf())

        basicList[0]
    }

    @Test(expected = IndexOutOfBoundsException::class)
    fun shouldContainCorrectValuesAndThrowForWrongPosition() {
        val basicList = BasicListImpl(listOf("A", "B", "ABC"))

        assertEquals(basicList[0], "A")
        assertEquals(basicList[1], "B")
        assertEquals(basicList[2], "ABC")

        basicList[3]
    }

    @Test
    fun shouldReturnValidValuesAfterSourceChange() {
        val source = mutableListOf("A", "B", "C")
        val basicList = BasicListImpl(source)

        assertEquals(basicList[0], "A")
        assertEquals(basicList[1], "B")
        assertEquals(basicList[2], "C")

        source[1] = "E"
        source[2] = "P"

        assertEquals(basicList[0], "A")
        assertEquals(basicList[1], "E")
        assertEquals(basicList[2], "P")
    }

    @Test
    fun shouldReturnCorrectSizeWhenSourceChanges() {
        val source = mutableListOf("A", "B", "C")
        val basicList = BasicListImpl(source)
        assertEquals(basicList.size, 3)
        source.add("D")
        assertEquals(basicList.size, 4)
    }
}
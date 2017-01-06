package com.elpassion.android.commons.recycler.basic.impl

import com.elpassion.android.commons.recycler.basic.BasicMap
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class BasicMapImplTest {

    @Test
    fun shouldCreateBasicMapFromRegularMap() {
        val basicMap = BasicMapImpl<Int, String>(mapOf())

        assert(basicMap is BasicMap<Int, String?>)
    }

    @Test
    fun shouldReturnCorrectValuesForGivenKeys() {
        val basicMap = BasicMapImpl(mapOf(1 to "A", 2 to "B", -7 to "ABC"))

        assertEquals(basicMap[1], "A")
        assertEquals(basicMap[2], "B")
        assertEquals(basicMap[-7], "ABC")
    }

    @Test
    fun shouldReturnNullForNonExistingValues() {
        val basicMap = BasicMapImpl(mapOf(1 to "A", 2 to "B", -7 to "ABC"))

        assertNull(basicMap[-1])
        assertNull(basicMap[3])
        assertNull(basicMap[4])
    }

    @Test
    fun shouldReturnValidValueAfterSourceChange() {
        val source = mutableMapOf(1 to "G", 2 to "O")
        val basicMap = BasicMapImpl(source)
        assertEquals(basicMap[1], "G")
        assertEquals(basicMap[2], "O")
        source[1] = "E"
        source[2] = "P"
        assertEquals(basicMap[1], "E")
        assertEquals(basicMap[2], "P")
    }

    @Test
    fun shouldReturnNullWhenSourceValueDeleted() {
        val source = mutableMapOf(1 to "G")
        val basicMap = BasicMapImpl(source)
        assertEquals(basicMap[1], "G")
        source.remove(1)
        assertNull(basicMap[1])
    }
}
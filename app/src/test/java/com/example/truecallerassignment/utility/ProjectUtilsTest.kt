package com.example.truecallerassignment.utility

import org.junit.Assert
import org.junit.Test

internal class ProjectUtilsTest {
    @Test
    fun tenthChar_valid_success(){
        val input = "hello world"
        val expected = 'l'
        val char = ProjectUtils.get10thChar(input)
        Assert.assertEquals(expected, char)
    }

    @Test
    fun tenthChar_invalidRange_success(){
        val input = "hello"
        val expected = '\u0000'
        val char = ProjectUtils.get10thChar(input)
        Assert.assertEquals(expected, char)
    }

    @Test
    fun tenthChar_null_success(){
        val input = null
        val expected = '\u0000'
        val char = ProjectUtils.get10thChar(input)
        Assert.assertEquals(expected, char)
    }

    @Test
    fun every10thChar_valid_success(){
        val input = "This is Truecaller Assignment"
        val expected = "r, A, "
        val result = ProjectUtils.getEvery10thCharBrief(input)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun every10thChar_validRange_success(){
        val input = "Agricultur"
        val expected = "r, "
        val result = ProjectUtils.getEvery10thCharBrief(input)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun every10thChar_invalidRange_success(){
        val input = "Hello"
        val expected = ""
        val result = ProjectUtils.getEvery10thCharBrief(input)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun every10thChar_null_success(){
        val input = null
        val expected = ""
        val result = ProjectUtils.getEvery10thCharBrief(input)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun uniqueWords_valid_success(){
        val input = "Hello World"
        val expected = 2
        val result = ProjectUtils.getUniqueWords(input)
        Assert.assertEquals(expected, result.size)
    }

    @Test
    fun uniqueWords_dupliacte_success(){
        val input = "Hello World Hello Universe"
        val expected = 3
        val result = ProjectUtils.getUniqueWords(input)
        Assert.assertEquals(expected, result.size)
    }

    @Test
    fun uniqueWords_caseSensitive_success(){
        val input = "hello Hello"
        val expected = 1
        val result = ProjectUtils.getUniqueWords(input)
        Assert.assertEquals(expected, result.size)
    }

    @Test
    fun uniqueWords_validRange_success(){
        val input = "Hello"
        val expected = 1
        val result = ProjectUtils.getUniqueWords(input)
        Assert.assertEquals(expected, result.size)
    }

    @Test
    fun uniqueWords_empty_success(){
        val input = ""
        val expected = 1
        val result = ProjectUtils.getUniqueWords(input)
        Assert.assertEquals(expected, result.size)
    }

    @Test
    fun uniqueWordsBrief_valid_success(){
        val input = "I am Sunil, I am from India, I work as an IT Professional"
        val set = ProjectUtils.getUniqueWords(input)
        val expected = "(${set.count()})\nI, am, Sunil,, from, India,, work, as, an, IT, Professional, "
        val result = ProjectUtils.getWordsBriefString(set)
        Assert.assertEquals(expected.lowercase(), result)
    }

    @Test
    fun uniqueWordsBrief_empty_success(){
        val input = ""
        val set = ProjectUtils.getUniqueWords(input)
        val expected = "(${set.count()})\n"
        val result = ProjectUtils.getWordsBriefString(set)
        Assert.assertEquals(expected, result)
    }

    @Test
    fun uniqueWordsBrief_invalid_success(){
        val input = "   "
        val set = ProjectUtils.getUniqueWords(input)
        val expected = "(${set.count()})\n"
        val result = ProjectUtils.getWordsBriefString(set)
        Assert.assertEquals(expected, result)
    }

}
package com.example.truecallerassignment.utility

import java.util.*

class ProjectUtils {
    companion object{
        fun getUniqueWords(response: String): MutableSet<String> {
            val words = response.split(" ").toSet()
//            val uniqueWords: MutableSet<String> = TreeSet<String>(String.CASE_INSENSITIVE_ORDER)
//            uniqueWords.addAll(words)
            val uniqueWords: MutableSet<String> = mutableSetOf()
            words.forEach {
                uniqueWords.add(it.lowercase())
            }

            return uniqueWords
        }

        fun get10thChar(response: String?): Char {
            if(response != null && response.length >= ProjectConstants.LENGTH_CONSTANT_10) {
                return response[ProjectConstants.LENGTH_CONSTANT_10 - 1]
            }else{
                return Char.MIN_VALUE
            }
        }

        fun getEvery10thCharBrief(response: String?):String{
            var result = String()
            if(response != null && response.length >= ProjectConstants.LENGTH_CONSTANT_10) {
                var i = ProjectConstants.LENGTH_CONSTANT_10 - 1
                while (i < response.length) {
                    result += response[i] + ", "
                    i += ProjectConstants.LENGTH_CONSTANT_10
                    if (i == ProjectConstants.LENGTH_CONSTANT_500) {
                        break
                    }
                }
            }
            return result
        }

        fun getWordsBriefString(set:Set<String>): String {
            var resultString = "(${set.count()})\n"
            for (i in 0 until set.size){
                if(set.elementAt(i).trim().isNotEmpty()) {
                    resultString += set.elementAt(i) + ", "
                }
                if(i == ProjectConstants.LENGTH_CONSTANT_10)
                    break
            }
            return resultString
        }
    }
}
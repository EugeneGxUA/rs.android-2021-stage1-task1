package subtask3

import java.util.*

class StringParser {

    private val resultList = LinkedList<String>()
//    private val regEx = Regex("\\[(.*?)\\]|\\(.*?\\)|<(.*?)>")

    private val subsRuleList = LinkedList<Pair<Int, Int>>()
    private var input: String = ""

    fun getResult(inputString: String): Array<String> {
        input = inputString
        collectString(inputString)

        for (subPair in subsRuleList) {
            resultList.add(inputString.substring(subPair.first, subPair.second))
        }

        return resultList.toTypedArray()
    }

    private fun getRules(): Set<Pair<Char, Char>> {
        val set = LinkedHashSet<Pair<Char, Char>>()

        input.forEach {
            if (it == '<') {
                set.add(Pair(first = '<', second = '>'))
            }
            if (it == '(') {
                set.add(Pair(first = '(', second = ')'))
            }
            if (it == '[') {
                set.add(Pair(first = '[', second = ']'))
            }
        }

        return set
    }

    private fun collectString(input: String) {
        for (rule in getRules()) {
            searchStringsByPair(rule)
        }
    }

    private fun searchStringsByPair(chars: Pair<Char, Char>) {
        if (!input.contains(chars.first) && !input.contains(chars.second)) {
            return
        }

        var foundOpenSymbol = false
        var ruleCounter = 0
        var openSymbol = 0;
        var closeSymbol = 0;
        var counter = 0

        while (counter < input.toCharArray().size && input.contains(chars.first) && input.contains(chars.second)) {
            if (input[counter] == chars.first) {
                ruleCounter++
                foundOpenSymbol = true
                if (ruleCounter == 1) {
                    openSymbol = counter
                }
            } else if (input[counter] == chars.second) {
                if (ruleCounter == 1) {
                    closeSymbol = counter
                }
                ruleCounter--

            }

            if (ruleCounter == 0 && foundOpenSymbol) {
                subsRuleList.add(Pair(openSymbol + 1, closeSymbol))
                input = String(input.toCharArray().also {
                    it[openSymbol] = ' '
                    it[closeSymbol] = ' '
                })

                if (input.contains(chars.first) && input.contains(chars.second)) {
                    searchStringsByPair(chars)
                } else {
                    break
                }
            }
            counter++
        }
    }
}

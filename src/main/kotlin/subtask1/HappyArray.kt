package subtask1

class HappyArray {

    fun convertToHappy(sadArray: IntArray): IntArray {
        var tmpArr = assembleHappy(sadArray)

        while (isSad(tmpArr)) {
            tmpArr = assembleHappy(tmpArr)
        }
        return tmpArr
    }

    private fun isSad(sadArray: IntArray) : Boolean {
        for (i in 1 until sadArray.size - 1) {
            if (sadArray[i - 1] + sadArray[i + 1] < sadArray[i]) {
                return true
            }
        }
        return false
    }

    private fun assembleHappy(sadArray: IntArray) : IntArray {
        val happy = mutableListOf<Int>()

        sadArray.forEachIndexed { index, element ->
            if (element == sadArray.first() || element == sadArray.last()) {
                happy.add(element)
            } else {
                if (sadArray[index - 1] + sadArray[index + 1] >= element) {
                    happy.add(element)
                }
            }
        }

        return happy.toIntArray()
    }
}

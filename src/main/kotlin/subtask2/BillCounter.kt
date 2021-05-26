package subtask2

import kotlin.math.absoluteValue

class BillCounter {

    fun calculateFairlySplit(bill: IntArray, k: Int, b: Int): String {
        val sumAnna = bill.filterIndexed { index, _ ->
            index != k
        }.sum()

        val result = (sumAnna / 2 - b).absoluteValue

        return if (result == 0) {
            "Bon Appetit"
        } else {
            result.toString()
        }
    }
}

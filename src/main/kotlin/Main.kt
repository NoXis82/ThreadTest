import kotlin.concurrent.thread

fun main() {
    val consumerA = Consumer("A")
    val consumerB = Consumer("B")
    val t1 = thread {
        consumerA.lockFirstAndTrySecond()
    }
    val t2 = thread {
        consumerB.lockFirstAndTrySecond()
    }
    t1.join()
    t2.join()
    println("main successfully finished")
}

class Consumer(private val name: String) {
    fun lockFirstAndTrySecond() {
        println("$name locked first, sleep and wait for second")
        Thread.sleep(1000)
        lockSecond()
    }

    @Synchronized
    fun lockSecond() {
        println("$name locked second")
    }
}
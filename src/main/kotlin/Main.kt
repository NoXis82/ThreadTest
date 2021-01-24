import kotlin.concurrent.thread

fun main() {
    val resource = Any()
    val consumerA = Consumer("A")
    val consumerB = Consumer("B")
    val t1 = thread {
        consumerA.lockFirstAndTrySecond(resource)
    }
    val t2 = thread {
        consumerB.lockFirstAndTrySecond(resource)
    }
    t1.join()
    t2.join()
    println("main successfully finished")
}

class Consumer(private val name: String) {

    fun lockFirstAndTrySecond(resource: Any) {
        synchronized(resource) {
            println("$name locked first, sleep and wait for second")
            Thread.sleep(1000)
            lockSecond()
        }
    }

    fun lockSecond() {
        println("$name locked second")
    }
}
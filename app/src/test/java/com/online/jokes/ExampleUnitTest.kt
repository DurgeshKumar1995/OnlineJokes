package com.online.jokes

import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import java.util.concurrent.ConcurrentLinkedQueue

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    lateinit var queue: ConcurrentLinkedQueue<Int>

    @Before
    fun initionlize() {
        queue = ConcurrentLinkedQueue<Int>()
    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun test_queue() {
        add(3)
        add(2)
        add(4)
        assertEquals(2, queue.size)
        println(queue)
    }

    fun add(data: Int) {
        queue.add(data)
        if (queue.size > 2) {
            queue.poll()
        }
    }
}

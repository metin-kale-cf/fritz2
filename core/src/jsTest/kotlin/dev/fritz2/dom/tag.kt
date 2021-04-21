package dev.fritz2.dom

import dev.fritz2.binding.storeOf
import dev.fritz2.dom.html.render
import dev.fritz2.identification.uniqueId
import dev.fritz2.test.initDocument
import dev.fritz2.test.runTest
import kotlinx.browser.document
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOf
import org.w3c.dom.HTMLDivElement
import kotlin.test.Test
import kotlin.test.assertEquals


class TagTests {

    @Test
    fun testSingleTag() = runTest {
        initDocument()

        val testId = uniqueId()
        val testClass = "testClass"

        render {
            div(id = testId) {
                className(flowOf(testClass))
            }
        }

        delay(100)

        val element = document.getElementById(testId).unsafeCast<HTMLDivElement>()

        assertEquals(testId, element.id)
        assertEquals("div", element.localName)
        assertEquals(testClass, element.className)
    }

    @Test
    fun testSingleTagWithBaseClass() = runTest {
        initDocument()

        val testId = uniqueId()
        val baseClass = "baseClass"
        val testClass = "testClass"

        render {
            div(baseClass = baseClass, id = testId) {
                className(flowOf(testClass))
            }
        }

        delay(100)

        val element = document.getElementById(testId).unsafeCast<HTMLDivElement>()

        assertEquals(testId, element.id)
        assertEquals("div", element.localName)
        assertEquals("$baseClass $testClass", element.className)
    }

    @Test
    fun testSingleTagWithBaseClassOnly() = runTest {
        initDocument()

        val testId = uniqueId()
        val baseClass = "baseClass"

        render {
            div(baseClass = baseClass, id = testId) {
                className(flowOf(""))
            }
        }

        delay(100)

        val element = document.getElementById(testId).unsafeCast<HTMLDivElement>()

        assertEquals(testId, element.id)
        assertEquals("div", element.localName)
        assertEquals(baseClass, element.className)
    }

    @Test
    fun testMultipleTags() = runTest {
        initDocument()

        val testRange = (0..4)
        val testIds = testRange.map { "testId$it" }
        val testClasses = testRange.map { "testClass$it" }

        render {
            ul(id = "list") {
                (flowOf(testIds)).renderEach {
                    li(id = it) {
                        classList(flowOf(testClasses))
                    }
                }
            }
        }

        delay(500)

        for (i in testRange) {
            val element = document.getElementById(testIds[i]).unsafeCast<HTMLDivElement>()
            assertEquals(testIds[i], element.id)
            assertEquals("li", element.localName)
            assertEquals(testClasses.joinToString(separator = " "), element.className, "wrong classes for $i")
        }
    }

    @Test
    fun testRenderWithCondition() = runTest {
        initDocument()

        val outerId = uniqueId()
        val innerId = uniqueId()

        val switch = storeOf(false)
        val data = storeOf("Test")

        render {
            div(id = outerId) {
                switch.data.render {
                    if (it) {
                        data.data.render { text ->
                            if (text.isNotBlank()) div(id = innerId) { +text }
                        }
                    }
                }
            }
        }

        delay(300)

        val outer = document.getElementById(outerId) as HTMLDivElement
        fun inner() = document.getElementById(innerId) as HTMLDivElement

        assertEquals(0, outer.childElementCount, "outer element has a children")

        for(i in 0..2) {
            switch.update(true)
            delay(200)

            assertEquals(1, outer.childElementCount, "[$i] outer element has no children")
            assertEquals("Test", inner().textContent, "[$i] inner has no text")

            switch.update(false)
            delay(200)

            assertEquals(0, outer.childElementCount, "[$i] outer element has no children")
        }
    }

    @Test
    fun testRenderEachPlaceholder() = runTest {
        initDocument()

        val store = storeOf(emptyList<Int>())
        val add = store.handle<Int> { l, n -> l + n }

        val testId = uniqueId()
        val firstId = uniqueId()

        render {
            div(id = testId) {
                div(id = firstId) {}
                store.renderEach {
                    ul(id = it.toString()) {}
                }
            }
        }

        val test = document.getElementById(testId) as HTMLDivElement
        assertEquals("#comment", test.lastChild?.nodeName, "placeholder is not rendered")

        for(i in 0..2) {
            add(i)
            delay(200)
            println(test.childElementCount.toString() + "\n")
            assertEquals("DIV", test.firstElementChild?.tagName, "[$i] first element is not correct")
        }

//        delay(200)
//
//        for(i in 0..2) {
//            delay(200)
//            store.update(store.current + i)
//        }

    }

}
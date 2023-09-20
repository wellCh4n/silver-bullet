package com.github.wellch4n.silver.bullet.json.fastjson

import com.github.wellch4n.silver.bullet.json.api.JSON
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test

/**
 * @author wellCh4n
 * @date 2023/9/20
 */
class FastJSONTest {

    @Test
    fun testObjectParse() {
        val obj = JSON.toObject("""
            {
                "1":"2"
            }
        """.trimIndent())
        Assertions.assertEquals("2", obj.get("1"))
    }

    @Test
    fun testArrayParse() {
        val array = JSON.toArray("""
            [
                {
                    "1": "2"
                }
            ]
        """.trimIndent())
        Assertions.assertEquals(1, array.size())
        val value = array.get(0).get("1")
        Assertions.assertEquals("2", value)
    }
}
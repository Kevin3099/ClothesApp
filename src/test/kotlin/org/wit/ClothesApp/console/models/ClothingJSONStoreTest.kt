package org.wit.ClothesApp.console.models

import org.junit.Test
import org.junit.jupiter.api.Assertions.*

class ClothingJSONStoreTest {
    val clothingStore = ClothingJSONStore()

    @Test
    fun findAll() {
        val testing = ClothingModel(1, "shoes", "Hii", 29.99, "Hii Description", "https://image")
        clothingStore.create(testing)
        assertEquals(clothingStore.findAll().size, clothingStore.clothing.size)
    }

    @Test
    fun findOne() {
        val testing = ClothingModel(10, "shoes", "Hii", 29.99, "Hii Description", "https://image")
        clothingStore.create(testing)
        assertEquals(
            clothingStore.findOne(clothingStore.clothing[clothingStore.clothing.size - 1].id),
            clothingStore.clothing[clothingStore.clothing.size - 1]
        )
    }

    @Test
    fun findByTitle() {
        val testing = ClothingModel(0, "shoes", "Hii", 29.99, "Hii Description", "https://image")
        clothingStore.create(testing)
        assertEquals(
            clothingStore.findByTitle(clothingStore.clothing[clothingStore.clothing.size - 1].title)!!.title,
            testing.title
        )
    }

    @Test
    fun filterByType() {
        val testing = ClothingModel(0, "shoes", "Hii", 29.99, "Hii Description", "https://image")
        clothingStore.create(testing)
        assertNotNull(clothingStore.filterByType("shoes").size)
    }

    @Test
    fun create() {
        val testing1 = ClothingModel(0, "shoes", "Hii", 29.99, "Hii Description", "https://image")
        clothingStore.create(testing1)
        assertTrue(clothingStore.clothing.contains(testing1))
    }

    @Test
    fun update() {
        val testing = ClothingModel(0, "shoes", "Hii", 29.99, "Hii Description", "https://image")
        clothingStore.create(testing)

        testing.title = "testing"
        clothingStore.update(testing)

        assertEquals(
            clothingStore.findOne(clothingStore.clothing[clothingStore.clothing.size - 1].id)!!.title,
            "testing"
        )
    }

    @Test
    fun delete() {
        val testing = ClothingModel(0, "shoes", "Hii", 29.99, "Hii Description", "https://image")
        clothingStore.create(testing)
        assertEquals(
            clothingStore.findOne(clothingStore.clothing[clothingStore.clothing.size - 1].id),
            clothingStore.clothing[clothingStore.clothing.size - 1]
        )

        clothingStore.delete(testing)

        assertFalse(clothingStore.clothing.contains(testing))
    }
}
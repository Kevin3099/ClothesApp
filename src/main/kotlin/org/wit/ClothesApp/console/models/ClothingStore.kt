package org.wit.ClothesApp.console.models

import org.wit.ClothesApp.console.models.ClothingModel

interface ClothingStore {
    fun findAll(): List<ClothingModel>
    fun findOne(id: Long): ClothingModel?
    fun findByTitle(title: String): ClothingModel?
    fun filterByType(clothingType: String): List<ClothingModel>
    fun filterByPrice(lowPrice: Double, highPrice: Double): List<ClothingModel>
    fun create(clothing: ClothingModel)
    fun update(clothing: ClothingModel)
    fun delete(clothing: ClothingModel)

}
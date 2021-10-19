package org.wit.ClothesApp.console.models

import mu.KotlinLogging
import org.wit.ClothesApp.console.models.ClothingModel

private val logger = KotlinLogging.logger {}

var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class ClothingMemStore : ClothingStore {

    val clothing = ArrayList<ClothingModel>()
    var filteredTypeClothing = mutableListOf<ClothingModel>()
    var filteredPriceClothing = mutableListOf<ClothingModel>()

    override fun findAll(): List<ClothingModel> {
        return clothing
    }

    override fun findOne(id: Long) : ClothingModel? {
        var foundClothing: ClothingModel? = clothing.find { p -> p.id == id }
        return foundClothing
    }
    override fun findByTitle(title: String): ClothingModel? {
        var foundClothing: ClothingModel? = clothing.find { p -> p.title == title }
        return foundClothing
    }

    override fun filterByType(clothingType: String) :MutableList<ClothingModel> {
        clothing.forEach{
            if(it.clothingType.equals(clothingType)){
                filteredTypeClothing.add(it)
            }
        }
        return filteredTypeClothing
    }

    override fun filterByPrice(lowPrice : Double, highPrice: Double) :MutableList<ClothingModel>  {
        clothing.forEach{
            if(it.price in lowPrice..highPrice){
                filteredPriceClothing.add(it)
            }
        }
        return filteredPriceClothing
    }

    override fun create(Clothing: ClothingModel) {
        Clothing.id = getId()
        clothing.add(Clothing)
        logAll()
    }

    override fun update(Clothing: ClothingModel) {
        var foundClothing = findOne(Clothing.id!!)
        if (foundClothing != null) {
            foundClothing.title = Clothing.title
            foundClothing.description = Clothing.description
        }
    }

    override fun delete(Clothing: ClothingModel) {
        clothing.remove(Clothing)
    }

    internal fun logAll() {
        clothing.forEach { logger.info("${it}") }
    }
}
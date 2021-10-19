package org.wit.ClothesApp.console.models

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import org.wit.ClothesApp.console.helpers.*
import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "clothing.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<ClothingModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}

class ClothingJSONStore : ClothingStore {

    var clothing = mutableListOf<ClothingModel>()
    var filteredTypeClothing = mutableListOf<ClothingModel>()
    var filteredPriceClothing = mutableListOf<ClothingModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<ClothingModel> {
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
        Clothing.id = generateRandomId()
        clothing.add(Clothing)
        serialize()
    }

    override fun update(Clothing: ClothingModel) {
        var foundClothing = findOne(Clothing.id!!)
        if (foundClothing != null) {
            foundClothing.title = Clothing.title
            foundClothing.description = Clothing.description
        }
         serialize()
    }

    override fun delete(Clothing: ClothingModel) {
        clothing.remove(Clothing)
        serialize()
    }

    internal fun logAll() {
        clothing.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(clothing, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        clothing = Gson().fromJson(jsonString, listType)
    }
}
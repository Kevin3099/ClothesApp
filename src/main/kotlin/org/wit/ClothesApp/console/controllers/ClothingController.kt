package org.wit.ClothesApp.console.controllers

import mu.KotlinLogging
import org.wit.ClothesApp.console.models.ClothingJSONStore
import org.wit.ClothesApp.console.models.ClothingModel
import org.wit.ClothesApp.console.views.ClothingView

class ClothingController {

    // val clothing = ClothingMemStore()
    val clothing = ClothingJSONStore()
    val ClothingView = ClothingView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Clothing Console App" }
        println("Clothing Kotlin App Version 4.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> listById()
                4 -> listByTitle()
                5 -> listFilteredByType()
                6 -> listFilteredByPrice()
                7 -> search()
                8 -> delete()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Clothing Console App" }
    }

    fun menu(): Int {
        return ClothingView.menu()
    }

    fun add() {
        var aClothing = ClothingModel()

        if (ClothingView.addClothingData(aClothing)) {
            clothing.create(aClothing)
            logger.info("Clothing Added")
        } else
            logger.info("Clothing Not Added")
    }

    fun listById() {
        ClothingView.listAllClothing(clothing)
    }

    fun update() {

        ClothingView.listAllClothing(clothing)
        var searchId = ClothingView.getId()
        val aClothing = search(searchId)

        if (aClothing != null) {
            if (ClothingView.updateClothingData(aClothing)) {
                clothing.update(aClothing)
                ClothingView.showClothing(aClothing)
                logger.info("Clothing Updated : [ $aClothing ]")
            } else
                logger.info("Clothing Not Updated")
        } else
            println("Clothing Not Updated...")
    }

    fun listFilteredByType() {
        ClothingView.filteredByTypeClothing(clothing)
    }

    fun listFilteredByPrice() {
        ClothingView.filteredByPriceClothing(clothing)
    }

    fun listByTitle() {
        ClothingView.findByTitleClothing(clothing)
    }


    fun delete() {
        ClothingView.listAllClothing(clothing)
        var searchId = ClothingView.getId()
        val aClothing = search(searchId)

        if (aClothing != null) {
            clothing.delete(aClothing)
            println("Clothing Deleted...")
            ClothingView.listAllClothing(clothing)
        } else
            println("Clothing Not Deleted...")
    }

    fun search() {
        val aClothing = search(ClothingView.getId())!!
        ClothingView.showClothing(aClothing)
    }


    fun search(id: Long): ClothingModel? {
        var foundClothing = clothing.findOne(id)
        return foundClothing
    }

    fun dummyData() {
        clothing.create(ClothingModel(title = "New York New York", description = "So Good They Named It Twice"))
        clothing.create(ClothingModel(title = "Ring of Kerry", description = "Some place in the Kingdom"))
        clothing.create(ClothingModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
}
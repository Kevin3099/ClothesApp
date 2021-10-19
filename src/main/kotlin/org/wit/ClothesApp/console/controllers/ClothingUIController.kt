package org.wit.ClothesApp.console.controllers

import mu.KotlinLogging
import org.wit.ClothesApp.console.models.ClothingJSONStore
import org.wit.ClothesApp.console.views.ListClothingScreen
import org.wit.ClothesApp.console.views.AddClothingScreen
import org.wit.ClothesApp.console.views.MenuScreen
import org.wit.ClothesApp.console.models.ClothingModel

import tornadofx.*


class ClothingUIController : Controller() {

    val clothing = ClothingJSONStore()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Placemark TornadoFX UI App" }
    }
    fun add(_title : String, _description : String){

        var aClothing = ClothingModel(title = _title, description = _description)
            clothing.create(aClothing)
            logger.info("Clothing Added")
    }

    fun loadListScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(ListClothingScreen::class, sizeToScene = true, centerOnScreen = true)
        }
        clothing.logAll()
    }

    fun loadAddScreen() {
        runLater {
            find(MenuScreen::class).replaceWith(AddClothingScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

    fun closeAdd() {
        runLater {
            find(AddClothingScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }
    fun closeList() {
        runLater {
            find(ListClothingScreen::class).replaceWith(MenuScreen::class, sizeToScene = true, centerOnScreen = true)
        }
    }

}
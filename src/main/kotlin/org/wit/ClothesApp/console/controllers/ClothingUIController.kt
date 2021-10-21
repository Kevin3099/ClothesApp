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
        logger.info { "Launching Clothing TornadoFX UI App" }
    }
    fun add(_title : String, _description : String, _clothingType: String, _price : Double, _image: String): Boolean {

        var aClothing = ClothingModel(title = _title, description = _description, clothingType = _clothingType, price = _price, image = _image)
          if ((_title.isNotEmpty() && _title.length < 250) && (_description.isNotEmpty() && _description.length <= 400)
             && (_image.contains("https://"))) {

            clothing.create(aClothing)
            logger.info("Clothing Added")
          return true
          }
        else{
            logger.info("Clothing not Added, Are all the details correct?")
              return false
        }
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
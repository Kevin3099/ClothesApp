package org.wit.ClothesApp.console.views

import org.wit.ClothesApp.console.controllers.ClothingUIController
import org.wit.ClothesApp.console.models.ClothingModel
import tornadofx.*

class ListClothingScreen : View("List Clothing") {

    val clothingUIController: ClothingUIController by inject()
    val tableContent = clothingUIController.clothing.findAll()
    val data = tableContent.observable()


    override val root = vbox {
        setPrefSize(600.0, 200.0)
        tableview(data) {
            readonlyColumn("ID", ClothingModel::id)
            readonlyColumn("TITLE", ClothingModel::title)
            readonlyColumn("DESCRIPTION", ClothingModel::description)
        }
        button("Close") {
            useMaxWidth = true
            action {
                runAsyncWithProgress {
                    clothingUIController.closeList()
                }
            }
        }
    }

}
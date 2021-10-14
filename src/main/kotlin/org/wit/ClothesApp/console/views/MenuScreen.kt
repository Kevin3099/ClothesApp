package org.wit.ClothesApp.console.views

import javafx.application.Platform
import javafx.geometry.Orientation
import org.wit.ClothesApp.console.controllers.ClothingUIController
import tornadofx.*

class MenuScreen : View("Clothing Main Menu") {

    val clothingUIController: ClothingUIController by inject()

    override val root = form {
        setPrefSize(600.0, 400.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            text("")
            button("Add Clothing") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        clothingUIController.loadAddScreen()
                    }
                }
            }
            button("Update Clothing") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        clothingUIController.loadAddScreen()
                    }
                }
            }
            button("Delete Clothing") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        clothingUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("List Clothing") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        clothingUIController.loadListScreen()
                    }
                }
            }
            button("Search/Filter Clothing") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        clothingUIController.loadAddScreen()
                    }
                }
            }
            text("")
            button("Exit") {

                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        Platform.exit();
                        System.exit(0);
                    }
                }
            }
        }

    }


}
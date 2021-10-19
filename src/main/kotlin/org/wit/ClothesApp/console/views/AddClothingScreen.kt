package org.wit.ClothesApp.console.views

import javafx.beans.property.SimpleDoubleProperty
import javafx.beans.property.SimpleStringProperty
import javafx.beans.value.ObservableValue
import javafx.geometry.Orientation
import javafx.scene.paint.Color
import org.wit.ClothesApp.console.controllers.ClothingUIController
import tornadofx.*
import java.util.*
import kotlin.reflect.KClass

class AddClothingScreen : View("Add Clothing") {
    val model = ViewModel()
    val _title = model.bind { SimpleStringProperty() }
    val _description = model.bind { SimpleStringProperty() }
    val _clothingType = model.bind { SimpleStringProperty() }
    val _image = model.bind { SimpleStringProperty() }
    val _price = model.bind { SimpleDoubleProperty() }
    val clothingUIController: ClothingUIController by inject()
    var logging = true


    override val root = form {
        setPrefSize(600.0, 200.0)
        fieldset(labelPosition = Orientation.VERTICAL) {
            field("Title") {
                textfield(_title).required()
            }
            field("Clothing Type") {
                textfield(_clothingType).required()
            }
            field("Price") {
                textfield(_price).required()
            }
            field("Image URL") {
                textfield(_image).required()
            }
            field("Description") {
                textarea(_description).required()
            }

            button("Add") {
                enableWhen(model.valid)
                isDefaultButton = true
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                            logging = clothingUIController.add(_title.toString(),_description.toString(), _clothingType.toString(), _price.getValue().toDouble(), _image.toString())
                        logging
                    }
                }
            }
            button("Close") {
                useMaxWidth = true
                action {
                    runAsyncWithProgress {
                        clothingUIController.closeAdd()
                    }
                }
            }
        }
    }
    override fun onDock() {
        _title.value = ""
        _description.value = ""
        model.clearDecorators()
    }
}
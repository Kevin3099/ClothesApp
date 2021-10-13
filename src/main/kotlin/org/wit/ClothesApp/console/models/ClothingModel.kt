package org.wit.ClothesApp.console.models

data class ClothingModel(var id: Long = 0,
                          var clothingType: String = "",
                          var title: String = "",
                          var price: Double = 0.00,
                          var description: String = "",
                          var image: String = "url")
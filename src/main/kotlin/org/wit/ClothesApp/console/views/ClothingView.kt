package org.wit.ClothesApp.console.views

import org.wit.ClothesApp.console.models.ClothingJSONStore
import org.wit.ClothesApp.console.models.ClothingModel

class ClothingView {

    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Clothing")
        println(" 2. Update Clothing")
        println(" 3. List All Clothing")
        println(" 4. Search Clothing")
        println(" 5. Delete Clothing")
        println("-1. Exit")
        println()
        print("Enter Option : ")
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    fun listClothing(clothings : ClothingJSONStore) {
        println("List All Clothing")
        println()
        clothings.logAll()
        println()
    }

    fun showClothing(clothing : ClothingModel) {
        if(clothing != null)
            println("Clothing Details [ $clothing ]")
        else
            println("Clothing Not Found...")
    }

    fun addClothingData(clothing : ClothingModel) : Boolean {

        println()
        print("Enter a Title : ")
        clothing.title = readLine()!!
        print("Enter a Description : ")
        clothing.description = readLine()!!

        return clothing.title.isNotEmpty() && clothing.description.isNotEmpty()
    }

    fun updateClothingData(clothing : ClothingModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (clothing != null) {
            print("Enter a new Title for [ " + clothing.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + clothing.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                clothing.title = tempTitle
                clothing.description = tempDescription
                return true
            }
        }
        return false
    }

    fun getId() : Long {
        var strId : String? // String to hold user input
        var searchId : Long // Long to hold converted id
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9

        return searchId
    }
}
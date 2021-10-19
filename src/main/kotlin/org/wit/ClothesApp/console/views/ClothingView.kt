package org.wit.ClothesApp.console.views

import jdk.nashorn.internal.objects.NativeArray.forEach
import org.wit.ClothesApp.console.models.ClothingJSONStore
import org.wit.ClothesApp.console.models.ClothingModel
import java.util.*

class ClothingView {
    val myScanner = Scanner(System.`in`)
    fun menu() : Int {

        var option : Int
        var input: String?

        println("MAIN MENU")
        println(" 1. Add Clothing")
        println(" 2. Update Clothing")
        println(" 3. List All Clothing")
        println("4. list All Clothing By Title")
        println("5. List All Clothing of Specific Type (Filtered by Type)")
        println("6. List All Clothing at a Specific Price (Filtered by Price")
        println(" 7. Search Clothing")
        println(" 8. Delete Clothing")
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

    fun listAllClothing(clothing : ClothingJSONStore) {
        println("List All Clothing")
        println()
        clothing.logAll()
        println()
    }

    fun listByTitleClothing(clothing : ClothingJSONStore) {
        var title: String?
        print("Enter title to find all clothing with that title : ")
        title = readLine()!!
        println(clothing.findByTitle(title))
    }

    fun filteredByTypeClothing(clothing : ClothingJSONStore){
        var type: String?
        print("Enter Clothing Type : ")
        type = readLine()!!
        println(clothing.filterByType(type))
    }
    fun filteredByPriceClothing(clothing : ClothingJSONStore){
        var price: Double?
        print("Enter Clothing Price : ")
        price = myScanner.nextDouble();
        println(clothing.filterByPrice(price))
    }

    fun showClothing(clothing : ClothingModel) {
        if(clothing != null)
            println("Clothing Details [ $clothing ]")
        else
            println("Clothing Not Found...")
    }



    fun addClothingData(clothing : ClothingModel) : Boolean {


        println("Add Clothing")
        println()
        print("Enter a Title : ")
        clothing.title = readLine()!!

        print("Enter a Description : ")
        clothing.description = readLine()!!

        print("Enter a Price (eg. 20.99) : ")
        clothing.price = myScanner.nextDouble()

        print("The Types of Clothing are: Shoes, Shirt, Pants, Hat, Hoodie. Please Enter a Type Of Clothing : ")
        clothing.clothingType = readLine()!!

        print("Please Link an Image URL : ")
        clothing.image = readLine()!!

        if ((clothing.title.isNotEmpty() && clothing.title.length < 250) && (clothing.description.isNotEmpty() && clothing.description.length <= 400) && (clothing.price != 0.00)
            && (clothing.clothingType.toUpperCase().equals("SHOES") || clothing.clothingType.toUpperCase().equals("PANTS") || clothing.clothingType.toUpperCase().equals("HAT")
                    || clothing.clothingType.toUpperCase().equals("HOODIE")
                    ||clothing.clothingType.toUpperCase().equals("SHIRT")) && (clothing.image.contains("https://"))) {

            return true
        }
        return false
    }

    fun updateClothingData(clothing : ClothingModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?
        var tempType: String?
        var tempPrice: Double?
        var tempImageUrl: String?


        if (clothing != null) {
            print("Enter a new Title for [ " + clothing.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + clothing.description + " ] : ")
            tempDescription = readLine()!!
            print("Enter a new Type of Clothing for [ " + clothing.clothingType + " ] : ")
            tempType = readLine()!!
            print("Enter a new Price for [ " + clothing.price + " ] : ")
            tempPrice = myScanner.nextDouble()
            print("Enter a new Image URL for [ " + clothing.image + " ] : ")
            tempImageUrl = readLine()!!


            if ((clothing.title.isNotEmpty() && clothing.title.length < 250) && (clothing.description.isNotEmpty() && clothing.description.length <= 400) && (clothing.price != 0.00)
                && (clothing.clothingType.toUpperCase().equals("SHOES") || clothing.clothingType.toUpperCase().equals("PANTS") || clothing.clothingType.toUpperCase().equals("HAT")
                        || clothing.clothingType.toUpperCase().equals("HOODIE")
                        ||clothing.clothingType.toUpperCase().equals("SHIRT")) && (clothing.image.contains("https://"))) {

                clothing.title = tempTitle
                clothing.description = tempDescription
                clothing.clothingType = tempType
                clothing.price = tempPrice
                clothing.image = tempImageUrl
                return true
            }
        }
        return false
    }

    fun getId(): Long {
        var strId: String? // String to hold user input
        print("Enter id to Search/Update/Delete : ")
        strId = readLine()!!

        return if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
    }
}
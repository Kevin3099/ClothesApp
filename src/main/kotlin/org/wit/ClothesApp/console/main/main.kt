// TODO validation on add/update
// ID changes on Deletion fix other id's
// Make the filter methods
// make search by title method
// Import Tornado

package org.wit.placemark.console.main

import mu.KotlinLogging
import org.wit.placemark.console.models.ClothingModel
import java.util.*

private val logger = KotlinLogging.logger {}

val clothingList = ArrayList<ClothingModel>()
val myScanner = Scanner(System.`in`)

fun main(args: Array<String>) {
    logger.info { "Launching Clothes Store Management Console App" }

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addClothing()
            2 -> updateClothing()
            3 -> listAllClothing()
            4 -> deleteClothing()
            5 -> filterClothingByType()
            6 -> filterClothingByPrice()
            7 -> searchClothingById()
            8 -> searchClothingByTitle()
            -99 -> dummyData()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}

fun menu() : Int {

    var option : Int
    var input: String?

    println("MAIN MENU")
    println(" 1. Add Clothing")
    println(" 2. Update Clothing")
    println(" 3. List All Clothing")
    println(" 4. Delete a Piece of Clothing")
    println(" 5. ListFilteredByTypeClothing")
    println(" 6. ListFilteredByPriceClothing")
    println(" 7. Search Clothing By Id")
    println(" 8. Search Clothing by Title")
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

fun addClothing(){
    var Clothing = ClothingModel()
    println("Add Clothing")
    println()
    //TODO ADD VALIDATION
    print("Enter a Title : ")
    Clothing.title = readLine()!!
    print("Enter a Description : ")
    Clothing.description = readLine()!!
    print("Enter a Price (eg. 20.99) : ")
    Clothing.price = myScanner.nextDouble()
    print("Enter a Type Of Clothing : ")
    Clothing.clothingType = readLine()!!
    print("Please Link an Image URL : ")
    Clothing.image = readLine()!!

    if (Clothing.title.isNotEmpty() && Clothing.description.isNotEmpty()) {
        Clothing.id = clothingList.size.toLong()
        clothingList.add(Clothing.copy())
        logger.info("Clothing Added : [ $Clothing ]")
    }
    else
        logger.info("Clothing Not Added some fields were entered incorrectly")
}

fun updateClothing() {
    println("Update Clothing")
    println()
    listAllClothing()
    var searchId = getId()
    val Clothing = searchById(searchId)

    if(Clothing != null) {
        print("Enter a new Title for [ " + Clothing.title + " ] : ")
        if(readLine() != ""){
            Clothing.title = readLine()!!}
        else {
            print("No new information added title is: ${Clothing.title}")
        }
        print("Enter a new Description for [ " + Clothing.description + " ] : ")
        if(readLine() !=""){
            Clothing.description = readLine()!!
        }
        else{
            print("No new information added description is: ${Clothing.description}")
        }
        print("Enter a new Type of Clothing for [ " + Clothing.clothingType + " ] : ")
        if(readLine() !=""){
            Clothing.clothingType = readLine()!!
        }
        else{
            print("No new information added Clothing Type is: ${Clothing.clothingType}")
        }
        print("Enter a new Price for [ " + Clothing.price + " ] : ")
        if(readLine() !=""){
            Clothing.price = myScanner.nextDouble()
        }
        else{
            print("No new Price detected the price is: ${Clothing.price}")
        }
        print("Enter a new Image URL for [ " + Clothing.image + " ] : ")
        if(readLine() !=""){
            Clothing.image = readLine()!!
        }
        else{
            print("No new Image URL detected the URL is: ${Clothing.image}")
        }
        println(
            "You updated [ " + Clothing.title + " ] for title " +
                    "and [ " + Clothing.description + " ] for description" +
                    "and [ $" + Clothing.price + " ] for price" +
                    "and [ " + Clothing.clothingType + " ] for type" +
                    "and [ " + Clothing.image + " ] for image"
        )
    }
    else
        println("Clothing Not Updated...")
}

fun listAllClothing() {
    println("List All Clothing Items")
    println(clothingList)
    clothingList.forEach { logger.info("${it}") }
    println()
}
fun deleteClothing(){
    var id = -1
    println("Delete a Clothing Item")
    listAllClothing()
    println("Please type the ID of the clothing you want to delete: ")
    id = myScanner.nextInt()!!
    clothingList.removeAt(id)

    //Changing ID Numbers
    for( clothing in clothingList){
        if(clothing.id > id){
            clothing.id = clothing.id -1
        }
    }
}

fun searchClothingById() {

    var searchId = getId()
    val Clothing = searchById(searchId)

    if(Clothing != null)
        println("Clothing Details [ $Clothing ]")
    else
        println("Clothing Not Found...")
}
fun searchClothingByTitle(){

}
fun filterClothingByType(){

}
fun filterClothingByPrice(){

}
fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun searchById(id: Long) : ClothingModel? {
    var foundPlacemark: ClothingModel? = clothingList.find { p -> p.id == id }
    return foundPlacemark
}

fun dummyData() {
    clothingList.add(ClothingModel(1, "New York New York", "So Good They Named It Twice"))
    clothingList.add(ClothingModel(2, "Ring of Kerry", "Some place in the Kingdom"))
    clothingList.add(ClothingModel(3, "Waterford City", "You get great Blaas Here!!"))
}
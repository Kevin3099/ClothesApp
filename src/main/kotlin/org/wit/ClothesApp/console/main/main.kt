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
//            2 -> updateClothing()
//            3 -> listAllClothing()
//            4 -> ListFilteredByTypeClothing()
//            5 -> ListFilteredByPriceClothing()
//            6 -> searchById()
//            7 -> searchByTitle()
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
    println(" 4. ListFilteredByTypeClothing")
    println(" 5. ListFilteredByPriceClothing")
    println(" 4. Search Clothing By Id")
    println(" 4. Search Clothing by Title")
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
    println("Update Placemark")
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
        println(
            "You updated [ " + Clothing.title + " ] for title " +
                    "and [ " + Clothing.description + " ] for description"
        )
    }
    else
        println("Placemark Not Updated...")
}

fun listAllClothing() {
    println("List All Placemarks")
    println()
    clothingList.forEach { logger.info("${it}") }
    println()
}

fun searchClothingById() {

    var searchId = getId()
    val aPlacemark = searchById(searchId)

    if(aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("Placemark Not Found...")
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
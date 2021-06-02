package com.example.bangkitcapstoneprojects.model

class Donate {

    var id: String? = null
    var idEvent: String? = null
    var emailUser: String? = null
    var targetFood: String? = null
    var targetMoney: String? = null
//    var donateFood: String? = null
//    var imageFood: String? = null
    var donateMoney: String? = null
    var imageMoney: String? = null

    constructor() {

    }

    constructor(id: String?, idEvent: String?, emailUser: String?, targetFood: String?, targetMoney: String?, donateMoney: String?, imageMoney: String?) {
        this.id = id
        this.idEvent = idEvent
        this.emailUser = emailUser
        this.targetFood = targetFood
        this.targetMoney = targetMoney
//        this.donateFood = donateFood
//        this.imageFood = imageFood
        this.donateMoney = donateMoney
        this.imageMoney = imageMoney
    }
}
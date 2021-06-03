package com.example.bangkitcapstoneprojects.model

class DonateFood {

    var idEvent: String? = null
    var emailUser: String? = null
    var currentDate: String? = null
    var donateFood: String? = null
    var imageFood: String? = null

    constructor() {

    }

    constructor(idEvent: String?, emailUser: String?, currentDate: String?, donateFood: String?, imageFood: String?) {
        this.idEvent = idEvent
        this.emailUser = emailUser
        this.currentDate = currentDate
        this.donateFood = donateFood
        this.imageFood = imageFood
    }
}
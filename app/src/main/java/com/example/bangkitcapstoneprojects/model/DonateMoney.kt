package com.example.bangkitcapstoneprojects.model

class DonateMoney {

    var id: String? = null
    var idEvent: String? = null
    var emailUser: String? = null
    var donateMoney: String? = null
    var imageMoney: String? = null

    constructor() {

    }

    constructor(id: String?, idEvent: String?, emailUser: String?, donateMoney: String?, imageMoney: String?) {
        this.id = id
        this.idEvent = idEvent
        this.emailUser = emailUser
        this.donateMoney = donateMoney
        this.imageMoney = imageMoney
    }
}
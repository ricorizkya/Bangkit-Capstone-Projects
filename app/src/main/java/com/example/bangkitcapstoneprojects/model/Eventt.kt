package com.example.bangkitcapstoneprojects.model


class Eventt {
        var id: String? = null
        var username: String? = null
        var email: String? = null
        var title: String? = null
        var desc: String? = null
        var food: String? = null
        var money: String? = null
        var people: String? = null
        var date: String? = null
        var image: String? = null

    constructor() {
        
    }

    constructor(id: String?, username: String?, email: String?, title: String?, desc: String?, food: String?, money: String?, people: String?, date: String?, image: String?) {
        this.id = id
        this.username = username
        this.email = email
        this.title = title
        this.desc = desc
        this.food = food
        this.money = money
        this.people = people
        this.date = date
        this.image = image
    }
}
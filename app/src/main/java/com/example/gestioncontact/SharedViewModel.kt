package com.example.gestioncontact

import androidx.lifecycle.ViewModel

class SharedViewModel : ViewModel() {
    val contacts = mutableListOf(
        Contact("Ben Ali", "Ahmed", "+216 20 123 456", "ahmed.benali@example.com", latitude = 36.8065, longitude = 10.1815),
        Contact("Trabelsi", "Sarra", "+216 98 765 432", "sarra.trabelsi@example.com", latitude = 36.8189, longitude = 10.1658),
        Contact("Gharbi", "Mohamed", "+216 55 111 222", "mohamed.gharbi@example.com", latitude = 36.7992, longitude = 10.1797),
        Contact("Jaziri", "Ines", "+216 22 333 444", "ines.jaziri@example.com", latitude = 36.8450, longitude = 10.1950),
        Contact("Mansouri", "Youssef", "+216 50 555 666", "youssef.mansouri@example.com", latitude = 36.7530, longitude = 10.2188),
        Contact("Chaabane", "Amira", "+216 99 888 777", "amira.chaabane@example.com"),
        Contact("Bouazizi", "Karim", "+216 24 000 111", "karim.bouazizi@example.com"),
        Contact("Hammami", "Nour", "+216 29 444 333", "nour.hammami@example.com")
    )

    var selectedIndex: Int = -1
}

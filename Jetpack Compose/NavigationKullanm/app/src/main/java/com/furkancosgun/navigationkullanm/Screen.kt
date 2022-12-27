package com.furkancosgun.navigationkullanm

//Bu class yardımıyla screen isimlerini ve parametrelerini kolayca yonetebiliriz
sealed class Screen(val route:String){
    object MainScreen : Screen("main")
    //Screen isimlerin erişim /* Screen.MainScreen.route */ şeklinde olur
    object DetailScreen : Screen("detail")

    fun withArgs(vararg args:String):String{//Diger sayfaya parametre ile gitmek için bu fonksionu kullanırız
        return buildString {
            append(route)
            args.forEach {
                append("/$it")
            }
        }
    }
}

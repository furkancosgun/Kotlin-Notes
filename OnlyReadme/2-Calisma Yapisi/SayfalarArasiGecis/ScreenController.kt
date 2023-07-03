package com.example.calismayapisi.SayfalarArasiGecis

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.calismayapisi.Model.User
import com.google.gson.Gson


@Composable
fun ScreenController() {//Sayfalar Arası Geçiş Yapmak için ana Yoneticimiz
    /*
        implementation("androidx.navigation:navigation-compose:2.6.0-alpha09")
        Sayfalar Arası Geçiş Yapmak İçin YUkarıdaki Implementasyon App gradle altına eklenir
     */

    val navController = rememberNavController()//Sayfalar Arası Geçiş Yapmak İçin nesne

    //Sayfa Yonetimini saglaması için nesneyi veririz başlangıc ekranımızı belirleriz
    NavHost(navController = navController, startDestination = "FirstScreen") {

        //Verilen Id Ye Gore Ekranları Tek Tek Oluştruruz Ve o Id Yardimiyla Geçiş Saglarız
        composable("FirstScreen") {
            FirstScreen(navController = navController)
        }
        composable("SecondScreen") {
            SecondScreen(navController = navController)
        }
        composable("ThirdScreen") {
            ThirdScreen(navController = navController)
        }

        //Veri Transferi Yapmak İçin Pathleeme Yontemini kullancagız
        //userId Adıdna Bir Parametre alcak ve bunun tipi int olcak diye belirttik
        composable("LastScreen/{userId}", arguments = listOf(
            navArgument("userId") {
                type = NavType.IntType
            }
        )) {
            //gelen parametreyi de direkt olacak ekrana gonderecegiz
            val userId = it.arguments?.getInt("userId")
            ScreenWithParam(navController = navController, userId = userId!!)
        }

        //Model Gonderimi
        composable("ScreenWithModel/{user}", arguments = listOf(
            navArgument("user") {
                type = NavType.StringType
            }
        )) {
            val strUser = it.arguments!!.getString("user")

            //Gson Kutuphanesi İle String Gelen Json Verisini Obje Olarak Donuştruyoruz
            val user = Gson().fromJson(strUser, User::class.java)
            ScreenWithModel(navController = navController, user = user)
        }
    }
/*
    //Geriye Doner
    navController.popBackStack()

    //Verilen Id ye Gore Sayfaya geçiş yapar
    navController.navigate("FirstScreen")

    //Sayfaya Geçiş Yaparken Backstackten Silinme
    //2.sayfaya gecerken 1 sayfayı backstackten sileriz
    navController.navigate("SecondScreen") {
        popUpTo("FirstScreen") { inclusive = true }
    }
 */
}
package com.furkancosgun.retrowithlocal.Utilites

import com.furkancosgun.retrowithlocal.Client.RetrofitClient
import com.furkancosgun.retrowithlocal.DAO.UserDAOInterface

class APIUtil {

        companion object {
            //ifconfig adress
            val baseUrl = "http://192.168.1.88:9000/api/"

            fun getUserDAOInterface(): UserDAOInterface {
                return RetrofitClient.getClient(baseUrl).create(UserDAOInterface::class.java)
            }
        }

}
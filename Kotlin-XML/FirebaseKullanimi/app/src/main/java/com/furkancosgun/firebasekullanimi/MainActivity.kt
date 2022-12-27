package com.furkancosgun.firebasekullanimi

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.furkancosgun.firebasekullanimi.Model.User
import com.furkancosgun.firebasekullanimi.databinding.ActivityMainBinding
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {
    private val TAG = "XFC"
    private lateinit var ref: DatabaseReference
    private lateinit var design: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = ActivityMainBinding.inflate(layoutInflater)
        setContentView(design.root)
        val db = FirebaseDatabase.getInstance()
        ref = db.getReference("users")


        design.btnSave.setOnClickListener {
            saveUser(
                User(
                    name = design.txtName.text.toString(),
                    surname = design.txtSurname.text.toString(),
                    note = design.txtNote.text.toString().toInt()
                )
            )
        }

        val users = getAllUsers()
    }

    fun saveUser(user: User) {
        ref.push().setValue(user)//Kaydetme


    }

    fun updateUser(user: User) {
        ref.child(user.id).updateChildren(userModelToHashMap(user))
    }

    fun deleteUser(user: User) {
        ref.child(user.id).removeValue()
    }

    //Model To HashMap
    fun userModelToHashMap(user: User): HashMap<String, Any> {
        val hashMap = HashMap<String, Any>()
        user.name?.let { hashMap["name"] = it }
        user.surname?.let { hashMap["surname"] = it }
        user.name?.let { hashMap["note"] = it }
        return hashMap
    }

    fun getAllUsers(): List<User> {
        val userList = mutableListOf<User>()//Boş Liste

        //Verilerin uzerinde doner
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    //Her bir veri user objesine donuştrulur
                    val user = snap.getValue(User::class.java)
                    user?.let {
                        val key = snap.key
                        user.id = key.toString()//Key alan değişimi sagladım
                        Log.d(TAG, user.toString())
                        userList.add(user)
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, error.message.toString())
            }
        })
        Log.d(TAG, userList.toString())
        return userList//Liste Geriye doner
    }

    fun getUsersByName(name: String) {
        val query = ref.orderByChild("name").equalTo(name)
        query.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (snap in snapshot.children) {
                    //Her bir veri user objesine donuştrulur
                    val user = snap.getValue(User::class.java)
                    user?.let {
                        val key = snap.key
                        user.id = key.toString()//Key alan değişimi sagladım
                        Log.d(TAG, user.toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e(TAG, error.message.toString())
            }
        })
    }
}
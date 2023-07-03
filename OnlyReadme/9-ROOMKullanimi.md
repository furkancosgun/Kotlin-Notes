# Installation
```groovy    

apply plugin: 'kotlin-kapt'
    var room_version = "2.5.2"
    implementation "androidx.room:room-runtime:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
``` 

# SQLITE Veritabanı Oluşturma
```bash
cd Desktop/ && sqlite3 users.sqlite
```
```sql
CREATE TABLE "users" 
(
    "id" INTEGER NOT NULL ,
    "name" TEXT NOT NULL,
    "telephone" TEXT NOT NULL,
    PRIMARY KEY("id" AUTOINCREMENT)
);
go

insert into users ("name","telephone") values ("furkan","+90 543 213 45 67")
go
```
```SQL
SELECT 'OLUSTURULAN SQLITE DOSYASI ANDROID STUDIO IÇINDEKI ASSETS DOSYASINA ATILIR'
```

# Entity Oluşturmak
```kotlin
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "users")
data class UserEntitiy(
    @PrimaryKey(autoGenerate = true) 
    @ColumnInfo(name = "id") @NotNull val id: Int,
    @ColumnInfo(name = "name") @NotNull val name: String,
    @ColumnInfo(name = "telephone") @NotNull val telephone: String
){
    override fun toString(): String {
        return "ID:${this.id},NAME:${this.name},TELEPHONE:${this.telephone}"
    }
}

/*
    Entitiy:Tablo İsmi
    AutoGenerate:Otomatik Artan Sayı
    ColumnInfo:Tablodaki Kolonun adı vermek zorunda değiliz aynı ise
    NotNull:Boş Olamaz
*/

```
# DAO (Database Access Object) Oluşturma
```kotlin
/*
AŞAĞıDAKI INTERFACE ILE VERITABANı IŞLEMLERI GERÇEKLEŞTIRILIR
*/
package com.example.roomkullanimi

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface UsersDAO {
    @Query("SELECT * FROM USERS")
    suspend fun getAllUsers(): List<User>

    @Insert
    suspend fun addUser(user: User)

    @Update
    suspend fun updateUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Query("SELECT COUNT(*) FROM USERS")
    suspend fun getUserCount(): Int

    @Query("SELECT * FROM USERS WHERE ID=:userId")
    suspend fun getUserById(userId: Int)

    @Query("SELECT * FROM USERS WHERE NAME LIKE '%' || :search_text || '%'")
    suspend fun getUsersByName(search_text: String): List<User>

    @Query("SELECT * FROM USERS ORDER BY RANDOM() LIMIT 1")
    suspend fun getUserByRandom(): List<User>

    //Raw
    @RawQuery
    suspend fun getCustomQuery(query: SupportSQLiteQuery): List<String>
}

```

# VERITABANI ERIŞIM SINIFI OLUSTURMA
```kotlin

package com.example.roomkullanimi

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1)
abstract class UserDatabase : RoomDatabase() {
    abstract fun getUsersDAO(): UsersDAO

    companion object {
        //NULL BIR VERITABANı OBJESI TANıMLANıR
        var DB: UserDatabase? = null
        //AŞAGıDAKI FONKSIıON ILE VERITABANı ERIŞIMI SAGLANıR 
        fun getDatabase(context: Context): UserDatabase? {
            if (DB == null) {//EGER VERITABANı OBJESI BOŞ ISE
             //SENKRONIZE BI ŞEKILDE BUILD EDILIP OLUŞTRULUR
                synchronized(UserDatabase::class.java) {
                    DB = Room.databaseBuilder(
                        context = context.applicationContext,
                        UserDatabase::class.java,
                        "users.sqlite"//ASSETS ALTıNDAKI ISMI ILE AYNı OLMALıDıR
                    ).createFromAsset("users.sqlite").build()
                }
            }
            return DB //SONRASıNDA RETURN EDILIR
        }
    }
}
```

# CRUD IŞLEMLERI YAPMAK
### REPOSITORY KULLANMAK
```kotlin
//UserRepository.kt
package com.example.roomkullanimi

import android.content.Context

class UserRepository(context: Context) {
    private var db: UsersDAO

    init {
        db = UserDatabase.getDatabase(context)!!.getUsersDAO()
    }

    suspend fun getAllUsers(): List<UserEntity> {
        return db.getAllUsers()
    }

    suspend fun getUserById(id: Int): UserEntity {
        return db.getUserById(id)
    }

    suspend fun getUserByName(name: String): List<UserEntity> {
        return db.getUsersByName(name)
    }

    suspend fun addUser(user: UserEntity) {
        db.addUser(user)
    }

    suspend fun updateUser(user: UserEntity) {
        db.updateUser(user)
    }

    suspend fun deleteUser(user: UserEntity) {
        db.deleteUser(user)
    }
    
    //Custom Q
    suspend fun getCustomQuery(query: SupportSQLiteQuery): List<String> {
        return db.getCustomQuery(query)
    }
}
```
### Repository Sınıfı Yardımıyla CRUD Işlemlerini Gerçekleştirmek
```kotlin
//MainView.kt
@Composable
@Preview(showSystemUi = true)
fun MainView() {
    val context = LocalContext.current//Context
    val userRepo = UserRepository(context)//Repo

    //Launched Effect İlk Açılışta Çalışan Fonksiyonumuz

    //Create
    LaunchedEffect(key1 = true) {
        userRepo.addUser(UserEntity(0, "AHMET", "+90 567 890 12 34"))
        for (user in userRepo.getAllUsers()) {
            Log.d(TAG, "MainView: $user")
        }
    }

    //Read
    LaunchedEffect(key1 = true) {
        for (user in userRepo.getAllUsers()) {
            Log.d(TAG, "MainView: $user")
            //OUT:MainView: ID:1,NAME:HASAN,TELEPHONE:+90 567 890 12 34
        }
    }

    //Update
    LaunchedEffect(key1 = true) {
        userRepo.updateUser(UserEntity(1, "HASAN", "+90 567 890 12 34"))
        for (user in userRepo.getAllUsers()) {
            Log.d(TAG, "MainView: $user")
        }
    }


    //Delete
    LaunchedEffect(key1 = true) { 
        userRepo.deleteUser(UserEntity(1, "HASAN", "+90 567 890 12 34"))
        for (user in userRepo.getAllUsers()) { 
            Log.d(TAG, "MainView: $user")
        }
    }

    //And Custom Query
    LaunchedEffect(key1 = true) {
        val results =
            userRepo.getCustomQuery(
                SimpleSQLiteQuery("select name from sqlite_master where type = 'table'")
                                   )

        results.forEach {
            Log.d(TAG, "MainView: $it")
            /*
            MainView: users
            MainView: sqlite_sequence
            MainView: android_metadata
            MainView: room_master_table
            */
        }
    }

}
```
# Room Kullanımı Baştan Sona (MVVM,LIVEDATA IÇERIR)

- # Implemetation

  ```gradle
  //build.gradle(:app)
  apply plugin: 'kotlin-kapt'

  //MVVM
  implementation("androidx.navigation:navigation-compose:2.6.0")

  //Observer
  implementation "androidx.compose.runtime:runtime-livedata:1.6.0-alpha01"

  //Room
  var room_version = "2.5.2"
  implementation "androidx.room:room-runtime:$room_version"
  implementation "androidx.room:room-ktx:$room_version"
  kapt "androidx.room:room-compiler:$room_version"
  ```

- # Entity Sınıfı Oluşturmak

  ```kotlin
  //Entities/TodoEntity.kt
  /*
  create table "todos" ("id" INTEGER NOT NULL,"title" TEXT NOT NULL, PRIMARY KEY("id" AUTOINCREMENT))
  */
  @Entity(tableName = "todos")
  class TodoEntity(
      @PrimaryKey(autoGenerate = true) @ColumnInfo("id") val id: Int,
      @ColumnInfo("title") val title: String,
  )
  ```

- # Database Object Access(DAO) Arayüzü Oluşturmak

  ### Veritabanı Ile İlgili İşlemler Bu Arayüz Yardımıyla Yapılır

  ```kotlin
  //DAO/TodoDAO.kt

  @Dao
  interface TodoDAO {

      @Insert
      suspend fun insertTodo(todoEntity: TodoEntity)

      @Update
      suspend fun updateTodo(todoEntity: TodoEntity)

      @Delete
      suspend fun deleteTodo(todoEntity: TodoEntity)

      @Query("SELECT * FROM TODOS")
      suspend fun getAllTodos(): List<TodoEntity>

  }
  ```

- # Veritabanı Erişim Sınıfı Oluşturmak

  ### Veritabanına Erişimini Bu Sınıf Sayesinde Yaparız

  ```kotlin
  //Database/TodoDatabase.kt

  @Database(entities = [TodoEntity::class], version = 2)
  abstract class TodoDatabase : RoomDatabase() {
      abstract fun getTodoDAO(): TodoDAO

      companion object {
          private var INSTANCE: TodoDatabase? = null
          private const val DB_NAME = "todos.sqlite"

          fun getTodoDatabase(context: Context): TodoDatabase? {
              if (INSTANCE == null) {
                  synchronized(TodoDatabase::class.java) {
                      INSTANCE =
                          Room.databaseBuilder(
                              context.applicationContext,
                              TodoDatabase::class.java,
                              DB_NAME
                          )
                              .createFromAsset(DB_NAME).build()
                  }
              }
              return INSTANCE
          }
      }
  }
  ```

- # Veritabanı İşlemlerini Yönetmek İçin Repository Sınıfı Oluşturmak

  ### Her İşlemden Sonra Arayüzü Güncellemek İçin Veritabanı Yardımıyla Tekrardan Verileri Alırız

  ```kotlin
  //Repositories/TodoRepository.kt

  class TodoRepository(application: Application) {
      private var todoList = MutableLiveData<List<TodoEntity>>()
      private var database: TodoDAO

      init {
          todoList = MutableLiveData()
          database = TodoDatabase.getTodoDatabase(application.applicationContext)!!.getTodoDAO()
      }

      fun getLiveTodos(): MutableLiveData<List<TodoEntity>> {
          return todoList
      }

      fun getAllTodos() {
          CoroutineScope(Dispatchers.Main).launch {
              todoList.value = database.getAllTodos()
          }
      }

      fun updateTodo(todoEntity: TodoEntity) {
          CoroutineScope(Dispatchers.IO).launch {
              database.updateTodo(todoEntity)
          }
          getAllTodos()
      }

      fun createTodo(todoEntity: TodoEntity) {
          CoroutineScope(Dispatchers.IO).launch {
              database.insertTodo(todoEntity)
          }
          getAllTodos()
      }

      fun deleteTodo(todoEntity: TodoEntity) {
          CoroutineScope(Dispatchers.IO).launch {
              database.deleteTodo(todoEntity)
          }
          getAllTodos()
      }
  }
  ```

- # Kullanıcı Arayüzü İçin ViewModel Oluşturmak

  ```kotlin
  //ViewModels/MainViewModel.kt

  class MainViewModel(application: Application) : AndroidViewModel(application) {
      var todoList = MutableLiveData<List<TodoEntity>>()
      private var todoRepository: TodoRepository

      init {
          todoRepository = TodoRepository(application)
          todoList = todoRepository.getLiveTodos()
      }

      fun getAllTodos() {
          todoRepository.getAllTodos()
      }

      fun createTodo(title: String) {
          todoRepository.createTodo(TodoEntity(0, title))
      }

      fun updateTodo(id: Int, title: String) {
          todoRepository.updateTodo(TodoEntity(id, title))
      }

      fun deleteTodo(todoEntity: TodoEntity) {
          todoRepository.deleteTodo(todoEntity)
      }
  }
  ```

- # ViewModel Sınıfları İçin Factory Sınıfı Yazmak

  ### ViewModel Sınıflarımızda Veritabanı Erişimi Yapcagımız İçin (context) İhtiyacımız Vardır Bu Nedenle Factory Sınıflarını Da Oluşturmamız Gereklidir

  ```kotlin
  //Factories/MainViewModelFactory.kt
  class MainViewModelFactory(var application: Application) :
      ViewModelProvider.AndroidViewModelFactory(application) {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
          return MainViewModel(application) as T
      }
  }
  ```

- # Son Olarak View Oluşturalim

  ```kotlin
  //Views/MainView.kt
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  @Preview(showSystemUi = true)
  fun MainView() {
      var tfId by remember {
          mutableStateOf("")
      }
      var tfTitle by remember {
          mutableStateOf("")
      }
      val context = LocalContext.current
      val viewModel: MainViewModel =
          viewModel(factory = MainViewModelFactory(context.applicationContext as Application))
      LaunchedEffect(key1 = true) {
          viewModel.getAllTodos()
      }
      val snackbarHostState = remember {
          SnackbarHostState()
      }
      val scope = rememberCoroutineScope()
      val todoList = viewModel.todoList.observeAsState()


      Scaffold(topBar = {
          TopAppBar(title = {
              Text(text = "Home", fontWeight = FontWeight.Bold)
          })
      }, snackbarHost = {
          SnackbarHost(hostState = snackbarHostState)
      }) {

          Column(
              Modifier
                  .fillMaxSize()
                  .padding(it)
          ) {
              Box(
                  modifier = Modifier
                      .heightIn(max = (LocalConfiguration.current.screenHeightDp / 3.5).dp)
                      .fillMaxSize()
              ) {
                  Column(
                      modifier = Modifier.padding(16.dp),
                      verticalArrangement = Arrangement.spacedBy(8.dp)
                  ) {
                      TextField(
                          modifier = Modifier.fillMaxWidth(),
                          value = tfId,
                          onValueChange = { text ->
                              tfId = text
                          }, label = {
                              Text(text = "Id")
                          })
                      TextField(
                          modifier = Modifier.fillMaxWidth(),
                          value = tfTitle,
                          onValueChange = { text ->
                              tfTitle = text
                          }, label = {
                              Text(text = "Title")
                          })
                      Row(
                          modifier = Modifier.fillMaxWidth(),
                          horizontalArrangement = Arrangement.SpaceAround
                      ) {
                          Button(onClick = {
                              viewModel.createTodo(tfTitle)
                              scope.launch {
                                  snackbarHostState.showSnackbar("Saved")
                              }
                          }) {
                              Text(text = "Save")
                          }
                          Button(onClick = {
                              viewModel.updateTodo(tfId.toInt(), tfTitle)
                              scope.launch {
                                  snackbarHostState.showSnackbar("Updated")
                              }
                          }) {
                              Text(text = "Update")
                          }
                      }
                  }
              }
              Box(
                  modifier = Modifier
                      .fillMaxSize(), contentAlignment = Alignment.Center
              ) {
                  if (todoList.value.isNullOrEmpty()) {
                      Text(text = "Empty")
                  } else {
                      LazyColumn(Modifier.fillMaxSize()) {
                          todoList.value!!.let { todos ->
                              items(todos.size) { index ->
                                  Card(modifier = Modifier.padding(8.dp)) {
                                      Row(
                                          modifier = Modifier
                                              .fillMaxWidth()
                                              .padding(8.dp)
                                              .clickable {
                                                  tfId = todos[index].id.toString()
                                                  tfTitle = todos[index].title
                                              },
                                          verticalAlignment = Alignment.CenterVertically,
                                          horizontalArrangement = Arrangement.SpaceBetween
                                      ) {
                                          Text(text = todos[index].title)
                                          Button(onClick = {
                                              scope.launch {
                                                  snackbarHostState.showSnackbar("Deleted")
                                              }
                                              viewModel.deleteTodo(todos[index])
                                          }) {
                                              Text(text = "Delete")
                                          }
                                      }
                                  }
                              }
                          }
                      }
                  }
              }
          }
      }
  }

  ```

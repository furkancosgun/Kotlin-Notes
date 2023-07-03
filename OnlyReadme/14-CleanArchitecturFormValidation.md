# Clean Architecture Form Validation

### Implementation

```gradle
//build.gradle(:app)
//MVVM
implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.1")
```

### Create Form Validation Result Model

```kotlin
//domain/use_case/ValidationResult.kt
package com.example.cleanarchitectureformvalidation.domain.use_case

data class ValidationResult(val successful:Boolean, val errorMessage:String?=null)
```

### Create Form Validation Event Model

```kotlin
//presentation/RegistrationFormEvent.kt
package com.example.cleanarchitectureformvalidation.presentation

sealed class RegistrationFormEvent {
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class RepeatedPasswordChanged(val repeatedPassword: String) : RegistrationFormEvent()
    data class AcceptTermsChanged(val acceptTerms: Boolean) : RegistrationFormEvent()
    object Submit : RegistrationFormEvent()
}
```

### Crate Form State Model

```kotlin
//presntation/RegistrationFormEvent.kt

package com.example.cleanarchitectureformvalidation.presentation

data class RegistrationFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val repeatedPassword: String = "",
    val repeatedPasswordError: String? = null,
    val acceptedTerms: Boolean = false,
    val acceptedTermsError: String? = null
)
```

### Create Form Validation Use Cases

```kotlin
//domain.use_case/ValidateEmail.kt
package com.example.cleanarchitectureformvalidation.domain.use_case

import android.util.Patterns

class ValidateEmail {
    fun execute(email: String): ValidationResult {
        if (email.isBlank()) {
            return ValidationResult(false, "The email can't be blank")
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return ValidationResult(false, "That's not a valid email")
        }
        return ValidationResult(true)
    }
}

//domain.use_case/ValidatePassword.kt
package com.example.cleanarchitectureformvalidation.domain.use_case

class ValidatePassword {
    fun execute(password: String): ValidationResult {
        if (password.length < 8) {
            return ValidationResult(false, "The password needs to consist of a least 8 character")
        }
        val containsLettersAndDigits =
            password.any { it.isDigit() } && password.any { it.isLetter() }
        if (!containsLettersAndDigits) {
            return ValidationResult(
                false,
                "The password needs to contains at least one letter and digit"
            )
        }
        return ValidationResult(true)
    }
}


//domain.use_case/ValidateRepeatedPassword.kt
package com.example.cleanarchitectureformvalidation.domain.use_case

class ValidateRepeatedPassword {
    fun execute(password: String, repeatedPassword: String): ValidationResult {
        if (password != repeatedPassword) {
            return ValidationResult(false, "The passwords don't match")
        }
        return ValidationResult(true)
    }
}

//domain.use_case/ValidateAcceptTerms.kt
package com.example.cleanarchitectureformvalidation.domain.use_case

class ValidateAcceptTerms {
    fun execute(acceptTerms: Boolean): ValidationResult {
        if (!acceptTerms) {
            return ValidationResult(false, "Please accept the terms")
        }
        return ValidationResult(true)
    }
}
```

### Create ViewModel

```kotlin
//presentation/MainViewModel.kt
package com.example.cleanarchitectureformvalidation.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureformvalidation.domain.use_case.ValidateAcceptTerms
import com.example.cleanarchitectureformvalidation.domain.use_case.ValidateEmail
import com.example.cleanarchitectureformvalidation.domain.use_case.ValidatePassword
import com.example.cleanarchitectureformvalidation.domain.use_case.ValidateRepeatedPassword
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

//u can use di here
class MainViewModel(
    private val validateEmail: ValidateEmail = ValidateEmail(),
    private val validatePassword: ValidatePassword = ValidatePassword(),
    private val validateRepeatedPassword: ValidateRepeatedPassword = ValidateRepeatedPassword(),
    private val validateAcceptTerms: ValidateAcceptTerms = ValidateAcceptTerms(),
) : ViewModel() {
    //Declerations

    //Form State
    var state by mutableStateOf(RegistrationFormState())

    //Form Validation OK Channel
    private val validationChannel = Channel<ValidationEvent>()

    //Form Event
    val validationEvents = validationChannel.receiveAsFlow()

    //Events
    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }

            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }

            is RegistrationFormEvent.RepeatedPasswordChanged -> {
                state = state.copy(repeatedPassword = event.repeatedPassword)
            }

            is RegistrationFormEvent.AcceptTermsChanged -> {
                state = state.copy(acceptedTerms = event.acceptTerms)
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    //Submit data check area
    private fun submitData() {
        //Validating
        val emailResult = validateEmail.execute(state.email)
        val passwordResult = validatePassword.execute(state.password)
        val repeatedPasswordResult =
            validateRepeatedPassword.execute(state.password, state.repeatedPassword)
        val acceptTermsResult = validateAcceptTerms.execute(state.acceptedTerms)

        //Error Check
        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult,
            acceptTermsResult
        ).any {
            !it.successful
        }

        //Update state
        state = state.copy(
            emailError = emailResult.errorMessage,
            passwordError = passwordResult.errorMessage,
            repeatedPasswordError = repeatedPasswordResult.errorMessage,
            acceptedTermsError = acceptTermsResult.errorMessage,
        )

        //if has error return
        if (hasError) {
            return
        }

        //else what do you want
        viewModelScope.launch {
            validationChannel.send(ValidationEvent.Success)
        }
    }

    //Validation OK Event
    sealed class ValidationEvent {
        object Success : ValidationEvent()
    }
}
```

### And Create View

```kotlin
@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun MainView() {
    //Declerations
    val viewModel = viewModel<MainViewModel>()
    val state = viewModel.state
    val context = LocalContext.current

    //if validation OK
    LaunchedEffect(key1 = context) {
        // show toast
        viewModel.validationEvents.collect { event ->
            when (event) {
                MainViewModel.ValidationEvent.Success -> {
                    Toast.makeText(
                        context, "Registration Successful", Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(32.dp), verticalArrangement = Arrangement.Center
    ) {
        //Email
        TextField(value = state.email, onValueChange = {
            viewModel.onEvent(RegistrationFormEvent.EmailChanged(it))
        }, isError = state.emailError != null, modifier = Modifier.fillMaxWidth(), label = {
            Text(text = "Email")
        }, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email))
        if (state.emailError != null) {
            Text(text = state.emailError, color = MaterialTheme.colorScheme.error)
        }

        //Password
        TextField(value = state.password,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.PasswordChanged(it))
            },
            isError = state.passwordError != null,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            visualTransformation = PasswordVisualTransformation()
        )
        if (state.passwordError != null) {
            Text(
                text = state.passwordError, color = MaterialTheme.colorScheme.error
            )
        }

        //Repeated Password
        TextField(value = state.repeatedPassword,
            onValueChange = {
                viewModel.onEvent(RegistrationFormEvent.RepeatedPasswordChanged(it))
            },
            isError = state.repeatedPasswordError != null,
            modifier = Modifier.fillMaxWidth(),
            label = {
                Text(text = "Repeat Password")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )
        if (state.repeatedPasswordError != null) {
            Text(
                text = state.repeatedPasswordError, color = MaterialTheme.colorScheme.error
            )
        }

        //Accept Terms
        Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = state.acceptedTerms, onCheckedChange = {
                viewModel.onEvent(RegistrationFormEvent.AcceptTermsChanged(it))
            })
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = "Accept Terms")
        }
        if (state.acceptedTermsError != null) {
            Text(
                text = state.acceptedTermsError, color = MaterialTheme.colorScheme.error
            )
        }

        //Submit Button
        Button(modifier = Modifier.fillMaxWidth(), onClick = {
            viewModel.onEvent(RegistrationFormEvent.Submit)
        }) {
            Text(text = "Submit")
        }
    }
}
```

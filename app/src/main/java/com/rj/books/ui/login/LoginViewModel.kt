package com.rj.books.ui.login

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import android.util.Patterns
import com.rj.books.R
import com.rj.books.model.Result
import com.rj.books.model.login.data.*

import io.reactivex.observers.DisposableObserver
import io.reactivex.observers.DisposableSingleObserver

class LoginViewModel(private val loginRepository: LoginRepository) : ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginFormState: LiveData<LoginFormState> = _loginForm

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult

    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> = _registerResult

    private val _authMode = MutableLiveData<Int>()
    val authMode: LiveData<Int> = _authMode

    fun changeAuthMode(mode: Int) {
        _authMode.value = mode
    }

    /**
     * Subscribe to loginResult when this function is called.
     */
    fun login(context: Context, username: String, password: String) {
        // can be launched in a separate asynchronous job
        loginRepository.login(context, username, password)
             .subscribe(object : DisposableSingleObserver<Result<LoggedInUser>>() {
                 override fun onSuccess(result: Result<LoggedInUser>) {
                     if (result is Result.Success<LoggedInUser>) {
                         _loginResult.value =
                             LoginResult(
                                 success = LoggedInUserView(
                                     displayName = result.data.displayName
                                 )
                             )
                     } else {
                         _loginResult.value =
                             LoginResult(error = R.string.login_failed)
                     }
                 }

                 override fun onError(e: Throwable) {
                     e.printStackTrace()
                     // TODO implement other error handlers
                 }
             })
    }

    fun loginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value =
                LoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value =
                LoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value =
                LoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    public fun isLoggedIn(): Boolean = LoginRepository.isLoggedIn

    // Register

    /**
     * Register a user.
     * Subscribe to registerResult when this function is called.
     */
    fun register(context: Context, fullName: String, username: String, password: String) {
        // can be launched in a separate asynchronous job
        loginRepository.register(context, fullName, username, password)
            .subscribe(object : DisposableSingleObserver<Result<RegisteredUser>>() {
                override fun onSuccess(result: Result<RegisteredUser>) {
                    if (result is Result.Success<RegisteredUser>)
                        _registerResult.value = RegisterResult(success = true)
                    else
                        _registerResult.value = RegisterResult(error = R.string.login_failed)
                }

                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    // TODO implement other error handlers
                }
            })
    }

}

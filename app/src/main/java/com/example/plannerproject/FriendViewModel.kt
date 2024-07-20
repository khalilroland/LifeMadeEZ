package com.example.plannerproject

import android.app.Application
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Exception
import java.util.UUID


//KR: View model for Friends, essiential for the Friendscreen and AddFriend screen
//KR: this class manages the ui related data for friends
class FriendViewModel(app: Application) : AndroidViewModel(app) {
    //KR: repo used to initialized an instance of UserRepository.
    private lateinit var repo: UserRepository
    //KR: wWait represents whether the system is currently waiting for a operation.
    //KR: important so operations dont run at the same time and crash
    private val wWait: MutableState<Boolean>
    //KR: waiting property that is read-only.
    val wait: State<Boolean>
    // KR: uUsers is a a private mutable state that represents a list of User objects.
    //KR: The users property is a read-only state that exposes the _users state to external classes.
    //KR: initiliazes val uUsers with a empty list
    private val uUsers: MutableState<List<User>> = mutableStateOf(listOf())
    //KR: val users creates a immutable state for picsum images which makes it accessable outside of this class
    //KR: assigns  this ^ to this v
    val users: State<List<User>> = uUsers
    //KR: testFriend for testing, represents a User
    val testFriend = User(UUID.randomUUID(),"testFriend","testFriend", 0, false)
    //KR: UsersFetcher used to fetch user data
    private val usersFetcher = UsersFetcher(getApplication())

    //KR: init block initializes waiting to a false state
    init {
        wWait = mutableStateOf(false)
        //KR: the wait property is assigned the same value
        wait = wWait
        //KR: couroutine launched using viewModelScope.
        viewModelScope.launch {
            //KR: This coroutine initializes wWait to true
            wWait.value = true
            //KR: sets up the repo using UserOfflineRepository
            repo = UserOfflineRepository(getApplication(), getApplication())
            //KR: retrieves users from the repo
            uUsers.value = repo.getUsers()
            try {
                //KR: fetches additional users using usersFetcher
                val users = usersFetcher.fetchUsers()
                // KR: iterates through the fetched users
                // KR: inserts each user into the repo
                users.forEach {user -> repo.insertUser(user)}
            } catch (e: Exception) {

            }
            // KR: checks if the test friend exist
            if(repo.findUserById("testFriend") == null){
                // KR: inserts the testFriend user into the repo if it doesn't exist
                repo.insertUser(testFriend)
                // KR: updates uUsers with latest list from repo
                uUsers.value = repo.getUsers()
            }
            // KR: updates uUsers with latest list from repository
            uUsers.value = repo.getUsers()
            // KR: sets the wWait state to false
            // KR: indicates running code has completed
            wWait.value = false
        }
    }

    //function to delete
    suspend fun deleteUser(user: User) {
        // KR: deletes user from repo
        repo.deleteUser(user)
        // KR: updates uUser with  list of users from the repository
        uUsers.value = repo.getUsers()
    }

    //function to updateu ser and user score
    fun updateUser(user: User) {
        // KR: updatedUser, deleted score + 10 which was used to add 10 to the score when task complete, now it does it in a different file
        val updatedUser = User(user.id, user.userName, user.password, user.score , false)
        viewModelScope.launch {
            // KR: sets the wWait state to true
            // KR: indicates running codestarted
            wWait.value = true
            // KR: updates the uUser in the repo with updatedUser val
            repo.updateUser(updatedUser)
            // KR: updates uUsers with list of users from the repo
            uUsers.value = repo.getUsers()
            //KR: sets the wWait state to false
            //KR: indicates running code has completed
            wWait.value = false
        }
    }

    //function for addingUsers
    fun addUser(user: User) {
        viewModelScope.launch {
            // KR: sets the wWait state to true
            // KR: indicates running codestarted
            wWait.value = true
            // KR: inserts the user into the repo
            repo.insertUser(user)
            // KR: updates uUser list of users from repo
            uUsers.value = repo.getUsers()
            // KR: sets the wWait state to false
            // KR: indicates running code has completed
            wWait.value = false
        }
    }

}
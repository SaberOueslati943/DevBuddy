package com.saberoueslati.devbuddy.features.addtask

sealed class AddTaskReaction {
    object OnBackClicked : AddTaskReaction()
    object OnSaveTaskCompleted : AddTaskReaction()
    object InvalidTitle : AddTaskReaction()
}
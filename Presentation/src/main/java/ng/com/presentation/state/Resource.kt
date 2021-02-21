package ng.com.presentation.state

class Resource<out T> constructor(val status: ResourceState, val data: T?, var message:String?)
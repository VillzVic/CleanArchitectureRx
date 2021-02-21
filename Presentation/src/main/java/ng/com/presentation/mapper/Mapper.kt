package ng.com.presentation.mapper

interface Mapper<in D, out V> {

    fun mapToView(type: D) : V
}
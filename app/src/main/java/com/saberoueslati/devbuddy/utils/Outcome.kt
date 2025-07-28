package com.saberoueslati.devbuddy.utils

class UnwrapException(message: String) : Exception(message)

sealed class Outcome<out V, out E> {
    fun getOrThrow(): V = when (this) {
        is Success -> data
        is Failure -> throw UnwrapException("Cannot unwrap $this")
    }

    fun <V2> map(transformValue: (V) -> V2): Outcome<V2, E> = when (this) {
        is Success -> Success(transformValue(data))
        is Failure -> this
    }

    fun <E2> mapError(transformError: (E) -> E2): Outcome<V, E2> = when (this) {
        is Success -> this
        is Failure -> Failure(transformError(error))
    }

    fun <V2> fold(transformValue: (V) -> V2, transformError: (E) -> V2): V2 = when (this) {
        is Success -> transformValue(data)
        is Failure -> transformError(error)
    }

    override fun toString(): String = when (this) {
        is Success -> "👌Success[data=$data]"
        is Failure -> "🆘 Error[exception=$error]"
    }

    companion object {
        private fun <V> success(value: V): Success<V> = Success(value)
        private fun <E> failure(error: E): Failure<E> = Failure(error)

        fun <V, E> all(vararg outcomes: Outcome<V, E>) = all(outcomes.asIterable())

        private fun <V, E> all(outcomes: Iterable<Outcome<V, E>>): Outcome<List<V>, E> {
            return success(outcomes.map {
                when (it) {
                    is Success -> it.data
                    is Failure -> return it
                }
            })
        }

        inline fun <V> wrapException(
            exception: Exception? = null,
            function: () -> V
        ): Outcome<V, Exception> = try {
            Success(function())
        } catch (e: Exception) {
            Failure(exception ?: e)
        }
    }
}

class Success<out V>(val data: V) : Outcome<V, Nothing>() {
    override fun toString() = "Outcome.Success($data)"
    override fun hashCode() = data?.hashCode() ?: 0
    override fun equals(other: Any?) =
        other is Success<*> && data == other.data
}

class Failure<out E>(val error: E) : Outcome<Nothing, E>() {
    override fun toString() = "Outcome.Failure($error)"
    override fun hashCode() = error?.hashCode() ?: 0
    override fun equals(other: Any?) =
        other is Failure<*> && error == other.error
}

fun <V, E> Outcome<V, E>.getOrElse(default: V) = when (this) {
    is Success -> data
    is Failure -> default
}

fun <V, E> Outcome<V, E>.getOrElseTransform(transformError: (E) -> V) = when (this) {
    is Success -> data
    is Failure -> transformError(error)
}

fun <V, V2, E> Outcome<V, E>.andThen(transformValue: (V) -> Outcome<V2, E>): Outcome<V2, E> =
    when (this) {
        is Success -> transformValue(data)
        is Failure -> this
    }

suspend fun <V, V2, E> Outcome<V, E>.andThenThread(transformValue: suspend (V) -> Outcome<V2, E>): Outcome<V2, E> =
    when (this) {
        is Success -> transformValue(data)
        is Failure -> this
    }

fun <V, E, E2> Outcome<V, E>.andThenError(transformError: (E) -> Outcome<V, E2>): Outcome<V, E2> =
    when (this) {
        is Success -> this
        is Failure -> transformError(error)
    }
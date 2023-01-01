package uz.akfadiler.testappaliftech.utils

import androidx.lifecycle.Observer


class EventObserver<T>(
    private val onEventUnhandledContent: (T) -> Unit
) : Observer<SingleEventLD<T>> {
    override fun onChanged(event: SingleEventLD<T>?) {
        event?.getContentIfNotHandled()?.let { value ->
            onEventUnhandledContent(value)
        }
    }
}
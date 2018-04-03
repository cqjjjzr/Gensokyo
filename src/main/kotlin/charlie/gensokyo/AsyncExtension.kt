@file:JvmName("Gensokyo")
@file:JvmMultifileClass

package charlie.gensokyo

import javax.swing.SwingUtilities
import javax.swing.SwingWorker
import kotlin.concurrent.thread

fun doAsync(threadName: String = "AsyncThread-" + System.currentTimeMillis(),
            isDaemon: Boolean = false,
            block: () -> Unit) {
    thread(name = threadName, isDaemon = isDaemon, block = block)
}

fun uiThread(block: () -> Unit) {
    SwingUtilities.invokeAndWait(block)
}

fun uiThreadAsync(block: () -> Unit) {
    SwingUtilities.invokeLater(block)
}

inline fun <T, V> doAsyncWorker(crossinline block: () -> T,
                                crossinline blockUIThreadProcess: (V) -> Unit = {},
                                crossinline blockDone: (T) -> Unit = {},
                                crossinline blockDoneCancelled: () -> Unit = {}) {
    object : SwingWorker<T, V>() {
        override fun doInBackground(): T = block()

        override fun process(chunks: MutableList<V>) = chunks.forEach(blockUIThreadProcess)

        override fun done() {
            if (isCancelled) blockDoneCancelled()
            else blockDone(get())
        }
    }.execute()
}

inline fun <T> doAsyncWorkerNoReturn(crossinline block: () -> Unit,
                                     crossinline blockUIThreadProcess: (T) -> Unit = {}) {
    object : SwingWorker<Unit, T>() {
        override fun doInBackground() = block()

        override fun process(chunks: MutableList<T>) = chunks.forEach(blockUIThreadProcess)
    }.execute()
}

package charlie.gensokyo

fun main(args: Array<String>) {
    systemLookAndFeel()
    frame (title = "Test", show = true) {
        // size { width = 500; height = 500 }
        size(500, 500)
        abstractLayout
        exitOnClose

        button("Hello!") {
            // size { width = 100; height = 100 }
            size(100, 100)
            // location { x = 50; y = 30 }
            location(50, 30)
            listenAction { _ ->
                println("click!")
                hide
            }
        }
    }
}
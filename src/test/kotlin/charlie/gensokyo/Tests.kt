package charlie.gensokyo

import javax.swing.JPanel

fun main(args: Array<String>) {
    systemLookAndFeel()
    frame (title = "Test", show = true) {
        size(500, 500)
        exitOnClose

        gridLayout {
            row {
                button("Hello!") {
                    listenAction { _ ->
                        println("click!")
                        hide
                    }
                }
                // gap
            }
            row {
                button("Another Hello!") {
                    listenAction { _ ->
                        println("another click!")
                        hide
                    }
                }
                // gap
            }
            row {
                JPanel().apply {
                    gridLayout {
                        row {
                            button("Oh Boy♂Next♂Door!") {
                                listenAction { _ ->
                                    println("Ahh fuck you")
                                    hide
                                }
                            }
                            button("change the boss of the gym!") {
                                listenAction { _ ->
                                    println("Ahh FA♂Q")
                                    hide
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
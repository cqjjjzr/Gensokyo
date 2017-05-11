package charlie.gensokyo

import kotlin.system.exitProcess

fun main(args: Array<String>) {
    systemLookAndFeel()
    frame (title = "Test", show = true) {
        size(500, 500)
        exitOnClose

        menuBar {
            subMenu("File") {
                item("Open") {
                    listenAction {
                        println("BOOM!")
                    }
                }
                separator
                subMenu("Recent") {
                    item("nanimo arimasen") {
                        listenAction {
                            println("SHIT!")
                        }
                    }
                }
                separator
                item("Exit") {
                    listenAction {
                        println("NOPE! SHIT!")
                        exitProcess(0)
                    }
                }
            }
        }

        gridLayout {
            row {
                button("Hello!") {
                    listenAction {
                        println("click!")
                        hide
                    }
                }
                // gap
            }
            row {
                button("Another Hello!") {
                    listenAction {
                        println("another click!")
                        hide
                    }
                }
                // gap
            }
            row {
                container {
                    gridLayout {
                        row {
                            button("Oh Boy♂Next♂Door!") {
                                listenAction{
                                    println("Ahh fuck you")
                                    hide
                                }
                            }
                            button("change the boss of the gym!") {
                                listenActionWithEvent { source, _, _, _ ->
                                    println("Ahh FA♂Q $source")
                                    hide
                                    this@frame.hide
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
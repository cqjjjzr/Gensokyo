package charlie.gensokyo

import java.net.URL
import javax.swing.JTextArea
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    systemLookAndFeel()
    val updateArea = JTextArea()
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
                            doAsync(isDaemon = true) {
                                URL("http://ice1000.org/2017/05/11/KotlinDSL2/#javafx-dsl")
                                        .openConnection()
                                        .getInputStream()
                                        .reader()
                                        .apply {
                                            forEachLine { uiThreadAsync { updateArea.append("$it\n") } }
                                        }
                            }
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

            row {
                scroll(textArea("Hahahahaha", updateArea))
            }
        }
    }
}
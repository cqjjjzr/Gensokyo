package charlie.gensokyo

import java.net.URL
import javax.swing.JTextArea
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    useSystemLookAndFeel()
    frame(title = "Test", show = true) {
        size(500, 500)
        exitOnClose

        menuBar {
            subMenu("File") {
                item("Open") {
                    onAction {
                        println("BOOM!")
                    }
                }
                separator
                subMenu("Recent") {
                    item("nanimo arimasen") {
                        onAction {
                            println("SHIT!")
                            doAsync(isDaemon = true) {
                                URL(plainInputBox("Input a URL", initialValue = "http://ice1000.org/categories/#Kotlin"))
                                        .openConnection()
                                        .getInputStream()
                                        .reader()
                                        .forEachLine {
                                            uiThreadAsync {
                                                (this@frame.componentFromID("contentTextArea") as JTextArea)
                                                        .append("$it\n")
                                            }
                                        }
                                uiThread {
                                    plainMessageBox("Oh♂I'm♂Done", "I Like It")
                                    yesNoBox("Clear box?", "Ahh♂Fuck") {
                                        yesButton { (this@frame.componentFromID("contentTextArea") as JTextArea).text = "" }
                                        noButton { plainMessageBox("Ahh♂Fuck♂You!") }
                                    }
                                }
                            }
                        }
                    }
                }
                separator
                item("Exit") {
                    onAction {
                        println("NOPE! SHIT!")
                        exitProcess(0)
                    }
                }
            }
        }

        gridLayout {
            row {
                button("Hello!") {
                    onAction {
                        println("click!")
                        hide
                    }
                }
                // gap
            }
            row {
                button("Another Hello!") {
                    onAction {
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
                                onAction {
                                    println("Ahh fuck you")
                                    hide
                                }
                            }
                            button("change the boss of the gym!") {
                                onActionWithEvent { source, _, _, _ ->
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
                scroll(textArea("Hahahahaha") {
                    assignID("contentTextArea")
                })
            }
        }
    }
}
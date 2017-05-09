package charlie.gensokyo

import javax.swing.LookAndFeel
import javax.swing.UIManager

var globalLookAndFeel: LookAndFeel get() = UIManager.getLookAndFeel()
    set(value) = UIManager.setLookAndFeel(value)

var globalLookAndFeelClassName: String get() = UIManager.getLookAndFeel().javaClass.name
    set(value) = UIManager.setLookAndFeel(value)

fun systemLookAndFeel() = UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName())
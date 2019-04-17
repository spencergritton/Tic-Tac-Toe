package edu.uiowa.JavaFX

import edu.uiowa.Board.Board
import javafx.event.EventHandler
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.layout.StackPane
import javafx.scene.text.TextAlignment
import javafx.stage.Stage
import javafx.geometry.Pos
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.VBox

// JavaFx class for the PauseMenu that is reachable by clicking Esc during a game
class PauseMenu: Stage {
    constructor(
            caller: Stage
    ) : super() {

        val root = StackPane()
        val scene = Scene(root)
        scene.stylesheets.add("stylesheet.css")
        this.scene = scene
        val display = Canvas(300.0, 400.0)
        val vbox = VBox()
        vbox.alignment = Pos.CENTER
        vbox.style = "-fx-padding: 10px;"

        val title = Label()
        title.text = "Tic Tac Toe"
        title.id = "title"
        title.textAlignment = TextAlignment.CENTER

        val resume = SoundButton()
        resume.text = "Resume"
        resume.textAlignment = TextAlignment.CENTER
        resume.translateY = -30.0
        resume.onAction = EventHandler {
            this.hide()
        }

        val menus = SoundButton()
        menus.text = "Back to Menus"
        menus.textAlignment = TextAlignment.CENTER
        menus.translateY = -15.0
        menus.onAction = EventHandler {
            this.hide()
            caller.hide()
            TitlePage()
        }

        val exit = SoundButton()
        exit.text = "Exit"
        exit.textAlignment = TextAlignment.CENTER
        exit.onAction = EventHandler {
            caller.hide()
            this.hide()
        }

        val mute = SoundButton()
        val volumeOn = Image("volumeOn.png", 20.0, 20.0, true, true)
        val volumeOff = Image("volumeOff.png", 20.0, 20.0, true, true)
        var muteVal = false
        if (media?.isMute == true) {muteVal = true}
        if (media?.isMute == false) {muteVal = false}
        mute.graphic = if (muteVal) ImageView(volumeOff) else ImageView(volumeOn)
        mute.onAction = EventHandler {
            if (media?.isMute == true) {
                media?.isMute = false
                media?.volume = 100.0
                mute.graphic = ImageView(volumeOn)
            } else {
                media?.isMute = true
                media?.volume = 10.0
                mute.graphic = ImageView(volumeOff)
            }
        }

        StackPane.setAlignment(title, Pos.TOP_CENTER)
        StackPane.setAlignment(vbox, Pos.CENTER)
        StackPane.setAlignment(exit, Pos.BOTTOM_LEFT)
        StackPane.setAlignment(mute, Pos.BOTTOM_RIGHT)

        vbox.children.add(resume)
        vbox.children.add(menus)

        root.children.add(display)
        root.children.add(title)
        root.children.add(vbox)
        root.children.add(exit)
        root.children.add(mute)

        this.show()
    }
}
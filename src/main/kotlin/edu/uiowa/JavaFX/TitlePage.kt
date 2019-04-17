package edu.uiowa.JavaFX

import javafx.event.EventHandler
import javafx.geometry.Pos
import javafx.scene.Scene
import javafx.scene.canvas.Canvas
import javafx.scene.control.Label
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import javafx.scene.layout.StackPane
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import javafx.scene.text.TextAlignment
import javafx.stage.Stage
import javafx.util.Duration
import java.io.File

var media: MediaPlayer? = null

// Title page that users land on with links to all relevant menus
class TitlePage: Stage {
    constructor() : super() {
        this.title = "Tic Tac Title"
        val root = StackPane()
        val scene = Scene(root)
        scene.stylesheets.add("stylesheet.css")
        this.scene = scene

        val playButton = SoundButton()
        playButton.text = "Play"
        playButton.id = "playButton"
        playButton.textAlignment = TextAlignment.CENTER
        playButton.onAction = EventHandler {
            this.hide()
            GameType()
        }

        val rulesButton = SoundButton()
        rulesButton.text = "Rules"
        rulesButton.textAlignment = TextAlignment.CENTER
        rulesButton.translateY = 50.0
        rulesButton.onAction = EventHandler {
            this.hide()
            Rules()
        }

        val controlsButton = SoundButton()
        controlsButton.text = "Controls"
        controlsButton.textAlignment = TextAlignment.CENTER
        controlsButton.translateY = 100.0
        controlsButton.onAction = EventHandler {
            this.hide()
            Controls()
        }

        val exit = SoundButton()
        exit.text = "Exit"
        exit.translateY = 150.0
        exit.onAction = EventHandler { this.hide() }

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

        val title = Label()
        title.text = "Tic Tac Toe"
        title.id = "title"
        title.textAlignment = TextAlignment.CENTER

        StackPane.setAlignment(title, Pos.TOP_CENTER)
        StackPane.setAlignment(mute, Pos.BOTTOM_LEFT)

        val display = Canvas(450.0, 450.0)
        root.children.add(display)
        root.children.add(playButton)
        root.children.add(rulesButton)
        root.children.add(controlsButton)
        root.children.add(title)
        root.children.add(exit)
        root.children.add(mute)

        if (media == null) {
            val musicFile = "./src/main/resources/background.mp3"
            val sound = Media(File(musicFile).toURI().toString())
            media = MediaPlayer(sound)
            media?.cycleCount = MediaPlayer.INDEFINITE
            media?.startTime = Duration.ZERO
            media?.stopTime = Duration.seconds(507.0)
            media?.play()
        }

        this.show()
    }
}
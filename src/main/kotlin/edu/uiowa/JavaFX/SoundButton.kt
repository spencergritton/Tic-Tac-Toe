package edu.uiowa.JavaFX

import javafx.event.EventHandler
import javafx.scene.control.Button
import javafx.scene.media.Media
import javafx.scene.media.MediaPlayer
import java.io.File

// Custom button that plays sound when clicked
class SoundButton: Button {
    constructor(): super() {
        this.onMousePressed = EventHandler {
            val musicFile = "./src/main/resources/button.mp3"
            val sound = Media(File(musicFile).toURI().toString())
            val mediaPlayer = MediaPlayer(sound)
            mediaPlayer.play()
        }
    }
}
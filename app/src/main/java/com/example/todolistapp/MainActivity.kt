package com.example.todolistapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.compose.setContent
import com.example.todolistapp.ui.todo_list.TodoScreen
import com.giphy.sdk.ui.views.GiphyDialogFragment
import com.giphy.sdk.core.models.Media
import com.giphy.sdk.ui.views.GiphyDialogFragment.GifSelectionListener
import com.giphy.sdk.ui.GPHContentType

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TodoScreen { onGifSelected ->
                showGiphyPicker(onGifSelected)
            }
        }
    }

    private fun showGiphyPicker(onGifSelected: (String) -> Unit) {
        val giphyDialog = GiphyDialogFragment.newInstance()
        giphyDialog.gifSelectionListener = object : GifSelectionListener {
            override fun didSearchTerm(term: String) {
                TODO("Not yet implemented")
            }

            override fun onDismissed(selectedContentType: GPHContentType) {
                TODO("Not yet implemented")
            }

            override fun onGifSelected(media: Media, searchTerm: String?, selectedContentType: GPHContentType) {
                onGifSelected(media.id)
                giphyDialog.dismiss()
            }

            // Implement other abstract members if any
        }
        giphyDialog.show(supportFragmentManager, "GiphyDialogFragment")
    }
}

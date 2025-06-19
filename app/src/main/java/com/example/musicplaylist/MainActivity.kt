package com.example.musicplaylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicplaylist.ui.theme.MusicPlaylistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent{
            //State to control which screen is currently displayed.
            //"main" for the first screen,list for the second screen.
            //Using 'remember' with 'mutableStateOf' directly within the lambda
            //avoids cass-level private val/var declarations
            val currentScreen = remember { mutableStateOf("main") }

            //State to hold the list of music playlist
            //'mutableStateListOf' provides an observable list for Compose.
            val musicPlaylists = remember { mutableStateListOf<MusicPlaylist>0 }

            //State to control the visibility of the "Add to Playlist" dialog
            val show AddPlaylistDialog = remember {mutableStateOf(false)}

            //State for the input fields within the 
            }
        }
    }




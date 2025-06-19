package com.example.musicplaylist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.platform.LocalContext
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

            //State for the input fields within the "Add Playlist" dialog
            //These are also managed using 'remember' and 'mutableStateOf' locally
            val itemNameInput = remember { mutableStateOf("") }
            val categoryInput = remember { mutableStateOf("") }
            val quantityInput = remember { mutableStateOf("") }
            val commentsInput = remember { mutableStateOf("") }

            // Get the current context to display Toast messages and finish the activity.
            val context = LocalContext.current

            // Apply Material Design theme to the app.
            MaterialTheme {
                // A surface container using the 'background' color from the theme.
                Surface(
                    modifier = Modifier.fillMaxSize(), // Fills the entire screen.
                    color = MaterialTheme.colorScheme.background
                ) {
                    // Conditional rendering based on the 'currentScreen' state.
                    when (currentScreen.value) {
                        "main" -> {
                            // --- Screen One UI ---
                            Column(
                                modifier = Modifier
                                    .fillMaxSize() // Fills the available space.
                                    .padding(16.dp), // Adds padding around the column.
                                horizontalAlignment = Alignment.CenterHorizontally, // Centers content horizontally.
                                verticalArrangement = Arrangement.Center // Centers content vertically.
                            ) {
                                // Button to show the "Add to Packing List" dialog.
                                Button(
                                    onClick = { showAddItemDialog.value = true },
                                    modifier = Modifier
                                        .fillMaxWidth() // Makes the button fill the width.
                                        .padding(vertical = 8.dp) // Vertical padding for spacing.
                                ) {
                                    Text("Add to Packing List")
                                }

                                Spacer(Modifier.height(16.dp)) // Vertical spacing.

                                // Button to navigate to the second screen.
                                Button(
                                    onClick = { currentScreen.value = "list" },
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                ) {
                                    Text("Go to Second Screen")
                                }

                                Spacer(Modifier.height(16.dp)) // Vertical spacing.

                                // Button to exit the application.
                                Button(
                                    onClick = { (context as Activity).finish() }, // Finishes the current activity.
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(vertical = 8.dp)
                                ) {
                                    Text("Exit App")
                                }
                            }

                            // --- "Add Item" Dialog UI ---
                            // This dialog is shown when 'showAddItemDialog' is true.
                            if (showAddItemDialog.value) {
                                Dialog(onDismissRequest = { showAddItemDialog.value = false }) {
                                    Surface(
                                        modifier = Modifier.fillMaxWidth(), // Dialog surface fills width.
                                        shape = MaterialTheme.shapes.medium, // Rounded corners for the dialog.
                                        color = MaterialTheme.colorScheme.surface // Background color for the dialog.
                                    ) {
                                        Column(
                                            modifier = Modifier.padding(16.dp), // Padding inside the dialog.
                                            horizontalAlignment = Alignment.CenterHorizontally // Centers content.
                                        ) {
                                            Text(
                                                "Add New Packing Item",
                                                style = MaterialTheme.typography.headlineSmall // Larger text style.
                                            )
                                            Spacer(Modifier.height(16.dp))

                                            // Input field for Item Name.
                                            OutlinedTextField(
                                                value = itemNameInput.value,
                                                onValueChange = { itemNameInput.value = it },
                                                label = { Text("Item Name") },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 4.dp)
                                            )
                                            // Input field for Category.
                                            OutlinedTextField(
                                                value = categoryInput.value,
                                                onValueChange = { categoryInput.value = it },
                                                label = { Text("Category") },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 4.dp)
                                            )
                                            // Input field for Quantity.
                                            OutlinedTextField(
                                                value = quantityInput.value,
                                                onValueChange = { quantityInput.value = it },
                                                label = { Text("Quantity") },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 4.dp)
                                            )
                                            // Input field for Comments.
                                            OutlinedTextField(
                                                value = commentsInput.value,
                                                onValueChange = { commentsInput.value = it },
                                                label = { Text("Comments") },
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(vertical = 4.dp)
                                            )

                                            Spacer(Modifier.height(16.dp))

                                            // Row for Cancel and Add Item buttons.
                                            Row(
                                                modifier = Modifier.fillMaxWidth(),
                                                horizontalArrangement = Arrangement.SpaceAround // Distributes space evenly.
                                            ) {
                                                // Cancel button for the dialog.
                                                Button(onClick = {
                                                    showAddItemDialog.value =
                                                        false // Dismiss the dialog.
                                                    // Clear input fields.
                                                    itemNameInput.value = ""
                                                    categoryInput.value = ""
                                                    quantityInput.value = ""
                                                    commentsInput.value = ""
                                                }) {
                                                    Text("Cancel")
                                                }
                                                // Add Item button for the dialog.
                                                Button(onClick = {
                                                    val name = itemNameInput.value.trim()
                                                    val category = categoryInput.value.trim()
                                                    val quantityStr = quantityInput.value.trim()
                                                    val comments = commentsInput.value.trim()

                                                    // --- Error Handling for Input ---
                                                    if (name.isEmpty() || category.isEmpty() || quantityStr.isEmpty()) {
                                                        Toast.makeText(
                                                            context,
                                                            "Please fill in all required fields (Name, Category, Quantity).",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        return@Button // Exit the onClick lambda.
                                                    }

                                                    val quantity = quantityStr.toIntOrNull()
                                                    if (quantity == null || quantity <= 0) {
                                                        Toast.makeText(
                                                            context,
                                                            "Quantity must be a positive number.",
                                                            Toast.LENGTH_SHORT
                                                        ).show()
                                                        return@Button // Exit the onClick lambda.
                                                    }

                                                    // Add the new item to the packing list.
                                                    packingItems.add(
                                                        PackingItem(
                                                            name,
                                                            category,
                                                            quantity,
                                                            comments
                                                        )
                                                    )
                                                    showAddItemDialog.value =
                                                        false // Dismiss the dialog.
                                                    // Clear input fields after adding.
                                                    itemNameInput.value = ""
                                                    categoryInput.value = ""
                                                    quantityInput.value = ""
                                                    commentsInput.value = ""
                                                    Toast.makeText(
                                                        context,
                                                        "Item added!",
                                                        Toast.LENGTH_SHORT
                                                    ).show()
                                                }) {
                                                    Text("Add Item")
                                                }
                                            }
                                        }



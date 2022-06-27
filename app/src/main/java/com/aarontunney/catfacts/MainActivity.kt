package com.aarontunney.catfacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.aarontunney.catfacts.ui.theme.CatFactsTheme

// This is the main view of the app
// Mostly written from this tutorial: https://howtodoandroid.com/jetpack-compose-retrofit-recyclerview/
class MainActivity : ComponentActivity() {

    // The View Model contains the state of the view and fetches the data it needs
    val mainViewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatFactsTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    // Create a list view
                    FactList(factList = mainViewModel.factListResponse)
                    // And call the backend API
                    mainViewModel.getFactList()
                }
            }
        }
    }
}

@Composable
fun FactList(factList: List<Fact>) {
    // Create a lazy column (i.e. it only creates items when they're needed)
    LazyColumn {
        itemsIndexed(items = factList) { index, item ->
            // Create a fact item view for each fact
            FactItem(fact = item, index)
        }
    }
}

@Composable
fun FactItem(fact: Fact, index: Int) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface() {
            // A simple column of views
            Column(
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxHeight()
            ) {
                // The fact text
                Text(
                    text = fact.text,
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                // The user text - not very useful as it's just an ID!
                Text(
                    text = fact.user,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier
                        .background(
                            Color.LightGray
                        )
                        .padding(4.dp)
                )
            }
        }
    }
}

// @Preview create a preview of the created view (in Android Studio's
// design or split tab)
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CatFactsTheme {
        val status = Fact.Status(verified = true, sentCount = 1)
        val fact = Fact(id = "Test ID", user = "Test User", source = "Test Source",
        text = "This is a cat fact", updatedAt = "", type = "cat", createdAt = "",
        deleted = false, used = false)
        FactItem(fact = fact, index = 0)
    }
}
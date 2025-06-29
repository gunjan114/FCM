package com.example.fcmpractice

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

@Composable
fun AdminSendScreen() {
    var title by remember { mutableStateOf("") }
    var body by remember { mutableStateOf("") }
    var target by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    Column(modifier = Modifier
        .padding(24.dp)
        .fillMaxWidth()) {
        Text(text = "Send Notification", style = MaterialTheme.typography.titleLarge)

        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = title, onValueChange = { title = it }, label = { Text("Title") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = body, onValueChange = { body = it }, label = { Text("Body") })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(value = target, onValueChange = { target = it }, label = { Text("Target (home/fav/cart/profile)") })
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            sendNotificationToAll(title, body, target) {
                status = it
            }
        }) {
            Text("Send")
        }

        Spacer(modifier = Modifier.height(16.dp))
        Text(text = status)
    }
}

fun sendNotificationToAll(title: String, body: String, target: String, onDone: (String) -> Unit) {
    val url = "https://sendnotification-alzq2mx7cq-uc.a.run.app"

    val client = OkHttpClient()

    val json = """
        {
            "title": "$title",
            "body": "$body",
            "target": "$target"
        }
    """.trimIndent()

    val requestBody = json.toRequestBody("application/json".toMediaType())

    val request = Request.Builder()
        .url(url)
        .post(requestBody)
        .build()

    client.newCall(request).enqueue(object : Callback {
        override fun onFailure(call: Call, e: IOException) {
            onDone("Failed: ${e.message}")
        }

        override fun onResponse(call: Call, response: Response) {
            if (response.isSuccessful) {
                onDone("Success!")
            } else {
                onDone("Error: ${response.message}")
            }
        }
    })
}


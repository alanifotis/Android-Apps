package com.example.businesscard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.businesscard.ui.theme.BusinessCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BusinessCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xFF6200ee))
                            .padding(bottom = 30.dp)

                    ) {
                        Image(
                            painter = painterResource(R.drawable.android_logo),
                            contentDescription = "Business Logo",
                            modifier = Modifier
                                .height(180.dp)
                                .width(180.dp)
                        )
                        Text(
                            text = "Hello, Android!",
                            letterSpacing = 5.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.ExtraBold,
                            color = colorResource(R.color.white),
                            modifier = Modifier
                                .padding(8.dp)
                        )
                    }
                    Column(
                        verticalArrangement = Arrangement.Bottom,
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(bottom = 25.dp)
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        {
                            Icon(
                                Icons.Filled.Call,
                                contentDescription = "Phone Number",
                                tint = Color.Green,

                                modifier = Modifier.padding(end = 25.dp, bottom = 11.dp)
                            )
                            Text(
                                text = "+355 (69) 234 567 89",
                                color = Color.White,
                                letterSpacing = 2.sp,
                                fontWeight = FontWeight.W500,
                                modifier = Modifier.padding(bottom = 11.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        {
                            Icon(
                                imageVector = Icons.Filled.Share,
                                contentDescription = "Share",
                                tint = Color.Black,
                                modifier = Modifier.padding(end = 25.dp, bottom = 11.dp)
                            )
                            Text(
                                text = "+355 (69) 234 567 89",
                                color = Color.White,
                                letterSpacing = 2.sp,
                                fontWeight = FontWeight.W500,
                                modifier = Modifier.padding(bottom = 11.dp)
                            )
                        }
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        {
                            Icon(
                                Icons.Filled.Email,
                                contentDescription = "E-Mail Address",
                                tint = Color.Red,
                                modifier = Modifier.padding(end = 25.dp, bottom = 11.dp)
                            )
                            Text(
                                text = "example@joedoe.com",
                                color = Color.White,
                                letterSpacing = 2.sp,
                                fontWeight = FontWeight.W500,
                                modifier = Modifier.padding(bottom = 11.dp)
                            )
                        }

                    }

                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BusinessCardTheme {
    }
}

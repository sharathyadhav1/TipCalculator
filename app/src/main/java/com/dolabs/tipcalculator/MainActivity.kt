package com.dolabs.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dolabs.tipcalculator.components.InputField
import com.dolabs.tipcalculator.ui.theme.TipCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                Text(text = "hello again")
            }
        }
    }
}

@Composable
fun MyApp(content:@Composable () ->Unit){

    TipCalculatorTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }

}

@Composable
fun TopHeaderSection(totalPerPerson : Double  = 0.0){
    Surface(modifier = Modifier
        .fillMaxWidth()
        .height(150.dp)
        .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
                    color = Color(0xFF9281AF)
    ){
        Column(
            modifier = Modifier.padding(12.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center


        ) {
            val total ="%.2f".format(totalPerPerson)
            Text(text = "Total per person",
                style = MaterialTheme.typography.h5)
            Text(text = "$$total",
                style = MaterialTheme.typography.h4,
                fontWeight = FontWeight.ExtraBold)

        }


    }
    
}
@Preview(showBackground = true)
@Composable
fun MainContent(){

    val totalBillState = remember {
        mutableStateOf("")
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()

    }

    val keyBoardController = LocalSoftwareKeyboardController.current


    Surface(modifier = Modifier
        .padding(2.dp)
        .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ){
        Column {
            InputField(valueState = totalBillState.value,
                        labelId = "Enter Bill",
                        enabled = true,
                        isSingleLine = true,
                        onAction = KeyboardActions {
                            if(!validState) return@keyboardActions
                        })

        }
    }

}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

////@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TipCalculatorTheme {
        Greeting("Android")
    }
}
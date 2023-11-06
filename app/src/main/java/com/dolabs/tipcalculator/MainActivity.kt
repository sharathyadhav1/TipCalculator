package com.dolabs.tipcalculator

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dolabs.tipcalculator.components.InputField
import com.dolabs.tipcalculator.ui.theme.TipCalculatorTheme
import com.dolabs.tipcalculator.widgets.RoundedIconButton

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp{
                MainContent()
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
@OptIn(ExperimentalComposeUiApi::class)
@Preview(showBackground = true)
@Composable
fun MainContent(){

    BillForm(){billAmt ->
        Log.e("#####","$billAmt")

    }
    
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun BillForm(modifier :Modifier = Modifier,
             onValChange:(String) -> Unit){

    val totalBillState = remember {
        mutableStateOf("")
    }

    val validState = remember(totalBillState.value) {
        totalBillState.value.trim().isNotEmpty()
    }


    val sliderPositionState = remember {
        mutableStateOf(0f)
    }

    val keyboardController = LocalSoftwareKeyboardController.current

    Surface(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .wrapContentHeight(),

        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 2.dp, color = Color.LightGray)
    ){
        Column ( /*modifier = Modifier.padding(6.dp)
           verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start*/){
            InputField(
                modifier = Modifier.padding(10.dp),
                valueState = totalBillState,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions{
                    if (!validState) return@KeyboardActions
                    //onvaluechange

                    onValChange(totalBillState.value.trim())

                    keyboardController?.hide()
                })
            if(validState){
                Row(modifier = Modifier
                    .padding(3.dp)
                    .wrapContentHeight(),
                    horizontalArrangement = Arrangement.End){

                    Text(text = "Split", modifier = Modifier.align(
                        alignment = Alignment.CenterVertically
                    ))

                    Spacer(modifier = Modifier.width(20.dp))

                    Row(modifier = Modifier.padding(horizontal = 3.dp),
                        horizontalArrangement = Arrangement.End){
                        RoundedIconButton(imageVector = Icons.Default.Remove,
                            onclick = {

                        })
                        
                        Text(text = "2",modifier= Modifier
                            .padding(start = 10.dp, end = 10.dp)
                            .align(alignment = Alignment.CenterVertically))
                        RoundedIconButton(imageVector = Icons.Default.Add,
                            onclick = {

                        })
                    }


                }
            }

            //TIP ROW
            Row (modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(10.dp)){
                Text(text = "Tip",modifier= Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .align(alignment = Alignment.CenterVertically))

                Spacer(modifier = Modifier.width(200.dp))

                Text(text = "$34.00",modifier= Modifier
                    .padding(start = 10.dp, end = 10.dp)
                    .align(alignment = Alignment.CenterVertically))

            }
            
            
            Slider(modifier = Modifier.padding(start = 10.dp, end = 10.dp), value = sliderPositionState.value, onValueChange = {newValue ->
                sliderPositionState.value = newValue

            }, steps = 5)




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
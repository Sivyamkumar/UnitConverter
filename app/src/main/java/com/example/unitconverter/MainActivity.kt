package com.example.unitconverter

import android.os.Bundle
import android.renderscript.Sampler.Value
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember{ mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember{ mutableStateOf("Meters") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionfactor = remember {mutableStateOf(1.00)}
    val oconversionfactor = remember {mutableStateOf(1.00)}

    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionfactor.value * 100.0 / oconversionfactor.value)/100
        outputValue = result.toString()
    }

    Column(
        modifier=Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
            Text(
                "Unit Converter",
                style = MaterialTheme.typography.headlineLarge,
                fontFamily = FontFamily.Serif,
                fontSize = 32.sp
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue=it
            convertUnits()
        },
            label = { Text(text = "Enter Value", fontFamily = FontFamily.Monospace, fontSize = 16.sp) })
        Spacer(modifier = Modifier.height(16.dp))
        Row{
//            Input Box
            Box{
                Button(onClick = { iExpanded= true }) {
                    Text(
                        text = inputUnit,
                        fontFamily = (FontFamily.Monospace),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(10.dp,10.dp,10.dp,10.dp)
                    )
                    Icon(Icons.Default.ArrowDropDown,"Arrow Down")
                }
                DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false}) {
                    DropdownMenuItem(text ={ Text("Centimeter")}, onClick = {
                        inputUnit = "Centimeter"
                        iExpanded=false
                        conversionfactor.value = 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text ={ Text("Meter")}, onClick = {
                        inputUnit = "Meter"
                        iExpanded=false
                        conversionfactor.value = 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text ={ Text("Feet")}, onClick = {
                        inputUnit = "Feet"
                        iExpanded=false
                        conversionfactor.value = 3.28084
                        convertUnits()
                    })
                    DropdownMenuItem(text ={ Text("Milimeters")}, onClick = {
                        inputUnit = "Milimeters"
                        iExpanded=false
                        conversionfactor.value = 0.01
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
//            Output Box
            Box{
                Button(onClick = { oExpanded=true }) {
                    Text(
                        text = outputUnit,
                        fontFamily =  (FontFamily.Monospace),
                        fontSize = 12.sp,
                        modifier = Modifier.padding(10.dp,10.dp,10.dp,10.dp)
                    )
                    Icon(Icons.Default.ArrowDropDown,"Arrow Down")
                    DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded=false }) {
                        DropdownMenuItem(
                            text ={ Text("Centimeter")},
                            onClick = {
                                outputUnit = "Centimeter"
                                oExpanded=false
                                oconversionfactor.value = 0.01
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text ={ Text("Meter")},
                            onClick = {
                                outputUnit = "Meter"
                                oExpanded=false
                                oconversionfactor.value = 1.00
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text ={ Text("Feet")},
                            onClick = {
                                outputUnit = "Feet"
                                oExpanded=false
                                oconversionfactor.value = 3.28084
                                convertUnits()
                            }
                        )
                        DropdownMenuItem(
                            text ={ Text("Milimeters")},
                            onClick = {
                                outputUnit = "Milimeters"
                                oExpanded=false
                                oconversionfactor.value = 0.001
                                convertUnits()
                            }
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

//        Result Text
        Row {
            Text(
                style = MaterialTheme.typography.bodyLarge,
                text = "Result: ",
                fontSize = 24.sp,
            )
            Text(
                text = "$outputValue ",
                style = MaterialTheme.typography.bodyMedium ,
                fontSize = 24.sp,
            )
            Text(
                text = outputUnit,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 24.sp,
            )

        }
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showSystemUi = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}
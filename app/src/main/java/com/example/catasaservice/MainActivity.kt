package com.example.catasaservice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    var isBooting: Boolean = true
    var menuState: AppState = AppState.Type
    var image: String = ""
    var cat: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpMenu()
    }

    private fun setUpMenu(){
        val menu: NDSpinner = findViewById(R.id.spinner_menu)
        // Create an ArrayAdapter using the string array and a default spinner layout

        val stringArray = when(menuState){
            AppState.Type -> R.array.Menu_array
            AppState.Image -> R.array.picture_type_array
            AppState.Cat -> R.array.cat_type_array
        }
        ArrayAdapter.createFromResource(
            this,
            stringArray,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            menu.adapter = adapter
        }
        menu.onItemSelectedListener = this
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View?, pos: Int, id: Long) {
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
        val console: TextView = findViewById(R.id.Console_textView)
        console.setText(menuState.toString())
        if (isBooting){
            isBooting = false
            return
        }
        when(menuState){
            AppState.Type -> selectType(parent.selectedItem.toString())
            AppState.Image -> selectImage(parent.selectedItem.toString())
            AppState.Cat -> selectCat(parent.selectedItem.toString())
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>) {
        // Another interface callback
        // Do Nothing

    }
    enum class AppState {
        Type, Image, Cat
    }
    fun selectType(text: String){
        val types: Array<String> = resources.getStringArray(R.array.Menu_array)
        if (text == types[0])
        {
            menuState= AppState.Image
        }
        else if (text == types[1])
        {
            menuState= AppState.Cat
        }
        else {
            throw Exception("WTF ! It's a type that does not exist !")
        }
        updateText()
    }
    fun selectImage(text: String){
        image = text
        menuState= AppState.Type

        updateText()
    }
    fun selectCat(text: String){
        cat = text
        menuState= AppState.Type

        updateText()
    }

    fun updateText()
    {
        isBooting = true
        setUpMenu()
        setUpButton()
    }

    fun setUpButton(){
        val bouton: Button = findViewById(R.id.buttonIdAPI)

    }

}
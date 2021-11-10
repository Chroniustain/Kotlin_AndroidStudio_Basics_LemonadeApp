/*
 * Copyright (C) 2021 The Android Open Source Project.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lemonade

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private val LEMONADE_STATE = "LEMONADE_STATE"
    private val LEMON_SIZE = "LEMON_SIZE"
    private val SQUEEZE_COUNT = "SQUEEZE_COUNT"
    // SELECT represents the "pick lemon" state
    private val SELECT = "select"
    // SQUEEZE represents the "squeeze lemon" state
    private val SQUEEZE = "squeeze"
    // DRINK represents the "drink lemonade" state
    private val DRINK = "drink"
    // RESTART represents the state where the lemonade has be drunk and the glass is empty
    private val RESTART = "restart"
    // Default the state to select
    private var lemonadeState = "select"
    // Default lemonSize to -1
    private var lemonSize = -1
    // Default the squeezeCount to -1
    private var squeezeCount = -1

    private var lemonTree = LemonTree()
    private var lemonImage: ImageView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // === DO NOT ALTER THE CODE IN THE FOLLOWING IF STATEMENT ===
        if (savedInstanceState != null) {
            lemonadeState = savedInstanceState.getString(LEMONADE_STATE, "select")
            lemonSize = savedInstanceState.getInt(LEMON_SIZE, -1)
            squeezeCount = savedInstanceState.getInt(SQUEEZE_COUNT, -1)
        }
        // === END IF STATEMENT ===

        lemonImage = findViewById(R.id.image_lemon_state)
        setViewElements()
        lemonImage!!.setOnClickListener {
            // TODO: call the method that handles the state when the image is clicked
            clickLemonImage()
        }
        lemonImage!!.setOnLongClickListener {
            // TODO: replace 'false' with a call to the function that shows the squeeze count
            showSnackbar()
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     * This method saves the state of the app if it is put in the background.
     */
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(LEMONADE_STATE, lemonadeState)
        outState.putInt(LEMON_SIZE, lemonSize)
        outState.putInt(SQUEEZE_COUNT, squeezeCount)
        super.onSaveInstanceState(outState)
    }


    private fun clickLemonImage() {

//   My first approach.

        when (lemonadeState) {
            SELECT -> {
                lemonadeState = SQUEEZE
                lemonSize = lemonTree.pick()
                squeezeCount = 0
            }
            SQUEEZE -> if (lemonSize == 0) {
                    lemonadeState = DRINK
                    lemonSize = - 1
                } else {
                    squeezeCount = squeezeCount + 1
                    lemonSize = lemonSize - 1
                }
            DRINK -> {
                lemonadeState = RESTART
                lemonSize = -1
            }

            RESTART -> lemonadeState = SELECT
        }
        setViewElements()
    }

//    private fun clickLemonImage() {
//
//        when (lemonadeState) {
//
//            SELECT -> {
//                lemonadeState = SQUEEZE
//                lemonSize = lemonTree.pick()
//                squeezeCount = 0
//            }
//
//            SQUEEZE -> {
//                squeezeCount += 1
//                lemonSize -= 1
//                if (lemonSize == 0) {
//                    lemonadeState = DRINK
//                }
//            }
//
//            DRINK -> {
//                lemonSize = -1
//                lemonadeState = RESTART
//            }
//
//            RESTART -> {
//                lemonadeState = SELECT
//            }
//        }
//
//        setViewElements()
//    }



//  My first approach:

//    private fun setViewElements() {
//        val textAction: TextView = findViewById(R.id.text_action)
//        val lemonImage: ImageView = findViewById(R.id.image_lemon_state)
//        val stringsResource = when (lemonadeState) {
//            SELECT -> R.string.lemon_select
//            SQUEEZE -> R.string.lemon_squeeze
//            DRINK -> R.string.lemon_drink
//            RESTART -> R.string.lemon_empty_glass
//            else -> R.string.error
//        }
//
//        val drawableResource = when (lemonadeState) {
//            SELECT -> R.drawable.lemon_tree
//            SQUEEZE -> R.drawable.lemon_squeeze
//            DRINK -> R.drawable.lemon_drink
//            RESTART -> R.drawable.lemon_restart
//            else -> R.drawable.customer_support_line
//        }
//
//        textAction.setText(stringsResource)
//        lemonImage.setImageResource(drawableResource)
//    }

//  My second approach:

//        private fun setViewElements() {
//            val textAction: TextView = findViewById(R.id.text_action)
//            val lemonImage: ImageView = findViewById(R.id.image_lemon_state)
//            var stringsResource = R.string.lemon_select
//            var drawableResource = R.drawable.lemon_tree
//
//            when (lemonadeState) {
//                "select" -> {
//                    stringsResource = R.string.lemon_select
//                    drawableResource = R.drawable.lemon_tree
//                }
//                "squeeze" -> {
//                    stringsResource = R.string.lemon_squeeze
//                    drawableResource = R.drawable.lemon_squeeze
//                }
//                "drink" -> {
//                    stringsResource = R.string.lemon_drink
//                    drawableResource = R.drawable.lemon_drink
//                }
//                "restart" -> {
//                    stringsResource = R.string.lemon_empty_glass
//                    drawableResource = R.drawable.lemon_restart
//                }
//                else -> {
//                    stringsResource = R.string.lemon_select
//                    drawableResource = R.drawable.lemon_tree
//             }
//            }
//            textAction.setText(stringsResource)
//            lemonImage.setImageResource(drawableResource)

// My third approach
//    private fun setViewElements() {
//        val textAction: TextView = findViewById(R.id.text_action)
//        if (lemonadeState == SELECT) {
//            textAction.text = getString(R.string.lemon_select)
//            lemonImage?.setImageResource(R.drawable.lemon_tree)
//        } else if (lemonadeState == SQUEEZE) {
//            textAction.text = getString(R.string.lemon_squeeze)
//            lemonImage?.setImageResource(R.drawable.lemon_squeeze)
//        } else if (lemonadeState == DRINK) {
//            textAction.text = getString(R.string.lemon_drink)
//            lemonImage?.setImageResource(R.drawable.lemon_drink)
//        } else {
//            textAction.text = getString(R.string.lemon_empty_glass)
//            lemonImage?.setImageResource(R.drawable.lemon_restart)
//        }
//    }


    private fun setViewElements() {
        val textAction: TextView = findViewById(R.id.text_action)
        when (lemonadeState) {
            SELECT -> {
                textAction.text = getString(R.string.lemon_select)
                lemonImage?.setImageResource(R.drawable.lemon_tree)
            }

            SQUEEZE -> {
                textAction.text = getString(R.string.lemon_squeeze)
                lemonImage?.setImageResource(R.drawable.lemon_squeeze)
            }

            DRINK -> {
                textAction.text = getString(R.string.lemon_drink)
                lemonImage?.setImageResource(R.drawable.lemon_drink)
            }

            RESTART -> {
                textAction.text = getString(R.string.lemon_empty_glass)
                lemonImage?.setImageResource(R.drawable.lemon_restart)
            }
        }
    }

    /**
     * === DO NOT ALTER THIS METHOD ===
     * Long clicking the lemon image will show how many times the lemon has been squeezed.
     */
    private fun showSnackbar(): Boolean {
        if (lemonadeState != SQUEEZE) {
            return false
        }
        val squeezeText = getString(R.string.squeeze_count, squeezeCount)
        Snackbar.make(
            findViewById(R.id.constraint_Layout),
            squeezeText,
            Snackbar.LENGTH_SHORT
        ).show()
        return true
    }
}

/**
 * A Lemon tree class with a method to "pick" a lemon. The "size" of the lemon is randomized
 * and determines how many times a lemon needs to be squeezed before you get lemonade.
 */
  class LemonTree {
    fun pick(): Int {
        return (2..4).random()
    }
  }

/*
 * Copyright 2000-2017 Vaadin Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package com.example.karibudsl

import com.github.mvysny.karibudsl.v10.*
import com.github.mvysny.kaributools.setPrimary
import com.vaadin.flow.component.Component
import com.vaadin.flow.component.Key
import com.vaadin.flow.component.button.Button
import com.vaadin.flow.component.grid.Grid
import com.vaadin.flow.component.notification.Notification
import com.vaadin.flow.component.textfield.TextField
import com.vaadin.flow.router.Route
import org.vaddon.ElementMediaQuery
import org.vaddon.css.query.MediaQuery
import org.vaddon.css.query.values.WidthAttributes

/**
 * The main view contains a button and a click listener.
 */
@Route("")
class MainView : KComposite() {
    private lateinit var nameField: TextField
    private lateinit var greetButton: Button

    private lateinit var demoGrid: Grid<DemoData>

    // The main view UI definition
    private val root = ui {

        verticalLayout() {
            setSizeFull()

            demoGrid = grid {
                setSizeFull()
                columnFor(DemoData::text1)
                columnFor(DemoData::text2)

                setItems(listOf(
                    DemoData("Item 1.1", "Item 1.2"),
                    DemoData("Item 2.1", "Item 2.2")
                ))
            }
            this.add(getMediaQueries(demoGrid))
        }
    }

    private fun getMediaQueries(element: Component): List<ElementMediaQuery> {
        val elementMediaQueryMax720 = ElementMediaQuery { value ->

            if (value)
                adjustVisibleGridColumns(719)
            else
                adjustVisibleGridColumns(720)
        }
        elementMediaQueryMax720.setElement(element)
        elementMediaQueryMax720.setQuery(MediaQuery(WidthAttributes.MaxWidth("720px")))

        return listOf(elementMediaQueryMax720)
    }

    private fun adjustVisibleGridColumns(width: Int) {

        println("adjusting width: $width")
        // Change which columns are visible depending on browser width
        val visibleCols: BooleanArray = when {
            width < 720 -> booleanArrayOf(true, false)
            else -> booleanArrayOf(true, true)
        }

        for (c in visibleCols.indices) {
            demoGrid.getColumns()[c].isVisible = visibleCols[c]
        }
    }
}

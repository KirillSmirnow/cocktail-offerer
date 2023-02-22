import csstype.Display
import csstype.number
import csstype.px
import emotion.react.css
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input

external interface CocktailItemProps : Props {
    var cocktail: Cocktail
    var onRefresh: () -> Unit
}

val CocktailItem = FC<CocktailItemProps> { props ->
    div {
        div {
            +(props.cocktail.name + if (props.cocktail.cooked) " (cooked)" else "")
            css {
                flexGrow = number(1.0)
            }
        }
        input {
            type = InputType.checkbox
            checked = props.cocktail.available
            onChange = {
                GlobalScope.launch {
                    updateCocktail(props.cocktail, !props.cocktail.available)
                    props.onRefresh()
                }
            }
            css {
                margin = 1.px
            }
        }
        button {
            +"✏️"
            onClick = {
                val newName = window.prompt("Name", default = props.cocktail.name)
                if (newName != null) {
                    GlobalScope.launch {
                        updateCocktail(props.cocktail, newName)
                        props.onRefresh()
                    }
                }
            }
            css {
                margin = 1.px
            }
        }
        button {
            +"\uD83D\uDDD1"
            onClick = {
                val confirmed = window.confirm("Delete ${props.cocktail.name}?")
                if (confirmed) {
                    GlobalScope.launch {
                        deleteCocktail(props.cocktail.id)
                        props.onRefresh()
                    }
                }
            }
            css {
                margin = 1.px
            }
        }
        css {
            display = Display.flex
        }
    }
}

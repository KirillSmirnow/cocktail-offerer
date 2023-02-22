import csstype.Display
import csstype.number
import csstype.px
import emotion.react.css
import react.FC
import react.Props
import react.dom.html.InputType
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.input

external interface CocktailItemProps : Props {
    var cocktail: Cocktail
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
            css {
                margin = 1.px
            }
        }
        button {
            +"✏️"
            css {
                margin = 1.px
            }
        }
        button {
            +"\uD83D\uDDD1"
            css {
                margin = 1.px
            }
        }
        css {
            display = Display.flex
        }
    }
}

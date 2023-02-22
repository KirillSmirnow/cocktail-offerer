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
        +props.cocktail.name
    }
    input {
        type = InputType.checkbox
        checked = props.cocktail.available
    }
    button {
        +"Edit"
    }
}

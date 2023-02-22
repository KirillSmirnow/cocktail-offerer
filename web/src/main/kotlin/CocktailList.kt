import react.FC
import react.Props
import react.dom.html.ReactHTML.div

external interface CocktailListProps : Props {
    var cocktails: List<Cocktail>
}

val CocktailList = FC<CocktailListProps> { props ->
    for (cocktail in props.cocktails) {
        div {
            CocktailItem {
                this.cocktail = cocktail
            }
        }
    }
}

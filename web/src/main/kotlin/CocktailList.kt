import react.FC
import react.dom.html.ReactHTML.div

val CocktailList = FC<Nothing> {
    val cocktails = listOf(Cocktail(1, "c1", true, true))
    for (cocktail in cocktails) {
        div {
            CocktailItem {
                this.cocktail = cocktail
            }
        }
    }
}

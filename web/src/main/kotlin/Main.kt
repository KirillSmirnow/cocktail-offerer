import csstype.Auto
import csstype.pct
import csstype.px
import emotion.react.css
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.FC
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.hr
import react.useState

val Main = FC<Nothing> {
    var cocktailsFetched: Boolean by useState(false)
    var cocktails: List<Cocktail> by useState(emptyList())
    GlobalScope.launch {
        if (!cocktailsFetched) {
            cocktails = getCocktails()
            cocktailsFetched = true
        }
    }

    div {
        Header {
            this.cocktails = cocktails
            this.onRefresh = { cocktailsFetched = false }
        }
        hr {}
        CocktailList {
            this.cocktails = cocktails
            this.onRefresh = { cocktailsFetched = false }
        }
        css {
            width = 350.px
            height = 100.pct
            margin = Auto.auto
        }
    }
}

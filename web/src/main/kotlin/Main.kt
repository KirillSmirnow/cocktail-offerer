import csstype.Auto
import csstype.pct
import csstype.px
import emotion.react.css
import react.FC
import react.dom.html.ReactHTML.div
import react.dom.html.ReactHTML.hr

val Main = FC<Nothing> {
    div {
        div {
            +"Cocktails"
        }
        hr {}
        div {
            CocktailList {
            }
        }
        css {
            width = 500.px
            height = 100.pct
            margin = Auto.auto
        }
    }
}

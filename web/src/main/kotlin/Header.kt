import csstype.Display
import csstype.number
import csstype.px
import emotion.react.css
import kotlinx.browser.window
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import react.FC
import react.Props
import react.dom.html.ReactHTML.button
import react.dom.html.ReactHTML.div

external interface HeaderProps : Props {
    var cocktails: List<Cocktail>
    var onRefresh: () -> Unit
}

val Header = FC<HeaderProps> { props ->
    div {
        div {
            val available = props.cocktails.count { it.available }
            val total = props.cocktails.size
            +"Cocktails ($available/$total)"
            css {
                flexGrow = number(1.0)
            }
        }
        button {
            +"➕"
            onClick = {
                val name = window.prompt("Name")
                if (name != null) {
                    GlobalScope.launch {
                        createCocktail(name)
                        props.onRefresh()
                    }
                }
            }
        }
        css {
            display = Display.flex
            paddingTop = 5.px
        }
    }
}

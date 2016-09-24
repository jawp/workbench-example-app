package example
import scala.scalajs.js.annotation.JSExport
import org.scalajs.dom
import org.scalajs.dom.html
import org.scalajs.dom.html.{Div, LI, UList}

import scalatags.JsDom.TypedTag
import scalatags.JsDom.all._


object DB {

  sealed trait Status
  case object Landing extends Status
  case object Starting extends Status
  case class Flying(action: Action) extends Status
  case class Standing(action: Action) extends Status

  sealed trait Action
  case object Pecking extends Action //dziobać
  case object Shitting extends Action



  case class Pigeon(name: String, age: Int, status: Status)
  type PigeonId = Int

  var pigeons = Map[PigeonId, Pigeon](
    1 -> Pigeon("Lucy", 1, Landing),
    2 -> Pigeon("Bob", 2, Flying(Shitting)),
    3 -> Pigeon("Carmen", 2, Starting),
    4 -> Pigeon("Graham", 2, Standing(Pecking)),
    5 -> Pigeon("All", 2, Landing)
  )

  def removePigeon(): Unit = {
    pigeons = pigeons.tail
  }
}

@JSExport
object ScalaJSExample {

  @JSExport
  def main(mainDiv: html.Element): Unit = {

    val pigeonDiv = div().render
    mainDiv.innerHTML = ""
    mainDiv.appendChild(pigeonDiv)

    val killPigeonButton = button(
      onclick := {() =>
        DB.removePigeon()
        renderPigeons(pigeonDiv)
      },
      "kill pigeon"
    ).render

    renderPigeons(pigeonDiv)

    mainDiv.appendChild(killPigeonButton)
  }

  def renderPigeons(pigeonDiv: Div) = {
    dom.console.log("rendering")
    val pigeonsList: UList = ul(DB.pigeons.map { case (id, pigeon) =>
      li(pigeon.name) }.toSeq: _*).render

    pigeonDiv.innerHTML = ""
    pigeonDiv.appendChild(pigeonsList)
    pigeonDiv.appendChild(pigeonsList)
  }
}

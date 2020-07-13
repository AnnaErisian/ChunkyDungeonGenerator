package blue.thejester.cdg.viewer.main

import tornadofx.*

class CDGViewer: App(CDGView::class)

fun main(args: Array<String>) {
    launch<CDGViewer>(args)
}

// @GENERATOR:play-routes-compiler
// @SOURCE:/home/synerzip/Personal/poc/sale-purchase-service/conf/routes
// @DATE:Thu Mar 17 17:14:12 IST 2016


package router {
  object RoutesPrefix {
    private var _prefix: String = "/"
    def setPrefix(p: String): Unit = {
      _prefix = p
    }
    def prefix: String = _prefix
    val byNamePrefix: Function0[String] = { () => prefix }
  }
}

class SelcukFlixProvider : MainAPI() {
    override var mainUrl = "https://selcukflix.com"
    override var name = "SelcukFlix"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    override val lang = "tr"
}

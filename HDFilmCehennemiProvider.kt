class HDFilmCehennemiProvider : MainAPI() {
    override var mainUrl = "https://hdfilmcehennemi.com"
    override var name = "HDFilmCehennemi"
    override val supportedTypes = setOf(TvType.Movie, TvType.TvSeries)

    override val lang = "tr"
}

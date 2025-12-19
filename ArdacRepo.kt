import com.lagradost.cloudstream3.*
import com.lagradost.cloudstream3.utils.*

class ArdacRepo : MainAPI() {

    override var name = "ArdacRepo"
    override var mainUrl = "https://hdfilmcehennemi.com"
    override val lang = "tr"

    override val supportedTypes = setOf(
        TvType.Movie,
        TvType.TvSeries
    )

    override suspend fun getMainPage(
        page: Int,
        request: MainPageRequest
    ): HomePageResponse {

        val doc = app.get(mainUrl).document

        val items = doc.select(".film-item").mapNotNull {
            val title = it.select("h2").text()
            val link = it.select("a").attr("href")
            val poster = it.select("img").attr("src")

            if (title.isEmpty() || link.isEmpty()) return@mapNotNull null

            newMovieSearchResponse(title, link) {
                this.posterUrl = poster
            }
        }

        return HomePageResponse(
            listOf(
                HomePageList(
                    "Yeni Eklenenler",
                    items
                )
            )
        )
    }

    override suspend fun load(url: String): LoadResponse {
        val doc = app.get(url).document

        val title = doc.selectFirst("h1")?.text() ?: "Bilinmeyen"
        val poster = doc.selectFirst("img")?.attr("src")

        return newMovieLoadResponse(title, url, TvType.Movie) {
            this.posterUrl = poster
        }
    }

    override suspend fun loadLinks(
        data: String,
        isCasting: Boolean,
        subtitleCallback: (SubtitleFile) -> Unit,
        callback: (ExtractorLink) -> Unit
    ) {
        val doc = app.get(data).document

        // iframe veya video source bulur
        val iframe = doc.selectFirst("iframe")?.attr("src") ?: return

        loadExtractor(
            iframe,
            data,
            subtitleCallback,
            callback
        )
    }
}

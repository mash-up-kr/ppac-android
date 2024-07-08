package team.ppac.detail.model

import kotlinx.collections.immutable.ImmutableList

data class DetailMemeUiModel(
    val imageUrl: String = TEST_IMAGE_URL,
    val name: String,
    val hashTags: ImmutableList<String>,
    val sourceDescription: String,
) {
    companion object {
        //Todo 서버에서 이미지 로드 url이 에러가있어서 임시로 url로 대체한건데 수정해야함
        const val TEST_IMAGE_URL =
            "https://s3-alpha-sig.figma.com/img/2215/ecea/fcea7f8dbec74e16f56675f756edb8b5?Expires=1720396800&Key-Pair-Id=APKAQ4GOSFWCVNEHN3O4&Signature=P8TH9OlXy0-7cZDofJLFdsRUNDhXsiEaeRgPKZSvHAQM8jNRMRulpsz7s1cvBuQWRvoB1vfYpY1eGZdRyPGgZEWafyvNXDh9GxJS2n~PBNgGSxGy10c2uRU1OmOxE5hwMlci6BGGvLamKTO1LZ4A4yPEaCwPtVyZswWFbAXYdCSUMidW0zp94EDCCCcFPmOp0Un6usY7AsZ18bchDMY-iQmqSG9V8dqyQELHKhJefdt0pqBdiCw3wPjovNp3KfwUy4hnV4s9rKmv4P5YZkALvTsurix~U5TjXR9GpDFeTPMlgLRXS86sd1yPnTk0Ajs9kEacCNCxQHb7QBpt1hrKcg__"
    }
}

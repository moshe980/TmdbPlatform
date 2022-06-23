
package com.giniapps.tmdbplatform.model.response

import java.util.*

data class MediaCategory(val id: UUID, val name: String, var list: List<TmdbItem>)
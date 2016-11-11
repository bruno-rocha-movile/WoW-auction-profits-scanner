package hello

/**
 * Created by bruno.rocha on 11/11/16.
 */

data class WowItem (
        val id: Long,
        val name: String,
        val formula: List<Long> = listOf()
)
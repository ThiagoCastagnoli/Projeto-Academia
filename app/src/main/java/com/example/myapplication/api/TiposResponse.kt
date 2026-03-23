
data class TiposResponse (
    val success: Boolean,
    val data: List<Tipos>,
)

data class Tipos(
    val id: String,
    val titulo: String,
    val descricao: String,
    val iconName: String,
)

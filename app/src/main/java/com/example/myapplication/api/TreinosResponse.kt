
data class TreinosResponse(
    val success: Boolean,
    val data: List<Treino>,
)

data class Treino(
    val id: Long,
    val tipo: Tipo,
    val titulo: String,
    val descricao: String,
    val data: String,
    val visibilidade: String,
)

data class Tipo(
    val id: Long,
    val titulo: String,

)
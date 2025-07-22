import com.github.cecnull1.cecnull1lib.utils.vector.KVec3
import com.github.cecnull1.cecnull1lib.utils.vector.toBlockPos
import com.github.cecnull1.cecnull1lib.utils.vector.toVec3
import java.math.BigDecimal

fun main() {
    val vec3 = KVec3(1.0, 2.0, 3.0)
    println(vec3)
    println(vec3.toVec3())
    println(vec3.toBlockPos())
    val number = BigDecimal("1.45")
    println(number)
}
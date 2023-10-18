import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
	val now: LocalDateTime? = null
	
	/// 이렇게 추가적으로 들어가야 할 로직이 없을 때는
	/// run을 사용하지 않고 한 줄로 간결하게 표현가능
	val n = now?.let {
		it
	}?: LocalDateTime.now()
	
	/// 이렇게 뭔가 여러 줄 이상의 로직이 들어가야 할 때는
	/// run을 사용해서 scope를 만들어준다
	val n2 = now?.let {
		val minusDay = it.minusDays(1)
		minusDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	}?: run {
		val minusDay = LocalDateTime.now().minusDays(1)
		minusDay.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
	}
	
	println(n)
}

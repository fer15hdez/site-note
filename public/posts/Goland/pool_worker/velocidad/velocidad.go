package velocidad

func GetVelocidadFactor(m int) int {

	switch m {
	case 1:
		return m * 10
	case 2:
		return m * 20
	case 3:
		return m * 30
	case 4:
		return m * 40
	default:
		return 0

	}
}

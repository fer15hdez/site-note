package velocidad

import (
	"testing"
)

func TestGetVelocidadFactor(t *testing.T) {
	test := []struct {
		marcha   int
		esperado int
	}{
		{marcha: 1, esperado: 10},
		{marcha: 2, esperado: 2 * 20},
		{marcha: 3, esperado: 3 * 30},
		{marcha: 4, esperado: 4 * 40},
	}

	for _, value := range test {
		result := GetVelocidadFactor(value.marcha)

		if result != value.esperado {
			t.Errorf("Esperaba %d, obtuve %d", value.esperado, result)
		}
	}

}

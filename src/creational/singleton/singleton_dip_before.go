/*
	Singleton is a creational design pattern that lets you ensure that a class has only one instance,
	while providing a global access point to this instance.

	Don't depend directly on singleton.
	Depend on some interface that singleton implements
*/

package singleton

import (
	"fmt"
	"sync"
)

var dipBeforeOnce sync.Once

type liveDatabaseEx struct {
	capitals map[string]int
}

func (db *liveDatabaseEx) getPopulation(cityName string) int {
	return db.capitals[cityName]
}

var liveDatabaseExInstance *liveDatabaseEx

func getLiveDatabaseExInstance() *liveDatabaseEx {
	if liveDatabaseExInstance == nil {
		dipBeforeOnce.Do(
			func() {
				liveDatabaseExInstance = &liveDatabaseEx{
					capitals: map[string]int{
						"Jakarta":  1000,
						"Bandung":  950,
						"Surabaya": 900,
					},
				}
			},
		)
	}
	return liveDatabaseExInstance
}

func getTotalPopulationEx(cities []string) int {
	result := 0
	for _, city := range cities {
		result += getLiveDatabaseExInstance().getPopulation(city) // this line violate DIP
	}
	return result
}

func DipBeforeExample() {
	cities := []string{"Jakarta", "Surabaya"}
	totalPopulation := getTotalPopulationEx(cities)
	ok := totalPopulation == (1000 + 900)
	if ok {
		fmt.Println("getTotalPopulationEx() is CORRECT")
	} else {
		fmt.Println("getTotalPopulationEx() is FALSE")
	}
}

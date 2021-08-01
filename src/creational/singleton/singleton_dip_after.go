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

var dipAfterOnceLive sync.Once
var dipAfterOnceMock sync.Once

type database interface {
	getPopulation(cityName string) int
}

type liveDatabase struct {
	capitals map[string]int
}

func (db *liveDatabase) getPopulation(cityName string) int {
	return db.capitals[cityName]
}

var liveDatabaseInstance *liveDatabase

func GetLiveDatabaseInstance() *liveDatabase {
	if liveDatabaseInstance == nil {
		dipAfterOnceLive.Do(
			func() {
				liveDatabaseInstance = &liveDatabase{
					capitals: map[string]int{
						"Jakarta":  1000,
						"Bandung":  950,
						"Surabaya": 900,
					},
				}
			},
		)
	}
	return liveDatabaseInstance
}

type mockedDatabase struct {
	capitals map[string]int
}

func (db *mockedDatabase) getPopulation(city string) int {
	return db.capitals[city]
}

var mockedDatabaseInstance *mockedDatabase

func getMockedDatabaseInstance() *mockedDatabase{
	if mockedDatabaseInstance == nil {
		dipAfterOnceMock.Do(
			func() {
				mockedDatabaseInstance = &mockedDatabase{
					capitals: map[string]int{
						"City A": 1,
						"City B": 2,
						"City C": 3,
					},
				}
			},
		)
	}
	return mockedDatabaseInstance
}

type ResearchPopulationInterface interface {
	getTotalPopulation(cities []string) int
}

type researchPopulation struct {
	db database
}

func (rp *researchPopulation) getTotalPopulation(cities []string) int {
	result := 0
	for _, city := range cities {
		result += rp.db.getPopulation(city) // this does not violate DIP because using abstraction
	}
	return result
}

func DipAfterExample() {
	liveCities := []string{"Jakarta", "Surabaya"}
	dbLive := GetLiveDatabaseInstance()
	rpLive := researchPopulation{db: dbLive}
	totalLivePopulation := rpLive.getTotalPopulation(liveCities)
	expectedLive := 1000 + 900
	if totalLivePopulation == expectedLive {
		fmt.Println("getTotalPopulation() using live db is CORRECT")
	} else {
		fmt.Println("getTotalPopulation() using live db is FALSE")
	}

	cities := []string{"City A", "City C"}
	db := getMockedDatabaseInstance()
	rp := researchPopulation{db: db}
	totalPopulation := rp.getTotalPopulation(cities)
	ok := totalPopulation == (1 + 3)
	if ok {
		fmt.Println("getTotalPopulation() using mocked db is CORRECT")
	} else {
		fmt.Println("getTotalPopulation() using mocked db is FALSE")
	}
}

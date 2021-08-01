package main

import (
	"fmt"

	"github.com/akbarbudiman/personal-designpattern/src/behavioral/strategy"
	"github.com/akbarbudiman/personal-designpattern/src/creational/singleton"
)

func main() {
	fmt.Println("Example of Strategy Design Pattern. Before:")
	strategy.StrategyBeforeExample()
	fmt.Println("Example of Strategy Design Pattern. After:")
	strategy.StrategyAfterExample()

	fmt.Println("Example of Singleton")
	singleton.Example()
}

package main

import (
	"fmt"
	"github.com/akbarbudiman/personal-designpattern/src/behavioral/strategy"

	"github.com/akbarbudiman/personal-designpattern/src/creational/singleton"
)

func main() {
	printTitle("Example of Strategy Design Pattern. Before:")
	strategy.StrategyBeforeExample()
	printTitle("Example of Strategy Design Pattern. After:")
	strategy.StrategyAfterExample()

	printTitle("Example of Singleton")
	singleton.Example()
	printTitle("Example of Singleton with DIP violation")
	singleton.DipBeforeExample()
	printTitle("Example of Singleton with correct DIP implementation")
	singleton.DipAfterExample()
}

func printTitle(title string) {
	fmt.Println("===", title, "===")
}

package main

import (
	"fmt"
	"github.com/akbarbudiman/personal-designpattern/src/structural/decorator"
)

func main() {
	//printTitle("Example of Singleton")
	//singleton.Example()
	//printTitle("Example of Singleton with DIP violation")
	//singleton.DipBeforeExample()
	//printTitle("Example of Singleton with correct DIP implementation")
	//singleton.DipAfterExample()
	//
	//printTitle("Example of Adapter")
	//adapter.Example()
	printTitle("Example of Decorator")
	decorator.Example()


	//printTitle("Example of Strategy Design Pattern. Before:")
	//strategy.StrategyBeforeExample()
	//printTitle("Example of Strategy Design Pattern. After:")
	//strategy.StrategyAfterExample()
}

func printTitle(title string) {
	fmt.Println("===", title, "===")
}

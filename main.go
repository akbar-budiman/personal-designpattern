package main

import (
	"fmt"
	"strings"

	"github.com/akbarbudiman/personal-designpattern/src/behavioral/strategy"
)

func main() {
	fmt.Println("Example of Strategy Design Pattern. Before:")
	strategy.StrategyBeforeExample()
	fmt.Println("Example of Strategy Design Pattern. After:")
	strategy.StrategyAfterExample()

	b := &strings.Builder{}
	b.WriteString("a")
	b.WriteString("b")
	fmt.Println(b.String())
}

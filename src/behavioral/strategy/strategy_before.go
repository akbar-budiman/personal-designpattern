package strategy

import (
	"fmt"
	"strings"
)

type Format int

const (
	Html     Format = iota
	Markdown Format = iota
)

type listBuilder struct {
	builder *strings.Builder
}

func (lb *listBuilder) create(lang Format, listName string, items []string) {
	lb.builder.WriteString(listName + " :\n")
	switch lang {
	case Html:
		lb.builder.WriteString("<ul>\n")
		for _, item := range items {
			lb.builder.WriteString("  <li>" + item + "</li>\n")
		}
		lb.builder.WriteString("</ul>\n")
	case Markdown:
		for _, item := range items {
			lb.builder.WriteString(" * " + item + "\n")
		}
	}
}

func (lb *listBuilder) String() string {
	return lb.builder.String()
}

func (lb *listBuilder) reset() {
	lb.builder.Reset()
}

func StrategyBeforeExample() {
	var listName string
	var items []string = []string{"foo", "bar", "baz"}

	listName = "myList in html"
	var lb *listBuilder = &listBuilder{&strings.Builder{}}
	lb.create(Html, listName, items)
	fmt.Println(lb)

	listName = "myList in markdown"
	lb.reset()
	lb.create(Markdown, listName, items)
	fmt.Println(lb)
}

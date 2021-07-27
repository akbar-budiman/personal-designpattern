package strategy

import (
	"fmt"
	"strings"
)

type strategy interface {
	createPreItems(builder *strings.Builder)
	createPostItems(builder *strings.Builder)
	createItems(builder *strings.Builder, items []string)
}

type htmlStrategy struct {
}

func (*htmlStrategy) createPreItems(builder *strings.Builder) {
	builder.WriteString("<ul>\n")
}

func (*htmlStrategy) createPostItems(builder *strings.Builder) {
	builder.WriteString("</ul>\n")
}

func (*htmlStrategy) createItems(b *strings.Builder, items []string) {
	for _, item := range items {
		b.WriteString("  <li>" + item + "</li>\n")
	}
}

type markdownStrategy struct {
}

func (*markdownStrategy) createPreItems(_ *strings.Builder) {
}

func (*markdownStrategy) createPostItems(_ *strings.Builder) {
}

func (*markdownStrategy) createItems(b *strings.Builder, items []string) {
	for _, item := range items {
		b.WriteString(" * " + item + "\n")
	}
}

var (
	htmlStrategyInstance     = &htmlStrategy{}
	markdownStrategyInstance = &markdownStrategy{}
)

type contextListBuilder struct {
	builder  *strings.Builder
	strategy strategy
}

func newContextListBuilder(strategy strategy) *contextListBuilder {
	context := contextListBuilder{&strings.Builder{}, strategy}
	return &context
}

func (context *contextListBuilder) setStrategy(strategy strategy) {
	context.strategy = strategy
}

func (context *contextListBuilder) create(listName string, items []string) {
	context.builder.WriteString(listName + " :\n")
	context.strategy.createPreItems(context.builder)
	context.strategy.createItems(context.builder, items)
	context.strategy.createPostItems(context.builder)
}

func (context *contextListBuilder) reset() {
	context.builder.Reset()
}

func (context *contextListBuilder) String() string {
	return context.builder.String()
}

func StrategyAfterExample() {
	var formats []Format = []Format{Html, Markdown}
	var listName []string = []string{"myList in html", "myList in markdown"}
	var items []string = []string{"foo", "bar", "baz"}

	var lb = newContextListBuilder(nil)
	for i := 0; i < len(formats); i = i + 1 {
		lb.reset()
		switch formats[i] {
		case Html:
			lb.setStrategy(htmlStrategyInstance)
		case Markdown:
			lb.setStrategy(markdownStrategyInstance)
		}
		lb.create(listName[i], items)
		fmt.Println(lb)
	}
}

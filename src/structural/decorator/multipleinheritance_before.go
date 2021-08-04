package decorator

import "fmt"

type BirdEx struct {
	Age int
}

func (b *BirdEx) Fly() {
	if b.Age >= 10 {
		fmt.Println("Flying!")
	}
}

type LizardEx struct {
	Age int
}

func (l *LizardEx) Crawl() {
	if l.Age < 10 {
		fmt.Println("Crawling!")
	}
}

type DragonEx struct {
	BirdEx
	LizardEx
}

func MultipleInheritanceBeforeExample() {
	d := DragonEx{}
	d.BirdEx.Age = 10
	fmt.Println(d.LizardEx.Age)
	d.Fly()
	d.Crawl()
}

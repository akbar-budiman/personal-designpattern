package decorator

import "fmt"

type Shape interface {
	Render() string
}

type Circle struct {
	Radius float32
}

func (c *Circle) Render() string {
	return fmt.Sprintf("Circle of radius %f",
		c.Radius)
}

func (c *Circle) Resize(factor float32) {
	c.Radius *= factor
}

type Square struct {
	Side float32
}

func (s *Square) Render() string {
	return fmt.Sprintf("Square with side %f", s.Side)
}

type ColoredShape struct {
	Shape Shape
	Color string
}

func (c *ColoredShape) Render() string {
	return fmt.Sprintf("%s has color %s",
		c.Shape.Render(), c.Color)
}

type TransparentShape struct {
	Shape Shape
	Transparency float32
}

func (t *TransparentShape) Render() string {
	return fmt.Sprintf("%s has transparency %f%%",
		t.Shape.Render(), t.Transparency * 100.0)
}

type ClientApplication struct {
	request *request
}

func (c *ClientApplication) setRequest(req *request) *ClientApplication{
	c.request = req
	return c
}

func (c *ClientApplication) processRender() string {
	shape := c.request.basicShape
	if c.request.color.val != "" {
		shape = &ColoredShape{Shape:shape, Color: c.request.color.val}
	}
	if c.request.transparency.val != 0 {
		shape = &TransparentShape{Shape: shape, Transparency: c.request.transparency.val}
	}
	return shape.Render()
}

func Example() {
	client := &ClientApplication{}

	firstRequest := &request{
		basicShape: &Circle{Radius: 7},
		color: &colorRequest{},
		transparency: &transparencyRequest{},
	}
	firstRequestResult := client.setRequest(firstRequest).processRender()
	fmt.Println("firstRequestResult:", firstRequestResult)

	secondRequest := &request{
		basicShape: &Square{Side: 10},
		color: &colorRequest{val: "red"},
		transparency: &transparencyRequest{},
	}
	secondRequestResult := client.setRequest(secondRequest).processRender()
	fmt.Println("secondRequestResult:", secondRequestResult)

	thirdRequest := &request{
		basicShape: &Circle{Radius: 14},
		color: &colorRequest{val: "green"},
		transparency: &transparencyRequest{val: 0.25},
	}
	thirdRequestResult := client.setRequest(thirdRequest).processRender()
	fmt.Println("thirdRequestResult:", thirdRequestResult)

	forthRequest := &request{
		basicShape: &Square{Side: 20},
		color: &colorRequest{},
		transparency: &transparencyRequest{val: 0.75},
	}
	forthRequestResult := client.setRequest(forthRequest).processRender()
	fmt.Println("forthRequestResult:", forthRequestResult)

	circle := &Circle{2}
	fmt.Println("simple circle:", circle.Render())

	redCircle := &ColoredShape{circle, "Red"}
	fmt.Println("colored circle:", redCircle.Render())

	rhsCircle := TransparentShape{redCircle, 0.5}
	fmt.Println("transparent colored circle:", rhsCircle.Render())
}
package decorator

import "fmt"

type ShapeEx interface {
	Render() string
}

type CircleEx struct {
	Radius float32
}

func (c *CircleEx) Render() string {
	return fmt.Sprintf("Circle of radius %f",
		c.Radius)
}

func (c *CircleEx) Resize(factor float32) {
	c.Radius *= factor
}

type SquareEx struct {
	Side float32
}

func (s *SquareEx) Render() string {
	return fmt.Sprintf("Square with side %f", s.Side)
}

type ColoredCircle struct {
	Circle CircleEx
	Color  string
}

func (c *ColoredCircle) Render() string {
	return fmt.Sprintf("%f has color %f",
		c.Circle.Render(), c.Color)
}

type ColoredSquare struct {
	Square SquareEx
	Color  string
}

func (s *ColoredSquare) Render() string {
	return fmt.Sprintf("%f has color %f",
		s.Square.Render(), s.Color)
}

type TransparentCircle struct {
	Circle       CircleEx
	Transparency float32
}

func (c *TransparentCircle) Render() string {
	return fmt.Sprintf("%f has transparency %f",
		c.Circle.Render(), c.Transparency)
}

type TransparentSquare struct {
	Square       SquareEx
	Transparency float32
}

func (s *TransparentSquare) Render() string {
	return fmt.Sprintf("%f has transparency %f",
		s.Square.Render(), s.Transparency)
}

type ColoredTransparentCircle struct {
	Circle       CircleEx
	Color        string
	Transparency float32
}

func (c *ColoredTransparentCircle) Render() string {
	return fmt.Sprintf("%f color %f has transparency %f",
		c.Circle.Render(), c.Color, c.Transparency)
}

type ColoredTransparentSquare struct {
	Square       SquareEx
	Color        string
	Transparency float32
}

func (s *ColoredTransparentSquare) Render() string {
	return fmt.Sprintf("%f has color %f has transparency %f",
		s.Square.Render(), s.Color, s.Transparency)
}

/*
Adapter is a structural design pattern, which allows incompatible objects to collaborate.

Given : holeCheckerInClient that willing to keep unchanged,
also featured with roundObject interface and circle struct.
Desired : new struct square introduced, and needs to support holeCheckerInClient.
Solution : create adapter for square to roundObject
 */

package adapter

import (
	"fmt"
	"math"
)

type holeCheckerInClient struct {
	holeRadius float64
}

func (h *holeCheckerInClient) isEnough(obj roundObject) bool {
	return h.holeRadius <= obj.getRadius()
}

type roundObject interface {
	getRadius() float64
}

type circle struct {
	radius float64
}

func (c *circle) getRadius() float64 {
	return c.radius
}

type square struct {
	width float64
}

type squareToCircleAdapter struct {
	square *square
	radius float64
}

func (stcAdapter *squareToCircleAdapter) getRadius() float64 {
	if stcAdapter.radius == 0 {
		stcAdapter.radius = stcAdapter.square.width * math.Sqrt(2)
	}
	return stcAdapter.radius
}

var cachedRoundObjectFromSquare = map[float64]roundObject {}

func squareToRoundObject(square *square) roundObject {
	if obj, exist := cachedRoundObjectFromSquare[square.width] ; exist {
		fmt.Println("reuse square with width:", square.width)
		return obj
	} else {
		fmt.Println("create new adapter for square with width:", square.width)
		newObj := &squareToCircleAdapter{square: square}
		cachedRoundObjectFromSquare[square.width] = newObj
		return cachedRoundObjectFromSquare[square.width]
	}
}

func Example() {
	holeChecker := &holeCheckerInClient{holeRadius: 10}

	circle := &circle{radius: 14}
	isCircleEnough := holeChecker.isEnough(circle)
	fmt.Println("isCircleEnough:", isCircleEnough)

	squareOne := &square{width: 10}
	squareOneAdapter := squareToRoundObject(squareOne)
	isSquareOneEnough := holeChecker.isEnough(squareOneAdapter)
	fmt.Println("isSquareOneEnough:", isSquareOneEnough)

	squareTwo := &square{width: 10}
	squareTwoAdapter := squareToRoundObject(squareTwo)
	isSquareTwoEnough := holeChecker.isEnough(squareTwoAdapter)
	fmt.Println("isSquareTwoEnough:", isSquareTwoEnough)
}
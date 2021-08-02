package decorator

type colorRequest struct {
	val string
}

type transparencyRequest struct {
	val float32
}

type request struct {
	basicShape   Shape
	color        *colorRequest
	transparency *transparencyRequest
}

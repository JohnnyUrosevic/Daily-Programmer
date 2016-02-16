package main

import (
	"fmt"
	"strings"
)

func main() {
	fmt.Println(atbash("foobar"))
	fmt.Println(atbash("wizard"))
	fmt.Println(atbash("/r/dailyprogrammer"))
	fmt.Println(atbash("gsrh rh zm vcznkov lu gsv zgyzhs xrksvi"))
}

func atbash(s string) string {
	return strings.Map(atbashRune, s)
}

func atbashRune(r rune) rune {
	switch {
	case 'a' <= r && r <= 'z':
		return 'z' - r + 'a'
	case 'A' <= r && r <= 'Z':
		return 'Z' - r + 'A'
	default:
		return r
	}
}

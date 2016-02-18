package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

func main() {
	player, board := readInput("254 Intermediate/input.text")
	fmt.Println(board)
	fmt.Println(player)
}

func readInput(path string) (string, []string) {
	content, err := ioutil.ReadFile(path)
	if err != nil {
		panic(err)
	}
	lines := strings.Split(string(content), "\n")

	return lines[0], lines[1:]
}

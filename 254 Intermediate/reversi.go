package main

import (
	"fmt"
	"io/ioutil"
	"strings"
)

const (
	size = 8
)

type position struct {
	row int
	col int
}

type direction struct {
	dr int
	dc int
}

func main() {
	player, board := readInput("254 Intermediate/input.text")

	var moves []position

	legalMoves := 0

	directions := make([]direction, 8)
	directions[0] = direction{1, 0}
	directions[1] = direction{-1, 0}
	directions[2] = direction{0, 1}
	directions[3] = direction{0, -1}
	directions[4] = direction{1, 1}
	directions[5] = direction{1, -1}
	directions[6] = direction{-1, 1}
	directions[7] = direction{-1, -1}

	//calculate moves
	for i, row := range board {
		for j := 0; j < len(row); j++ {
			char := string(row[j])

			if char != player {
				continue
			}

			for _, dir := range directions {
				p := checkDir(board, player, i, j, dir.dr, dir.dc)

				if p.row != -1 && !contains(moves, p) {
					moves = append(moves, p)
				}
			}

		}
	}

	for _, point := range moves {
		fmt.Println(point)

		row := []byte(board[point.row])
		row[point.col] = '*'
		board[point.row] = string(row)

		legalMoves++
	}

	//print output
	fmt.Printf("%d legal moves for %s", legalMoves, player)
	fmt.Println()

	for _, row := range board {
		fmt.Println(row)
	}
}

func checkDir(board []string, player string, r, c, dr, dc int) position {
	checking := false

	for {
		r += dr
		c += dc

		if r >= size || c >= size || r < 0 || c < 0 {
			return position{-1, -1}
		}

		if !checking {
			switch string(board[r][c]) {
			case "-", player:
				return position{-1, -1}
			default:
				checking = true
			}
		} else {
			switch string(board[r][c]) {
			case player:
				return position{-1, -1}
			case "-":
				return position{r, c}
			}
		}
	}
}

func contains(s []position, e position) bool {
	for _, a := range s {
		if a.row == e.row && a.col == e.col {
			return true
		}
	}
	return false
}

func readInput(path string) (string, []string) {
	content, err := ioutil.ReadFile(path)
	if err != nil {
		panic(err)
	}
	lines := strings.Split(string(content), "\n")

	return strings.TrimSpace(lines[0]), lines[1:]
}

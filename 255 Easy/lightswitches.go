package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	fmt.Println(flipSwitches("255 Easy/input.txt"))
	fmt.Println(flipSwitches("255 Easy/input2.txt"))
}

func flipSwitches(path string) int {
	file, err := os.Open(path)
	if err != nil {
		fmt.Println(err)
	}
	defer file.Close()

	scanner := bufio.NewScanner(file)

	_ = scanner.Scan()
	numSwitches, _ := strconv.ParseUint(scanner.Text(), 0, 64)
	lights := make([]bool, numSwitches)

	for scanner.Scan() {
		switchRange := strings.Split(scanner.Text(), " ")

		start, _ := strconv.ParseInt(switchRange[0], 0, 64)
		end, _ := strconv.ParseInt(switchRange[1], 0, 64)
		if start > end {
			temp := start
			start = end
			end = temp
		}

		for i := start; i <= end; i++ {
			lights[i] = !lights[i]
		}
	}

	count := 0
	for _, value := range lights {
		if value {
			count++
		}
	}
	return count
}

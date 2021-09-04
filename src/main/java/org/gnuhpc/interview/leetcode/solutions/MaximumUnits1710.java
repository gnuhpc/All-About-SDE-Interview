package org.gnuhpc.interview.leetcode.solutions;

public class MaximumUnits1710 {
    /*
    func maximumUnits(boxTypes [][]int, truckSize int) int {
    sum := 0

	//这里是按 `numberOfUnitsPerBox` 降序，优先使用大的装载单元数量
	sort.Slice(boxTypes, func(i int , j int) bool {
		return boxTypes[i][1] > boxTypes[j][1]
	})

	//遍历 `boxTypes`，直到达到 `truckSize` 停止
	for i := 0; i < len(boxTypes); i++ {
		if boxTypes[i][0] < truckSize {
			truckSize -= boxTypes[i][0]
			sum += boxTypes[i][0] * boxTypes[i][1]
		} else {
			sum += truckSize * boxTypes[i][1]
			break
		}
	}

	return sum
}
     */
}

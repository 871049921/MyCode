#include <stdio.h>
#include <stdlib.h>

int calculateTime(int currentFloor, int *num, int length);

int main() {
    int n;
    int totalTime = 0;
    scanf("%d", &n);
    int *a = (int *)malloc(sizeof(int) * n);
    for(int i = 0; i < n; i ++) {
        int num;
        scanf("%d", &num);
        a[i] = num;
    }
    totalTime = calculateTime(0, a, n);
    printf("%d", totalTime);
}

int calculateTime(int currentFloor, int *num, int n) {
    int totalTime = 0;
    for(int i = 0; i < n; i ++) {
        if(currentFloor < num[i]) {
            totalTime += (num[i] - currentFloor) * 6 + 5;
        } else if(currentFloor == num[i]) {
            totalTime += 5;
        } else if(currentFloor > num[i]) {
            totalTime += (currentFloor - num[i]) * 4 + 5;
        }
        currentFloor = num[i];
    }
    return totalTime;
}
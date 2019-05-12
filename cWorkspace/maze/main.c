typedef struct {
    int x;
    int y;
    int pre_x;
    int pre_y;
} QElemType, SElemType;

#include <stdio.h>
#include "Queue.h"
#include <time.h>
#include <stdlib.h>
#include <math.h>
#include "Stack.h"

int directions[8][2] = {{0,1},{-1,1},{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1}};
SqQueue s, s2;

void InitMaze(int ***, int, int);// 初始化迷宫
void DealMaze(int **, int, int);// 处理迷宫
void CopyMaze(int **, int ***, int, int);// 复制一个迷宫
void PrintMaze(int **, int, int);// 打印迷宫
void FindEntrance(int **, int, int, int *);// 找到入口
int FindExport(int **, int **, int, int);// 找到出口
void CreateElem(QElemType *, int, int, int, int);// 创建元素
void PrintTrace(int **, int, int);// 打印路径

int maze1[7][8] = {
    {1,1,1,1,1,1,1,1},
    {1,2,1,1,1,1,1,1},
    {1,1,0,1,1,1,1,1},
    {1,1,0,0,1,1,1,1},
    {1,1,0,0,0,0,1,1},
    {1,1,0,1,1,0,3,1},
    {1,1,1,1,1,1,1,1},
};

int maze2[7][8] = {
        {1,1,1,1,1,1,1,1},
        {1,2,1,1,1,1,1,1},
        {1,1,0,1,1,1,1,1},
        {1,1,0,0,1,1,1,1},
        {1,1,0,0,1,0,1,1},
        {1,1,0,1,1,0,3,1},
        {1,1,1,1,1,1,1,1},
};

int main() {
    srand(time(NULL));
    int row = rand() % 10 + 7;
    int col = rand() % 10 + 8;
    int **maze;
    int *p[7], *q[7];
    for(int i = 0; i < 7; i ++) {
        p[i] = maze1[i];
        q[i] = maze2[i];
    }
    DealMaze(p, 7, 8);
    DealMaze(q, 7, 8);
    InitMaze(&maze, row, col);
    PrintMaze(maze, row, col);
    DealMaze(maze, row, col);
    system("pause");
    return 0;
}

void InitMaze(int ***maze, int row, int col) {
    *maze = (int **)malloc(sizeof(int *) * row);

    for(int i = 0; i < row; i ++) {
        *(*maze + i) = (int *)malloc(sizeof(int) * col);
    }

    for(int i = 0; i < row; i ++) {
        for(int j = 0; j < col; j ++) {
            if(i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                *(*(*maze + i) + j) = 1;
            } else {
                int c = rand()%2;
                *(*(*maze + i) + j) = (c == 1) ?  0 : 1;
            }
        }
    }

    *(*(*maze + 1) + 1) = 2;
    *(*(*maze + row - 2) + col - 2) = 3;
    printf("出口是：<%d,%d>\n", row - 2, col - 2);
}

void DealMaze(int **maze, int row, int col) {
    int **copyMaze;
    int entrance[2];
    int flag;
    InitQueue(&s);
    InitQueue(&s2);
    CopyMaze(maze, &copyMaze, row, col);
    FindEntrance(maze, row, col, entrance);
    QElemType q;
    CreateElem(&q, entrance[0], entrance[1], -1, -1);
    EnQueue(&s, q);
    flag = FindExport(maze, copyMaze, entrance[0], entrance[1]);
    printf("\n");
    if(flag == 1) {
        PrintTrace(maze, row, col);
        printf("有路径!\n");
    } else {
        printf("没有路径!\n");
    }
}

void CopyMaze(int **maze, int ***copyMaze, int row, int col) {
    *copyMaze = (int **)malloc(sizeof(int *) * row);

    for(int i = 0; i < row; i ++) {
        *(*copyMaze + i) = (int *)malloc(sizeof(int) * col);
    }


    for(int i = 0; i < row; i ++) {
        for(int j = 0; j < col; j ++) {
            *(*(*copyMaze + i) + j) = maze[i][j];
        }
    }
}

void FindEntrance(int **maze, int row, int col, int *entrance) {// 找到入口
    for(int i = 0; i < row; i ++) {
        for(int j = 0; j < col; j ++) {
            if(maze[i][j] == 2) {
                entrance[0] = i;
                entrance[1] = j;
                return;
            }
        }
    }
}

int FindExport(int **maze, int **copyMaze, int x, int y) {// 找到出口
    QElemType e;
    while(s.rear != s.font) {
        DeQueue(&s, &e);
        EnQueue(&s2, e);
        x = e.x;
        y = e.y;
        for(int i = 0; i < 8; i ++) {
            if(maze[x + directions[i][0]][y + directions[i][1]] == 0
               && copyMaze[x + directions[i][0]][y + directions[i][1]] == 0) {// 是路
                QElemType q;
                CreateElem(&q, x + directions[i][0], y + directions[i][1], x, y);
                EnQueue(&s, q);
                copyMaze[x + directions[i][0]][y + directions[i][1]] = 1;
            } else if(maze[x + directions[i][0]][y + directions[i][1]] == 3){// 是出口
                QElemType q;
                CreateElem(&q, x + directions[i][0], y + directions[i][1], x, y);
                EnQueue(&s, q);
                EnQueue(&s2, q);
                return 1;
            }
        }
    }
    return 0;
}

        void PrintMaze(int **maze, int row, int col) {
            printf("\n");
            for(int i = 0; i < row; i ++) {
                for(int j = 0; j < col; j ++) {
                    printf("%5d", maze[i][j]);
                }
        printf("\n");
    }
    printf("\n");
}

void CreateElem(QElemType *q, int x, int y, int pre_x, int pre_y) {
    q->x = x;
    q->y = y;
    q->pre_x = pre_x;
    q->pre_y = pre_y;
}

void PrintTrace(int **maze, int row, int col) {
    SqStack stack;
    InitStack(&stack);
    int p = s2.rear - 1;
    int q = p - 1;
    int num = 1;
    QElemType e;
    Push(&stack, s2.base[p]);
    while(q >= s2.font) {
        if(s2.base[p].pre_x == s2.base[q].x && s2.base[p].pre_y == s2.base[q].y) {
            Push(&stack, s2.base[q]);
            p = q;
        }
        q --;
    }

    while(!StackEmpty(stack)) {
        Pop(&stack, &e);
        printf("<%d,%d>->", e.x, e.y);
        maze[e.x][e.y] = num;
        num ++;
    }
    PrintMaze(maze, row, col);
}
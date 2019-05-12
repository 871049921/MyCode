
#ifndef MAZE_QUEUE_H
#define MAZE_QUEUE_H

#include <stdio.h>
#include <stdlib.h>

#define MAXSIZE 1000
#define OK 1
#define ERROR 0
#define OVERFLOW -1

typedef int Status;

typedef struct {
    QElemType *base;
    int font;
    int rear;
} SqQueue;

Status InitQueue(SqQueue *s);// 初始化队列
int QueueLength(SqQueue s);// 队列长度
Status EnQueue(SqQueue *s, QElemType e);// 入队
Status DeQueue(SqQueue *s, QElemType *e);// 出队
Status QueueTraversal(SqQueue s);// 遍历队列

Status InitQueue(SqQueue *s) {
    s->base = (QElemType *)malloc(sizeof(QElemType) * MAXSIZE);
    if(!s->base) {
        exit(OVERFLOW);
    }
    s->font = s->rear = 0;
    return OK;
}

int QueueLength(SqQueue s) {
    return (s.rear - s.font + MAXSIZE) % MAXSIZE;
}

Status EnQueue(SqQueue *s, QElemType e) {
    if (s->rear > MAXSIZE) {// 队列已满
        printf("EnQueue Fall!\n");
        return ERROR;
    }
    s->base[s->rear] = e;
    //printf("EnQueue:now:<%d,%d>,pre:<%d,%d>\n", s->base[s->rear].x, s->base[s->rear].y, s->base[s->rear].pre_x, s->base[s->rear].pre_y);
    s->rear = s->rear + 1;
    return OK;
}

Status DeQueue(SqQueue *s, QElemType *e) {
    if(s->font == s->rear) {
        printf("DnQueue Fall!\n");
        return ERROR;
    }
    *e = s->base[s->font];
    //printf("DeQueue::now:<%d,%d>,pre:<%d,%d>\n", s->base[s->font].x, s->base[s->font].y, s->base[s->font].pre_x, s->base[s->font].pre_y);
    s->font = s->font + 1;
}

Status QueueTraversal(SqQueue s) {
    int p = s.font;
    while(s.rear != p) {
        printf("now:<%d,%d>,pre<%d,%d>\n", s.base[p].x, s.base[p].y, s.base[p].pre_x, s.base[p].pre_y);
        p ++;
    }
}

#endif //MAZE_QUEUE_H

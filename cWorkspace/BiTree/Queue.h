
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

Status InitQueue(SqQueue *s);// ��ʼ������
int QueueLength(SqQueue s);// ���г���
Status EnQueue(SqQueue *s, QElemType e);// ���
Status DeQueue(SqQueue *s, QElemType *e);// ����
Status QueueEmpty(SqQueue s);// �Ƿ�Ϊ�ն���

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
    if (s->rear > MAXSIZE) {// ��������
        printf("EnQueue Fall!\n");
        return ERROR;
    }
    s->base[s->rear] = e;
    s->rear = s->rear + 1;
    return OK;
}

Status DeQueue(SqQueue *s, QElemType *e) {
    if(s->font == s->rear) {
        printf("DnQueue Fall!\n");
        return ERROR;
    }
    *e = s->base[s->font];
    s->font = s->font + 1;
}

Status QueueEmpty(SqQueue s) {
    if(s.font == s.rear) {
        return 1;
    } else {
        return 0;
    }
}


#endif //MAZE_QUEUE_H

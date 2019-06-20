
#include <stdio.h>
#include <stdlib.h>

#define MaxVex 100 //��ඥ�����
#define INFINITY INT_MAX //��ʾ����ֵ���� ��
#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR 0

typedef char VertexType;    // ���趥����������Ϊ�ַ�����
typedef VertexType QElemType;

#include "Queue.h"

typedef struct ArcNode {
    int adjvex;
    struct ArcNode *nextarc;
    int weight;
} ArcNode;

typedef struct VNode {
    VertexType date;
    ArcNode *firstarc;
} VNode, AdjList[MaxVex];

typedef struct {
    AdjList vertices;
    int vexnum, arcnum;
    int GraphKind;
} Graph;

int visited[MaxVex] = {FALSE};    //���ʱ�־����

/**********************�����Ӻ����Ķ���*********************/

int LocateVertex(Graph G, VertexType v);//�󶥵�λ�ú���
void InitGraph(Graph *G);
int CreateGraph(Graph *G);              //����һ������ͼ
void DepthFirstSearch(Graph G, int i);  //ͼ��������ȱ���
void TraverseGraph(Graph G, int type);
void BreadthFirstSearch(Graph G, int v);

/**************************������*************************/
int main() {
    Graph G1, G2;
    //InitGraph(&G1);
    //CreateGraph(&G1);
    InitGraph(&G2);
    CreateGraph(&G2);
    printf("DFSTraverse: ");
    TraverseGraph(G2, 1);
    //printf("BFSTraverse: ");
    //TraverseGraph(G1, 2);
}

/**********************�����Ӻ������ܵ�ʵ��*********************/

int LocateVertex(Graph G, VertexType v) { //���Ҳ����ض����λ��
    int j = 0, k;
    for (k = 0; k < G.vexnum; k++) {
        if (G.vertices[k].date == v) {
            j = k;
            break;
        }
    }
    return j;
}



void InitGraph(Graph *G) { //��ʼ���ڽӾ���
    printf("please enter the vextex number and the arc number:");
    scanf("%d %d", &(G->vexnum), &(G->arcnum));//����ͼ�Ķ�������ͱ���
    printf("please enter the kind of the graph:");
    scanf("%d", &(G->GraphKind));// 0Ϊ����ͼ,1Ϊ����ͼ
    printf("Initialization success.\n");
}
int CreateGraph(Graph *G) { //����һ������ͼ
    int i, j, k, weight;
    ArcNode* p;
    VertexType v1, v2;
    for (i = 0; i < G->vexnum; i++) {// ���㸳ֵ
        G->vertices[i].date = (i + 1) + '0';
        G->vertices[i].firstarc = NULL;
    }
    for (k = 0; k < G->arcnum; k++) {
        printf("Enter the two vertices and weights of the edge of the %d bar of the graph respectively:", k + 1);
        getchar();
        scanf("%c %c %d", &v1, &v2, &weight);//����һ���ߵ��������㡢Ȩֵ
        fflush(stdin);

        i = LocateVertex(*G, v1);
        j = LocateVertex(*G, v2);
        p = G->vertices[i].firstarc;
        while(p != NULL) {// �ҵ�β�ڵ�
            p = p->nextarc;
        }
        p = (ArcNode*)malloc(sizeof(ArcNode));
        p->adjvex = i;
        p->weight = weight;
        p->nextarc = NULL;
    }
    if(G->GraphKind == 0) {// ����ͼ
        p = G->vertices[j].firstarc;
        while(p != NULL) {// �ҵ�β�ڵ�
            p = p->nextarc;
        }
        p = (ArcNode*)malloc(sizeof(ArcNode));
        p->adjvex = i;
        p->weight = weight;
        p->nextarc = NULL;
    }
    return OK;
}

void TraverseGraph(Graph G, int type) {
    int i;
    for (i = 0; i < G.vexnum; i++) {// ��ʼ�����ʱ�־����
        visited[i] = FALSE;
    }
    for (i = 0; i < G.vexnum; i++) {// ѭ������������ȱ�����ͨ��ͼ�Ĳ�������ͼG����ͨͼ����˵���ִֻ��һ��
        if (!visited[i])
            if(type == 1) {
                DepthFirstSearch(G, i);
            } else if(type == 2) {
                BreadthFirstSearch(G, i);
            }
    }
    printf("\n");
}

void DepthFirstSearch(Graph G, int i) { //ͼ��������ȱ���
    int j;
    visited[i] = TRUE;
    printf("%c ", G.vertices[i].date);
    ArcNode *p = G.vertices[i].firstarc;
    while(p != NULL) {
        if(!visited[p->adjvex]) {
            DepthFirstSearch(G, p->adjvex);
        }
        p = p->nextarc;
    }
}

void BreadthFirstSearch(Graph G, int v) {
    SqQueue Q;
    QElemType u;
    ArcNode *p;
    InitQueue(&Q);
    for(v = 0; v < G.vexnum; v ++) {
        if(!visited[v]) {
            visited[v] = TRUE;
            printf("%c ", G.vertices[v].date);
            EnQueue(&Q, v);
            while(!QueueEmpty(Q)) {
                DeQueue(&Q, &u);
                p = G.vertices[v].firstarc;
                while(p != NULL) {
                    if(!visited[p->adjvex]) {
                        visited[p->adjvex] = TRUE;
                        printf("%c ", G.vertices[p->adjvex].date);
                        EnQueue(&Q, p->adjvex);
                    }
                    p = p->nextarc;
                }
            }
        }
    }
}
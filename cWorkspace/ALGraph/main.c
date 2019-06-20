#include <stdio.h>
#include <stdlib.h>
#define TRUE 1
#define FALSE 0

typedef int QElemType;
typedef char VertexType;

#include "Queue.h"

typedef enum {
    DG, UDG
} GraphKind;

typedef struct ArcNode {
    int adjvex;
    int weight;
    struct ArcNode *next;
} ArcNode;

typedef struct VNode {
    VertexType data;
    ArcNode *firstArc;
} VNode, *AdjList;

typedef struct {
    AdjList vertices;
    GraphKind kind;
    int vexnum, arcnum;
} ALGraph;

int visited[100];

int LocateVex_ALG(ALGraph G, VertexType v);
void CreateUDG_ALG(ALGraph *G);
void CreateDG_ALG(ALGraph *G);
int FirstAdjVex_ALG(ALGraph G, int v);
int NextAdjVex_ALG(ALGraph G, int v, int w);
void DFS_ALG(ALGraph G, int num);
void DFSTraverse_ALG(ALGraph G);
void BFSTraverse_ALG(ALGraph G);

int main() {
    ALGraph G1, G2;
    printf("G1:\n");
    CreateUDG_ALG(&G1);
    printf("G2:\n");
    CreateDG_ALG(&G2);
    printf("\nDFSTraverse:\n");
    DFSTraverse_ALG(G1);
    printf("\nBFSTraverse:\n");
    BFSTraverse_ALG(G2);
}

void CreateUDG_ALG(ALGraph *G) {
    int i, k, j, weight;
    ArcNode *p, *q;
    VertexType v1, v2;
    printf("vertex number:\n");
    scanf("%d", &G->vexnum);
    printf("edge number:\n");
    scanf("%d", &G->arcnum);
    G->kind = UDG;
    G->vertices = (AdjList) malloc(sizeof(VNode) * G->vexnum);
    printf("enter vertexs\n");
    for (i = 0; i < G->vexnum; ++i) {
        getchar();
        scanf("%c", &(G->vertices[i]).data);
        G->vertices[i].firstArc = NULL;
    }
    for (k = 0; k < G->arcnum; k++) {
        printf("Enter the two vertices and weight of the %d bar of the graph:", k + 1);
        getchar();
        scanf("%c %c %d", &v1, &v2, &weight);//输入一条边的两个顶点和权值
        i = LocateVex_ALG(*G, v1);
        j = LocateVex_ALG(*G, v2);
        q = (ArcNode *) malloc(sizeof(ArcNode));
        q->adjvex = j;
        q->weight = weight;
        q->next = G->vertices[i].firstArc;
        G->vertices[i].firstArc = q;
        p = (ArcNode *) malloc(sizeof(ArcNode));
        p->adjvex = i;
        p->weight = weight;
        p->next = G->vertices[j].firstArc;
        G->vertices[j].firstArc = p;
    }
}

void CreateDG_ALG(ALGraph *G) {
    int i, k, j, weight;
    ArcNode *p;
    VertexType v1, v2;
    printf("vertex number:\n");
    scanf("%d", &G->vexnum);
    printf("edge number:\n");
    scanf("%d", &G->arcnum);
    G->kind = DG;
    G->vertices = (AdjList) malloc(sizeof(VNode) * G->vexnum);
    printf("enter vertexs\n");
    for (i = 0; i < G->vexnum; ++i) {
        getchar();
        scanf("%c", &(G->vertices[i]).data);
        G->vertices[i].firstArc = NULL;
    }
    for (k = 0; k < G->arcnum; k++) {
        printf("Enter the two vertices and weight of the %d bar of the graph:", k + 1);
        getchar();
        scanf("%c %c %d", &v1, &v2, &weight);//输入一条边的两个顶点和权值
        i = LocateVex_ALG(*G, v1);
        j = LocateVex_ALG(*G, v2);
        p = (ArcNode *) malloc(sizeof(ArcNode));
        p->adjvex = j;
        p->weight = weight;
        p->next = G->vertices[i].firstArc;
        G->vertices[i].firstArc = p;
    }
}

int LocateVex_ALG(ALGraph G, VertexType v) {
    int i;
    for (i = 0; i < G.vexnum; i++) {
        if ((G.vertices + i)->data == v)
            break;
    }
    return i;
}

void BFSTraverse_ALG(ALGraph G) {
    int i, v = 0, w;
    SqQueue Q;
    for (i = 0; i < G.vexnum; i++) {
        visited[i] = FALSE;
    }
    InitQueue(&Q);
    EnQueue(&Q, v);
    visited[v] = TRUE;
    while (!QueueEmpty(Q)) {
        DeQueue(&Q, &v);
        printf("%c", G.vertices[v].data);
        for (w = FirstAdjVex_ALG(G, v); w >= 0; w = NextAdjVex_ALG(G, v, w)) {
            if (!visited[w]) {
                EnQueue(&Q, w);
                visited[w] = TRUE;
            }
        }
    }
    printf("\n");
}

void DFSTraverse_ALG(ALGraph G) {
    int i;
    for (i = 0; i < G.vexnum; i++) {
        visited[i] = FALSE;
    }
    DFS_ALG(G, 0);
    printf("\n");
}

void DFS_ALG(ALGraph G, int num) {
    int w;
    printf("%c", G.vertices[num].data);
    visited[num] = TRUE;
    for (w = FirstAdjVex_ALG(G, num); w >= 0; w = NextAdjVex_ALG(G, num, w)) {
        if (!visited[w]) {
            DFS_ALG(G, w);
        }
    }
}

int FirstAdjVex_ALG(ALGraph G, int v) {
    if (!G.vertices[v].firstArc)
        return -1;
    return G.vertices[v].firstArc->adjvex;
}

int NextAdjVex_ALG(ALGraph G, int v, int w) {
    ArcNode *p;
    if (!G.vertices[v].firstArc)
        return -1;
    p = G.vertices[v].firstArc;
    while (p->adjvex != w) {
        p = p->next;
    }
    if (!p->next)
        return -1;
    return p->next->adjvex;
}

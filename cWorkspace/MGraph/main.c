#define MAX_VERTEX_NUM 20
#define TRUE 1
#define FALSE 0
typedef int QElemType;
#include <stdio.h>
#include <stdlib.h>
#include "Queue.h"

typedef int VRType;
typedef char VertexType;

typedef enum {
    DG, UDG
} GraphKind;

typedef struct ArcCell {
    VRType adj;
} ArcCell, AdjMatrix[MAX_VERTEX_NUM][MAX_VERTEX_NUM];

typedef struct {
    VertexType vexs[MAX_VERTEX_NUM];
    AdjMatrix arcs;
    int vexnum, arcnum;
    GraphKind kind;
} MGraph;

int visited[100];

void CreateDG(MGraph *G);
void CreateUDG(MGraph *G);
int FirstAdjVex(MGraph G, int v);
int NextAdjVex(MGraph G, int v, int w);
int LocateVex(MGraph G, VertexType v);
void DFS(MGraph G, int num);
void DFSTraverse(MGraph G);
void BFSTraverse(MGraph G);

int main() {
    MGraph G1, G2;
    printf("G1:\n");
    CreateUDG(&G1);
    printf("G2:\n");
    CreateDG(&G2);
    printf("\nDFSTraverse:\n");
    DFSTraverse(G1);
    printf("\nBFSTraverse:\n");
    BFSTraverse(G2);
}

void CreateUDG(MGraph *G) {
    int i, j, k;
    char v1, v2;
    printf("vertex number:\n");
    scanf("%d", &(G->vexnum));
    printf("edge number:\n");
    scanf("%d", &(G->arcnum));
    G->kind = UDG;
    printf("enter vertexs\n");
    for (i = 0; i < G->vexnum; ++i) {
        getchar();
        scanf("%c", &G->vexs[i]);
    }
    for (i = 0; i < G->vexnum; ++i) {// 初始化邻接矩阵
        for (j = 0; j < G->vexnum; ++j) {
            (G->arcs)[i][j].adj = 0;
        }
    }
    for (k = 0; k < G->arcnum; k++) {
        printf("Enter the two vertices of the %d bar of the graph:", k + 1);
        getchar();
        scanf("%c %c", &v1, &v2);//输入一条边的两个顶点
        i = LocateVex(*G, v1);
        j = LocateVex(*G, v2);
        G->arcs[i][j].adj = 1;
        G->arcs[j][i].adj = 1;
    }
    printf("\n");
}

void CreateDG(MGraph *G) {
    int i, j, k;
    char v1, v2;
    printf("vertex number:\n");
    scanf("%d", &G->vexnum);
    printf("edge number:\n");
    scanf("%d", &G->arcnum);
    G->kind = DG;
    printf("enter vertexs\n");
    for (i = 0; i < G->vexnum; ++i) {
        getchar();
        scanf("%c", &G->vexs[i]);
    }
    for (i = 0; i < G->vexnum; i ++) {
        for (j = 0; j < G->vexnum; j ++) {
            (G->arcs)[i][j].adj = 0;
        }
    }
    for (k = 0; k < G->arcnum; k++) {
        printf("Enter the two vertices of the %d bar of the graph:", k + 1);
        getchar();
        scanf("%c %c", &v1, &v2);//输入一条边的两个顶点
        i = LocateVex(*G, v1);
        j = LocateVex(*G, v2);
        G->arcs[i][j].adj = 1;
    }
    printf("\n");
}

int LocateVex(MGraph G, VertexType v) {
    for (int i = 0; i < G.vexnum; i++) {
        if (G.vexs[i] == v) {
            return i;
        }
    }
    return -1;// 未找到
}

void DFSTraverse(MGraph G) {
    for (int i = 0; i < G.vexnum; i++) {
        visited[i] = FALSE;
    }
    DFS(G, 0);
}

void DFS(MGraph G, int num) {
    int w;
    printf("%c", G.vexs[num]);
    visited[num] = TRUE;
    for (w = FirstAdjVex(G, num); w >= 0; w = NextAdjVex(G, num, w)) {
        if (!visited[w]) {
            DFS(G, w);
        }
    }
}

int FirstAdjVex(MGraph G, int v) {
    for (int i = 0; i < G.vexnum; i++) {
        if (G.arcs[v][i].adj == 1) {
            return i;
        }
    }
    return -1;
}

int NextAdjVex(MGraph G, int v, int w) {
    for (int i = w + 1; i < G.vexnum; i++) {
        if (G.arcs[v][i].adj == 1) {
            return i;
        }
    }
    return -1;
}

void BFSTraverse(MGraph G) {
    int i, v, w;
    SqQueue Q;
    for (i = 0; i < G.vexnum; i++) {
        visited[i] = FALSE;
    }
    InitQueue(&Q);
    EnQueue(&Q, 0);
    visited[0] = TRUE;
    while (!QueueEmpty(Q)) {
        DeQueue(&Q, &v);
        printf("%c", G.vexs[v]);
        for (w = FirstAdjVex(G, v); w >= 0; w = NextAdjVex(G, v, w)) {
            if (!visited[w]) {
                EnQueue(&Q, w);
                visited[w] = TRUE;
            }
        }
    }
    printf("\n");
}

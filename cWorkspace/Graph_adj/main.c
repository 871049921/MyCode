
#include <stdio.h>
#include <stdlib.h>

#define MaxVex 100 //最多顶点个数
#define INFINITY INT_MAX //表示极大值，即 ∞
#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR 0

typedef char VertexType;    // 假设顶点数据类型为字符类型
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

int visited[MaxVex] = {FALSE};    //访问标志数组

/**********************各个子函数的定义*********************/

int LocateVertex(Graph G, VertexType v);//求顶点位置函数
void InitGraph(Graph *G);
int CreateGraph(Graph *G);              //创建一个无向图
void DepthFirstSearch(Graph G, int i);  //图的深度优先遍历
void TraverseGraph(Graph G, int type);
void BreadthFirstSearch(Graph G, int v);

/**************************主函数*************************/
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

/**********************各个子函数功能的实现*********************/

int LocateVertex(Graph G, VertexType v) { //查找并返回顶点的位置
    int j = 0, k;
    for (k = 0; k < G.vexnum; k++) {
        if (G.vertices[k].date == v) {
            j = k;
            break;
        }
    }
    return j;
}



void InitGraph(Graph *G) { //初始化邻接矩阵
    printf("please enter the vextex number and the arc number:");
    scanf("%d %d", &(G->vexnum), &(G->arcnum));//输入图的顶点个数和边数
    printf("please enter the kind of the graph:");
    scanf("%d", &(G->GraphKind));// 0为无向图,1为有向图
    printf("Initialization success.\n");
}
int CreateGraph(Graph *G) { //创建一个无向图
    int i, j, k, weight;
    ArcNode* p;
    VertexType v1, v2;
    for (i = 0; i < G->vexnum; i++) {// 顶点赋值
        G->vertices[i].date = (i + 1) + '0';
        G->vertices[i].firstarc = NULL;
    }
    for (k = 0; k < G->arcnum; k++) {
        printf("Enter the two vertices and weights of the edge of the %d bar of the graph respectively:", k + 1);
        getchar();
        scanf("%c %c %d", &v1, &v2, &weight);//输入一条边的两个顶点、权值
        fflush(stdin);

        i = LocateVertex(*G, v1);
        j = LocateVertex(*G, v2);
        p = G->vertices[i].firstarc;
        while(p != NULL) {// 找到尾节点
            p = p->nextarc;
        }
        p = (ArcNode*)malloc(sizeof(ArcNode));
        p->adjvex = i;
        p->weight = weight;
        p->nextarc = NULL;
    }
    if(G->GraphKind == 0) {// 无向图
        p = G->vertices[j].firstarc;
        while(p != NULL) {// 找到尾节点
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
    for (i = 0; i < G.vexnum; i++) {// 初始化访问标志数组
        visited[i] = FALSE;
    }
    for (i = 0; i < G.vexnum; i++) {// 循环调用深度优先遍历连通子图的操作，若图G是连通图，则此调用只执行一次
        if (!visited[i])
            if(type == 1) {
                DepthFirstSearch(G, i);
            } else if(type == 2) {
                BreadthFirstSearch(G, i);
            }
    }
    printf("\n");
}

void DepthFirstSearch(Graph G, int i) { //图的深度优先遍历
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
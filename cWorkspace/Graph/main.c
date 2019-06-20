
#include <stdio.h>
#include <stdlib.h>

#define MaxVex 100 //最多顶点个数
#define INFINITY INT_MAX //表示极大值，即∞
#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR 0

typedef char VertexType;    // 假设顶点数据类型为字符类型
typedef VertexType QElemType;

#include "Queue.h"

typedef struct {
    int GraphKind;
    VertexType vertex[MaxVex]; // 顶点数组
    int arcs[MaxVex][MaxVex]; // 邻接矩阵(权值)
    int vexnum, arcnum; // 图中的顶点数和边数
} Graph;

int visited[MaxVex] = {FALSE};    //访问标志数组

/**********************各个子函数的定义*********************/
void InitGraph(Graph *G);                    //初始化邻接矩阵
int LocateVertex(Graph *G, VertexType v);//求顶点位置函数
int CreateGraph(Graph *G);                //创建一个无向图
void DepthFirstSearch(Graph G, int i);  //图的深度优先遍历
void TraverseGraph(Graph G, int type);
void BreadthFirstSearch(Graph G, int v);

/**************************主函数*************************/
int main() {
    Graph G1, G2;
    InitGraph(&G1);
    CreateGraph(&G1);
    InitGraph(&G2);
    CreateGraph(&G2);
    printf("DFSTraverse: ");
    TraverseGraph(G1, 1);
    printf("BFSTraverse: ");
    TraverseGraph(G2, 2);
}

/**********************各个子函数功能的实现*********************/
void InitGraph(Graph *G) { //初始化邻接矩阵
    int i, j;
    printf("please enter the vextex number and the arc number:");
    scanf("%d %d", &(G->vexnum), &(G->arcnum));//输入图的顶点个数和边数
    printf("please enter the kind of the graph:");
    scanf("%d", &(G->GraphKind));// 0为无向图,1为有向图
    for (i = 0; i < G->vexnum; i++) {
        for (j = 0; j < G->vexnum; j++) {
            G->arcs[i][j] = INFINITY;
        }
    }
    printf("Initialization success.\n");
}

int LocateVertex(Graph *G, VertexType v) { //查找并返回顶点的位置
    int j = 0, k;
    for (k = 0; k < G->vexnum; k++) {
        if (G->vertex[k] == v) {
            j = k;
            break;
        }
    }
    return j;
}

int CreateGraph(Graph *G) { //创建一个无向图
    int i, j, k;
    VertexType v1, v2;
    for (i = 0; i < G->vexnum; i++) {

        G->vertex[i] = (i + 1) + '0';
    }
    for (k = 0; k < G->arcnum; k++) {
        printf("Enter the two vertices of the %d bar of the graph:", k + 1);
        getchar();
        scanf("%c %c", &v1, &v2);//输入一条边的两个顶点
        i = LocateVertex(G, v1);
        j = LocateVertex(G, v2);
        if(G->GraphKind == 0) {// 无向图
            G->arcs[i][j] = 1;
            G->arcs[j][i] = 1;
        } else if(G->GraphKind == 1) {// 有向图
            G->arcs[i][j] = 1;
            G->arcs[j][i] = 0;
        }
    }
    printf("Create Graph success.\n");
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
    printf("%c ", G.vertex[i]);
    for (j = 0; j < G.vexnum; j++) {
        if (G.arcs[i][j] != INFINITY && !visited[j])
            DepthFirstSearch(G, j);
    }
}

void BreadthFirstSearch(Graph G, int v) {
    SqQueue Q;
    QElemType u;
    InitQueue(&Q);
    for(v = 0; v < G.vexnum; v ++) {
        visited[v] = FALSE;
    }
    for(v = 0; v < G.vexnum; v ++) {
        if(!visited[v]) {
            visited[v] = TRUE;
            printf("%c ", G.vertex[v]);
            EnQueue(&Q, v);
            while(!QueueEmpty(Q)) {
                DeQueue(&Q, &u);
                for(int w = 0; w < G.vexnum; w ++) {
                    if(!visited[w]) {
                        visited[w] = TRUE;
                        printf("%c ", G.vertex[w]);
                        EnQueue(&Q, w);
                    }
                }
            }
        }
    }
}
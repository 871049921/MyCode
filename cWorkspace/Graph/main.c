
#include <stdio.h>
#include <stdlib.h>

#define MaxVex 100 //��ඥ�����
#define INFINITY INT_MAX //��ʾ����ֵ������
#define TRUE 1
#define FALSE 0
#define OK 1
#define ERROR 0

typedef char VertexType;    // ���趥����������Ϊ�ַ�����
typedef VertexType QElemType;

#include "Queue.h"

typedef struct {
    int GraphKind;
    VertexType vertex[MaxVex]; // ��������
    int arcs[MaxVex][MaxVex]; // �ڽӾ���(Ȩֵ)
    int vexnum, arcnum; // ͼ�еĶ������ͱ���
} Graph;

int visited[MaxVex] = {FALSE};    //���ʱ�־����

/**********************�����Ӻ����Ķ���*********************/
void InitGraph(Graph *G);                    //��ʼ���ڽӾ���
int LocateVertex(Graph *G, VertexType v);//�󶥵�λ�ú���
int CreateGraph(Graph *G);                //����һ������ͼ
void DepthFirstSearch(Graph G, int i);  //ͼ��������ȱ���
void TraverseGraph(Graph G, int type);
void BreadthFirstSearch(Graph G, int v);

/**************************������*************************/
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

/**********************�����Ӻ������ܵ�ʵ��*********************/
void InitGraph(Graph *G) { //��ʼ���ڽӾ���
    int i, j;
    printf("please enter the vextex number and the arc number:");
    scanf("%d %d", &(G->vexnum), &(G->arcnum));//����ͼ�Ķ�������ͱ���
    printf("please enter the kind of the graph:");
    scanf("%d", &(G->GraphKind));// 0Ϊ����ͼ,1Ϊ����ͼ
    for (i = 0; i < G->vexnum; i++) {
        for (j = 0; j < G->vexnum; j++) {
            G->arcs[i][j] = INFINITY;
        }
    }
    printf("Initialization success.\n");
}

int LocateVertex(Graph *G, VertexType v) { //���Ҳ����ض����λ��
    int j = 0, k;
    for (k = 0; k < G->vexnum; k++) {
        if (G->vertex[k] == v) {
            j = k;
            break;
        }
    }
    return j;
}

int CreateGraph(Graph *G) { //����һ������ͼ
    int i, j, k;
    VertexType v1, v2;
    for (i = 0; i < G->vexnum; i++) {

        G->vertex[i] = (i + 1) + '0';
    }
    for (k = 0; k < G->arcnum; k++) {
        printf("Enter the two vertices of the %d bar of the graph:", k + 1);
        getchar();
        scanf("%c %c", &v1, &v2);//����һ���ߵ���������
        i = LocateVertex(G, v1);
        j = LocateVertex(G, v2);
        if(G->GraphKind == 0) {// ����ͼ
            G->arcs[i][j] = 1;
            G->arcs[j][i] = 1;
        } else if(G->GraphKind == 1) {// ����ͼ
            G->arcs[i][j] = 1;
            G->arcs[j][i] = 0;
        }
    }
    printf("Create Graph success.\n");
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
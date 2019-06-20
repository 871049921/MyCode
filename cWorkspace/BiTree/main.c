
typedef char TreeElemType;

typedef struct BiTreeNode {
    TreeElemType date;
    struct BiTreeNode *lchild;
    struct BiTreeNode *rchild;
} BiTreeNode, *BiTree;

typedef BiTree SElemType;
typedef BiTree QElemType;

#include "Stack.h"
#include "Queue.h"

void CreateBiTree(BiTree *);// ���������
void PreOrder(BiTree);// �������������
void InOrder(BiTree);// �������������
void PostOrder(BiTree);// �������������
int TreeDepth(BiTree);// ����α���������
void Pre_CreateBiTree(BiTree *);// ���򴴽�������
int LeafNumber(BiTree);// Ҷ�ڵ�����
int LevelOrder(BiTree);// ����ηǵݹ����������
void NRPreOrder(BiTree);// ����ǵݹ����������
int judgeBiTree(BiTree, BiTree);// �ж����������Ƿ����

int main() {
    int leaf;
    BiTree bt = NULL;
    BiTree pre_bt1, pre_bt2;
    CreateBiTree(&bt);
    InOrder(bt);
    leaf = LeafNumber(bt);
    printf("������Ҷ�ڵ���Ϊ��%d\n", leaf);
    fflush(stdin);
    printf("�������һ�Ŷ�����\n");
    Pre_CreateBiTree(&pre_bt1);
    fflush(stdin);
    printf("������ڶ��Ŷ�����\n");
    Pre_CreateBiTree(&pre_bt2);
    if (judgeBiTree(pre_bt1, pre_bt2) == 1) {
        printf("���Ŷ�������ȣ�\n");
    } else {
        printf("���Ŷ���������ȣ�\n");
    }
    system("pause");
}

void CreateBiTree(BiTree *bt) {
    int flag = 0;// 0Ϊ��������1Ϊ������
    char str[100];
    SqStack s;
    BiTree bp, brecv;
    bp = (BiTree) malloc(sizeof(BiTreeNode));
    brecv = (BiTree) malloc(sizeof(BiTreeNode));
    InitStack(&s);
    printf("������������ʽ������\n");
    scanf("%s", str);
    for (int i = 0; str[i] != '#'; i++) {
        switch (str[i]) {
            case '(':
                Push(&s, bp);
                flag = 0;
                break;
            case ')':
                Pop(&s, &brecv);
                break;
            case ',':
                flag = 1;
                break;
            default:// �����ڵ�
                bp = (BiTree) malloc(sizeof(BiTreeNode));
                bp->date = str[i];
                bp->lchild = NULL;
                bp->rchild = NULL;
                if (!*bt) {// ����
                    *bt = bp;
                } else {
                    GetTop(s, &brecv);
                    if (flag == 0) {// ������
                        brecv->lchild = bp;
                    } else if (flag == 1) {
                        brecv->rchild = bp;
                    }
                }
                break;
        }
    }
}

void PreOrder(BiTree bt) {
    if (bt) {
        PreOrder(bt->lchild);
        printf("%c", bt->date);
        PreOrder(bt->rchild);
    }
}

void InOrder(BiTree bt) {
    if (bt) {
        InOrder(bt->lchild);
        printf("%c", bt->date);
        InOrder(bt->rchild);
    }
}

void PostOrder(BiTree bt) {
    if (bt) {
        PostOrder(bt->lchild);
        printf("%c", bt->date);
        PostOrder(bt->rchild);
    }
}

int TreeDepth(BiTree bt) {
    if(bt == NULL) {
        return 0;
    } else {
        int l,r;
        l = TreeDepth(bt->lchild) + 1;
        r = TreeDepth(bt->rchild) + 1;
        if(l > r) {
            return l;
        } else {
            return r;
        }
    }
}

void Pre_CreateBiTree(BiTree *bt) {
    char ch;
    scanf("%c", &ch);
    if (ch == ' ') {
        *bt = NULL;
    } else if (ch != '\r') {
        *bt = (BiTree) malloc(sizeof(BiTreeNode));
        (*bt)->date = ch;
        Pre_CreateBiTree(&((*bt)->lchild));
        Pre_CreateBiTree(&((*bt)->rchild));
    }
}

int judgeBiTree(BiTree bt1, BiTree bt2) {
    if (!bt1 && !bt2) {               /* ��Ϊ��ʱ��� */
        return 1;
    }
    if ((bt1 && !bt2) || (!bt1)) {   /* һ��Ϊ��,һ����Ϊ��ʱ����� */
        return 0;
    }
    if (bt1->date == bt2->date) {     /* �ݹ��ж��Ƿ���� */
        if (judgeBiTree(bt1->lchild, bt2->lchild) && judgeBiTree(bt1->rchild, bt2->rchild))
            return 1;
    }
    return 0;
}

int LeafNumber(BiTree bt) {
    if(!bt) {
        return 0;
    } else {
        if((!bt->lchild) && (!bt->rchild)) {
            return 1;
        } else {
            return LeafNumber(bt->lchild) + LeafNumber(bt->rchild);
        }
    }
}

int LevelOrder(BiTree bt) {
    SqQueue q;
    BiTree bp;
    InitQueue(&q);
    bp = bt;
    printf("%c", bp->date);
    EnQueue(&q, bp);
    while(!QueueEmpty(q)) {
        DeQueue(&q, &bp);
    }
    if(!bp->lchild) {
        printf("%c", bp->date);
        EnQueue(&q, bp);
    }
    if(!bp->rchild) {
        printf("%c", bp->date);
        EnQueue(&q, bp);
    }
}

void NRPreOrder(BiTree bt){
    SqStack s;
    BiTree p = bt;
    InitStack(&s);
    while(!StackEmpty(s) || p) {
        while(p) {
            Push(&s, p);
            printf("%c", p->date);
            p = p->lchild;
        }
        Pop(&s, &p);
        p = p->rchild;
    }
}
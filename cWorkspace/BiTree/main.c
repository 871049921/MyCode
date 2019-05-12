
typedef char TreeElemType;

typedef struct BiTreeNode {
    TreeElemType date;
    struct BiTreeNode *lchild;
    struct BiTreeNode *rchild;
} BiTreeNode, *BiTree;

typedef BiTree SElemType;

#include "Stack.h"

void CreateBiTree(BiTree *);// ���������
void mid(BiTree);// �������������
void Pre_CreateBiTree(BiTree *);// ���򴴽�������
int judgeBiTree(BiTree, BiTree);// �ж����������Ƿ����

int main() {
    BiTree bt = NULL;
    BiTree pre_bt1, pre_bt2;
    CreateBiTree(&bt);
    mid(bt);
    printf("\n");
    Pre_CreateBiTree(&pre_bt1);
    fflush(stdin);
    Pre_CreateBiTree(&pre_bt2);
    if (judgeBiTree(pre_bt1, pre_bt2) == 1) {
        printf("���Ŷ�������ȣ�\n");
    } else {
        printf("���Ŷ���������ȣ�\n");
    }
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
                if (!(*bt)) {// ����
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

void mid(BiTree bt) {
    if (bt) {
        mid(bt->lchild);
        printf("%c", bt->date);
        mid(bt->rchild);
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
    if (bt1 || bt2) {
        if (bt1->date != bt2->date) {
            return 0;
        }
        judgeBiTree(bt1->lchild, bt2->lchild);
        judgeBiTree(bt1->rchild, bt2->rchild);
    }
    if (bt1 == NULL && bt2 == NULL) {
        return 1;
    }
}
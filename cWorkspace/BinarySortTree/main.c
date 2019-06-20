#include <stdio.h>
#include <stdlib.h>

#define TRUE 1
#define FALSE 0

typedef int TreeElemType;
typedef int Status;

typedef struct BiTreeNode {
    TreeElemType data;
    struct BiTreeNode *lchild;
    struct BiTreeNode *rchild;
} BiTreeNode, *BiTree;

void Init(BiTree *T);// 初始化二叉树
Status SearchBST(BiTree T, TreeElemType Key, BiTree f, BiTree *p);// 查找
Status InsertBST(BiTree *T, TreeElemType e);// 插入
Status DeleteBST(BiTree *T, TreeElemType key);// 删除
Status Delete(BiTree *T);// 删除节点

int main() {

}

void Init(BiTree *T) {
    (*T)->lchild = NULL;
    (*T)->rchild = NULL;
}

Status SearchBST(BiTree T, TreeElemType Key, BiTree f, BiTree *p) {
    if(!T) {
        return FALSE;
    } else if(Key == T->data) {// 找到了
        *p = T;
        return TRUE;
    } else if(Key < T->data) {// 关键字小于，左子树查找
        return SearchBST(T->lchild, Key, T, p);
    } else {// 关键字大于，右子树查找
        return SearchBST(T->rchild, Key, T, p);
    }
}

Status InsertBST(BiTree *T, TreeElemType e) {
    BiTree p;
    if(!SearchBST(T, e, NULL, &p)) {
        BiTree s = (BiTree)malloc(sizeof(BiTreeNode));
        s->data = e;
        s->lchild = NULL;
        s->rchild = NULL;
        if(!p) {
            T = s;
        } else if(e < p->data) {// 关键字小于，左子树查找
            p->lchild = s;
        } else {
            p->rchild = s;
        }
        return TRUE;
    } else {
        printf("%d is exists\n", e);
        return FALSE;
    }
}

Status DeleteBST(BiTree *T, TreeElemType key) {
    if(!T) {
        return FALSE;
    } else if(key == (*T)->data) {
        return (Delete(T));
    } else if(key < (*T)->data) {
        return DeleteBST((*T)->lchild, key);
    } else {
        return DeleteBST((*T)->rchild, key);
    }
}

Status Delete(BiTree *p) {
    BiTree q, s;
    if(!(*p)->rchild) {// 右子树为空
        q = *p;
        (*p) = (*p)->lchild;
        free(q);
    } else if(!(*p)->lchild) {// 左子树为空
        q = *p;
        (*p) = (*p)->rchild;
        free(q);
    } else {// 左右子树均不为空
        q = *p;
        s = (*p)->lchild;
        while(s->rchild) {
            q = s;
            s = s->rchild;
        }
        (*p)->data = s->data;
        if(q != *p) {
            q->rchild = s->lchild;
        } else {
            q->lchild = s->lchild;
        }
    }
    return TRUE;
}
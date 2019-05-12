#ifndef _SPARSEMATRIX_H
#define _SPARSEMATRIX_H

#define OK 1
#define ERROR 0

typedef int Status;

typedef struct {
    int** SSMatrix;//稀疏矩阵值的存放
    int i, j;//稀疏矩阵的行列数
}SMatrix;//稀疏矩阵

Status CreateSMatrix(SMatrix *);//创建稀疏矩阵
Status DestroySMatrix(SMatrix *);//销毁稀疏矩阵
Status PrintSMatrix(SMatrix);//打印稀疏矩阵
//Status TransposeSMatrix(SMatrix, SMatrix *);//转置稀疏矩阵
#endif // !_SPARSEMATRIX_H





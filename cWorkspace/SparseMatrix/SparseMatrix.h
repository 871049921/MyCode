

#ifndef SPARSEMATRIX_H
#define SPARSEMATRIX_H

typedef int Status;

typedef struct {
    int **SSMatrix;// 稀疏矩阵值的存放
    int i, j;// 稀疏矩阵的行列数
} SMatrix;// 稀疏矩阵


#endif //SPARSEMATRIX_H

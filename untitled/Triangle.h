//
// Created by mikhail on 31.05.17.
//

#ifndef UNTITLED_TRIANGLE_H
#define UNTITLED_TRIANGLE_H


#include <vector>
#include <map>
#include <list>
#include "Vector3d.h"
#include <algorithm>

//using namespace std;

class Triangle {
private:
    int indexVertex[3];
    Vector3d normalVector;
    std::vector<Triangle> neighboring;
public:
    const std::vector <Triangle> &getNeighboring() const {
        return neighboring;
    };

    Triangle(int indexVertex[3]) {
        for (int i = 0; i < 3; ++i)
            this->indexVertex[i] = indexVertex[i];
    };

    /*~Triangle();*/

    void initNormalVector(std::map<int, Vector3d> vertices) {
        Vector3d firstVector = vertices.at(indexVertex[0]);
        Vector3d secondVector = vertices.at(indexVertex[1]);
        Vector3d thirdVector = vertices.at(indexVertex[2]);

        normalVector = calculateNormalVector(firstVector, secondVector, thirdVector);

        normalVector.normalize();
    };

    Vector3d calculateNormalVector(Vector3d firstVector, Vector3d secondVector, Vector3d thirdVector) {
        double A = (secondVector.getY() - firstVector.getY()) * (thirdVector.getZ() - firstVector.getZ())
                   - (thirdVector.getY() - firstVector.getY()) * (secondVector.getZ() - firstVector.getZ());
        double B = (thirdVector.getX() - firstVector.getX()) * (secondVector.getZ() - firstVector.getZ())
                   - (secondVector.getX() - firstVector.getX()) * (thirdVector.getZ() - firstVector.getZ());
        double C = (secondVector.getX() - firstVector.getX()) * (thirdVector.getY() - firstVector.getY())
                   - (thirdVector.getX() - firstVector.getX()) * (secondVector.getY() - firstVector.getY());

        return Vector3d(A, B, C);
    };

    const int *getIndexVertex() const {
        return indexVertex;
    };

    const Vector3d &getNormalVector() const {
        return normalVector;
    };

    bool isNeighboring(Triangle triangle) {
        int i = 0;
        int l = 0;
        for (unsigned long j = 0; j < 3; ++j) {
            /* if (find(indexVertex.begin(), indexVertex.end(), triangle.getIndexVertex().at(j)) != indexVertex.end()) {*/
            if (triangle.getIndexVertex()[0] == indexVertex[j] || triangle.getIndexVertex()[1] == indexVertex[j] || triangle.getIndexVertex()[2] == indexVertex[j]) {
                i++;
            } else {
                l++;
            }
            if (i == 2) return true;
            if (l == 2) return false;
        }
        return false;
    };

    double getAngleWithAnotherTriangle(Triangle triangle) {
        return acos(normalVector.dotProduct(triangle.getNormalVector()));
    };

    void check(std::map<int, std::vector<Triangle>> &map) {
        neighboring = map.at(0);
        for (int index : indexVertex) {
            neighboring.insert(neighboring.end(), map.at(index).begin(), map.at(index).end());
            /*std::merge(neighboring.begin(), neighboring.end(),
                       map.at(index).begin(), map.at(index).end(),
                       neighboring.end());*/
            //neighboring.std::list<Triangle>::merge(map.at(index));
        }
        if (find(neighboring.begin(), neighboring.end(), *this) != neighboring.end()) {
            neighboring.erase(std::remove(neighboring.begin(), neighboring.end(), *this), neighboring.end());
        }
    };

    bool operator <( const Triangle& other) {
        return this->getIndexVertex()[0] > other.getIndexVertex()[0];
    }

    bool operator ==(const Triangle& other) {
        return this->getIndexVertex()[0] == other.getIndexVertex()[0];
    }
};

/*Triangle::~Triangle() {
    delete indexVertex;
    delete neighboring;
}*/


#endif //UNTITLED_TRIANGLE_H

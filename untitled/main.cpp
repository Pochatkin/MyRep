#include <iostream>
#include <cmath>
#include <vector>
#include <algorithm>
#include <list>
#include <map>
#include "Triangle.h"

int main()
{
    int numberTriangle;
    int numberVertex;
    std::cin >> numberTriangle;
    std::cin >> numberVertex;
    std::map<int, Vector3d> vertices;
    std::vector<Triangle> listTriangle;
    std::map<int, std::vector<Triangle>> vertexToTriangle;
    for (int i = 0; i < numberTriangle; i++) {
        /*int numberVerticesTriangle[3];
        std::cin >> numberVerticesTriangle[0] >>
            numberVerticesTriangle[1] >>
            numberVerticesTriangle[2];*/
        int numberVerticesTriangle[3];
        for(int j = 0; j < 3; j++)
            std::cin >> numberVerticesTriangle[j];
        Triangle tempTriangle = Triangle(numberVerticesTriangle);
        listTriangle.push_back(tempTriangle);
        for (int aNumberVerticesTriangle : numberVerticesTriangle) {
            std::vector<Triangle> trianglesForFixVertices;
            if (vertexToTriangle.count(aNumberVerticesTriangle) != 0) {
                trianglesForFixVertices = (std::vector<Triangle> &&) vertexToTriangle.at(
                        aNumberVerticesTriangle);
                trianglesForFixVertices.push_back(tempTriangle);
            } else {
                trianglesForFixVertices = std::vector<Triangle>();
                trianglesForFixVertices.push_back(tempTriangle);
                vertexToTriangle.insert(make_pair(aNumberVerticesTriangle, trianglesForFixVertices));
            }
        }
        listTriangle.at(listTriangle.size() - 1).check(vertexToTriangle);
    }
    for (int i = 0; i < numberVertex; i++) {
        int x,y,z;
        std::cin >> x >> y >> z;
        vertices.insert(std::make_pair(i, Vector3d(x,y,z)));
    }
    for (Triangle triangle : listTriangle) {
        triangle.initNormalVector(vertices);
    }
    double maxAngle = 0;
    for (Triangle firstTriangle : listTriangle) {
        for (Triangle secondTriangle : firstTriangle.getNeighboring()) {
            if (firstTriangle.isNeighboring(secondTriangle)) {
                double angle = firstTriangle.getAngleWithAnotherTriangle(secondTriangle);
                if (angle > maxAngle)
                    maxAngle = angle;
            }
        }
    }
    std::cout << maxAngle;
    return 0;
}




